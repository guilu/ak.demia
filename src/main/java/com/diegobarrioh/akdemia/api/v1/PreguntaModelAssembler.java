package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Pregunta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PreguntaModelAssembler implements RepresentationModelAssembler<Pregunta, EntityModel<Pregunta>> {

    @Override
    public EntityModel<Pregunta> toModel(Pregunta pregunta) {

        return EntityModel.of( pregunta,
                linkTo(methodOn(PreguntaController.class).pregunta(pregunta.getId())).withSelfRel(),
                linkTo(methodOn(PreguntaController.class).preguntas()).withRel("preguntas")
        );
    }
}
