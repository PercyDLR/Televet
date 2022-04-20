package com.example.televet.Controller;

import com.example.televet.Dto.ServiciosMascotasDto;
import com.example.televet.Entity.Mascota;
import com.example.televet.Entity.Raza;
import com.example.televet.Repository.CuentaRepository;
import com.example.televet.Repository.MascotaRepository;
import com.example.televet.Repository.RazaRepository;
import com.example.televet.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    MascotaRepository mr;

    @Autowired
    CuentaRepository cr;

    @Autowired
    RazaRepository rr;

    @Autowired
    ServicioRepository servicioRepository;

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
        return "mascotas/details";
    }

    @GetMapping("/new")
    public String agregarMascota(Model model){
        model.addAttribute("duenosList",cr.findByAdmin(0));
        model.addAttribute("razaList",rr.findAll());
        return "mascotas/newForm";
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

    @PostMapping("/save")
    public String guardarMascota(Mascota mascota, Model model, RedirectAttributes attr) {

        if (mascota.getId() == 0) {
            attr.addFlashAttribute("msg", "Mascota creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Mascota actualizado exitosamente");
        }
        if (mascota.getNombre() != null) {
            mr.save(mascota);
            return "redirect:/mascotas";
        } else {
            if (mascota.getId() != 0) {
                model.addAttribute("mascota", mascota);
                model.addAttribute("duenosList",cr.findByAdmin(0));
                model.addAttribute("razaList",rr.findAll());
                return "mascotas/editForm";
            } else {
                return "mascotas/newForm";
            }
        }
    }

    @GetMapping("/edit")
    public String editarMascota(Model model, @RequestParam("id") int id) {
        Optional<Mascota> optionalMascota = mr.findById(id);
        if (optionalMascota.isPresent()) {
            Mascota mascota = optionalMascota.get();
            model.addAttribute("mascota",mascota);
            model.addAttribute("duenosList",cr.findByAdmin(0));
            model.addAttribute("razaList",rr.findAll());
            return "mascotas/editForm";
        } else {
            return "redirect:/mascotas";
        }
    }


}
