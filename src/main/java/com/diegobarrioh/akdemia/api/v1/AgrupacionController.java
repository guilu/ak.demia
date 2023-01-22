package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.repository.AgrupacionRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = ApiRequestMappings.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class AgrupacionController {

    private final AgrupacionRepository agrupacionRepository;
    private final AgrupacionModelAssembler assembler;


    public AgrupacionController(AgrupacionRepository agrupacionRepository, AgrupacionModelAssembler assembler) {
        this.agrupacionRepository = agrupacionRepository;
        this.assembler = assembler;
    }

    // tag::get-aggregate-root[]
    @GetMapping(value = "/agrupaciones")
    public CollectionModel<EntityModel<Agrupacion>> agrupaciones() {

        List<EntityModel<Agrupacion>> agrupaciones = agrupacionRepository.findAll().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(agrupaciones,
                linkTo(methodOn(AgrupacionController.class).agrupaciones()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/agrupaciones")
    ResponseEntity<EntityModel<Agrupacion>> newAgrupacion(@RequestBody Agrupacion agrupacion) {

        EntityModel<Agrupacion> agrupacionEntityModel = assembler.toModel(agrupacionRepository.save(agrupacion));

        return ResponseEntity.created(agrupacionEntityModel
                .getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(agrupacionEntityModel);

    }

    @GetMapping(value = "/agrupaciones/{id}")
    EntityModel<Agrupacion> agrupacion(@PathVariable("id") Long id) {
        Agrupacion agrupacion = agrupacionRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("agrupacion",id));
        return assembler.toModel(agrupacion);
    }

    @PutMapping("/agrupaciones/{id}")
    ResponseEntity<EntityModel<Agrupacion>> replaceAgrupacion(@RequestBody Agrupacion newAgrupacion, @PathVariable Long id) {
        Agrupacion updatedAgrupacion =  agrupacionRepository.findById(id)
                .map( agrupacion -> {
                    agrupacion.setTexto(newAgrupacion.getTexto());
                    return agrupacionRepository.save(agrupacion);
                })
                .orElseGet( () -> {
                    newAgrupacion.setId(id);
                    return agrupacionRepository.save(newAgrupacion);
                });
        EntityModel<Agrupacion> agrupacionEntityModel = assembler.toModel(updatedAgrupacion);

        return ResponseEntity
                .created(agrupacionEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(agrupacionEntityModel);
    }

    @DeleteMapping("/agrupaciones/{id}")
    ResponseEntity<EntityModel<Agrupacion>> deleteAgrupacion(@PathVariable Long id) {
        agrupacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
