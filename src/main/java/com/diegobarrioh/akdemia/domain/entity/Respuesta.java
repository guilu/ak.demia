package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

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

}