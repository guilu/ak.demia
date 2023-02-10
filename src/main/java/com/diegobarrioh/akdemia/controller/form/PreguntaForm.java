package com.diegobarrioh.akdemia.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PreguntaForm {

    @NotNull(message = "Una pregunta debe pertenecer a un tema")
    private int idTema;

    @NotEmpty(message = "El texto de la pregunta no se puede dejar en blanco")
    private String textoPregunta;

    private String texto1Respuesta;
    private String texto2Respuesta;
    private String texto3Respuesta;
    private String texto4Respuesta;

    private int respuestaCorrecta;
}
