package com.diegobarrioh.akdemia.domain.repository;

import com.diegobarrioh.akdemia.domain.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    ApiKey findByKeyValue(String apikeyvalue);

}
