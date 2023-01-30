package com.diegobarrioh.akdemia.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PreguntaForm {

    @NotNull(message = "Una pregunta debe pertenecer a un tema")
    private int idTema;

    @NotEmpty(message = "El texto de la pregunta no se puede dejar en blanco")
    private String texto;

    private String[] respuestas;


}
