/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.controlador.PublicacionController;
import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnogeek.comprameya.entidad.PasswordResetToken;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Persona;
import com.tecnogeek.comprameya.entidad.QUsuario;
import com.tecnogeek.comprameya.enums.Role;
import com.tecnogeek.comprameya.exceptions.DuplicateEmailException;
import com.tecnogeek.comprameya.repositories.PasswordResetTokenRepository;
import com.tecnogeek.comprameya.repositories.PerfilRepository;
import com.tecnogeek.comprameya.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;
import com.tecnogeek.comprameya.utils.FileManager;
import java.io.IOException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author genaro
 */
@Service
@Slf4j
public class UsuarioService {

    @Getter
    @Autowired
    UsuarioRepository repository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    private final QUsuario qUsuario = QUsuario.usuario;

    public Usuario getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return repository.findActiveUserByLogin(userName);
    }

    private void setImage(RegistrationForm userAccountData, Usuario usuario){
        try {            
            
            if( !userAccountData.isSocialSignIn() ){
                
                if(userAccountData.getImage() != null){
                    String rutaImg = FileManager.saveFile(userAccountData.getImage());
                    userAccountData.setImageUrl(rutaImg);
                }else{
                    userAccountData.setImageUrl(null);
                }
            }else{
                usuario.setRutaImagen(userAccountData.getImageUrl());
            }                        
        } catch (IOException ex) {
            log.error(PublicacionController.class.getName(), "No se pudo cargar imagen", ex);
        }
    }
    
    @Transactional
    public Usuario registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
        String correo = "";
        String login = "";
        
        if(userAccountData.isSocialSignIn()) {
            if(userAccountData.getEmail()!=null){
                correo = userAccountData.getEmail();
            } else {
                correo = userAccountData.getProfileUrl();
            }
            login = userAccountData.getProfileUrl();
        } else {
            correo = userAccountData.getEmail();
            login = correo;
        }
        
        if(correo == null) {
            throw new DuplicateEmailException("Para completar el proceso de registro por favor agregue su dirección de correo");
        }
        
        if (userExists(login)) {
            throw new DuplicateEmailException("El correo: " + userAccountData.getEmail() + " ya está siendo usado. Por favor use otro para completar el registro.");
        }

        String encodedPassword = encodePassword(userAccountData);

        Persona persona = new Persona();
        persona.setCorreo(correo);
        persona.setNombre(userAccountData.getFirstName());
        persona.setApellido(userAccountData.getLastName());
        persona.setTelefono(userAccountData.getTelephone());
        persona = personaRepository.save(persona);

        Perfil perfil = perfilRepository.findByNombre(Role.USUARIO.getRoleName());

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setPass(encodedPassword);
        usuario.setPersona(persona);
        usuario.setPerfil(perfil);
        
        setImage(userAccountData, usuario);

        if (userAccountData.isSocialSignIn()) {
            usuario.setSignInProvider(userAccountData.getSignInProvider());
        }

        return repository.save(usuario);
    }

    private boolean userExists(String email) {
        
        if(email == null){
            return false;
        }
        
        Usuario user = repository.findActiveUserByLogin(email);

        return user != null;
    }

    private String encodePassword(RegistrationForm dto) {
        String encodedPassword = null;

        if (dto.isNormalRegistration()) {
            encodedPassword = passwordEncoder.encode(dto.getPassword());
        }

        return encodedPassword;
    }
    
    public void createPasswordResetTokenForUser(final Usuario user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }    
    
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

}
