package br.edu.fiap.marketplace.controller;

import br.edu.fiap.marketplace.controller.mapper.GenericController;
import br.edu.fiap.marketplace.dto.CatalogoProdutoRequest;
import br.edu.fiap.marketplace.dto.CatalogoProdutoResponse;
import br.edu.fiap.marketplace.entity.CatalogoProduto;
import br.edu.fiap.marketplace.service.CatalogoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/** TODO criar as rotas públicas de leitura e protegidas de alteração do catálogo. */
@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Catálogo")
public class CatalogoProdutoController implements GenericController {

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
    public ResponseEntity<CatalogoProdutoResponse> cadastrar( @Valid @RequestBody CatalogoProdutoRequest request ) {
        CatalogoProdutoResponse response = catalogoProdutoService.cadastrarProduto( request );

        URI localizacao = gerarHeaderLocation( request.nome() );

        return ResponseEntity.created( localizacao ).body( response );
    }

    @GetMapping
    @Operation( summary = "Listar todos os produtos" )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista criada"),

    })
    public ResponseEntity<List<CatalogoProdutoResponse>> listaProdutos() {

        List<CatalogoProdutoResponse> catalogo = catalogoProdutoService.listar();

        return ResponseEntity.ok( catalogo );
    }

    @GetMapping( "/{id}" )
    @Operation( summary = "Listar por Id" )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Dados inválidos")
    })
    public ResponseEntity<CatalogoProdutoResponse> buscarPorId( @Valid @PathVariable Long id ) {

        CatalogoProdutoResponse buscarProduto = catalogoProdutoService.buscarPorId( id );

        return ResponseEntity.ok( buscarProduto );
    }

}
