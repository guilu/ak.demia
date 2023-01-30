package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = DomainModelNames.TB05_EXAMEN, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Examen extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Size(max = 1024)
    @Column
    private String texto;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DomainModelNames.TB06_EXAMEN_PREGUNTA,
            schema = DomainModelNames.SCHEMA,
            joinColumns = @JoinColumn(name = "ID_EXAMEN", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "id")
    )
    private Collection<Pregunta> preguntas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Examen examen = (Examen) o;
        return Objects.equals(texto, examen.texto) && Objects.equals(preguntas, examen.preguntas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), texto, preguntas);
    }
}
