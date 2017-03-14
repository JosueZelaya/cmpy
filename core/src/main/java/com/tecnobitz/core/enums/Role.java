/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnobitz.core.enums;

/**
 *
 * @author josue.zelaya
 */
public enum Role {
    
    USUARIO("ROLE_USER"),
    ADMINISTRADOR("ROLE_ADMIN");
    
    String roleName;
    
    private Role(final String roleName){
        this.roleName = roleName;
    }
    
    public String getRoleName(){
        return this.roleName;
    }
    
    public static Role getRole(final String roleName){
        Role ret = null;
        for(Role activeEnum : values()){
            if(activeEnum.getRoleName().equals(roleName)){
                ret = activeEnum;
                break;
            }
        }
        return ret;
    }
    
}
