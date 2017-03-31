/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnobitz.cmpy.entidad.PasswordResetToken;
import com.tecnobitz.cmpy.entidad.Persona;
import com.tecnobitz.cmpy.entidad.Usuario;
import com.tecnobitz.cmpy.enums.SocialMediaService;
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
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    
    @RequestMapping(value = "/user/getProfile", method = RequestMethod.GET)
    public @ResponseBody Usuario getProfile(Model model)  {
         Usuario usuario = service.getLoggedUser();
         return usuario; 
    }
    
    @ResponseBody
    @RequestMapping(value = "/user/updateCell", method = RequestMethod.POST)
    public ResponseEntity<String> updateCell(
            @RequestParam(value = "newCell", required = true) BigInteger newCell){
        Usuario u = service.getLoggedUser();
        Persona p = u.getPersona();
        p.setCelular(newCell);
        p.setTelefono(newCell);        
        service.getRepository().save(u);
        return ResponseEntity.ok("ok");
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/user/updateProfile", method = RequestMethod.POST)
    public ResponseEntity<String> updateProfile
    (
        @RequestParam(value = "nombre", required = true) String nombre,
        @RequestParam(value = "apellido", required = true) String apellido,
        @RequestParam(value = "telefono", required = true) String telefono,
        @RequestParam(value = "sexo", required = true) boolean sexo,
        @RequestParam(value = "dia", required = true) int dia,
        @RequestParam(value = "mes", required = true) int mes,
        @RequestParam(value = "anio", required = true) int anio
    ) throws ParseException 
    {
        Usuario u = service.getLoggedUser();
        Persona p = u.getPersona();
        
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setTelefono(BigInteger.valueOf(Long.parseLong(telefono)));
        
        p.setGenero(sexo);
         
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(anio);
        Date date = formatter.parse(dateInString);
        p.setFechaNacimiento(date);
        
        service.getRepository().save(u);
        
        log.info("{} se actualizo el perfil", u.getLogin());
        return ResponseEntity.ok("ok");
    }    

    @ResponseBody
    @RequestMapping(value = "/user/updatePass", method = RequestMethod.POST)
    public ResponseEntity<String> updateUserPass(@RequestParam(value = "passActual", required = true) String passActual,
            @RequestParam(value = "passNuevo", required = true) String passNuevo,
            @RequestParam(value = "passConfirmacion", required = true) String passConfirmacion) {

        Usuario u = service.getLoggedUser();
        
        if(passNuevo.equals("")){
            log.info("{} ha intentado asignar un password vacío", u.getLogin());
            return new ResponseEntity<>("Asigne un password", new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
        }

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
    
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPageSignin(WebRequest request, Model model) {
        log.info("Se registra por medio de facebook");
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        RegistrationForm userAccountData = createRegistrationDTO(connection);
        
        try{
            registerAndLoggedUser(userAccountData, request);
        } catch (DuplicateEmailException ex) {                   
            model.addAttribute("user", userAccountData);
            model.addAttribute("emailErrorMsg", ex.getMessage());
            log.info(ex.getMessage());
            return "duplicateEmail";
        }
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String redirectRequestToRegistrationPage(WebRequest request, Model model) {
        log.info("Se registra por medio de facebook");
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        RegistrationForm userAccountData = createRegistrationDTO(connection);
        
        try{
            registerAndLoggedUser(userAccountData, request);
        } catch (DuplicateEmailException ex) {
            model.addAttribute("user", userAccountData);
            model.addAttribute("emailErrorMsg", ex.getMessage());
            log.info(ex.getMessage());
            return "duplicateEmail";
        }
        
        return "redirect:/";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
            BindingResult result,
            WebRequest request) {
        
        if(result.hasErrors()){
            return "registrationForm";
        }
        
        try{
            registerAndLoggedUser(userAccountData, request);
        } catch (DuplicateEmailException ex) {
            addFieldError(
                    "user",
                    "email",
                    userAccountData.getEmail(),
                    ex.getLocalizedMessage(),
                    result);
            return "registrationForm";
        }
        
        return "redirect:/";

    }
    
    private Usuario registerAndLoggedUser(RegistrationForm userAccountData,
            WebRequest request) throws DuplicateEmailException{
        
        Usuario registered =  service.registerNewUserAccount(userAccountData);

        SecurityUtil.logInUser(registered);
        providerSignInUtils.doPostSignUp(registered.getLogin(), request);
        
        log.info("Nuevo usuario creado: {} ", registered.getLogin());
        return registered;
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
    public ResponseEntity<String> savePassword(Locale locale, 
                                                @RequestParam("password") String password, 
                                                WebRequest request) {
        
        Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = service.getRepository().findActiveUserByLogin(user.getLogin());
        
        if(password.equals("")){
            log.info("{} ha intentado asignar un password vacío", user.getLogin());
            return new ResponseEntity<>("Asigne un password", new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
        }
        
        String encodedPass = passwordEncoder.encode(password);
        user.setPass(encodedPass);
        try{
            service.getRepository().save(user);        
        }catch(Exception e){
            log.error(e.getMessage());
        }
        
        SecurityUtil.logInUser(user);
        providerSignInUtils.doPostSignUp(user.getLogin(), request);        
        
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
            ConnectionKey providerKey = connection.getKey();
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            dto.setEmail(socialMediaProfile.getEmail());
            dto.setProfileUrl(connection.getProfileUrl());
            dto.setFirstName(socialMediaProfile.getFirstName());
            dto.setLastName(socialMediaProfile.getLastName());            
            dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
            dto.setImageUrl(connection.getImageUrl());
        }

        return dto;
    }

    private boolean isSocialUser(ConnectionKey providerKey){
        return SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase())!=null;
    }
}
