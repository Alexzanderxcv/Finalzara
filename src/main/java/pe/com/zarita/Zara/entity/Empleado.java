/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author melan
 */
@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idempleado;
    
    private String nombreempleado;
    private String apellidopaterno;
    private String apellidomaterno;
    private String telefono;
    private String direccion;
    private LocalDate fechanacimiento;
    private String sexo;
    private String correo;
    private LocalDate fechaingreso;
    private boolean estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idtipodocumento")
    private TipoDocumento idtipodocumento;
    @ManyToOne
    @JoinColumn(name = "iddistrito")
    private Distrito iddistrito;
}
