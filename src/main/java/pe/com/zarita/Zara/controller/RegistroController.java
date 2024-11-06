/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.com.zarita.Zara.entity.Cliente;
import pe.com.zarita.Zara.entity.Rol;
import pe.com.zarita.Zara.entity.Usuario;
import pe.com.zarita.Zara.service.RolService;
import pe.com.zarita.Zara.service.UsuarioService;

/**
 *
 * @author melan
 */
@Controller
public class RegistroController {
    

     @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;
    

    @GetMapping("/registro")
    public String mostrarRegistroUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro_usuario";  
    }
    //---------------------------MOSTRAR-----------------------------------------------------------
     @GetMapping("/admin/registrarempleado")
    public String mostrarRegistroUsuarioEmpleado(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/registrarempleado";  
    }
    //--------------------------------------------------------------------------------------
   @PostMapping("/registro_usuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        // Asignar rol de cliente y estado activo
        Rol rolCliente = rolService.findByNombrerol("Cliente");
        usuario.setRol(rolCliente);
        usuario.setEstado(true);

        // Guardar el usuario en la base de datos
        usuarioService.guardarUsuario(usuario);

        // Redirigir al registro de cliente, pasando el idUsuario recién creado
        return "redirect:/registro_cliente/" + usuario.getIdusuario();
    }
    //-------------------------------ACCION DE CREAR USUARIO EMPLEADO-------------------------------------------------------
     @PostMapping("/registro_usuario_empleado")
    public String registrarUsuarioEmpleado(@ModelAttribute Usuario usuario, Model model) {
        // Asignar rol de cliente y estado activo
        Rol rolEmpleado = rolService.findByNombrerol("Empleado");
        usuario.setRol(rolEmpleado);
        usuario.setEstado(true);

        // Guardar el usuario en la base de datos
        usuarioService.guardarUsuario(usuario);

        // Redirigir al registro de Empleado, pasando el idUsuario recién creado
        return "redirect:/admin/registro_empleado_usuario/" + usuario.getIdusuario();
    }
}