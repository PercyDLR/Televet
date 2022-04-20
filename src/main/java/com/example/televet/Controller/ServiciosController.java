package com.example.televet.Controller;

import com.example.televet.Dto.OpcionResponsableDto;
import com.example.televet.Entity.Opcion;
import com.example.televet.Entity.Responsable;
import com.example.televet.Entity.Servicio;
import com.example.televet.Repository.OpcionRepository;
import com.example.televet.Repository.ResponsableRepository;
import com.example.televet.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/servicios")
public class ServiciosController {

    @Autowired
    OpcionRepository opcionRepository;

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    ResponsableRepository responsableRepository;

    @GetMapping(value = {"", "/","/lista" })
    public String listar(Model model){
        List<OpcionResponsableDto> opcionResponsablesList = opcionRepository.obtenerOpciones();
        model.addAttribute("opcionList",opcionResponsablesList);
        return"servicios/lista";
    }

    @GetMapping(value="/editar")
    public String editar(@RequestParam("idOpcion") int idOpcion,
                         @RequestParam("idServicio") int idServicio,
                         Model model){
        Optional<Opcion> optionalOpcion = opcionRepository.findById(idOpcion);
        Optional<Servicio> optionalServicio = servicioRepository.findById(idServicio);
        if(optionalOpcion.isPresent() && (optionalServicio.isPresent() || idServicio==0)) {
            if(optionalServicio.isPresent()){
                Servicio servicio = optionalServicio.get();
                model.addAttribute("servicio", servicio);
            }

            Opcion opcion = optionalOpcion.get();
            List<Responsable> responsableList = responsableRepository.findAll();

            model.addAttribute("opcion", opcion);
            model.addAttribute("responsableList", responsableList);
            return "servicios/editar";
        }else {
            return "redirect:/servicios/lista";
        }
    }

    @PostMapping("/editarOpcion")
    public String editarOpcion(Opcion opcion){
        Optional<Opcion> optionalOpcion = opcionRepository.findById(opcion.getId());
        if(optionalOpcion.isPresent()){
            Opcion opcionDB = optionalOpcion.get();
            opcionDB.setPrecio(opcion.getPrecio());
            opcionRepository.save(opcionDB);
        }
        return "redirect:/servicios/lista";
    }

    @PostMapping("/editarResponsable")
    public String editarResponsable(Servicio servicio){
        Optional<Servicio> optionalServicio = servicioRepository.findById(servicio.getId());
        if(optionalServicio.isPresent()){
            Servicio servicioDB = optionalServicio.get();
            servicioDB.setResponsable(servicio.getResponsable());
            servicioRepository.save(servicioDB);
        }
        return "redirect:/servicios/lista";
    }

    @GetMapping("/nueva")
    public String nuevaOpcion(){
        return "servicios/nuevo";
    }

    @PostMapping("/nueva")
    public String guardarOpcion(Opcion opcion){
        opcionRepository.save(opcion);
        return "redirect:/servicios/lista";
    }
}
