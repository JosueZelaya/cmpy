/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.impl;

import com.mysema.query.types.expr.BooleanExpression;
import com.tecnobitz.cmpy.entidad.Destinatario;
import com.tecnobitz.cmpy.entidad.QDestinatario;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.repositories.DestinatarioRepository;
import com.tecnobitz.cmpy.repositories.custom.DestinatarioRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class DestinatarioRepositoryImpl implements DestinatarioRepositoryCustom{
    
    @Autowired
     DestinatarioRepository destinatarioRepository;
     
     private final QDestinatario qDestinatario = QDestinatario.destinatario;
     
     @Override
     public Iterable<Destinatario> getDestinarioUsuario(Usuario destinatario, Usuario emisor)
     {
         BooleanExpression byDestinatario = qDestinatario.usuarioDestinatario.eq(destinatario);
         BooleanExpression byEmisor = qDestinatario.mensaje.usuarioEmisor.eq(emisor);
         return destinatarioRepository.findAll(byDestinatario.and(byEmisor));
     }
     
     @Override
     public Iterable<Destinatario> getDestinario(Usuario usuario)
     {
         BooleanExpression byDestinatario = qDestinatario.usuarioDestinatario.eq(usuario);
         BooleanExpression byEmisor = qDestinatario.mensaje.usuarioEmisor.eq(usuario);
         return destinatarioRepository.findAll(byDestinatario.or(byEmisor));
     }
    
}
