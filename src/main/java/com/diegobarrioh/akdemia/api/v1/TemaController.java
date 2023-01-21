package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.domain.repository.TemaRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApiRequestMappings.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class TemaController {

    private final TemaRepository temaRepository;

    private final TemaModelAssembler assembler;

    public TemaController(TemaRepository temaRepository, TemaModelAssembler assembler) {
        this.temaRepository = temaRepository;
        this.assembler = assembler;
    }

    @GetMapping("/temas")
    public CollectionModel<EntityModel<Tema>> temas() {
        List<EntityModel<Tema>> temas = temaRepository.findAll().stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of( temas,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TemaController.class).temas()).withSelfRel());
    }

    @PostMapping("/temas")
    ResponseEntity<?> newTema(@RequestBody Tema tema) {
        EntityModel<Tema> temaEntityModel = assembler.toModel(temaRepository.save(tema));

        return ResponseEntity.created(
                temaEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(temaEntityModel);
    }

    @GetMapping("/temas/{id}")
    public Tema tema(@PathVariable("id") Long id) {
        return temaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("tema", id));
    }

    @PutMapping("/temas/{id}")
    ResponseEntity<?> replaceTema(@RequestBody Tema newTema, @PathVariable Long id) {
        Tema updatedTema =  temaRepository.findById(id)
                .map(tema -> {
                            tema.setTexto(newTema.getTexto());
                            return temaRepository.save(tema);
                        })
                .orElseGet(() -> {
                            newTema.setId(id);
                            return temaRepository.save(newTema);
                        });

        EntityModel<Tema> temaEntityModel = assembler.toModel(updatedTema);

        return ResponseEntity.created(
                temaEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(temaEntityModel);
    }

    @DeleteMapping("/temas/{id}")
    ResponseEntity<?> deleteTema(@PathVariable Long id) {
        temaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
