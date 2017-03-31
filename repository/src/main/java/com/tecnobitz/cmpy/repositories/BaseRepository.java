/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories;

import com.tecnobitz.cmpy.entidad.BaseEntity;
import java.io.Serializable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author jzelaya
 * @param <T>
 * @param <ID>
 */

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>,ID extends Serializable> 
        extends PagingAndSortingRepository<T, ID>,
        QueryDslPredicateExecutor<T>, 
        Serializable {    
}
