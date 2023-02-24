package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.controller.form.PreguntaForm;
import com.diegobarrioh.akdemia.controller.form.TemaForm;
import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.entity.Pregunta;
import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.service.AgrupacionService;
import com.diegobarrioh.akdemia.service.PreguntaService;
import com.diegobarrioh.akdemia.service.TemaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@Transactional
@RequestMapping("/backstage")
public class BackstagePageController {

    private final AgrupacionService agrupacionService;
    private final TemaService temaService;

    private final PreguntaService preguntaService;

    public BackstagePageController(AgrupacionService agrupacionService, TemaService temaService, PreguntaService preguntaService) {
        this.agrupacionService = agrupacionService;
        this.temaService = temaService;
        this.preguntaService = preguntaService;
    }

    @GetMapping(value={"/",""})
    public String index(){
        return "backstage/index";
    }

    @GetMapping("/new-agrupacion")
    public String newAgrupacionPage(Model model) {
        model.addAttribute("agrupaciones", agrupacionService.getAgrupacionesAlphabetically());
        model.addAttribute("agrupacion",new Agrupacion());

        return "backstage/agrupacion";
    }

    @PostMapping("/new-agrupacion")
    public String newAgrupacionSubmit(@ModelAttribute Agrupacion agrupacion, Model model) {
        model.addAttribute("agrupacion",agrupacionService.save(agrupacion));

        model.addAttribute("agrupaciones", agrupacionService.getAgrupacionesAlphabetically());
        return "backstage/agrupacion";
    }

    @GetMapping("/new-tema")
    public String nuevoTemaForm(TemaForm temaForm, Model model){
        model.addAttribute("agrupaciones", agrupacionService.getAgrupacionesAlphabetically());
        model.addAttribute("temaForm", new TemaForm());

        return "backstage/tema";
    }

   @PostMapping("/new-tema")
   public String nuevaTemaSubmit(@Valid TemaForm temaForm, BindingResult bindingResult,Model model) {

       if (bindingResult.hasErrors()) {
           return "backstage/tema";
       }
       temaService.save(temaForm);

       model.addAttribute("agrupaciones", agrupacionService.getAgrupacionesAlphabetically());
       model.addAttribute("temaForm",new TemaForm() );

       return "backstage/tema";
    }

    @GetMapping("/new-pregunta")
    public String nuevaPreguntaForm(Model model){
        model.addAttribute("temas",temaService.getTemas());
        model.addAttribute("preguntaForm",new PreguntaForm());
        return "backstage/pregunta";

    }

    @PostMapping("/new-pregunta")
    public String nuevaPreguntaSubmit(@Valid PreguntaForm preguntaForm, BindingResult bindingResult, Model model) {

        log.info("La pregunta a insertar {}",preguntaForm.toString());
        Pregunta pregunta = preguntaService.getPreguntaFrom(preguntaForm);
        Tema tema = temaService.getTema(preguntaForm.getIdTema());
        pregunta.setTema(tema);

        preguntaService.save(pregunta);

        model.addAttribute("temas",temaService.getTemas());
        model.addAttribute("preguntaForm",new PreguntaForm());
        return "backstage/pregunta";
    }



    @GetMapping("/agrupaciones")
    public String agrupaciones(Model model) {
        model.addAttribute("agrupaciones", agrupacionService.getAgrupacionesAlphabetically());
        return "backstage/agrupaciones";
    }
    @GetMapping("/temas")
    public String temas(Model model) {
        model.addAttribute("temas", temaService.getTemas());
        return "backstage/temas";
    }

    @GetMapping("/preguntas")
    public String preguntas(Model model) {
        model.addAttribute("preguntas", preguntaService.getPreguntas());
        return "backstage/preguntas";
    }


}
