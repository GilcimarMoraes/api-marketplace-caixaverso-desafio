package br.edu.fiap.marketplace.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.math.BigDecimal;

/**
 * TODO implementar um teste unitário de quantidade, total ou estado do carrinho.
 * Instanciar somente objetos Java reais.
 */
class CarrinhoTest {
    private Usuario usuario;
    private CatalogoProduto catalogoProduto;

    @BeforeEach
    void prepararRelacionamento(){
        usuario = new Usuario("Anderson Barbosa", "anderson@teste.com", "Senha@123");
        catalogoProduto = new CatalogoProduto("Computador", "Notebook", new BigDecimal("1000.00"),10, true);
    }

    @Test
    void deveCalcularTotal(){
        //ARRANGE
        Carrinho carrinho = new Carrinho(usuario, catalogoProduto, 2);

        //ASSERT
        assertThat(carrinho.calcularTotal()).isEqualTo("2000.00");
    }

}
