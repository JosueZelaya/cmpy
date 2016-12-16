/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories.impl;

import com.mysema.query.types.expr.BooleanExpression;
import com.tecnogeek.comprameya.entidad.PasswordResetToken;
import com.tecnogeek.comprameya.entidad.QPasswordResetToken;
import com.tecnogeek.comprameya.repositories.PasswordResetTokenRepository;
import com.tecnogeek.comprameya.repositories.custom.PasswordResetTokenRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alexander
 */
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepositoryCustom{

    @Autowired
    private PasswordResetTokenRepository repository;
    
    QPasswordResetToken qPasswordResetToken = QPasswordResetToken.passwordResetToken; 
    
    
    @Override
    public PasswordResetToken findByToken(String token) {
        BooleanExpression matchToken = qPasswordResetToken.token.eq(token);
        return repository.findOne(matchToken);
    }
    
}
