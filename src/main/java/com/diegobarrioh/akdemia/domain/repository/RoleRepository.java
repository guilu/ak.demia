package com.diegobarrioh.akdemia.domain.repository;

import com.diegobarrioh.akdemia.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find by name.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);

    /**
     * Delete.
     *
     * @param role the role
     */
    @Override
    void delete(Role role);
}