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
import pe.com.zarita.Zara.entity.TipoDocumento;
import pe.com.zarita.Zara.repository.TipoDocumentoRepository;

/**
 *
 * @author Camila Campos
 */
@Service
public class TipoDocumentoService {
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    // Obtener todos los tipos de documento
    public List<TipoDocumento> findAll() {
        return tipoDocumentoRepository.findAll();
    }

    // Guardar un nuevo tipo de documento
    public TipoDocumento save(TipoDocumento tipoDocumento) {
        return tipoDocumentoRepository.save(tipoDocumento);
    }
    public List<TipoDocumento> listarDistrito() {
        try {
            return tipoDocumentoRepository.findByEstadoTrue();
        } catch (Exception e) {
            System.err.println("Error al listar Distritos: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
    // Obtener tipo de documento por su ID
   public TipoDocumento findById(Long id){
        return tipoDocumentoRepository.findById(id).get();
    }
    
    public TipoDocumento obtenerEmpleadoPorId(Long id) {
        return tipoDocumentoRepository.findById(id).orElse(null);
    }
    
    // Buscar tipos de documento por estado
    public List<TipoDocumento> findByEstado(boolean estado) {
        return tipoDocumentoRepository.findByEstado(estado);
    }

    // Buscar tipos de documento por descripción
    public List<TipoDocumento> findByDescripcion(String descripcion) {
        return tipoDocumentoRepository.findByDescripcion(descripcion);
    }
    
    public void eliminarDocumentoLogicamente(Long id) {
        TipoDocumento documento = obtenerEmpleadoPorId(id);
        if (documento != null) {
            documento.setEstado(false);
            tipoDocumentoRepository.save(documento);
        }
    }


    public void reactivarDocumento(Long id) {
        TipoDocumento documento = obtenerEmpleadoPorId(id);
        if (documento != null) {
            documento.setEstado(true);
            tipoDocumentoRepository.save(documento);
        }
    }
    public TipoDocumento update(TipoDocumento p) {
        if (p.getIdtipodocumento()== null) {
            throw new IllegalArgumentException("ID del empleado no puede ser null");
        }

        TipoDocumento objempleado = tipoDocumentoRepository.findById(p.getIdtipodocumento())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + p.getIdtipodocumento()));

        // Copiar las propiedades del objeto actualizado al objeto existente
        BeanUtils.copyProperties(p, objempleado, "idempleado"); // Excluimos el campo ID para no modificarlo

        return tipoDocumentoRepository.save(objempleado);
    }

}