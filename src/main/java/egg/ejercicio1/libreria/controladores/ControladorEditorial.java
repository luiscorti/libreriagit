/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.controladores;

import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author luisc
 */
    @Controller
@RequestMapping("/editorial")
public class ControladorEditorial {
        
        @Autowired
        private EditorialServicio editorialServicio;
        
        
    @GetMapping("/registro")
    public String editorial () {
        return "editorial.html";
    
}
      @PostMapping("/registro")
    public String registrar(ModelMap msn, @RequestParam String nombre) throws ErrorServicio {
        try {
            editorialServicio.registrarEditorial(nombre);
        msn.put("perfect", "buena tilin ");
          return "editorial.html";
            
        } catch (ErrorServicio e) {
            
             msn.put("mal", "si seras boludo solo un dato tenes que poner");
              return "editorial.html";
        }
        

    }

    
    
    
    
    
    
}
