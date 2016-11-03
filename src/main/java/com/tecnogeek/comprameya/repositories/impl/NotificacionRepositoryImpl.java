/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.tecnogeek.comprameya.entidad.Notificacion;
import com.tecnogeek.comprameya.entidad.QNotificacion;
import com.tecnogeek.comprameya.entidad.QUsuario;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.custom.NotificacionRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class NotificacionRepositoryImpl implements NotificacionRepositoryCustom{
    
    @Autowired
    EntityManager em;
    
    QUsuario qUsuario = QUsuario.usuario;
    QNotificacion qNotificacion = QNotificacion.notificacion;
    
    public JPAQuery newJpaQuery() {
        return new JPAQuery(em);
    }

    @Override
    public List<Notificacion> getNotificaciones(Usuario usuario) {
        return null;
    }
    
    
}
