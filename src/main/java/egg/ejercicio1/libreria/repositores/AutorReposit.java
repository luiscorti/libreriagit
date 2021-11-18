/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.repositores;

import egg.ejercicio1.libreria.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorReposit extends JpaRepository<Autor, String> {
    
    @Query("SELECT c FROM Autor c WHERE c.nombre = :nombre")
    public Autor buscarporNombre(@Param("nombre") String nombre);
    
    @Query("SELECT a from Autor a WHERE a.alta = true ")
	public List<Autor> buscarActivos();
    
}
