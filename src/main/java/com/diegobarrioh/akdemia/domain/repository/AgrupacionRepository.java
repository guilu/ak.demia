package com.diegobarrioh.akdemia.domain.repository;

import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgrupacionRepository extends JpaRepository<Agrupacion, Long> {

    List<Agrupacion> findByTextoContainingIgnoreCase(String texto);
}