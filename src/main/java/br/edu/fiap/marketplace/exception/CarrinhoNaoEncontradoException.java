package br.edu.fiap.marketplace.exception;

/** Erro 404 usado quando o carrinho solicitado não existe. */
public class CarrinhoNaoEncontradoException extends RuntimeException {
    public CarrinhoNaoEncontradoException(Long id) {
        super("Carrinho não encontrado: " + id);
    }
}
