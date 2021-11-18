/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.controladores;

import egg.ejercicio1.libreria.entidades.Autor;
import egg.ejercicio1.libreria.entidades.Editorial;
import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.servicios.LibroServicio;
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
@RequestMapping("/libro")
public class ControladorLibro {
     
      @Autowired
    private LibroServicio libroServicio;
     
     
    @GetMapping("/")
    public String libro () {
        return "libro.html";

}
     @GetMapping("/registro")
    public String libroregistro() {
        return "libroRegistro.html";
    }
    
     @PostMapping("/registro")
    public String registrar(ModelMap msn, @RequestParam String titulo,Long isbn,@RequestParam Integer anio,Autor autor,Editorial editorial,@RequestParam Integer ejemplares ) throws ErrorServicio {
        try {
            libroServicio.guardarlibro(isbn, titulo, anio, autor, editorial, ejemplares);
       
        msn.put("perfect", "buena tilin ");
        return "libroRegistro.html";
            
        } catch (ErrorServicio e) {
            
             msn.put("mal", "si seras boludo solo un dato tenes que poner");
            return "libroRegistro.html";
        }
        

    }

    
    
}
