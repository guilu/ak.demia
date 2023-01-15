package com.diegobarrioh.akdemia.api.v1;

import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.repository.AgrupacionRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgrupacionController {

    private final AgrupacionRepository agrupacionRepository;

    public AgrupacionController(AgrupacionRepository agrupacionRepository) {
        this.agrupacionRepository = agrupacionRepository;
    }

    @GetMapping(value = "/agrupaciones")
    public List<Agrupacion> agrupaciones() {
        return agrupacionRepository.findAll();
    }

    @GetMapping(value = "/agrupaciones/{id}")
    public Agrupacion agrupacion(@PathVariable("id") Long id) {
        return agrupacionRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("/agrupaciones/"+id,"Agrupacion"));
    }

}
