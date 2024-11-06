/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.zarita.Zara.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar usuario por nombre de usuario
    Optional<Usuario> findByNombreusuario(String nombreusuario);
    
    // Verificar si el nombre de usuario ya existe
    boolean existsByNombreusuario(String nombreusuario);
    
    
    Optional<Usuario> findById(Long idusuario);
}

