/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.DetalleVenta;
import pe.com.zarita.Zara.entity.Venta;
import pe.com.zarita.Zara.repository.DetalleVentaRepository;

/**
 *
 * @author Camila Campos
 */
@Service
public class DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    // Obtener todos los detalles de venta
    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    // Guardar un nuevo detalle de venta
    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    // Obtener detalle de venta por su ID
    public Optional<DetalleVenta> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    // Buscar detalles de venta por ID de venta
    public List<DetalleVenta> findByIdVenta(Venta venta) {
        return detalleVentaRepository.findByVenta(venta);
    }
    
    public List<DetalleVenta> listarDetalleVenta() {
        try {
            return detalleVentaRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error al listar detalle de ventas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}


