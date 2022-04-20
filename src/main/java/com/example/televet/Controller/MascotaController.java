package com.example.televet.Controller;

import com.example.televet.Dto.ServiciosMascotasDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    MascotaRepository mr;

    @GetMapping(value = {"","/lista"})
    public String listado(@RequestParam(required=false,name="search") String search,
                          @RequestParam(required=false,name="filtro") String filtro,
                          Model model){
        List<ServiciosMascotasDto> mascotaList;
        if(filtro==null){filtro="";}
        if(search==null){search="";}
        switch(filtro){
            case "sexo":
                mascotaList =mr.listaSexo(search);
                break;
            case "raza":
                mascotaList =mr.listaRaza(search);
                break;
            case "contacto":
                mascotaList =mr.listaCuenta(search);
                break;
            default:
                mascotaList =mr.listaContadorServicios(search);
        }
        model.addAttribute("listaMascotas",mascotaList);
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
