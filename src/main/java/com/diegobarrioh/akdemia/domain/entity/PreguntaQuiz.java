package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = DomainModelNames.TB08_PREGUNTA_QUIZ, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PreguntaQuiz extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "PREGUNTA_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Pregunta pregunta;

    private boolean acertada;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PreguntaQuiz that = (PreguntaQuiz) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
