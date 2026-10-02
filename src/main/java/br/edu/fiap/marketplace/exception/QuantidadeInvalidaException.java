package br.edu.fiap.marketplace.exception;

public class QuantidadeInvalidaException extends RuntimeException{

    public QuantidadeInvalidaException() {
        super( "A quantidade digitada é inválida");
    }
}
