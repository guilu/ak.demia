package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Respuesta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RespuestaModelAssembler implements RepresentationModelAssembler<Respuesta, EntityModel<Respuesta>> {

    @Override
    public EntityModel<Respuesta> toModel(Respuesta respuesta) {

        return EntityModel.of( respuesta,
                linkTo(methodOn(RespuestaController.class).respuesta(respuesta.getId())).withSelfRel(),
                linkTo(methodOn(RespuestaController.class).respuestas()).withRel("respuestas")
        );
    }
}
