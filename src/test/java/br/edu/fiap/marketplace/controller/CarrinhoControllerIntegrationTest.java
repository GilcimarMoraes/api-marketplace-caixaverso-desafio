package br.edu.fiap.marketplace.controller;

import br.edu.fiap.marketplace.dto.CarrinhoResponse;
import br.edu.fiap.marketplace.entity.StatusCarrinho;
import br.edu.fiap.marketplace.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * TODO implementar um @WebMvcTest com MockMvc e o CarrinhoService mockado.
 * O cenário mínimo deve comprovar status, JSON e validação HTTP.
 */
@WebMvcTest( CarrinhoController.class )
@Import( GlobalExceptionHandler.class )
class CarrinhoControllerIntegrationTest {
    @Nested
    class criarCarrinho {
        @Test
        @DisplayName( "POST, deve criar um novo carrinho")
        void deveCadastrarUmNovoCarrinho() throws Exception{
            CarrinhoResponse response = new CarrinhoResponse(
                    1L, 2L, "nome", 3L, "produto",
                    new BigDecimal("100"), 2, new BigDecimal(200), StatusCarrinho.FINALIZADO, Instant.now()
            );
        }
    }
}
