/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.dto.Message;
import com.tecnogeek.comprameya.dto.OutputMessage;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author alexander
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;
    
    @MessageMapping("/greetings")
    @SendTo("/topic/greetings")
    public OutputMessage greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new OutputMessage(message, new Date());
    }
    
    @RequestMapping(path="/greetings", method=POST)
    public void greet(String greeting) {
        String text = "Hello, " + greeting + "!";
        this.template.convertAndSend("/topic/greetings", text);
    }

}
