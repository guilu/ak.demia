package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.repository.AgrupacionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AgrupacionService {

    private final AgrupacionRepository agrupacionRepository;

    public AgrupacionService(AgrupacionRepository agrupacionRepository) {
        this.agrupacionRepository = agrupacionRepository;
    }

    public Agrupacion save(Agrupacion agrupacion) {
        log.info("Guardando nueva agrupacion {} con id: {}",agrupacion,agrupacion.getId());
        return this.agrupacionRepository.save(agrupacion);
    }

    public Object getAgrupacionesAlphabetically() {
        return this.agrupacionRepository.findAllByOrderByTexto();
    }
}
