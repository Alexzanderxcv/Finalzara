/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Marca;
import pe.com.zarita.Zara.repository.MarcaRepository;

/**
 *
 * @author Camila Campos
 */
@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    // Obtener todas las marcas
    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    // Guardar una nueva marca
    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    // Obtener marca por su ID
    public Optional<Marca> findById(Long id) {
        return marcaRepository.findById(id);
    }

    // Buscar marca por nombre
    public Optional<Marca> findByNombremarca(String nombremarca) {
        return marcaRepository.findByNombremarca(nombremarca);
    }
    

     
    public List<Marca> obtenerTodasLasMarcas() {
        List<Marca> categoria = marcaRepository.findAll();
        System.out.println("Cantidad de Marca recuperados: " + categoria.size());

        // Imprimir información de cada producto
        categoria.forEach(producto
                -> System.out.println("Marca: " + producto.getNombremarca()
                        + ", Imagen: " + producto.getImagen()));

        return categoria;
    }
}

