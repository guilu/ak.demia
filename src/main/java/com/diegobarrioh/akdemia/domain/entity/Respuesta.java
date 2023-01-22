package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = DomainModelNames.TB04_RESPUESTA, schema = DomainModelNames.SCHEMA)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Respuesta extends BaseEntity {

    private static final long serialVersionUID = 4L;

    @ManyToOne
    @JoinColumn(name = "PREGUNTA_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Pregunta pregunta;

    @Size(max = 1024)
    @Column
    private String texto;

    @Column
    private boolean correcta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Respuesta respuesta = (Respuesta) o;
        return getId() != null && Objects.equals(getId(), respuesta.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}