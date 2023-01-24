package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.domain.entity.Agrupacion;
import com.diegobarrioh.akdemia.domain.repository.AgrupacionRepository;
import com.diegobarrioh.akdemia.service.AgrupacionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    public BackstagePageController(AgrupacionService agrupacionService) {
        this.agrupacionService = agrupacionService;
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
        model.addAttribute("new-agrupacion",agrupacionSaved);
        return "backstage/agrupacion";
    }
}
