/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author luisc
 */
 @Controller
@RequestMapping("/libro")
public class ControladorLibro {
    @GetMapping("/")
    public String libro () {
        return "libro.html";
    
}
}
