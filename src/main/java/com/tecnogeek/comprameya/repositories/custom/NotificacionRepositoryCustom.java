/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.custom;

import com.tecnogeek.comprameya.entidad.Notificacion;
import com.tecnogeek.comprameya.entidad.Usuario;

/**
 *
 * @author alexander
 */
public interface NotificacionRepositoryCustom {
    public void informarASuscriptores(Notificacion notifiacion, Usuario emisor);
}
