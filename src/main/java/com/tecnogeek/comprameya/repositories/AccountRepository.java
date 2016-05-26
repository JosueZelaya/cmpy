/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.social.facebook.api.Account;

/**
 *
 * @author josue.zelaya
 */
public interface AccountRepository {
    
    public void createAccount(Account account);

    Account findAccountByUsername(String username);
    
}
