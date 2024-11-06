/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.zarita.Zara.entity.Cliente;

/**
 *
 * @author Camila Campos
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Buscar cliente por nombre
    List<Cliente> findByNombrecliente(String nombrecliente);
    
    // Buscar clientes por apellido paterno
    List<Cliente> findByApellidopaterno(String apellidopaterno);
    
    // Buscar clientes por apellido materno
    List<Cliente> findByApellidomaterno(String apellidomaterno);
    
    // Buscar clientes por estado
    List<Cliente> findByEstadoTrue();

    
    // Buscar cliente por correo
    Optional<Cliente> findByCorreo(String correo);
    
    // Buscar clientes por teléfono
    List<Cliente> findByTelefono(String telefono);
}