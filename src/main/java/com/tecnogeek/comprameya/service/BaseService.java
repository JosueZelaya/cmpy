/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.entidad.BaseEntity;
import com.tecnogeek.comprameya.repositories.BaseRepository;
import java.io.Serializable;

/**
 *
 * @author jzelaya
 * @param <T>
 * @param <ID>
 */
public abstract class BaseService <T extends BaseEntity<ID>,  ID extends Serializable> {
    
    /**
     *
     * @return
     */
    public abstract BaseRepository<T, ID> getRepository();
    
}
