package br.edu.fiap.marketplace.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** TODO criar as rotas protegidas de criação, consulta e alteração do carrinho. */
@RestController
@RequestMapping("/api/carrinhos")
@Tag(name = "Carrinhos")
@SecurityRequirement(name = "bearerAuth")
public class CarrinhoController {
}
