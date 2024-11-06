/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Empleado;
import pe.com.zarita.Zara.entity.Usuario;
import pe.com.zarita.Zara.repository.EmpleadoRepository;

/**
 *
 * @author melan
*/
@Service
public class EmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
     
     public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }
     
     public Empleado findById(Long id){
        return empleadoRepository.findById(id).get();
    }
    // Obtener todos los clientes
    public List<Empleado> findAllCustom() {
        return empleadoRepository.findAllCustom();
    }

    public List<Empleado> listarEmpleados() {
        try {
            return empleadoRepository.findByEstadoTrue();
        } catch (Exception e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }


    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminarEmpleadoLogicamente(Long id) {
        Empleado empleado = obtenerEmpleadoPorId(id);
        if (empleado != null) {
            empleado.setEstado(false);
            empleadoRepository.save(empleado);
        }
    }


    public void reactivarEmpleado(Long id) {
        Empleado empleado = obtenerEmpleadoPorId(id);
        if (empleado != null) {
            empleado.setEstado(true);
            empleadoRepository.save(empleado);
        }
    }
    public Empleado update(Empleado p) {
        if (p.getIdempleado() == null) {
            throw new IllegalArgumentException("ID del empleado no puede ser null");
        }

        Empleado objempleado = empleadoRepository.findById(p.getIdempleado())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + p.getIdempleado()));

        // Copiar las propiedades del objeto actualizado al objeto existente
        BeanUtils.copyProperties(p, objempleado, "idempleado"); // Excluimos el campo ID para no modificarlo

        return empleadoRepository.save(objempleado);
    }

    
    
    
}
