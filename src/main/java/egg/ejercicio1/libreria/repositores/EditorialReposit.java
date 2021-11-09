/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.ejercicio1.libreria.repositores;


import egg.ejercicio1.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface EditorialReposit extends JpaRepository<Editorial, String>{
     @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
    public Editorial buscarporNombre(@Param("nombre") String nombre);
    
    
}
