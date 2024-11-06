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
import pe.com.zarita.Zara.entity.Cliente;
import pe.com.zarita.Zara.entity.Usuario;
import pe.com.zarita.Zara.repository.DistritoRepository;
import pe.com.zarita.Zara.repository.TipoDocumentoRepository;
import pe.com.zarita.Zara.service.ClienteService;
import pe.com.zarita.Zara.service.UsuarioService;


@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DistritoRepository distritoRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    
    @GetMapping("/registro_cliente/{idUsuario}")
    public String mostrarRegistroCliente(@PathVariable Long idUsuario, Model model) {
        // Crear un nuevo objeto Cliente
        Cliente cliente = new Cliente();
        
        // Buscar el Usuario por id y manejar la posible excepción
        Usuario usuario = usuarioService.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        
        // Asociar el Usuario al Cliente
        cliente.setUsuario(usuario);
        
        // Agregar el objeto cliente al modelo
        model.addAttribute("cliente", cliente);
        
        // Agregar la lista de distritos al modelo
        model.addAttribute("distritos", distritoRepository.findAll());
        
        // Agregar la lista de tipos de documento al modelo
        model.addAttribute("tiposDocumento", tipoDocumentoRepository.findAll());
        
        // Retornar la vista para registrar al cliente
        return "registro_cliente";  
    }
    
    @PostMapping("/registro_cliente")
    public String registrarCliente(@ModelAttribute Cliente cliente) {
        // Estado del cliente activo
        cliente.setEstado(true);

        // Guardar cliente en la base de datos
        clienteService.guardarCliente(cliente);

        // Redirigir a la vista correspondiente del cliente
        return "redirect:/login";
    }
    
    @GetMapping("/cliente/vistacliente")
    public String mostrarVistaCliente(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario !=null){
            model.addAttribute("nombreusuario", usuario.getNombreusuario());
                    return "cliente/vistacliente";  // Asegúrate de que esta vista exista
        } else{
            return "redirect:/login";
        }
    }
     @GetMapping("/admin/listarcliente")
    public String listarEmpleados(Model model) {
        List<Cliente> clientes = clienteService.listarCliente();
        model.addAttribute("clientes", clientes);
        return "admin/listarcliente";
    }
    
    @GetMapping("/cliente/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarClienteLogicamente(id);
        return "redirect:/admin/listarcliente";
    }
    
    @GetMapping("/admin/habilitarcliente")
    public String MostrarHabilitarCliente(Model modelo) {
        modelo.addAttribute("clientes", clienteService.findAll());
        return "admin/habilitarcliente";
    }
    
    @GetMapping("/cliente/reactivarcliente/{id}")
    public String reactivarDocumento(@PathVariable Long id) {
        clienteService.reactivarCliente(id);
        return "redirect:/admin/habilitarcliente";
    }
}
