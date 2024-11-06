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
import pe.com.zarita.Zara.entity.TipoDocumento;
import pe.com.zarita.Zara.repository.TipoDocumentoRepository;
import pe.com.zarita.Zara.service.TipoDocumentoService;

@Controller
public class DocumentoController {
    
    @Autowired
    private TipoDocumentoService documentoService;
    
    @Autowired
    private TipoDocumentoRepository repositorio;
         // Mostrar lista de empleados activos
    @GetMapping("/admin/listardocumento")
    public String listarDocumentos(Model model) {
        List<TipoDocumento> documentos = documentoService.listarDistrito();
        model.addAttribute("documentos", documentos);
        return "admin/listardocumento";
    }
    
    // Deshabilitar DOCUMENTO (eliminación lógica)
    @GetMapping("/documento/eliminar/{id}")
    public String eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminarDocumentoLogicamente(id);
        return "redirect:/admin/listardocumento";
    }
    
    @GetMapping("/admin/habilitardocumento")
    public String MostrarHabilitarDocumento(Model modelo) {
        modelo.addAttribute("documentos", documentoService.findAll());
        return "admin/habilitardocumento";
    }
    
    // Reactivar empleado
    @GetMapping("/documento/reactivardocumento/{id}")
    public String reactivarDocumento(@PathVariable Long id) {
        documentoService.reactivarDocumento(id);
        return "redirect:/admin/habilitardocumento";
    }
    
    @GetMapping("/admin/actualizardocumento/{id}")
    public String MostrarActualizaEmpleado(@PathVariable Long id, Model modelo) {
        TipoDocumento documento = documentoService.findById(id);

        modelo.addAttribute("tiposDocumento", documento);
        return "admin/actualizardocumento";
    }
    
    @PostMapping("/admin/actualizardocumento/{id}")
    public String ActualizarEmpleado(@PathVariable Long id, @ModelAttribute("empleados") TipoDocumento p) {
        p.setIdtipodocumento(id);  // Asegúrate de que el objeto empleado tenga el ID
        documentoService.update(p);
        return "redirect:/admin/listardocumento";
    }
}
