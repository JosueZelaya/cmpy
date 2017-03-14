/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnobitz.core.entidad.Recurso;

/**
 *
 * @author alexander
 */
public interface RecursoRepositoryCustom {
    public Iterable<Recurso> getRecursos(Long publicacionId, int page, int itemsByPage);
}
