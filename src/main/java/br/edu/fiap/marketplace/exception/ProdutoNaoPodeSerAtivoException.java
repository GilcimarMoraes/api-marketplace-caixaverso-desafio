package br.edu.fiap.marketplace.exception;

public class ProdutoNaoPodeSerAtivoException extends RuntimeException{

    public ProdutoNaoPodeSerAtivoException( Long id ) {
        super( "Produto: " +id + " náo pode ser ativado pois não possui estoque suficiente." );
    }
}
