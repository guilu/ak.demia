package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.entity.Tema;
import com.diegobarrioh.akdemia.service.AgrupacionService;
import com.diegobarrioh.akdemia.service.TemaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/")
    public String index(){
        return "backstage/index";
    }

    @GetMapping("/new-agrupacion")
    public String newAgrupacionPage(Model model) {
        model.addAttribute("agrupacion",new Agrupacion());
        return "backstage/agrupacion";
    }

    @PostMapping("/new-agrupacion")
    public String newAgrupacion(@ModelAttribute Agrupacion agrupacion, Model model) {
        Agrupacion agrupacionSaved = agrupacionService.save(agrupacion);
        model.addAttribute("agrupacion",agrupacionSaved);
        return "backstage/agrupacion";
    }


    @GetMapping("/new-tema")
    public String nuevoTemaForm(Model model){
        model.addAttribute("agrupaciones", agrupacionService.getAgrupacionesAlphabetically());
        model.addAttribute("tema", new Tema());
        return "backstage/tema";
    }

   @PostMapping("/new-tema")
   public String nuevaTemaSubmit(@ModelAttribute Tema tema,Model model) {
        model.addAttribute("agrupaciones", agrupacionService.getAgrupacionesAlphabetically());
        model.addAttribute("tema",temaService.save(tema));
        return "backstage/tema";
    }

}
