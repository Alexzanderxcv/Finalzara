/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.zarita.Zara.entity.DetalleVenta;
import pe.com.zarita.Zara.entity.Venta;

/**
 *
 * @author Camila Campos
 */
@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    // Buscar detalles de venta por id de venta
    List<DetalleVenta> findByVenta(Venta venta);
}