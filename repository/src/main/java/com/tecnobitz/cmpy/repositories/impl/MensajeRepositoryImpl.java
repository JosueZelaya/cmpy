/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.tecnobitz.cmpy.entidad.Mensaje;
import com.tecnobitz.cmpy.entidad.QDestinatario;
import com.tecnobitz.cmpy.entidad.QMensaje;
import com.tecnobitz.cmpy.entidad.QUsuario;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.MensajeRepository;
import com.tecnogeek.comprameya.repositories.custom.MensajeRepositoryCustom;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class MensajeRepositoryImpl implements MensajeRepositoryCustom{
    
    @Autowired
    EntityManager em;
    
    @Autowired
    MensajeRepository mensajeRepository;
    
    private final QMensaje qMensaje = QMensaje.mensaje;
    private final QUsuario qUsuario = QUsuario.usuario;
    private final QDestinatario qDestinatario = QDestinatario.destinatario;
    
    public JPAQuery newJpaQuery() {
        return new JPAQuery(em);
    }
    
    @Override
    public Iterable<Mensaje> getMensajeUsuario(long usuario_id,Usuario usuarioLocal,int page){
        return newJpaQuery().from(qMensaje)
                .leftJoin(qMensaje.destinatarioList,qDestinatario)
                .where(
                        (qDestinatario.usuarioDestinatario.id.eq(usuario_id)
                        .and(qMensaje.usuarioEmisor.eq(usuarioLocal)))
                        .or(
                         (qDestinatario.usuarioDestinatario.eq(usuarioLocal)
                         .and(qMensaje.usuarioEmisor.id.eq(usuario_id)))
                        )
                )
                .orderBy(qMensaje.id.desc())
                .limit(20)
                .offset(page * 20)
                .list(qMensaje);
    }
    
    public Iterable<Usuario> getUsuarios(Usuario usuarioLocal){
    
        
        return newJpaQuery().from(qUsuario)
               .where(qUsuario.in
                        (
                            newJpaQuery().distinct().from(qMensaje)
                            .leftJoin(qMensaje.destinatarioList,qDestinatario)
                            .where(qDestinatario.usuarioDestinatario.eq(usuarioLocal).and(qMensaje.usuarioEmisor.ne(usuarioLocal))).list(qMensaje.usuarioEmisor)
                        )
                       .or(qUsuario.in
                        (
                            newJpaQuery().distinct().from(qMensaje)
                            .leftJoin(qMensaje.destinatarioList,qDestinatario)
                            .where(qDestinatario.usuarioDestinatario.ne(usuarioLocal).and(qMensaje.usuarioEmisor.eq(usuarioLocal))).list(qDestinatario.usuarioDestinatario)                       
                       )
                       )
               ).list(qUsuario);
              
    }
    
    public Iterable<Mensaje> getMensajeNoLeido(Usuario usuarioLocal,int page) {
          
        return newJpaQuery().from(qMensaje)
                .where(
                    qMensaje.id.in(
                            newJpaQuery().from(qMensaje)
                                .leftJoin(qMensaje.destinatarioList,qDestinatario)
                                .where(
                                qDestinatario.usuarioDestinatario.eq(usuarioLocal)
                                .and(qMensaje.visto.eq(false)))
                                .groupBy(qMensaje.usuarioEmisor).list(qMensaje.id.max())
                        )
                )
                .orderBy(qMensaje.id.desc())
                .limit(20)
                .offset(page * 20)
                .list(qMensaje);
    }
    
    public Long getMensajeUsuarioNoleidoTotal(Long usuarioId,Usuario usuarioLocal){
    
        return newJpaQuery().from(qMensaje)
                .leftJoin(qMensaje.destinatarioList,qDestinatario)
                .where(
                  qDestinatario.usuarioDestinatario.eq(usuarioLocal)
                   .and(qMensaje.usuarioEmisor.id.eq(usuarioId))
                   .and(qMensaje.visto.eq(false))
                )
                .count();
    }
    
    public Long getMensajeNoLeidoTotal(Usuario usuarioLocal){
    
        return newJpaQuery().from(qMensaje)
                .leftJoin(qMensaje.destinatarioList,qDestinatario)
                .where(
                  qDestinatario.usuarioDestinatario.eq(usuarioLocal).and(qMensaje.visto.eq(false))
                )
                .count();
    }
    
    public Iterable<Mensaje> getMensajeUsuarioNoleido(Long usuarioId,Usuario usuarioLocal){
    
        return newJpaQuery().from(qMensaje)
                .leftJoin(qMensaje.destinatarioList,qDestinatario)
                .where(
                  qDestinatario.usuarioDestinatario.eq(usuarioLocal)
                   .and(qMensaje.usuarioEmisor.id.eq(usuarioId))
                   .and(qMensaje.visto.eq(false))
                )
                .list(qMensaje);
    }
    
}
