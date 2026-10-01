package br.edu.fiap.marketplace.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** TODO criar POST /api/auth/login usando LoginRequest e TokenResponse. */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação")
public class AutenticacaoController {
}
