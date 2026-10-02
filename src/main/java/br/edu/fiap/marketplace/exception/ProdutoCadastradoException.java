package br.edu.fiap.marketplace.exception;

public class ProdutoCadastradoException extends RuntimeException{

    public ProdutoCadastradoException() {
        super( "Produto já cadastrado." );
    }
}
