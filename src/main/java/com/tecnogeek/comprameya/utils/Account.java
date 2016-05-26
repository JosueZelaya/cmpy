/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.utils;

/**
 *
 * @author josue.zelaya
 */
public class Account {
    
    private final String username;

    private final String password;

    private final String firstName;

    private final String lastName;

    public Account(String username, String password, String firstName, String lastName) {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
    }

    public String getUsername() {
            return username;
    }

    public String getPassword() {
            return password;
    }

    public String getFirstName() {
            return firstName;
    }

    public String getLastName() {
            return lastName;
    }
    
}
