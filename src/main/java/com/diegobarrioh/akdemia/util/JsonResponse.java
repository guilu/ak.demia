package com.diegobarrioh.akdemia.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JsonResponse {

    private boolean success;
    private String redirectUrl;
    private Integer code;
    private String message;
}
