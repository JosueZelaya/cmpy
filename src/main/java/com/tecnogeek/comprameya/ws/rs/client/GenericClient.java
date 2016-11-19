/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.ws.rs.client;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author genaro
 */
public class GenericClient {
    
    public static String getRequest(String url) throws IOException{
                    
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);
            InputStream is = target.request(MediaType.APPLICATION_XML).get(InputStream.class);
            String theString = IOUtils.toString(is);
            
            return theString;
    }
    
}
