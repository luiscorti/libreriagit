/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.servicios;

import egg.ejercicio1.libreria.entidades.Autor;
import egg.ejercicio1.libreria.entidades.Editorial;
import egg.ejercicio1.libreria.entidades.Libro;
import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.repositores.LibroReposit;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroReposit libroReposit;
    
    @Transactional
    public void guardarlibro(Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, Integer ejemplares) throws ErrorServicio {
        
        validarLibro(isbn, titulo, anio, autor, editorial, ejemplares);
        
        Libro libro = new Libro();
        libro.setAlta(true);
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setEjemplaresPrestados(0);
        libroReposit.save(libro);
        
    }
    
    public void validarLibro(Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, Integer ejemplares) throws ErrorServicio {
        
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("el titulo no puede ser nulo");
        }
        if (anio == null) {
            throw new ErrorServicio("el año no puede ser nulo");
        }
        if (isbn == null) {
            throw new ErrorServicio("el isbn no puede ser nulo");
        }
        if (autor == null) {
            throw new ErrorServicio("el autor no puede ser nulo");
        }
        if (editorial == null) {
            throw new ErrorServicio("el editorial no puede ser nulo");
        }
        if (ejemplares == null || ejemplares < 0) {
            throw new ErrorServicio("la cantidad de ejemplares no puede ser nulo");
        }
        
    }
    
    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorServicio {
        
        validarLibro(isbn, titulo, anio, autor, editorial, ejemplares);
        
        Optional<Libro> resultado = libroReposit.findById(id);
        if (resultado.isPresent()) {
            
            Libro libro = resultado.get();
            libro.setAlta(true);
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libroReposit.save(libro);
        }
    }

    @Transactional
    public void prestarLibro(String id) throws ErrorServicio {
        
        Optional<Libro> resultado = libroReposit.findById(id);
        if (resultado.isPresent()) {
            
            Libro libro = resultado.get();
            
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
            
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
            libroReposit.save(libro);
        }
    }
     @Transactional
    public void devolverLibro(String id) throws ErrorServicio {
        
        Optional<Libro> resultado = libroReposit.findById(id);
        if (resultado.isPresent()) {
            
            Libro libro = resultado.get();
            
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
            
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
            libroReposit.save(libro);
        }
    }

    @Transactional
    public void dardeBaja(String id) {
        Optional<Libro> resultado = libroReposit.findById(id);
        if (resultado.isPresent()) {
            Libro libro = resultado.get();
            libro.setAlta(false);
            libroReposit.save(libro);
        }
        
    }
    
    @Transactional
    public void dardeAlta(String id) {
        Optional<Libro> resultado = libroReposit.findById(id);
        if (resultado.isPresent()) {
            Libro libro = resultado.get();
            libro.setAlta(true);
            libroReposit.save(libro);
        }
        
    }
}
