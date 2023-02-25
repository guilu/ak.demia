package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Collection;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = DomainModelNames.TB00_APIKEYS, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiKey extends BaseEntity {

    private String keyValue;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DomainModelNames.TB00_APIKEY_ROLE,
            schema = DomainModelNames.SCHEMA,
            joinColumns = @JoinColumn(name = "ID_APIKEY", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "id"))
    private Collection<Role> roles;
}
