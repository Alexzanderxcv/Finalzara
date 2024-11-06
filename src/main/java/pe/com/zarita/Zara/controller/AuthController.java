/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.zarita.Zara.entity.Usuario;
import pe.com.zarita.Zara.service.UsuarioService;


@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    
@GetMapping("/")
public String MostrarVista(Model model) {
    model.addAttribute("usuario", new Usuario());
    return "login"; // O redirigir a "/login"
}
   @GetMapping("/login")
public String MostrarIndex(Model model) {
    model.addAttribute("usuario", new Usuario());
    return "login"; // O redirigir a "/login"
}


    @PostMapping("/login")
    public String login(@RequestParam String nombreusuario,
                        @RequestParam String contrasenia,
                        HttpSession session, Model model) {
        boolean isValid = usuarioService.validarCredenciales(nombreusuario, contrasenia);

        if (!isValid) {
            model.addAttribute("error", "Credenciales incorrectas");
            model.addAttribute("usuario", new Usuario());
            return "login";
        }

        Optional<Usuario> usuarioOpt = usuarioService.findByNombreusuario(nombreusuario);
        Usuario usuario = usuarioOpt.get();
        session.setAttribute("usuario", usuario);

        Long idRol = usuario.getRol().getIdrol();
        String rol = usuario.getRol().getNombrerol();

        switch (rol) {
            case "Admin":
                return "redirect:/admin/vistaadmin";
            case "Cliente":
                return "redirect:/cliente/vistacliente";
            case "Empleado":
                return "redirect:/empleado/vistaempleado";
            default:
                return "redirect:/login";
        }
    }

}

//**
   // @GetMapping("/usuario")
    //public ResponseEntity<?> getUsuario(HttpSession session) {
      //  Usuario usuario = (Usuario) session.getAttribute("usuario");

        //if (usuario != null) {
            //return ResponseEntity.ok(usuario);
        //77} else {
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay usuario logueado");
     //   }
    //}



