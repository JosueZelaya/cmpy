/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.core.entidad;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author alexander
 */
@Entity
@Table(name = "userconnection")
@Getter
@Setter
public class UserConnection {
       
    @EmbeddedId
    private UserConnectionPk userConnectionPk;
    
    private String displayName;
    private String profileUrl;
    private String imageUrl;
    @NotNull
    private String accessToken;
    private String secret;
    private String refreshToken;
    private Long expireTime;
    @NotNull
    private Integer rank;
}

@Embeddable
@Getter
@Setter
class UserConnectionPk implements Serializable{
    
    private String userid;

    private String providerId;
    
    private String providerUserId;

}