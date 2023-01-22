package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = DomainModelNames.TB00_PRIVILEGE, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Privilege extends BaseEntity {
    /**
     * The name.
     */
    private String name;

    /**
     * The roles.
     */
    @ManyToMany(mappedBy = "privileges")
    @ToString.Exclude
    private Collection<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Privilege privilege = (Privilege) o;
        return getId() != null && Objects.equals(getId(), privilege.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}