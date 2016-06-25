/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.interfaces;

import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.exceptions.DuplicateEmailException;

/**
 *
 * @author alexander
 */
public interface UsuarioServiceInterface {
    
    public Usuario registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
    
}
