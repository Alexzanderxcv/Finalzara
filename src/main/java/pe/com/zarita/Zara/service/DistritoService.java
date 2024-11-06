/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Distrito;
import pe.com.zarita.Zara.repository.DistritoRepository;

/**
 *
 * @author Camila Campos
 */
@Service
public class DistritoService {
    @Autowired
    private DistritoRepository distritoRepository;

    // Obtener todos los distritos
    public List<Distrito> findAll() {
        return distritoRepository.findAll();
    }
    
    // Listar solo los distritos activos
    public List<Distrito> listarDistrito() {
        try {
            return distritoRepository.findByEstadoTrue();
        } catch (Exception e) {
            System.err.println("Error al listar Distritos: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }

    // Guardar un nuevo distrito
    public Distrito save(Distrito distrito) {
        return distritoRepository.save(distrito);
    }

    // Obtener distrito por su ID
    public Optional<Distrito> findById(Long id) {
        return distritoRepository.findById(id);
    }

    // Buscar distrito por nombre
    public List<Distrito> findByNombredistrito(String nombredistrito) {
        return distritoRepository.findByNombredistrito(nombredistrito);
    }

    // Actualizar un distrito
    public Distrito actualizarDistrito(Long id, Distrito nuevoDistrito) {
        return distritoRepository.findById(id).map(distrito -> {
            distrito.setNombredistrito(nuevoDistrito.getNombredistrito());
            distrito.setEstado(nuevoDistrito.isEstado());
            return distritoRepository.save(distrito);
        }).orElseThrow(() -> new IllegalArgumentException("Distrito no encontrado"));
    }

    // Deshabilitar un distrito (eliminación lógica)
   public void deshabilitarDistrito(Long id) {
    Optional<Distrito> distritoOpt = distritoRepository.findById(id);
    if (distritoOpt.isPresent()) {
        Distrito distrito = distritoOpt.get();
        distrito.setEstado(false);
        distritoRepository.save(distrito);
    } else {
        throw new IllegalArgumentException("Distrito no encontrado para deshabilitar");
    }
}
   


   // Listar solo los distritos deshabilitados
public List<Distrito> getDeshabilitarDistrito() {
    try {
        return distritoRepository.findByEstadoFalse(); // Asegúrate de tener este método en tu repositorio
    } catch (Exception e) {
        System.err.println("Error al listar distritos deshabilitados: " + e.getMessage());
        return new ArrayList<>();
    }
}

    

    // Reactivar un distrito
    public void reactivarDistrito(Long id) {
        distritoRepository.findById(id).ifPresent(distrito -> {
            distrito.setEstado(true);
            distritoRepository.save(distrito);
        });
    }
}