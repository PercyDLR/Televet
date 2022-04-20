package com.example.televet.Controller;

import com.example.televet.Entity.Responsable;
import com.example.televet.Repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/responsables")
public class ResponsableController {

    @Autowired
    ResponsableRepository responsableRepository;

    @GetMapping(value = {"","/lista"})
    public String listaResponsable(Model model){
        model.addAttribute("listaResponsables",responsableRepository.findAll());
        return "responsables/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoResponsable(){
        return "responsables/crear";
    }

    @PostMapping("/guardar")
    public String guardarResponsable(Responsable responsable, RedirectAttributes attr){
        responsableRepository.save(responsable);
        attr.addFlashAttribute("msg","Se ha credo un nuevo responsable exitosamente");
        return "redirect:/responsables/lista";
    }

}
