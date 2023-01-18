package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Pregunta;
import com.diegobarrioh.akdemia.domain.repository.PreguntaRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApiRequestMappings.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class PreguntaController {

    private final PreguntaRepository preguntaRepository;

    public PreguntaController(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    @GetMapping("/preguntas")
    public List<Pregunta> preguntas() {
        return preguntaRepository.findAll();
    }

    @PostMapping("/preguntas")
    Pregunta newPregunta(@RequestBody Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @GetMapping("/preguntas/{id}")
    public Pregunta pregunta(@PathVariable("id") Long id) {
        return preguntaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pregunta",id));
    }

    @PutMapping("/preguntas/{id}")
    Pregunta replacePregunta(@RequestBody Pregunta newPregunta, @PathVariable Long id) {
        return preguntaRepository.findById(id)
                .map(pregunta -> {
                    pregunta.setTexto(newPregunta.getTexto());
                    return preguntaRepository.save(pregunta);
                })
                .orElseGet(() -> {
                    newPregunta.setId(id);
                    return preguntaRepository.save(newPregunta);
                });
    }

    @DeleteMapping("/preguntas/{id}")
    void deletePregunta(@PathVariable Long id) {
        preguntaRepository.deleteById(id);
    }

}
