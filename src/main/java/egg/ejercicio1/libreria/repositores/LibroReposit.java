/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.repositores;

import egg.ejercicio1.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

@Repository
public interface LibroReposit extends JpaRepository<Libro, String>{
   @Query("SELECT c FROM Libro c WHERE c.isbn = :isbn")
    public Libro buscarporisbn(@Param("isbn") Long isbn);
    
 @Query("SELECT c FROM Libro c WHERE c.titulo = :titulo")
    public Libro buscarportitulo(@Param("titulo") String titulo);

    
    
}
