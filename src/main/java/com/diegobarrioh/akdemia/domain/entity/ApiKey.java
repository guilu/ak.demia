package com.diegobarrioh.akdemia.domain.entity;

import com.diegobarrioh.akdemia.domain.DomainModelNames;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Collection;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = DomainModelNames.TB00_APIKEYS, schema = DomainModelNames.SCHEMA)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiKey extends BaseEntity {

    private String key;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DomainModelNames.TB00_USER_ROLE,
            schema = DomainModelNames.SCHEMA,
            joinColumns = @JoinColumn(name = "ID_APIKEY", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "id"))
    private Collection<Role> roles;
}
