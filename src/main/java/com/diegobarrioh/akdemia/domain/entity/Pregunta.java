package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pregunta pregunta = (Pregunta) o;
        return getId() != null && Objects.equals(getId(), pregunta.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
