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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author josue.zelaya
 */
@Controller
@SessionAttributes("user")
public class RegistrationController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    public RegistrationController(ConnectionFactoryLocator connectionFactoryLocator,
            UsersConnectionRepository connectionRepository) {
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
    public String updateUser() {
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/user/updatePass", method = RequestMethod.POST)
    public ResponseEntity<String> updateUserPass(@RequestParam(value = "passActual", required = true) String passActual,
            @RequestParam(value = "passNuevo", required = true) String passNuevo,
            @RequestParam(value = "passConfirmacion", required = true) String passConfirmacion) {

        Usuario u = service.getLoggedUser();

        if (!passNuevo.equals(passConfirmacion)) {
            return new ResponseEntity<>("Las contraseñas no son iguales", new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
        }

        if (!passwordEncoder.matches(passActual, u.getPass())) {
            return new ResponseEntity<>("Contraseña incorrecta", new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
        }

        String encodedPass = passwordEncoder.encode(passNuevo);
        u.setPass(encodedPass);
        service.getRepository().save(u);
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        RegistrationForm registration = createRegistrationDTO(connection);

        model.addAttribute("user", registration);

        return "registrationForm";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
            BindingResult result,
            WebRequest request) throws Exception {

        if (result.hasErrors()) {
            return "registrationForm";
        }

        Usuario registered = createUserAccount(userAccountData, result);

        if (registered == null) {
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
        } catch (DuplicateEmailException ex) {
            addFieldError(
                    "user",
                    "email",
                    userAccountData.getEmail(),
                    ex.getLocalizedMessage(),
                    result);
        }

        return registered;
    }

    private void addFieldError(String objectName, String fieldName, String fieldValue, String errorCode, BindingResult result) {
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
