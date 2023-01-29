package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.controller.form.PreguntaForm;
import com.diegobarrioh.akdemia.controller.form.TemaForm;
import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.service.AgrupacionService;
import com.diegobarrioh.akdemia.service.TemaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("/backstage")
public class BackstagePageController {

    private final AgrupacionService agrupacionService;
    private final TemaService temaService;

    public BackstagePageController(AgrupacionService agrupacionService, TemaService temaService) {
        this.agrupacionService = agrupacionService;
        this.temaService = temaService;
    }

    @GetMapping(value={"/",""})
    public String index(){
        return "backstage/index";
    }

    @GetMapping("/new-agrupacion")
    public String newAgrupacionPage(Model model) {
        model.addAttribute("agrupacion",new Agrupacion());

        return "backstage/agrupacion";
    }

    @PostMapping("/new-agrupacion")
    public String newAgrupacionSubmit(@ModelAttribute Agrupacion agrupacion, Model model) {
        model.addAttribute("agrupacion",agrupacionService.save(agrupacion));

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

        model.addAttribute("preguntaForm",new PreguntaForm());
        return "backstage/pregunta";

    }

}
