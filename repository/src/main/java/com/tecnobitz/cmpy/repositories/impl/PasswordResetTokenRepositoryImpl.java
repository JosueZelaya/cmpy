/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.cmpy.repositories.impl;

import com.mysema.query.types.expr.BooleanExpression;
import com.tecnobitz.cmpy.entidad.PasswordResetToken;
import com.tecnobitz.cmpy.entidad.QPasswordResetToken;
import com.tecnobitz.cmpy.repositories.PasswordResetTokenRepository;
import com.tecnobitz.cmpy.repositories.custom.PasswordResetTokenRepositoryCustom;
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
