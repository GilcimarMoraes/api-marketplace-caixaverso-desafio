package br.edu.fiap.marketplace.entity;

import br.edu.fiap.marketplace.exception.CatalogoInativoException;
import br.edu.fiap.marketplace.exception.QuantidadeInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * TODO implementar um teste unitário da regra de estoque ou preço.
 * Não iniciar o Spring e não usar repository neste arquivo.
 */
class CatalogoProdutoTest {

    @Nested
    class ProdutoValido {
        @Test
        @DisplayName( "Deve criar um produto válido" )
        void deveCriarUmprodutoValido() {
            CatalogoProduto produto = new CatalogoProduto(
                    "Teclado",
                    "Teclado com cabo",
                new BigDecimal( "39.90" ),
                    10,
                    true
            );

            assertThat( produto.getNome() ).isEqualTo( "Teclado" );
            assertThat( produto.getPreco() ).isEqualByComparingTo( "39.90" );
            assertThat( produto.getEstoque() ).isEqualTo( 10 );
            assertThat( produto.isAtivo() ).isTrue();
            assertThat( produto.getId() ).isNull();
        }
    }

    @Nested
    class Preco {
        @Test
        @DisplayName( "Deve alterar preco com sucesso" )
        void deveAlterarPrecoQuandoAtivo() {
            CatalogoProduto produto = new CatalogoProduto(
                    "nome", "desc", new BigDecimal( "10.00"), 2, true
            );
            produto.alterarPreco( new BigDecimal( "20.00" ) );

            assertThat( produto.getPreco() ).isEqualByComparingTo( "20.00" );
        }

        @Test
        @DisplayName( "Não deve alterar preco de produto inativo" )
        void NaoDeveAlterarPrecoQuandoAtivo() {
            CatalogoProduto produto = new CatalogoProduto(
                    "nome", "desc", new BigDecimal( "10.00"), 2, false
            );
            assertThatThrownBy( () -> produto.alterarPreco( new BigDecimal( "20.00" ) ) )
                    .isInstanceOf(CatalogoInativoException.class);

            assertThat( produto.getPreco() ).isEqualByComparingTo( "10.00" );
        }

        @Test
        @DisplayName( "Deve baixar o estoque com sucesso" )
        void deveBaixarEstoque() {
            CatalogoProduto produto = new CatalogoProduto(
                    "nome", "desc", new BigDecimal( "10.00"), 2, false
            );

            produto.baixarEstoque( 1 );

            assertThat( produto.getEstoque() ).isEqualTo( 1 );
        }
    }

    @Test
    @DisplayName( "Não deve baixar estoque maior que disponivel" )
    void NaoDeveAlterarPrecoQuandoAtivo() {
        CatalogoProduto produto = new CatalogoProduto(
                "nome", "desc", new BigDecimal( "10.00"), 2, false
        );
        assertThatThrownBy( () -> produto.baixarEstoque( 3 ) )
                .isInstanceOf( QuantidadeInvalidaException.class );

        assertThat( produto.getEstoque() ).isEqualTo( 2 );
    }
}
