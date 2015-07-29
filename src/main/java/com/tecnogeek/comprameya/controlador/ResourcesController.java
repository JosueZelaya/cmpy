/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jzelaya
 */
@Controller
@RequestMapping("/images")
public class ResourcesController {
    
    @RequestMapping(value = "/getImage/{imageId:.+}")
    @ResponseBody
    public byte[] getImage(@PathVariable String imageId, HttpServletRequest request){
        byte[] bytes=null;
        try {
            System.out.println("serving image: "+imageId);
            String userDir = System.getProperty("user.home");                
            String fileSeparator = System.getProperty("file.separator");
//      userDir = "file:/"+userDir+fileSeparator+"src"+fileSeparator+"images"+fileSeparator+imageId;
            userDir = userDir+fileSeparator+"src"+fileSeparator+"images"+fileSeparator+imageId;
            Path path = Paths.get(userDir);
             bytes = Files.readAllBytes(path);
            
        } catch (IOException ex) {            
            Logger.getLogger(ResourcesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bytes;
    }
    
}
