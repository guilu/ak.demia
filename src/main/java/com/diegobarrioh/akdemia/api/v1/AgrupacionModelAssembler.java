package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AgrupacionModelAssembler implements RepresentationModelAssembler<Agrupacion, EntityModel<Agrupacion>> {

    @Override
    public EntityModel<Agrupacion> toModel(Agrupacion agrupacion) {
        return EntityModel.of( agrupacion,
                linkTo(methodOn(AgrupacionController.class).agrupacion(agrupacion.getId())).withSelfRel(),
                linkTo(methodOn(AgrupacionController.class).agrupaciones()).withRel("agrupaciones")
        );
    }
}
