/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.NotificacionUsuario;
import com.tecnogeek.comprameya.entidad.Usuario;
import java.util.List;

/**
 *
 * @author alexander
 */
public interface NotificacionUsuarioRepositoryCustom {
    public List<NotificacionUsuario> getNotificaciones(Usuario usuario);
    public List<NotificacionUsuario> findBy(String link, String mensaje);
}
