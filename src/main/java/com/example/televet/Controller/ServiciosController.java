package com.example.televet.Controller;

import com.example.televet.Entity.Opcion;
import com.example.televet.Repository.OpcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/servicios")
public class ServiciosController {

    @Autowired
    OpcionRepository opcionRepository;

    @GetMapping(value = {"", "/","lista" })
    public String listar(Model model){
        List<Opcion> opcionList = opcionRepository.findAll();
        model.addAttribute("opcionList",opcionList);
        return"servicios/lista";
    }

}
