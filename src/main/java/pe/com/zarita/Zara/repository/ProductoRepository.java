/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.zarita.Zara.entity.Producto;
import org.springframework.stereotype.Repository;
import pe.com.zarita.Zara.entity.Categoria;
import pe.com.zarita.Zara.entity.Marca;

/**
 *
 * @author Camila Campos
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
   // Buscar producto por nombre
    Optional<Producto> findByNombreproducto(String nombreproducto);
    // Método personalizado para recuperar todos los productos
    @Query("SELECT p FROM Producto p WHERE p.estado = true")
    List<Producto> findAllCustom();
    // Verificar si el nombre del producto ya existe
    boolean existsByNombreproducto(String nombreproducto);

    // Buscar productos por estado (disponible o no)
    List<Producto> findByEstado(boolean estado);

    // Buscar productos por categoría
    List<Producto> findByIdcategoria(Categoria categoria);

    // Buscar productos por marca
    List<Producto> findByIdmarca(Marca marca);
    
    List<Producto> findByDescripcionContaining(String keyword);

}
