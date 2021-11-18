/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.controladores;

import egg.ejercicio1.libreria.entidades.Autor;
import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/registro")
    public String autor(ModelMap modelo) {
        List<Autor> todos = autorServicio.listarTodos();

        modelo.addAttribute("autores", todos);
        return "autor.html";
    }

    @PostMapping("/registro")
    public String registrar(ModelMap msn, @RequestParam String nombre) throws ErrorServicio {
        try {
            autorServicio.registrarAutor(nombre);
            msn.put("perfect", "buena tilin ");
            List<Autor> todos = autorServicio.listarTodos();
            msn.addAttribute("autores", todos);

            return "autor.html";

        } catch (ErrorServicio e) {
            List<Autor> todos = autorServicio.listarTodos();
            msn.addAttribute("autores", todos);
            msn.put("mal", "si seras boludo solo un dato tenes que poner");
            return "autor.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap mod) {

        mod.put("autor", autorServicio.buscar(id));
        List<Autor> todos = autorServicio.listarTodos();
        mod.addAttribute("autores", todos);
        return "autor-modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, ModelMap mod) throws ErrorServicio {

        try {
            autorServicio.modificarAutor(id, nombre);
            mod.put("perfect", "ya solucionaste la cagada ");
            List<Autor> todos = autorServicio.listarTodos();
            mod.addAttribute("autores", todos);
            return "autor.html";

        } catch (ErrorServicio e) {
            List<Autor> todos = autorServicio.listarTodos();
            mod.addAttribute("autores", todos);
            mod.put("mal", "si seras boludo solo un dato tenes que poner");
            return "autor.html";
        }
    }

}
