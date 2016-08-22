/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Persona;
import com.tecnogeek.comprameya.entidad.QUsuario;
import com.tecnogeek.comprameya.enums.Role;
import com.tecnogeek.comprameya.exceptions.DuplicateEmailException;
import com.tecnogeek.comprameya.repositories.PerfilRepository;
import com.tecnogeek.comprameya.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.tecnogeek.comprameya.repositories.UsuarioRepository;

/**
 *
 * @author genaro
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final QUsuario qUsuario = QUsuario.usuario;

    public Usuario getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return repository.findActiveUserByLogin(userName);
    }

    @Transactional
    public Usuario registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
        if (emailExist(userAccountData.getEmail())) {
            throw new DuplicateEmailException("The email address: " + userAccountData.getEmail() + " is already in use.");
        }

        String encodedPassword = encodePassword(userAccountData);

        Persona persona = new Persona();
        persona.setCorreo(userAccountData.getEmail());
        persona.setNombre(userAccountData.getFirstName());
        persona.setApellido(userAccountData.getLastName());
        persona = personaRepository.save(persona);

        Perfil perfil = perfilRepository.findByNombre(Role.USUARIO.getRoleName());

        Usuario usuario = new Usuario();
        usuario.setLogin(userAccountData.getEmail());
        usuario.setPass(encodedPassword);
        usuario.setFkPersona(persona);
        usuario.setFkPerfil(perfil);
        usuario.setRutaImagen(userAccountData.getImageUrl());

        if (userAccountData.isSocialSignIn()) {
            usuario.setSignInProvider(userAccountData.getSignInProvider());
        }

        return repository.save(usuario);
    }

    private boolean emailExist(String email) {
        Usuario user = repository.findActiveUserByLogin(email);

        if (user != null) {
            return true;
        }

        return false;
    }

    private String encodePassword(RegistrationForm dto) {
        String encodedPassword = null;

        if (dto.isNormalRegistration()) {
            encodedPassword = passwordEncoder.encode(dto.getPassword());
        }

        return encodedPassword;
    }

}
