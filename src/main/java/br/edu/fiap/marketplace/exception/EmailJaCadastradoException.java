package br.edu.fiap.marketplace.exception;

/** Erro 409 que impede duas credenciais com o mesmo e-mail. */
public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException(String email) {
        super("Email já cadastrado: " + email);
    }
}
