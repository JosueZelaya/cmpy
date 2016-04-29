/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.interfaces;

import org.springframework.social.facebook.api.Account;

/**
 *
 * @author josue.zelaya
 */
public interface AccountRepository {
    
    void createAccount(Account account);

    Account findAccountByUsername(String username);
    
}
