package com.diegobarrioh.akdemia.service;


import com.diegobarrioh.akdemia.service.storage.FileSystemStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PDFServiceTests {


    @Value("${storage.location}")
    public String location;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @Test
    void loadAllDefaultLocationFiles() {
        List<Path> filesInDefaultLocation = fileSystemStorageService.loadAll().toList();
        assertThat( filesInDefaultLocation.size() ).isNotNegative();
    }


}
