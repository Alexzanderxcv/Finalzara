/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.zarita.Zara.entity.TipoDocumento;

/**
 *
 * @author Camila Campos
 */
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
    
    // Buscar tipo de documento por estado
    List<TipoDocumento> findByEstado(boolean estado);
    
    List<TipoDocumento> findByEstadoTrue();
    // Buscar tipo de documento por descripción
    List<TipoDocumento> findByDescripcion(String descripcion);
}