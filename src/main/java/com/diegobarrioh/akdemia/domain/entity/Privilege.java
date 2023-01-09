package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = DomainModelNames.TB00_PRIVILEGE)
public class Privilege extends BaseEntity {
    /**
     * The name.
     */
    private String name;

    /**
     * The roles.
     */
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}