package br.edu.fiap.marketplace.exception;

import java.math.BigDecimal;

public class NovoPrecoInvalidoException extends RuntimeException {

    public NovoPrecoInvalidoException() {
        super( "Valor inválido. Valor não pode ser nulo, zero ou negativo." );
    }
}
