/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.rs.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/**
 *
 * @author genaro
 */
public class InsecureHostnameVerifier implements HostnameVerifier {
     @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }   
}
