package com.example.televet.Controller;

import com.example.televet.Entity.Mascota;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Mascota> optMaskot = mr.findById(id);
        if (optMaskot.isPresent()) {
            mr.deleteById(id);
            attr.addFlashAttribute("msg", "Mascota borrada exitosamente");
        }
        return "redirect:/mascotas";

    }


}
