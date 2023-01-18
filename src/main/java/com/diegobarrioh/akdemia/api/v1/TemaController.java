package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.api.ApiRequestMappings;
import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.domain.repository.TemaRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApiRequestMappings.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class TemaController {

    private final TemaRepository temaRepository;

    public TemaController(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @GetMapping("/temas")
    public List<Tema> temas() {
        return temaRepository.findAll();
    }

    @PostMapping("/temas")
    Tema newTema(@RequestBody Tema tema) {
        return temaRepository.save(tema);
    }

    @GetMapping("/temas/{id}")
    public Tema tema(@PathVariable("id") Long id) {
        return temaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("tema", id));
    }

    @PutMapping("/temas/{id}")
    Tema replaceTema(@RequestBody Tema newTema, @PathVariable Long id) {
        return temaRepository.findById(id)
                .map(tema -> {
                            tema.setTexto(newTema.getTexto());
                            return temaRepository.save(tema);
                        })
                .orElseGet(() -> {
                            newTema.setId(id);
                            return temaRepository.save(newTema);
                        });
    }

    @DeleteMapping("/temas/{id}")
    void deleteTema(@PathVariable Long id) {
        temaRepository.deleteById(id);
    }

}
