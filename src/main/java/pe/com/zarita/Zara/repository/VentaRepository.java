/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.zarita.Zara.entity.Cliente;
import pe.com.zarita.Zara.entity.Empleado;
import pe.com.zarita.Zara.entity.Venta;

/**
 *
 * @author Camila Campos
 */
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    
    // Método encontrar ventas por estado
    List<Venta> findByEstado(boolean estado);
    
    // Buscar ventas por cliente
    List<Venta> findByIdcliente(Cliente cliente);
    
    // Buscar ventas por empleado
    List<Venta> findByIdempleado(Empleado empleado);
}