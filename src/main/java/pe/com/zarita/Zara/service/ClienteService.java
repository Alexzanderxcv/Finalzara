/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Cliente;
import pe.com.zarita.Zara.entity.Usuario;
import pe.com.zarita.Zara.repository.ClienteRepository;

/**
 *
 * @author Camila Campos
 */
@Service
public class ClienteService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public List<Cliente> findAllCustom() {
        return clienteRepository.findAll();
    }
     public List<Cliente> listarCliente() {
        try {
            return clienteRepository.findByEstadoTrue();
        } catch (Exception e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
    // Guardar un nuevo cliente
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Obtener cliente por ID
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    // Buscar clientes por nombre
    public List<Cliente> findByNombrecliente(String nombrecliente) {
        return clienteRepository.findByNombrecliente(nombrecliente);
    }

    // Buscar clientes por apellido paterno
    public List<Cliente> findByApellidopaterno(String apellidopaterno) {
        return clienteRepository.findByApellidopaterno(apellidopaterno);
    }

    // Buscar clientes por apellido materno
    public List<Cliente> findByApellidomaterno(String apellidomaterno) {
        return clienteRepository.findByApellidomaterno(apellidomaterno);
    }
     public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    // Buscar cliente por correo
    public Optional<Cliente> findByCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }

    // Buscar clientes por teléfono
    public List<Cliente> findByTelefono(String telefono) {
        return clienteRepository.findByTelefono(telefono);
    }
   // Guardar un nuevo cliente con un nuevo usuario
    public Cliente registrarCliente(Cliente cliente, Usuario usuario) {
        // Registrar primero al usuario
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        
        // Asignar el idusuario del nuevo usuario al cliente
        cliente.setUsuario(nuevoUsuario); // Asignamos el objeto Usuario en lugar del ID
        cliente.setEstado(true); // Estado activo por defecto

        // Guardar cliente
        return clienteRepository.save(cliente);
    }
    
    public void eliminarClienteLogicamente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        if (cliente != null) {
            cliente.setEstado(false);
            clienteRepository.save(cliente);
        }
    }
    
     public void reactivarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        if (cliente != null) {
            cliente.setEstado(true);
            clienteRepository.save(cliente);
        }
    }
}
