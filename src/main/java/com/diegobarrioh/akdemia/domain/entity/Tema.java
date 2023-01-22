package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = DomainModelNames.TB02_TEMA, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tema extends BaseEntity {

    private static final long serialVersionUID = 2L;

    @Size(max = 1024)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "AGRUPACION_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Agrupacion agrupacion;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Pregunta> preguntas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tema tema = (Tema) o;
        return getId() != null && Objects.equals(getId(), tema.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}