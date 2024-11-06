/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.zarita.Zara.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author melan
 */
@Entity
@Table(name = "distrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Distrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddistrito;
    
    private String nombredistrito;
    
    private boolean estado;
}
