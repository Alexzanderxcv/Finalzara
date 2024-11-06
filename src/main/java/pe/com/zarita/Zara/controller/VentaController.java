/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.com.zarita.Zara.entity.DetalleVenta;
import pe.com.zarita.Zara.entity.Venta;
import pe.com.zarita.Zara.service.ClienteService;
import pe.com.zarita.Zara.service.EmpleadoService;
import pe.com.zarita.Zara.service.ProductoService;
import pe.com.zarita.Zara.service.VentaService;

/**
 *
 * @author melan
 */

@Controller
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/empleado/registroventa")
    public String mostrarFormularioVenta(Model model) {
        Venta venta = new Venta();
        model.addAttribute("venta", venta);

        // Añade la lista de empleados y clientes para los select en el formulario
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("clientes", clienteService.listarCliente());
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());

        return "empleado/registroventa";
    }
    
   @PostMapping("/guardar")
    public String registrarVenta(@ModelAttribute Venta venta,
                                 @RequestParam List<Integer> cantidades,
                                 @RequestParam List<Long> idsProductos) {
        List<DetalleVenta> detalles = new ArrayList<>();

        for (int i = 0; i < idsProductos.size(); i++) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCantidad(cantidades.get(i));
            detalle.setPreciounidad(productoService.obtenerPrecioProducto(idsProductos.get(i)));
            detalle.setProducto(productoService.obtenerProductoPorId(idsProductos.get(i)));
            detalles.add(detalle);
        }

        ventaService.registrarVenta(venta, detalles);
        return "redirect:/empleado/registroventa";
    }
    
    @GetMapping("/empleado/ventatotal")
    public String listarVentaTotal(Model model) {
        List<Venta> venta = ventaService.listarVenta();
        model.addAttribute("ventas", venta);
        return "empleado/ventatotal";
    }
}
