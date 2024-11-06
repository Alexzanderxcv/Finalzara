/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.zarita.Zara.entity.Marca;

/**
 *
 * @author Camila Campos
 */
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    // Buscar marcas por estado
    List<Marca> findByEstado(boolean estado);
    
    // Buscar marca por nombre
    Optional<Marca> findByNombremarca(String nombremarca);
}