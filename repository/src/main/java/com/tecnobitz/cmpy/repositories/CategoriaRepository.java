/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories;
import com.tecnobitz.cmpy.entidad.Categoria;
import com.tecnobitz.cmpy.repositories.custom.CategoriaRepositoryCustom;
/**
 *
 * @author genaro
 */
public interface CategoriaRepository extends 
        BaseRepository<Categoria, Long>, CategoriaRepositoryCustom {}
