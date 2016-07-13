/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.service;

import com.tecnogeek.comprameya.dto.RegistrationForm;
import com.tecnogeek.comprameya.entidad.Usuario;
import com.tecnogeek.comprameya.dto.pojoUsuario;
import com.tecnogeek.comprameya.entidad.Perfil;
import com.tecnogeek.comprameya.entidad.Persona;
import com.tecnogeek.comprameya.enums.Role;
import com.tecnogeek.comprameya.exceptions.DuplicateEmailException;
import com.tecnogeek.comprameya.repositories.PerfilRepository;
import com.tecnogeek.comprameya.repositories.PersonaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.tecnogeek.comprameya.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author genaro
 */

@Service
public class UsuarioService {
    
     @Autowired
     UserRepository repository;
     
     @Autowired
     PersonaRepository personaRepository;
     
     @Autowired
     PerfilRepository perfilRepository;
     
     @Autowired
     private PasswordEncoder passwordEncoder;
     
     public Usuario getUserByLogin(String login)
     {
        return repository.findByLogin(login);
     }
     
     public Usuario getLoggedUser()
     {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return getUserByLogin(userName);
     }
     

     
     public Usuario getUsuario(long id)
     {
         return repository.findOne(id);
     }
     
    public List<pojoUsuario> getUsuarioPojo(List<Usuario> lista)
    {
        List<pojoUsuario> lpUsuario = new ArrayList<>();


        for(Usuario usr : lista)
        {
            pojoUsuario p = new pojoUsuario();
            p.setId(usr.getId());
            p.setLogin(usr.getLogin());

            lpUsuario.add(p);
        }

        return lpUsuario;
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
//        Recurso recurso = new Recurso();
//        recurso.setRuta(userAccountData.getImageUrl());
//        List<Recurso> recursoList = new ArrayList<>();        
//        recursoList.add(recurso);
//        perfil.setRecursoList(recursoList);
        
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
        Usuario user = repository.findByLogin(email);
 
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
