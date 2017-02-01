/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.config.support.FacebookApiHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author jzelaya
 */
@Controller
@RequestMapping("/facebook")
public class FacebookOperationController {

    private static final Logger logger = LoggerFactory.getLogger(FacebookOperationController.class);

    @Autowired
    protected FacebookApiHelper facebookApiHelper;

    @Autowired
    UserIdSource userIdSource;

    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    public FacebookOperationController(UsersConnectionRepository usersConnectionRepository)
    {
        this.usersConnectionRepository = usersConnectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String shareWithFacebook(WebRequest request,Model model){

        Facebook facebook = facebookApiHelper.getApi();
        Connection<Facebook> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(Facebook.class);
        return "tilesname";
    }
}
