/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.controladores;

import egg.ejercicio1.libreria.entidades.Editorial;
import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.servicios.EditorialServicio;
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
@RequestMapping("/editorial")
public class ControladorEditorial {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registro")
    public String editorial(ModelMap mod) {
        List<Editorial> todos = editorialServicio.listarTodos();
        mod.addAttribute("editoriales", todos);
        return "editorial.html";

    }

    @PostMapping("/registro")
    public String registrar(ModelMap msn, @RequestParam String nombre) throws ErrorServicio {
        try {

            editorialServicio.registrarEditorial(nombre);
            msn.put("perfect", "buena tilin ");
            List<Editorial> todos = editorialServicio.listarTodos();
            msn.addAttribute("editoriales", todos);
            return "editorial.html";

        } catch (ErrorServicio e) {
            
            
            List<Editorial> todos = editorialServicio.listarTodos();
            msn.addAttribute("editoriales", todos);

            msn.put("mal", "si seras boludo solo un dato tenes que poner");
            return "editorial.html";
        }

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap mod) {

        mod.put("editorial", editorialServicio.buscar(id));
        List<Editorial> todos = editorialServicio.listarTodos();
        mod.addAttribute("editoriales", todos);

        return "editorial-modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, ModelMap mod) throws ErrorServicio {

        try {
            editorialServicio.modificarEditorial(id, nombre);
             mod.put("perfect", "buena tilin ");
            List<Editorial> todos = editorialServicio.listarTodos();
            mod.addAttribute("editoriales", todos);
            return "editorial.html";


        } catch (ErrorServicio e) {
            List<Editorial> todos = editorialServicio.listarTodos();
            mod.addAttribute("editoriales", todos);
            mod.put("mal", "si seras boludo solo un dato tenes que poner");
            return "autor.html";
        }
    }

}
