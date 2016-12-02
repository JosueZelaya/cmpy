/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.rs.client;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;



/**
 *
 * @author genaro
 */
public class GenericClient {
    
    public static String getRequest(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException{
        
        SSLContext sc = SSLContext.getInstance("SSL");


        TrustManager[] trustAllCerts = { new InsecureTrustManager() };
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
         
        Client client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();
        
            //Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);
            InputStream is = target.request(MediaType.APPLICATION_XML).get(InputStream.class);
            String theString = IOUtils.toString(is);
            
            return theString;
    }
    
}
