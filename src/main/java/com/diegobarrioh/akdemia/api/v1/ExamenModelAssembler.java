package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Examen;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExamenModelAssembler implements RepresentationModelAssembler<Examen, EntityModel<Examen>> {

    @Override
    public EntityModel<Examen> toModel(Examen examen) {

        return EntityModel.of(examen,
                linkTo(methodOn(ExamenController.class).examen(examen.getId())).withSelfRel(),
                linkTo(methodOn(ExamenController.class).examenes()).withRel("examenes")
        );

    }
}
