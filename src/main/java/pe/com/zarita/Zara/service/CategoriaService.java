/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Categoria;
import pe.com.zarita.Zara.repository.CategoriaRepository;

/**
 *
 * @author Camila Campos
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    // Guardar una nueva categoría
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Buscar categoría por nombre
    public Optional<Categoria> findByNombrecategoria(String nombrecategoria) {
        return categoriaRepository.findByNombrecategoria(nombrecategoria);
    }
    
    
    public List<Categoria> obtenerTodasLasCategorias() {
        List<Categoria> categoria = categoriaRepository.findAll();
        System.out.println("Cantidad de categoria recuperados: " + categoria.size());

        // Imprimir información de cada producto
        categoria.forEach(producto
                -> System.out.println("Categoria: " + producto.getNombrecategoria()
                        + ", Imagen: " + producto.getImagen()));

        return categoria;
    }
}