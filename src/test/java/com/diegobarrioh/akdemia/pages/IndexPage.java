package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IndexPage extends BasePage {

    @FindBy(how = How.ID, using = "blog_link")
    private WebElement lnkLogin;

    @Value("${spring.application.url}")
    private String url;


}
