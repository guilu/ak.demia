package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.domain.entity.ApiKey;
import com.diegobarrioh.akdemia.domain.repository.ApiKeyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public List<ApiKey> getAll() {
        return this.apiKeyRepository.findAll();
    }

    public ApiKey getByApikeyValue(String apiKey) {
        return this.apiKeyRepository.findByKeyValue(apiKey);
    }
}
