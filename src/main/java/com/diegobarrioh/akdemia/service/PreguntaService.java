package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.controller.form.PreguntaForm;
import com.diegobarrioh.akdemia.domain.entity.Pregunta;
import com.diegobarrioh.akdemia.domain.entity.Respuesta;
import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.domain.repository.PreguntaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;

    public PreguntaService(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    public Pregunta save(Pregunta pregunta){
        return this.preguntaRepository.save(pregunta);
    }

    public Pregunta getPreguntaFrom(PreguntaForm preguntaForm) {

        //un texto
        //un array de objetos Respuestas
        //      cada objeto respuesta es
        //                              un texto
        //                              y si es correcta o no.
        Pregunta pregunta = new Pregunta();
        pregunta.setTexto(preguntaForm.getTextoPregunta());

        List<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(new Respuesta(pregunta, preguntaForm.getTexto1Respuesta(), (preguntaForm.getRespuestaCorrecta()-1)==0 ));
        respuestas.add(new Respuesta(pregunta, preguntaForm.getTexto2Respuesta(), (preguntaForm.getRespuestaCorrecta()-1)==1 ));
        respuestas.add(new Respuesta(pregunta, preguntaForm.getTexto3Respuesta(), (preguntaForm.getRespuestaCorrecta()-1)==2 ));
        respuestas.add(new Respuesta(pregunta, preguntaForm.getTexto4Respuesta(), (preguntaForm.getRespuestaCorrecta()-1)==3 ));

        pregunta.setRespuestas(respuestas);

        return pregunta;
    }
}
