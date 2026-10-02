package br.edu.fiap.marketplace.service;

import br.edu.fiap.marketplace.dto.CatalogoProdutoRequest;
import br.edu.fiap.marketplace.dto.CatalogoProdutoResponse;
import br.edu.fiap.marketplace.entity.CatalogoProduto;
import br.edu.fiap.marketplace.exception.ProdutoCadastradoException;
import br.edu.fiap.marketplace.repository.CatalogoProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/** TODO implementar cadastro, listagem, busca e alterações do catálogo. */
@Service
public class CatalogoProdutoService {

    private final CatalogoProdutoRepository catalogoProdutoRepository;

    public CatalogoProdutoService(CatalogoProdutoRepository catalogoProdutoRepository) {
        this.catalogoProdutoRepository = catalogoProdutoRepository;
    }

    @Transactional( readOnly = true )
    public CatalogoProdutoResponse cadastrarProduto( CatalogoProdutoRequest request ) {

        Optional<CatalogoProduto> produtoEncontrado = catalogoProdutoRepository.findByNome( request.nome() );

        if( produtoEncontrado.isPresent() ) {
            throw new ProdutoCadastradoException();
        }

        CatalogoProduto novoProduto =new CatalogoProduto( request.nome(), request.descricao(), request.preco(),
                request.estoque(), request.ativo() );

        CatalogoProduto produtoSalvo = catalogoProdutoRepository.save( novoProduto );

        return CatalogoProdutoResponse.de( produtoSalvo );
    }
}
