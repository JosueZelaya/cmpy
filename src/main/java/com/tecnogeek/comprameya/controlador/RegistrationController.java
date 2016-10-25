/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.enums.SocialMediaService;
import com.tecnogeek.comprameya.exceptions.DuplicateEmailException;
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
import com.tecnogeek.comprameya.utils.SecurityUtil;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.tecnogeek.comprameya.service.UsuarioService;
import com.tecnogeek.comprameya.utils.FileManager;

/**
 *
 * @author josue.zelaya
 */

@Controller
@SessionAttributes("user")
public class RegistrationController {
    
    @Autowired
    private UsuarioService service;
    
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
        
        model.addAttribute("user", registration);
 
        return "registrationForm";
    }
    
    @RequestMapping(value ="/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
                                      BindingResult result,
                                      WebRequest request) throws Exception{
        
        if(result.hasErrors()){
            return "registrationForm";
        }
        
        String rutaImg = FileManager.saveFile(userAccountData.getImage());
        userAccountData.setImageUrl(rutaImg);
        Usuario registered = createUserAccount(userAccountData,result);
        
        if(registered==null){
            return "registrationForm";
        }                         
        
        SecurityUtil.logInUser(registered);
        providerSignInUtils.doPostSignUp(registered.getLogin(), request);
 
        return "redirect:/";
        
    }
    
    private Usuario createUserAccount(RegistrationForm userAccountData, BindingResult result) {
        Usuario registered = null;
 
        try {
            registered = service.registerNewUserAccount(userAccountData);
        }
        catch (DuplicateEmailException ex) {
            addFieldError(
                    "user",
                    "email",
                    userAccountData.getEmail(),
                    "NotExist.user.email",
                    result);
        }
 
        return registered;
    }
    
    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );
 
        result.addError(error);
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
            dto.setImageUrl(connection.getImageUrl());
        }
 
        return dto;
    }
    
}
