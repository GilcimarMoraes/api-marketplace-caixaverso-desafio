package br.edu.fiap.marketplace.controller.mapper;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GenericController {

    default URI gerarHeaderLocation( String nome ) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{nome}")
                .buildAndExpand( nome )
                .toUri();
    }
}
