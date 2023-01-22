package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Collection;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Agrupacion that = (Agrupacion) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}