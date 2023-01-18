package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.repository.AgrupacionRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = ApiRequestMappings.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class AgrupacionController {

    private final AgrupacionRepository agrupacionRepository;

    public AgrupacionController(AgrupacionRepository agrupacionRepository) {
        this.agrupacionRepository = agrupacionRepository;
    }

    // tag::get-aggregate-root[]
    @GetMapping(value = "/agrupaciones")
    public CollectionModel<EntityModel<Agrupacion>> agrupaciones() {

        List<EntityModel<Agrupacion>> agrupaciones = agrupacionRepository.findAll().stream()
                .map(
                        agrupacion -> EntityModel.of( agrupacion,
                                linkTo(methodOn(AgrupacionController.class).agrupacion(agrupacion.getId())).withSelfRel(),
                                linkTo(methodOn(AgrupacionController.class).agrupaciones()).withRel("agrupaciones"))).collect(Collectors.toList());

        return CollectionModel.of(agrupaciones,
                linkTo(methodOn(AgrupacionController.class).agrupaciones()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/agrupaciones")
    Agrupacion newAgrupacion(@RequestBody Agrupacion agrupacion) {
        return agrupacionRepository.save(agrupacion);
    }

    @GetMapping(value = "/agrupaciones/{id}")
    EntityModel<Agrupacion> agrupacion(@PathVariable("id") Long id) {
        Agrupacion agrupacion = agrupacionRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("agrupacion",id));

        return EntityModel.of( agrupacion,
                linkTo(methodOn(AgrupacionController.class).agrupacion(id)).withSelfRel(),
                linkTo(methodOn(AgrupacionController.class).agrupaciones()).withRel("agrupaciones")
        );
    }

    @PutMapping("/agrupaciones/{id}")
    Agrupacion replaceAgrupacion(@RequestBody Agrupacion newAgrupacion, @PathVariable Long id) {
        return agrupacionRepository.findById(id)
                .map( agrupacion -> {
                    agrupacion.setTexto(newAgrupacion.getTexto());
                    return agrupacionRepository.save(agrupacion);
                })
                .orElseGet( () -> {
                    newAgrupacion.setId(id);
                    return agrupacionRepository.save(newAgrupacion);
                });
    }

    @DeleteMapping("/agrupaciones/{id}")
    void deleteAgrupacion(@PathVariable Long id) {
        agrupacionRepository.deleteById(id);
    }

}
