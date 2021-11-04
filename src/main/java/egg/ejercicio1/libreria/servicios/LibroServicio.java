/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private boolean alta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;



 */
package egg.ejercicio1.libreria.servicios;

import egg.ejercicio1.libreria.entidades.Autor;
import egg.ejercicio1.libreria.entidades.Editorial;
import egg.ejercicio1.libreria.entidades.Libro;
import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.repositores.LibroReposit;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    @Autowired
    private LibroReposit libroReposit;
    @Transactional
    private void registrarLibro (Long isbn,String titulo,Integer anio,Autor autor,Editorial editorial) throws ErrorServicio{
        
         if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("el titulo no puede ser nulo");
        }
        if (anio == null ) {
            throw new ErrorServicio("el año no puede ser nulo");
        }
        if (isbn == null ) {
            throw new ErrorServicio("el isbn no puede ser nulo");
        }
        if (autor == null ) {
            throw new ErrorServicio("el autor no puede ser nulo");
        }
        if (editorial == null ) {
            throw new ErrorServicio("el editorial no puede ser nulo");
        }
            Libro libro =new Libro();
            libro.setAlta(true);
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libroReposit.save(libro);
        
    }
}
