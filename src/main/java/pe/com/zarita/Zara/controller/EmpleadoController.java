/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.com.zarita.Zara.entity.Distrito;
import pe.com.zarita.Zara.entity.Empleado;
import pe.com.zarita.Zara.entity.Usuario;
import pe.com.zarita.Zara.repository.DistritoRepository;
import pe.com.zarita.Zara.repository.TipoDocumentoRepository;
import pe.com.zarita.Zara.service.EmpleadoService;
import pe.com.zarita.Zara.service.UsuarioService;

@Controller
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DistritoRepository distritoRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    
    
    @GetMapping("/admin/vistaadmin")
    public String mostrarVistaAdmin(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario !=null){
            model.addAttribute("nombreusuario", usuario.getNombreusuario());
                    return "admin/vistaadmin";  // Asegúrate de que esta vista exista
        } else{
            return "redirect:/login";
        }
    }
    @GetMapping("/empleado/vistaempleado")
    public String mostrarVistaEmpleado(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario !=null){
            model.addAttribute("nombreusuario", usuario.getNombreusuario());
                    return "empleado/vistaempleado";  // Asegúrate de que esta vista exista
        } else{
            return "redirect:/login";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
    session.invalidate();  // Invalidar la sesión
    return "redirect:/login";  // Redirigir a la página de login
    }
     // Mostrar lista de empleados activos
    @GetMapping("/admin/listarempleado")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        model.addAttribute("empleados", empleados);
        return "admin/listarempleado";
    }


    // Mostrar formulario para crear o editar empleado
    @GetMapping("/admin/registraempleado")
    public String mostrarFormularioEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "admin/registraempleado";
    }
     @PostMapping("/admin/guardarusuario")
    public String guardarUsuario(@ModelAttribute Empleado empleado) {
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/admin/listarempleado";
    }
    // Guardar empleado
    @PostMapping("/admin/guardarempleado")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/admin/listarempleado";
    }

    // Deshabilitar empleado (eliminación lógica)
    @GetMapping("/empleado/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleadoLogicamente(id);
        return "redirect:/admin/listarempleado";
    }
    
    @GetMapping("/admin/habilitar")
    public String MostrarHabilitarEmpleado(Model modelo) {
        modelo.addAttribute("empleados", empleadoService.findAll());
        return "admin/habilitarempleado";
    }
    
    // Reactivar empleado
    @GetMapping("/empleado/reactivar/{id}")
    public String reactivarEmpleado(@PathVariable Long id) {
        empleadoService.reactivarEmpleado(id);
        return "redirect:/admin/habilitar";
    }
     @GetMapping("/admin/registro_empleado_usuario/{idUsuario}")
    public String mostrarRegistroEmpleado(@PathVariable Long idUsuario, Model model) {
        // Crear un nuevo objeto Cliente
        Empleado empleado = new Empleado();
        
        // Buscar el Usuario por id y manejar la posible excepción
        Usuario usuario = usuarioService.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        
        // Asociar el Usuario al Cliente
        empleado.setUsuario(usuario);
        
        // Agregar el objeto cliente al modelo
        model.addAttribute("empleado", empleado);
        
        // Agregar la lista de distritos al modelo
        model.addAttribute("distritos", distritoRepository.findAll());
        
        // Agregar la lista de tipos de documento al modelo
        model.addAttribute("tiposDocumento", tipoDocumentoRepository.findAll());
        
        // Retornar la vista para registrar al cliente
        return "admin/registro_empleado_usuario";  
    }
    
    @PostMapping("/admin/registro_empleado_usuario")
    public String registrarEmpleado(@ModelAttribute Empleado empleado) {
        // Estado del cliente activo
        empleado.setEstado(true);

        // Guardar cliente en la base de datos
        empleadoService.guardarEmpleado(empleado);

        // Redirigir a la vista correspondiente del cliente
        return "redirect:/admin/listarempleado";
    }
    
    @ModelAttribute("empleado")
    public Empleado ModeloEmpleado(){
        return new Empleado();
    }
    
    @GetMapping("/admin/actualizarempleado/{id}")
    public String MostrarActualizaEmpleado(@PathVariable Long id, Model modelo) {
        Empleado empleado = empleadoService.findById(id);

        // Verificar las fechas que se están recuperando
        System.out.println("Fecha de nacimiento: " + empleado.getFechanacimiento());
        System.out.println("Fecha de ingreso: " + empleado.getFechaingreso());

        modelo.addAttribute("empleados", empleado);
        modelo.addAttribute("distritos", distritoRepository.findAll());
        modelo.addAttribute("tiposDocumento", tipoDocumentoRepository.findAll());
        return "admin/actualizarempleado";
    }

    
    @PostMapping("/admin/actualizar/{id}")
    public String ActualizarEmpleado(@PathVariable Long id, @ModelAttribute("empleados") Empleado p) {
        p.setIdempleado(id);  // Asegúrate de que el objeto empleado tenga el ID
        empleadoService.update(p);
        return "redirect:/admin/listarempleado";
    }

}
