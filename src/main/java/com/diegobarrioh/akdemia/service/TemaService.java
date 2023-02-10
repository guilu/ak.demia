package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.controller.form.TemaForm;
import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.domain.repository.AgrupacionRepository;
import com.diegobarrioh.akdemia.domain.repository.TemaRepository;
import com.diegobarrioh.akdemia.ex.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {

    private final TemaRepository temaRepository;
    private final AgrupacionRepository agrupacionRepository;

    public TemaService(TemaRepository temaRepository, AgrupacionRepository agrupacionRepository) {
        this.temaRepository = temaRepository;
        this.agrupacionRepository = agrupacionRepository;
    }

    public Tema save(TemaForm temaForm){

        Tema tema = new Tema();
        tema.setTexto(temaForm.getTexto());

        Agrupacion agrupacion = agrupacionRepository.findById(temaForm.getIdAgrupacion()).orElseThrow( () -> new EntityNotFoundException("agrupacion", temaForm.getIdAgrupacion()));
        tema.setAgrupacion(agrupacion);

        return temaRepository.save(tema);
    }

    public List<Tema> getTemas(){
        return temaRepository.findAll();
    }

    public Tema getTema(int idTema) {
        return temaRepository.findById(Integer.toUnsignedLong(idTema)).orElseThrow( () -> new EntityNotFoundException("tema", Integer.toUnsignedLong(idTema)));
    }
}
