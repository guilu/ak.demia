package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Respuesta;
import com.diegobarrioh.akdemia.domain.repository.RespuestaRepository;
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
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;

    private final RespuestaModelAssembler assembler;

    public RespuestaController(RespuestaRepository respuestaRepository, RespuestaModelAssembler assembler) {
        this.respuestaRepository = respuestaRepository;
        this.assembler = assembler;
    }

    @GetMapping("/respuestas")
    public CollectionModel<EntityModel<Respuesta>> respuestas() {

        List<EntityModel<Respuesta>> respuestas = respuestaRepository.findAll().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of( respuestas,
                linkTo(methodOn(RespuestaController.class).respuestas()).withSelfRel());
    }

    @PostMapping("/respuestas")
    ResponseEntity<EntityModel<Respuesta>> newRespuesta(@RequestBody Respuesta respuesta) {

        EntityModel<Respuesta> respuestaEntityModel = assembler.toModel(respuestaRepository.save(respuesta));

        return ResponseEntity.created(
                respuestaEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(respuestaEntityModel);
    }

    @GetMapping("/respuestas/{id}")
    public EntityModel<Respuesta> respuesta(@PathVariable("id") Long id) {
        Respuesta respuesta = respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("respuesta",id));
        return assembler.toModel(respuesta);
    }

    @PutMapping("/respuestas/{id}")
    ResponseEntity<EntityModel<Respuesta>> replaceRespuesta(@RequestBody Respuesta newRespuesta, @PathVariable Long id) {
        Respuesta updatedRespuesta = respuestaRepository.findById(id)
                .map( respuesta -> {
                    respuesta.setTexto(newRespuesta.getTexto());
                    respuesta.setCorrecta(newRespuesta.isCorrecta());
                    return respuestaRepository.save(respuesta);
                })
                .orElseGet( () -> {
                    newRespuesta.setId(id);
                    return respuestaRepository.save(newRespuesta);
                });
        EntityModel<Respuesta> respuestaEntityModel = assembler.toModel(updatedRespuesta);

        return ResponseEntity
                .created(respuestaEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(respuestaEntityModel);
    }

    @DeleteMapping("/respuestas/{id}")
    ResponseEntity<EntityModel<Respuesta>> deleteRespuesta(@PathVariable Long id) {
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
