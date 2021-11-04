/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.servicios;

import egg.ejercicio1.libreria.entidades.Autor;
import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.repositores.AutorReposit;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    public AutorReposit autorReposit;
    @Transactional
    public void registrarAutor(String nombre) throws ErrorServicio {
        
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("el nombre no puede ser nulo");
        }
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(Boolean.TRUE);
        autorReposit.save(autor);
    }
    @Transactional
    public void eliminarAutor(String id) throws ErrorServicio {
        
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("el nombre no puede ser nulo");
        }
        
        Optional<Autor> resultado = autorReposit.findById(id);
        if (resultado.isPresent()) {
            Autor autor = resultado.get();
            autorReposit.delete(autor);
        } else {
            throw new ErrorServicio("no existe ese autor");            
        }
        
    }
    @Transactional
    public void modificarAutor(String id, String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("el nombre no puede ser nulo");
        }
        
        Optional<Autor> resultado = autorReposit.findById(id);
        if (resultado.isPresent()) {
            Autor autor = resultado.get();
            autor.setNombre(nombre);
            autorReposit.save(autor);
        } else {
            throw new ErrorServicio("no existe ese autor");            
        }
    }
    @Transactional
    public void darbaja(String id) throws ErrorServicio {
       
        Optional<Autor> resultado = autorReposit.findById(id);
        if (resultado.isPresent()) {
            Autor autor =resultado.get();
            autor.setAlta(Boolean.FALSE);
            autorReposit.save(autor);
        } else {
            throw new ErrorServicio("no existe ese autor");            
        }
    }
    @Transactional
     public void darAlta(String ID) throws ErrorServicio {
       
       
        Optional<Autor> resultado = autorReposit.findById(ID);
        if (resultado.isPresent()) {
            Autor autor =resultado.get();
            autor.setAlta(Boolean.TRUE);
            autorReposit.save(autor);
        } else {
            throw new ErrorServicio("no existe ese autor");            
        }
    }
}
