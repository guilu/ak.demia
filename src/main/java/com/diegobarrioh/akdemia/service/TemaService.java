package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.domain.repository.TemaRepository;
import org.springframework.stereotype.Service;

@Service
public class TemaService {

    private final TemaRepository temaRepository;

    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    public Tema save(Tema tema){
        return temaRepository.save(tema);
    }
}
