/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.com.zarita.Zara.entity.DetalleVenta;
import pe.com.zarita.Zara.service.DetalleVentaService;

/**
 *
 * @author melan
 */
@Controller
public class DetalleVentaController {
    
    @Autowired
    private DetalleVentaService detalleVenta;
    
    @GetMapping("/empleado/detalleventa")
    public String listarDetalleVenta(Model model) {
        List<DetalleVenta> detalle = detalleVenta.listarDetalleVenta();
        model.addAttribute("detalles", detalle);
        return "empleado/detalleventa";
    }
}
