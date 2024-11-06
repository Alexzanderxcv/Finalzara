/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Cliente;
import pe.com.zarita.Zara.entity.DetalleVenta;
import pe.com.zarita.Zara.entity.Empleado;
import pe.com.zarita.Zara.entity.Venta;
import pe.com.zarita.Zara.repository.DetalleVentaRepository;
import pe.com.zarita.Zara.repository.VentaRepository;

/**
 *
 * @author Camila Campos
 */
@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private DetalleVentaRepository detalleRepository;
    
     public List<Venta> listarVenta() {
        try {
            return ventaRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error al listar detalle de ventas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    // Obtener todas las ventas
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    // Guardar una nueva venta
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    // Obtener venta por su ID
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    // Eliminar venta por ESTADO
    public void disableByEstado(boolean estado) {
    List<Venta> ventas = ventaRepository.findByEstado(estado);
    for (Venta venta : ventas) {
        venta.setEstado(false);
        ventaRepository.save(venta); // Guarda los cambios en la bd
    }
}

    // Buscar ventas por estado
    public List<Venta> findByEstado(boolean estado) {
        return ventaRepository.findByEstado(estado);
    }

    // Buscar ventas por cliente
    public List<Venta> findByIdcliente(Cliente cliente) {
        return ventaRepository.findByIdcliente(cliente);
    }

    // Buscar ventas por empleado
    public List<Venta> findByIdempleado(Empleado Empleado) {
        return ventaRepository.findByIdempleado(Empleado);
    }
    
    public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        venta.setEstado(true);
        venta.setFecha(new Date());

        BigDecimal total = detalles.stream()
                .map(detalle -> detalle.getPreciounidad().multiply(BigDecimal.valueOf(detalle.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        venta.setTotal(total);

        Venta ventaGuardada = ventaRepository.save(venta);

        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(ventaGuardada);
            detalleRepository.save(detalle);
        }
        return ventaGuardada;
    }

}