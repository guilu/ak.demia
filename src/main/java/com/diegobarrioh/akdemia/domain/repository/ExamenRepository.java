package com.diegobarrioh.akdemia.domain.repository;

import com.diegobarrioh.akdemia.domain.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
}
