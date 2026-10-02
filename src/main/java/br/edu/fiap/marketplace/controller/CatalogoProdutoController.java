package br.edu.fiap.marketplace.controller;

import br.edu.fiap.marketplace.controller.mapper.GenericController;
import br.edu.fiap.marketplace.dto.CatalogoProdutoRequest;
import br.edu.fiap.marketplace.dto.CatalogoProdutoResponse;
import br.edu.fiap.marketplace.service.CatalogoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/** TODO criar as rotas públicas de leitura e protegidas de alteração do catálogo. */
@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Catálogo")
public class CatalogoProdutoController implements {

    private final CatalogoProdutoService catalogoProdutoService;

    public CatalogoProdutoController(CatalogoProdutoService catalogoProdutoService) {
        this.catalogoProdutoService = catalogoProdutoService;
    }

    @PostMapping
    @Operation( summary = "Cadastrar produto." )
    @ApiResponses({
            @ApiResponse( responseCode = "201", description = "Produto Cadastrado"),
            @ApiResponse( responseCode = "400", description = "Dados inválidos"),
            @ApiResponse( responseCode = "409", description = "Produto já cadastrado." )

    })
    public ResponseEntity<CatalogoProdutoResponse> cadastrar( CatalogoProdutoRequest request ) {
        CatalogoProdutoResponse response = catalogoProdutoService.cadastrarProduto( request );

        URI localizacao = gerarHeaderLocation( request.nome() );

        return ResponseEntity.created( localizacao ).ok( response );
    }
}
