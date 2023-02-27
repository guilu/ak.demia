package com.diegobarrioh.akdemia.ex;

import com.diegobarrioh.akdemia.controller.FileUploadController;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@ControllerAdvice
public class MaxUploadSizeExceededAdvice {

    @Value("${spring.servlet.multipart.max-file-size}")
    public  String maxFileSize;

    private FileUploadController fileUploadController;

    public MaxUploadSizeExceededAdvice(FileUploadController fileUploadController) {
        this.fileUploadController = fileUploadController;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeExceededException(Exception ex, RedirectAttributes redirectAttributes){
        log.warn("Handling MaxUploadSizeExceededException: {}",ex.getMessage());

        redirectAttributes.addFlashAttribute("message",
                "The file exceeds its maximum permitted size of "+ maxFileSize + ".");
        return "redirect:/file";
    }

}
