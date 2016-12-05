/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.dto;

import com.tecnogeek.comprameya.cmpyannotations.PasswordsNotEmpty;
import com.tecnogeek.comprameya.cmpyannotations.PasswordsNotEqual;
import com.tecnogeek.comprameya.enums.SocialMediaService;
import java.io.Serializable;
import java.math.BigInteger;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author josue.zelaya
 */

@Getter
@Setter
@RequiredArgsConstructor 

@PasswordsNotEmpty(
        triggerFieldName = "signInProvider",
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)
@PasswordsNotEqual(
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)
public class RegistrationForm implements Serializable{

        private static final long serialVersionUID = 1L;
    
        @Email
        @NotEmpty
        @Size(max = 100)
        private String email;
        
        @NotEmpty
        @Size(max = 100)
        private String firstName;
        
        @NotEmpty
        @Size(max = 100)
        private String lastName;

        private String password;

        private String passwordVerification;

        private SocialMediaService signInProvider;
        
        private String imageUrl;
        
        private MultipartFile image;
        
        private BigInteger telephone;

        //Constructor is omitted for the of clarity.

        public boolean isNormalRegistration() {
            return signInProvider == null;
        }

        public boolean isSocialSignIn() {
            return signInProvider != null;
        }
        
}