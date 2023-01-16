package com.diegobarrioh.akdemia.domain.repository;

import com.diegobarrioh.akdemia.domain.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

}
