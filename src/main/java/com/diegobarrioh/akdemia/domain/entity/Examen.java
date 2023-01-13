package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.Collection;

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
}
