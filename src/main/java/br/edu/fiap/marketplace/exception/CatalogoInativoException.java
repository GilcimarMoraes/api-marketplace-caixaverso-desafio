package br.edu.fiap.marketplace.exception;

public class CatalogoInativoException extends RuntimeException{

    public CatalogoInativoException( Long id ) {
        super( "Catalogo com id: " + id + " se encontra inativo." );
    }
}
