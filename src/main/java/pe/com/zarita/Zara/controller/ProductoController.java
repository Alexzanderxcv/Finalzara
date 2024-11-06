/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.com.zarita.Zara.entity.Categoria;
import pe.com.zarita.Zara.entity.Marca;
import pe.com.zarita.Zara.entity.Producto;
import pe.com.zarita.Zara.service.CategoriaService;
import pe.com.zarita.Zara.service.MarcaService;
import pe.com.zarita.Zara.service.ProductoService;

/**
 *
 * @author melan
 */

@Controller
public class ProductoController {
       
     @Autowired
    private ProductoService productoService;
      @Autowired
    private CategoriaService categoriaService;
       @Autowired
    private MarcaService marcaService;
     
   @GetMapping("/cliente/listarproducto") 
    public String obtenerTodosLosProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "cliente/listarproducto"; // Nombre de la plantilla Thymeleaf
    }
    
     @GetMapping("/cliente/listarcategoria") 
    public String obtenerTodasLasCategorias(Model model) {
        List<Categoria> categoria = categoriaService.obtenerTodasLasCategorias();
        model.addAttribute("categorias", categoria);
        return "cliente/listarcategoria"; // Nombre de la plantilla Thymeleaf
    }
    
     @GetMapping("/cliente/listarmarca") 
    public String obtenerTodasLasMarcas(Model model) {
        List<Marca> marca = marcaService.obtenerTodasLasMarcas();
        model.addAttribute("marcas", marca);
        return "cliente/listarmarca"; // Nombre de la plantilla Thymeleaf
    }
}
