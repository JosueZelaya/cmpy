/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnogeek.comprameya.enums.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import com.tecnogeek.comprameya.repositories.UserRepository;

/**
 *
 * @author josue.zelaya
 */

@Controller
@SessionAttributes("user")
public class RegistrationController {
    
    @Autowired
    private UserRepository service;
    
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    public RegistrationController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository) {        
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request,Model model){
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        
        RegistrationForm registration = createRegistrationDTO(connection);
        if(registration!=null){
            model.addAttribute("user", registration);
        }else{
            model.addAttribute("user", "anonymous");
        }        
 
        return "registrationForm";
    }
    
    private RegistrationForm createRegistrationDTO(Connection<?> connection) {
        RegistrationForm dto = new RegistrationForm();
 
        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            dto.setEmail(socialMediaProfile.getEmail());
            dto.setFirstName(socialMediaProfile.getFirstName());
            dto.setLastName(socialMediaProfile.getLastName());
 
            ConnectionKey providerKey = connection.getKey();
            dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
        }
 
        return dto;
    }
    
}
