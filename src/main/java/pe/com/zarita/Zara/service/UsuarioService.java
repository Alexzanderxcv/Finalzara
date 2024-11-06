/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import pe.com.zarita.Zara.entity.Rol;
import pe.com.zarita.Zara.entity.Usuario;
import pe.com.zarita.Zara.repository.RolRepository;
import pe.com.zarita.Zara.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByNombreusuario(String nombreusuario) {
        return usuarioRepository.findByNombreusuario(nombreusuario);
    }
    public Optional<Usuario> findById(Long idusuario){
        return usuarioRepository.findById(idusuario);
    }
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean validarCredenciales(String nombreusuario, String contrasenia) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreusuario(nombreusuario);
        // Aquí está la corrección: getContrasenia()
            return usuarioOpt.isPresent() && 
             usuarioOpt.get().getContrasenia().equals(contrasenia);
    }
    
    public Usuario registrarUsuario(Usuario usuario) {
        // Obtener el rol 'Cliente' y asignarlo al usuario
        Rol rolCliente = rolRepository.findByNombrerol("Cliente");
        usuario.setRol(rolCliente);
        usuario.setEstado(true); // Estado activo por defecto
        return usuarioRepository.save(usuario);
    }
    
    public Usuario registrarUsuarioEmpleado(Usuario usuario) {
        // Obtener el rol 'Cliente' y asignarlo al usuario
        Rol rolEmpleado = rolRepository.findByNombrerol("Empleado");
        usuario.setRol(rolEmpleado);
        usuario.setEstado(true); // Estado activo por defecto
        return usuarioRepository.save(usuario);
    }
}
