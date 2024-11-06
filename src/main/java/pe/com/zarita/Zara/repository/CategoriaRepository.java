/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.zarita.Zara.entity.Categoria;

/**
 *
 * @author Camila Campos
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Buscar categorías por estado
    List<Categoria> findByEstado(boolean estado);
    
    // Buscar categoría por nombre
    Optional<Categoria> findByNombrecategoria(String nombrecategoria);
}