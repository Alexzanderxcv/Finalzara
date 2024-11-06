/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Categoria;
import pe.com.zarita.Zara.entity.Marca;
import pe.com.zarita.Zara.entity.Producto;
import pe.com.zarita.Zara.repository.ProductoRepository;
/**
 *
 * @author Camila Campos
 */
@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> findAllCustom() {
    return productoRepository.findAllCustom();
    }
    
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
  
    // Obtener por su ID
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }
    
    // Eliminar por su ID
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
    
    // Buscar por nombre
    public Optional<Producto> findByNombreproducto(String nombreproducto) {
        return productoRepository.findByNombreproducto(nombreproducto);
    }
    
    // Buscar productos que contengan una palabra en su descripción
    public List<Producto> findByDescripcionContaining(String keyword) {
        return productoRepository.findByDescripcionContaining(keyword);
    }
    // Buscar productos por marca
    public List<Producto> findByIdmarca(Marca marca) {
        return productoRepository.findByIdmarca(marca);
    }
    
    public List<Producto> findByIdcategoria(Categoria categoria) {
        return productoRepository.findByIdcategoria(categoria);
    }
    
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        System.out.println("Cantidad de productos recuperados: " + productos.size());

        // Imprimir información de cada producto
        productos.forEach(producto
                -> System.out.println("Producto: " + producto.getNombreproducto()
                        + ", Imagen: " + producto.getImagenproducto()));

        return productos;
    }
    
    public BigDecimal obtenerPrecioProducto(Long idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return producto.getPrecio();  // Asumiendo que `Producto` tiene un campo `precio`
    }

    public Producto obtenerProductoPorId(Long idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

}
