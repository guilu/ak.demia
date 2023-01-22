package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = DomainModelNames.TB07_QUIZ, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Quiz extends BaseEntity {

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<PreguntaQuiz> preguntasQuiz;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @JsonIgnore
    private User user;

    private int aciertos;
    private int fallos;

    private int numeroPreguntasXTema;
    private int numeroPreguntasTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Quiz quiz = (Quiz) o;
        return getId() != null && Objects.equals(getId(), quiz.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
