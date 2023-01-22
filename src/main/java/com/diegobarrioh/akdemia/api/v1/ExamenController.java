package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Examen;
import com.diegobarrioh.akdemia.domain.repository.ExamenRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = ApiRequestMappings.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class ExamenController {

    private final ExamenRepository examenRepository;

    private final ExamenModelAssembler assembler;

    public ExamenController(ExamenRepository examenRepository, ExamenModelAssembler assembler) {
        this.examenRepository = examenRepository;
        this.assembler = assembler;
    }


    @GetMapping("/examenes")
    CollectionModel<EntityModel<Examen>> examenes() {
        List<EntityModel<Examen>> examenes = examenRepository.findAll().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(
                examenes, linkTo(methodOn(ExamenController.class).examenes()).withSelfRel()
        );

    }

    @GetMapping("/examenes/{id}")
    EntityModel<Examen> examen(@PathVariable Long id) {
        Examen examen = examenRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("examen",id));

        return assembler.toModel(examen);
    }

}
