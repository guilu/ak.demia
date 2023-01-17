package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Respuesta;
import com.diegobarrioh.akdemia.domain.repository.RespuestaRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;

    public RespuestaController(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    @GetMapping("/respuestas")
    public List<Respuesta> respuestas() {
        return respuestaRepository.findAll();
    }

    @GetMapping("/respuestas/{id}")
    public Respuesta respuesta(@PathVariable("id") Long id) {
        return  respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("respuesta",id));
    }
}
