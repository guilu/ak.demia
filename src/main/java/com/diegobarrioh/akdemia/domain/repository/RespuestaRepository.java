package com.diegobarrioh.akdemia.domain.repository;

import com.diegobarrioh.akdemia.domain.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {


}
