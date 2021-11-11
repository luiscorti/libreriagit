/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.controladores;

import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author luisc
 */
  @Controller
@RequestMapping("/autor")
public class ControladorAutor {
      
      
      @Autowired
      private AutorServicio autorServicio;
      
    @GetMapping("/")
    public String autor () {
        return "autor.html";
}
    
    @PostMapping("/registro")
    public String registrar (@RequestParam String nombre) throws ErrorServicio  {
         System.out.println("siiiiiii");
        autorServicio.registrarAutor(nombre);
       
         return "autor.html";
        
}
    
    
    
    
}
