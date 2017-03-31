/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.tecnobitz.cmpy.entidad.NotificacionUsuario;
import com.tecnobitz.cmpy.entidad.QNotificacion;
import com.tecnobitz.cmpy.entidad.QNotificacionUsuario;
import com.tecnobitz.cmpy.entidad.QUsuario;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.enums.TipoNotificacionEnum;
import com.tecnogeek.comprameya.repositories.custom.NotificacionUsuarioRepositoryCustom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class NotificacionUsuarioRepositoryImpl implements NotificacionUsuarioRepositoryCustom {

    @Autowired
    EntityManager em;
    
    QUsuario qUsuario = QUsuario.usuario;
    QNotificacion qNotificacion = QNotificacion.notificacion;
    QNotificacionUsuario qNotificacionUsuario = QNotificacionUsuario.notificacionUsuario;
    
    public JPAQuery newJpaQuery() {
        return new JPAQuery(em);
    }

    @Override
    public List<NotificacionUsuario> getNotificaciones(Usuario usuario) {
        List<NotificacionUsuario> notificacionesFiltradas = new ArrayList<>();
        Map<String, NotificacionUsuario> notiMap = new HashMap<>();
        
        BooleanExpression belongsToUser = qNotificacionUsuario.usuario.id.eq(usuario.getId());
        BooleanExpression notSeen = qNotificacionUsuario.visto.eq(false);   
        BooleanExpression tipoComentario = qNotificacionUsuario.notificacion.tipo.id.eq(TipoNotificacionEnum.COMENTARIO.getCodigo());
        
        List<NotificacionUsuario> notificaciones = newJpaQuery().distinct()
                .from(qNotificacionUsuario)
                .where(belongsToUser.and(notSeen).and(tipoComentario))
                .orderBy(qNotificacionUsuario.id.desc())
                .list(qNotificacionUsuario);        
        
        for(NotificacionUsuario notiUsuario : notificaciones){
            String key = notiUsuario.getNotificacion().getLink();
            key += notiUsuario.getNotificacion().getMensaje();                    
            notiMap.put(key, notiUsuario);
        }
        
        for(Entry<String, NotificacionUsuario> entry : notiMap.entrySet()){
            notificacionesFiltradas.add(entry.getValue());
        }
        
        return notificacionesFiltradas;
    }
    
    @Override
    public List<NotificacionUsuario> getTodasNotificaciones(Usuario usuario) {
        List<NotificacionUsuario> notificacionesFiltradas = new ArrayList<>();
        Map<String, NotificacionUsuario> notiMap = new LinkedHashMap<>();
        
        BooleanExpression belongsToUser = qNotificacionUsuario.usuario.id.eq(usuario.getId());
        BooleanExpression tipoComentario = qNotificacionUsuario.notificacion.tipo.id.eq(TipoNotificacionEnum.COMENTARIO.getCodigo());
        
        List<NotificacionUsuario> notificaciones = newJpaQuery().distinct()
                .from(qNotificacionUsuario)
                .where(belongsToUser.and(tipoComentario))
                .orderBy(qNotificacionUsuario.creationTime.desc())
                .list(qNotificacionUsuario);        
        
        for(NotificacionUsuario notiUsuario : notificaciones){
            String key = notiUsuario.getNotificacion().getLink();
            key += notiUsuario.getNotificacion().getMensaje();
            key += notiUsuario.isVisto();
            notiMap.put(key, notiUsuario);
        }
        
        for(Entry<String, NotificacionUsuario> entry : notiMap.entrySet()){
            notificacionesFiltradas.add(entry.getValue());
        }
        
        return notificacionesFiltradas;
    }

    @Override
    public List<NotificacionUsuario> findBy(String link, String mensaje) {
        BooleanExpression matchLink = qNotificacionUsuario.notificacion.link.eq(link);
        BooleanExpression matchMensaje = qNotificacionUsuario.notificacion.mensaje.eq(mensaje);
        BooleanExpression notSeen = qNotificacionUsuario.visto.eq(false);
        return newJpaQuery().distinct()
                .from(qNotificacionUsuario)
                .where(matchLink.and(matchMensaje).and(notSeen))
                .orderBy(qNotificacionUsuario.id.desc())                
                .list(qNotificacionUsuario); 
    }
    
}
