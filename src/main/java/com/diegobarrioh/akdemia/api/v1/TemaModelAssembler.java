package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Tema;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TemaModelAssembler implements RepresentationModelAssembler<Tema, EntityModel<Tema>> {

    @Override
    public EntityModel<Tema> toModel(Tema tema) {
        return EntityModel.of( tema,
                linkTo(methodOn(TemaController.class).tema(tema.getId())).withSelfRel(),
                linkTo(methodOn(TemaController.class).temas()).withRel("temas")
        );
    }
}
