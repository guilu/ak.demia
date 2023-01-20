package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Respuesta;
import com.diegobarrioh.akdemia.domain.repository.RespuestaRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApiRequestMappings.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;

    public RespuestaController(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    @GetMapping("/respuestas")
    public List<Respuesta> respuestas() {
        return respuestaRepository.findAll();
    }

    @PostMapping("/respuestas")
    Respuesta newRespuesta(@RequestBody Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    @GetMapping("/respuestas/{id}")
    public Respuesta respuesta(@PathVariable("id") Long id) {
        return  respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("respuesta",id));
    }

    @PutMapping("/respuestas/{id}")
    Respuesta replaceRespuesta(@RequestBody Respuesta newRespuesta, @PathVariable Long id) {
        return respuestaRepository.findById(id)
                .map( respuesta -> {
                    respuesta.setTexto(newRespuesta.getTexto());
                    respuesta.setCorrecta(newRespuesta.isCorrecta());
                    return respuestaRepository.save(respuesta);
                })
                .orElseGet( () -> {
                    newRespuesta.setId(id);
                    return respuestaRepository.save(newRespuesta);
                });
    }

    @DeleteMapping("/respuestas/{id}")
    void deleteRespuesta(@PathVariable Long id) {
        respuestaRepository.deleteById(id);
    }

}
