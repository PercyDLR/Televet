package com.example.televet.Controller;

import com.example.televet.Entity.Raza;
import com.example.televet.Repository.MascotaRepository;
import com.example.televet.Repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("filtro", "");
        return "mascotas/lista";
    }

    @PostMapping("/buscar")
    public String listado(Model model, @RequestParam("filtro") String filtro,
                          @RequestParam("busqueda") String busqueda){

        switch(filtro){
            case "":
                model.addAttribute("listaMascotas",mr.findAll());
                break;
            case "sexo":
                model.addAttribute("listaMascotas",mr.findBySexoContainingIgnoreCase(busqueda));
                break;
            case "raza":
                model.addAttribute("listaMascotas",mr.findByRazaDescripcionOrOtrosContainingIgnoreCase(busqueda,busqueda));
                break;
            case "contacto":
                break;
            default:
                return "redirect:/mascotas";
        }
        model.addAttribute("busqueda", busqueda);
        model.addAttribute("filtro", filtro);
        return "mascotas/lista";
    }

    @GetMapping("/info")
    public String detalleServicios(Model model, @RequestParam("id") int id){
        return "xd";
    }

    @GetMapping("/crear")
    public String agregarMascota(){
        return "xd";
    }

    @GetMapping("/editar")
    public String editarMascota(){
        return "xd";
    }

    @GetMapping("/borrar")
    public String borrarMascota(){
        return "xd";
    }
}
