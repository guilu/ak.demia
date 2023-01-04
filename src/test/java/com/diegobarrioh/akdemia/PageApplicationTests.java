package com.diegobarrioh.akdemia;

import com.diegobarrioh.akdemia.pages.IndexPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageApplicationTests {

    @Autowired
    private IndexPage indexPage;

    @Value("${spring.application.url}")
    private String appUrl;

    @Test
    void contextLoads() {
        indexPage.navigate("http://localhost:8080");
    }
}
s