/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.com.zarita.Zara.entity.Distrito;
import pe.com.zarita.Zara.service.DistritoService;

/**
 *
 * @author melan
 */
@Controller
public class DistritoController {
    
    @Autowired
    private DistritoService distritoService;

    // Listar distritos activos
    @GetMapping("/admin/listardistrito")
    public String listarDistritos(Model model) {
        List<Distrito> distritos = distritoService.listarDistrito(); // Cambiado a distritoService
        model.addAttribute("distritos", distritos); // Asegúrate de usar "distritos"
        return "admin/listardistrito"; // Verifica que esta vista exista
    }
    
  @GetMapping("admin/habilitardistrito")
public String habilitarDistritos(Model model) {
    List<Distrito> distritosEliminados = distritoService.getDeshabilitarDistrito(); // Asegúrate que este método retorne la lista correcta
    model.addAttribute("distritosEliminados", distritosEliminados); // Cambia el nombre del atributo
    return "admin/habilitardistrito"; // Asegúrate que esta vista exista
}

    
    // Mostrar formulario de actualización de distrito
    @GetMapping("/admin/actualizardistrito/{id}")
    public String mostrarActualizarDistrito(@PathVariable Long id, Model model) {
        Distrito distrito = distritoService.findById(id).orElseThrow(() -> new IllegalArgumentException("Distrito no encontrado"));
        model.addAttribute("distrito", distrito);
        return "admin/actualizardistrito"; // Verifica que esta vista exista
    }

    // Actualizar distrito
    @PostMapping("/admin/actualizarDis/{id}")
    public String actualizarDistrito(@PathVariable Long id, @ModelAttribute Distrito nuevoDistrito) {
        distritoService.actualizarDistrito(id, nuevoDistrito);
        return "redirect:/admin/listardistrito";
    }


    // Deshabilitar distrito (eliminación lógica)
    @GetMapping("/admin/deshabilitardistrito/{id}")
    public String deshabilitarDistrito(@PathVariable Long id) {
        distritoService.deshabilitarDistrito(id);
        return "redirect:/admin/listardistrito";
    }
    
    // Reactivar distrito
    @GetMapping("/admin/reactivardistrito/{id}")
    public String reactivarDistrito(@PathVariable Long id) {
        distritoService.reactivarDistrito(id);
        return "redirect:/admin/listardistrito";
    }

    // Eliminar distrito (eliminación lógica)
    @GetMapping("/admin/eliminar/{id}")
    public String eliminarDistrito(@PathVariable Long id) {
    // Llama al servicio para deshabilitar el distrito
    distritoService.deshabilitarDistrito(id);
    return "redirect:/admin/listardistrito";
}

}
