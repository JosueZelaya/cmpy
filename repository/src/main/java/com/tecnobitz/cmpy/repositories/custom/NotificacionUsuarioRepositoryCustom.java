/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.custom;

import com.tecnobitz.cmpy.entidad.NotificacionUsuario;
import com.tecnobitz.cmpy.entidad.Usuario;
import java.util.List;

/**
 *
 * @author alexander
 */
public interface NotificacionUsuarioRepositoryCustom {
    public List<NotificacionUsuario> getNotificaciones(Usuario usuario);
    public List<NotificacionUsuario> getTodasNotificaciones(Usuario usuario);
    public List<NotificacionUsuario> findBy(String link, String mensaje);
}
