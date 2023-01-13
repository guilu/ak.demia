package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Collection;

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


}