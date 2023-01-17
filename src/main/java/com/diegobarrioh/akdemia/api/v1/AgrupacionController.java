package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.repository.AgrupacionRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgrupacionController {

    private final AgrupacionRepository agrupacionRepository;

    public AgrupacionController(AgrupacionRepository agrupacionRepository) {
        this.agrupacionRepository = agrupacionRepository;
    }

    // tag::get-aggregate-root[]
    @GetMapping(value = "/agrupaciones")
    public List<Agrupacion> agrupaciones() {
        return agrupacionRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/agrupaciones")
    Agrupacion newAgrupacion(@RequestBody Agrupacion agrupacion) {
        return agrupacionRepository.save(agrupacion);
    }

    @GetMapping(value = "/agrupaciones/{id}")
    public Agrupacion agrupacion(@PathVariable("id") Long id) {
        return agrupacionRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("agrupacion",id));
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
