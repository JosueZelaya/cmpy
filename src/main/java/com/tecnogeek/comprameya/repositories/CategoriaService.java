/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;
import com.tecnogeek.comprameya.entidad.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author genaro
 */
public interface CategoriaService extends CrudRepository<Categoria,Long> {

    @Query("select c from Categoria c where c.fkCategoria is null")
    List<Categoria> getCategoriaPadres();
    
    List<Categoria> findByFkCategoria(Categoria categoria);
    
}
