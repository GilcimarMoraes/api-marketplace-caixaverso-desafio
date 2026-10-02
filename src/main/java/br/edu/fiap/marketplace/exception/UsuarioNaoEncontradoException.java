package br.edu.fiap.marketplace.exception;

/** Erro 404 usado quando o usuário solicitado não existe. */
public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário não encontrado: " + id);
    }
}
