package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = DomainModelNames.TB03_PREGUNTA, schema = DomainModelNames.SCHEMA)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pregunta extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Size(max = 1024)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "TEMA_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Tema tema;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Respuesta> respuestas;

    /**
     * The roles.
     */
    @ManyToMany(mappedBy = "preguntas")
    @ToString.Exclude
    @JsonIgnore
    private Collection<Examen> examenes;

}
