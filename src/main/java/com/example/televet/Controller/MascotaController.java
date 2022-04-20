package com.example.televet.Controller;

import com.example.televet.Entity.Raza;
import com.example.televet.Repository.MascotaRepository;
import com.example.televet.Repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    MascotaRepository mr;

    @GetMapping(value = {"","/lista"})
    public String listado(Model model){

        model.addAttribute("listaMascotas",mr.findAll());
        model.addAttribute("busqueda", null);
        return "mascotas/lista";
    }

    @GetMapping("/buscar")
    public String listado(Model model, @RequestParam("filtro") String filtro,
                          @RequestParam("busqueda") String busqueda){

        switch(filtro){
            case "":
                model.addAttribute("listaMascotas",mr.findAll());
                break;
            case "sexo":
                //model.addAttribute("listaMascotas",mr.findBySexo(busqueda));
                break;
            case "raza":
                //model.addAttribute("listaMascotas",mr.());
                break;
            case "contacto":
                break;
            default:
                return "redirect:/mascotas";
        }

        return "mascotas/lista";
    }
}
