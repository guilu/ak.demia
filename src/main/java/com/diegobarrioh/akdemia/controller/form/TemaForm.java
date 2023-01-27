package com.diegobarrioh.akdemia.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemaForm {

    @NotNull(message = "Un tema debe asignarse a una agrupacion")
    long idAgrupacion;
    @NotEmpty(message = "Texto no puede ser vacío")
    String texto;

}
