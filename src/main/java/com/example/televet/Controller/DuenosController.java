package com.example.televet.Controller;

import com.example.televet.Dto.MascotasPorDueno;
import com.example.televet.Entity.Cuenta;
import com.example.televet.Repository.CuentaRepository;
import com.example.televet.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/duenos")
public class DuenosController {
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    MascotaRepository mascotaRepository;

    @GetMapping(value = {"", "/lista"})
    public String listaDueno(Model model) {
        List<MascotasPorDueno> duenosList = cuentaRepository.listaDuenos();
        model.addAttribute("duenosList",duenosList);
        return "duenos/lista";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm() {
        return "duenos/newForm";
    }

    @PostMapping("/save")
    public String guardarDueno(Cuenta cuenta, Model model, RedirectAttributes attr) {
        cuenta.setAdmin(0);
        if (cuenta.getId() == null) {
            attr.addFlashAttribute("msg", "Producto creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
        }
        if (cuenta.getCorreo() != null && cuenta.getPassword() != null) {
            cuentaRepository.save(cuenta);
            return "redirect:/duenos";
        } else {
            if (cuenta.getId() != null) {
                model.addAttribute("dueno", cuenta);
                return "duenos/editForm";
            } else {
                return "duenos/newForm";
            }
        }
    }

    @GetMapping("/edit")
    public String editarDueno(Model model, @RequestParam("id") int id) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            model.addAttribute("dueno",cuenta);
            model.addAttribute("mascotasList",mascotaRepository.mascotaList(id));
            return "duenos/editForm";
        } else {
            return "redirect:/duenos";
        }
    }
}
