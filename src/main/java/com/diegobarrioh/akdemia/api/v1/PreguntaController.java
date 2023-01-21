package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Pregunta;
import com.diegobarrioh.akdemia.domain.repository.PreguntaRepository;
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
public class PreguntaController {

    private final PreguntaRepository preguntaRepository;

    private final PreguntaModelAssembler assembler;

    public PreguntaController(PreguntaRepository preguntaRepository, PreguntaModelAssembler assembler) {
        this.preguntaRepository = preguntaRepository;
        this.assembler = assembler;
    }

    @GetMapping("/preguntas")
    public CollectionModel<EntityModel<Pregunta>> preguntas() {
        List<EntityModel<Pregunta>> preguntas = preguntaRepository.findAll().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of( preguntas,
                linkTo(methodOn(PreguntaController.class).preguntas()).withSelfRel());
    }

    @PostMapping("/preguntas")
    ResponseEntity<EntityModel<Pregunta>> newPregunta(@RequestBody Pregunta pregunta) {

        EntityModel<Pregunta> preguntaEntityModel = assembler.toModel(preguntaRepository.save(pregunta));

        return ResponseEntity.created(
                preguntaEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(preguntaEntityModel);
   }

    @GetMapping("/preguntas/{id}")
    EntityModel<Pregunta> pregunta(@PathVariable("id") Long id) {
        Pregunta pregunta =  preguntaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pregunta",id));
        return assembler.toModel(pregunta);
    }

    @PutMapping("/preguntas/{id}")
    ResponseEntity<?>  replacePregunta(@RequestBody Pregunta newPregunta, @PathVariable Long id) {

        Pregunta updatedPregunta = preguntaRepository.findById(id)
                .map(pregunta -> {
                    pregunta.setTexto(newPregunta.getTexto());
                    return preguntaRepository.save(pregunta);
                })
                .orElseGet(() -> {
                    newPregunta.setId(id);
                    return preguntaRepository.save(newPregunta);
                });

        EntityModel<Pregunta> preguntaEntityModel = assembler.toModel(updatedPregunta);

        return ResponseEntity
                .created(preguntaEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(preguntaEntityModel);
    }

    @DeleteMapping("/preguntas/{id}")
    ResponseEntity<?> deletePregunta(@PathVariable Long id) {
        preguntaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
