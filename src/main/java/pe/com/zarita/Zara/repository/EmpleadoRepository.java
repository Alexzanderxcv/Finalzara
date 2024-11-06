/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.zarita.Zara.entity.Empleado;
import pe.com.zarita.Zara.entity.Usuario;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
    
    // Método personalizado para recuperar todos los empleados
    @Query("SELECT e FROM Empleado e WHERE e.estado = true")
    List<Empleado> findAllCustom();
    
    // Encontrar todos los empleados activos
    List<Empleado> findByEstadoTrue();

    // Encontrar todos los empleados inactivos
    List<Empleado> findByEstadoFalse();
    
    // Buscar empleados por nombre
    List<Empleado> findByNombreempleadoContaining(String nombre);
    
    
}