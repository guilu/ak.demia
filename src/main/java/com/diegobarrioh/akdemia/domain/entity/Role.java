package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Collection;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = DomainModelNames.TB00_ROLE, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Role extends BaseEntity {
    /**
     * The users.
     */
    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private Collection<User> users;

    /**
     * The privileges.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DomainModelNames.TB00_ROLE_PRIVILEGE,
            schema = DomainModelNames.SCHEMA,
            joinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRIVILEGE", referencedColumnName = "id")
    )
    private Collection<Privilege> privileges;

    /**
     * The name.
     */
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}