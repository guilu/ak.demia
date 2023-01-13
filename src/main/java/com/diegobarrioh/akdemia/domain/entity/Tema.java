package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import javax.validation.constraints.Size;

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


}