/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.zarita.Zara.entity.Distrito;

/**
 *
 * @author Camila Campos
 */
public interface DistritoRepository extends JpaRepository<Distrito, Long> {
    // Buscar distritos por nombre
    List<Distrito> findByNombredistrito(String nombredistrito);
    
     // Encontrar todos los Distrito activos
    List<Distrito> findByEstadoTrue();

    // Encontrar todos los Distrito inactivos
    List<Distrito> findByEstadoFalse();
}
