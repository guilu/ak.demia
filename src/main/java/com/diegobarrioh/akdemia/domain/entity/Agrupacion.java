package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = DomainModelNames.TB01_AGRUPACION, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Agrupacion extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Size(max = 1024)
    private String texto;

    @OneToMany(mappedBy = "agrupacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Tema> temas;

}