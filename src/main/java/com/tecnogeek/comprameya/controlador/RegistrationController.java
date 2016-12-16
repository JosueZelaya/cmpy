/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnogeek.comprameya.entidad.PasswordResetToken;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author josue.zelaya
 */
@Controller
@SessionAttributes("user")
@Slf4j
public class RegistrationController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

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
        
        log.info("{} ha cambiado su password", u.getLogin());
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        RegistrationForm registration = createRegistrationDTO(connection);

        model.addAttribute("user", registration);

        log.info("Se muestra formulario de registro");
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
            log.info("Se muestra formulario de registro");
            return "registrationForm";
        }

        SecurityUtil.logInUser(registered);
        providerSignInUtils.doPostSignUp(registered.getLogin(), request);

        log.info("Nuevo usuario creado: {} ", registered.getLogin());
        return "redirect:/";

    }

    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> resetPassword(
            HttpServletRequest request, @RequestParam("email") String userEmail) {

        Usuario user = service.getRepository().findActiveUserByLogin(userEmail);
        if (user == null) {
            return new ResponseEntity<>("Usuario no encontrado", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        String token = UUID.randomUUID().toString();
        service.createPasswordResetTokenForUser(user, token);
        String appUrl
                = "https://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getContextPath();

        SimpleMailMessage email = constructResetTokenEmail(appUrl, request.getLocale(), token, user);
        mailSender.send(email);

        log.info("{} desea resetear su password y se le ha enviado un email con un token para que pueda hacerlo.", user.getLogin());
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(
            Locale locale, Model model, @RequestParam("id") long id, @RequestParam("token") String token) {

        PasswordResetToken passToken = service.getPasswordResetToken(token);
        
        if(passToken == null){
            model.addAttribute("mensaje", "El token no es valido");
            return "invalidToken";
        }
        
        Usuario user = passToken.getUser();
        if (user.getId() != id) {
            model.addAttribute("mensaje", "El token no es valido");
            return "invalidToken";
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("mensaje", "El token ha expirado");
            return "invalidToken";
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getPerfil().getRole().getRoleName());
        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(authority);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, authoritiesList);
        SecurityContextHolder.getContext().setAuthentication(auth);

        log.info("{} pretende resetear su password", user.getLogin());
        return "redirect:/#/update_pass";
    }

    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public ResponseEntity<String> savePassword(Locale locale, @RequestParam("password") String password) {
        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = service.getRepository().findActiveUserByLogin(user.getLogin());
        String encodedPass = passwordEncoder.encode(password);
        user.setPass(encodedPass);
        try{
            service.getRepository().save(user);        
        }catch(Exception e){
            log.error(e.getMessage());
        }
        
        log.info("{} ha reseteado su password", user.getLogin());        
        return ResponseEntity.ok("ok");
    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, Usuario user) {
        String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        String message = "Cambio de Contraseña";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getLogin());
        email.setSubject("Reset Password");
        email.setText(message + ": " + url);
        email.setFrom(env.getProperty("support.email"));
        return email;
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
