/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.zarita.Zara.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.zarita.Zara.entity.Rol;
import pe.com.zarita.Zara.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAllCustom() {
        return rolRepository.findAllCustom();
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }

    public Rol findById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }
    
    public Rol findByNombrerol(String nombrerol){
        return rolRepository.findByNombrerol(nombrerol);
    }
}
