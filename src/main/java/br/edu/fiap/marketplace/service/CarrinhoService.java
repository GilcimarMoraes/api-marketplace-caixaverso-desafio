package br.edu.fiap.marketplace.service;

import br.edu.fiap.marketplace.dto.CarrinhoRequest;
import br.edu.fiap.marketplace.dto.CarrinhoResponse;
import br.edu.fiap.marketplace.entity.Carrinho;
import br.edu.fiap.marketplace.entity.CatalogoProduto;
import br.edu.fiap.marketplace.entity.Usuario;
import br.edu.fiap.marketplace.exception.CarrinhoNaoEncontradoException;
import br.edu.fiap.marketplace.exception.ProdutoNaoEncontradoException;
import br.edu.fiap.marketplace.exception.QuantidadeInvalidaException;
import br.edu.fiap.marketplace.exception.UsuarioNaoEncontradoException;
import br.edu.fiap.marketplace.repository.CarrinhoRepository;
import br.edu.fiap.marketplace.repository.CatalogoProdutoRepository;
import br.edu.fiap.marketplace.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CatalogoProdutoRepository catalogoProdutoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, UsuarioRepository usuarioRepository, CatalogoProdutoRepository catalogoProdutoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.usuarioRepository = usuarioRepository;
        this.catalogoProdutoRepository = catalogoProdutoRepository;
    }

    @Transactional
    public CarrinhoResponse novoCarrinho(CarrinhoRequest request) {
        if (request.quantidade() < 1) {
            throw new QuantidadeInvalidaException();
        }

        Usuario usuario = getUsuarioPorId(request.usuarioId());
        CatalogoProduto produto = getProdutoPorId(request.produtoId());

        Carrinho carrinho = new Carrinho(usuario, produto, request.quantidade());
        return CarrinhoResponse.de(carrinhoRepository.save(carrinho));
    }

    @Transactional(readOnly = true)
    public CarrinhoResponse buscarCarrinhoPorId(Long carrinhoId) {
        return CarrinhoResponse.de(getCarrinhoPorId(carrinhoId));
    }

    @Transactional(readOnly = true)
    public List<CarrinhoResponse> buscarCarrinhoPorUsuarioId(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
        return carrinhoRepository.findByUsuarioId(usuarioId).stream()
                .map(CarrinhoResponse::de)
                .toList();
    }

    @Transactional
    public CarrinhoResponse atualizarQuantidadePorCarrinhoId(Long carrinhoId, int quantidade) {
        Carrinho carrinho = getCarrinhoPorId(carrinhoId);
        carrinho.alterarQuantidade(quantidade);
        return CarrinhoResponse.de(carrinhoRepository.save(carrinho));
    }

    @Transactional
    public void cancelarCarrinho(Long carrinhoId) {
        Carrinho carrinho = getCarrinhoPorId(carrinhoId);
        carrinho.cancelar();
    }

    private Carrinho getCarrinhoPorId(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new CarrinhoNaoEncontradoException(carrinhoId));
    }

    private Usuario getUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    private CatalogoProduto getProdutoPorId(Long produtoId) {
        return catalogoProdutoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }
}
