package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Collection;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = DomainModelNames.TB00_ROLE)
public class Role extends BaseEntity {
    /**
     * The users.
     */
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    /**
     * The privileges.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DomainModelNames.TB00_ROLE_PRIVILEGE,
            joinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRIVILEGE", referencedColumnName = "id")
    )
    private Collection<Privilege> privileges;

    /**
     * The name.
     */
    private String name;
}