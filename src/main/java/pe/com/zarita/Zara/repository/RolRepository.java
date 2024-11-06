/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.zarita.Zara.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    // Consulta personalizada para obtener solo los roles activos
    @Query("SELECT r FROM Rol r WHERE r.estado = true")
    List<Rol> findAllCustom();
    
    Rol findByNombrerol(String nombrerol); 
}

