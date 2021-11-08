
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
    private void registrarLibro(Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorServicio {

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
        Libro libro = new Libro();
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

    public void validarLibro(Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorServicio {

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

        validarLibro(isbn, titulo, anio, autor, editorial, ejemplares, ejemplaresPrestados, ejemplaresRestantes);

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
