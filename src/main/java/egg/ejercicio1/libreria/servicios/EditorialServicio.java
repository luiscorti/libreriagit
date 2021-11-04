/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.servicios;

import egg.ejercicio1.libreria.entidades.Editorial;
import egg.ejercicio1.libreria.exepcion.ErrorServicio;
import egg.ejercicio1.libreria.repositores.EditorialReposit;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialReposit editorialReposit;
@Transactional
    public void registrarEditorial(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("el nombre no puede ser nulo");
        }

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);

        editorialReposit.save(editorial);

    }
@Transactional
    public void eliminarEditorial(String id) throws ErrorServicio {

        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("el id no puede ser nulo");
        }

        Optional<Editorial> resultado = editorialReposit.findById(id);
        if (resultado.isPresent()) {
            Editorial editorial = resultado.get();
            editorialReposit.delete(editorial);
        } else {
            throw new ErrorServicio("no existe esa editorial");
        }
    }
@Transactional
    public void modificarEditorial(String id, String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {

            throw new ErrorServicio("el nombre no puede ser nulo");
        }
        Optional<Editorial> resultado = editorialReposit.findById(id);
        if (resultado.isPresent()) {
            Editorial editorial = resultado.get();
            editorial.setNombre(nombre);
            editorialReposit.save(editorial);
        } else {
            throw new ErrorServicio("no existe esa editorial");
        }

    }
@Transactional
    public void darbaja(String id) throws ErrorServicio {

        Optional<Editorial> resultado = editorialReposit.findById(id);
        if (resultado.isPresent()) {
            Editorial editorial = resultado.get();
            editorial.setAlta(Boolean.FALSE);
            editorialReposit.save(editorial);
        } else {
            throw new ErrorServicio("no existe esa editorial");
        }

    }
@Transactional
    public void daralta(String id) throws ErrorServicio {

        Optional<Editorial> resultado = editorialReposit.findById(id);
        if (resultado.isPresent()) {
            Editorial editorial = resultado.get();
            editorial.setAlta(Boolean.TRUE);
            editorialReposit.save(editorial);
        } else {
            throw new ErrorServicio("no existe esa editorial");
        }

    }
}
