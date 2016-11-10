/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.tecnogeek.comprameya.entidad.Mensaje;
import com.tecnogeek.comprameya.entidad.QDestinatario;
import com.tecnogeek.comprameya.entidad.QMensaje;
import com.tecnogeek.comprameya.entidad.QUsuario;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.repositories.MensajeRepository;
import com.tecnogeek.comprameya.repositories.custom.MensajeRepositoryCustom;
import java.util.List;
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
    public Iterable<Mensaje> getMensajeUsuario(long usuario_id,Usuario usuarioLocal){
        return newJpaQuery().from(qMensaje)
                .leftJoin(qMensaje.destinatarioList,qDestinatario)
                .where(
                        (qDestinatario.usuarioDestinatario.id.eq(usuario_id)
                        .and(qMensaje.usuarioEmisor.eq(usuarioLocal)))
                        .or(
                         (qDestinatario.usuarioDestinatario.eq(usuarioLocal)
                         .and(qMensaje.usuarioEmisor.id.eq(usuario_id)))
                        )
                ).list(qMensaje);
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
}
