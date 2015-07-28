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
    public byte[] getImage(@PathVariable String imageId, HttpServletRequest request) throws IOException      {
        System.out.println("serving image: "+imageId);
        String userDir = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");                
//      userDir = "file:/"+userDir+fileSeparator+"src"+fileSeparator+"images"+fileSeparator+imageId;
        userDir = userDir+fileSeparator+"src"+fileSeparator+"images"+fileSeparator+imageId;
        Path path = Paths.get(userDir);
        byte[] bytes = Files.readAllBytes(path); 
        return bytes;
    }
    
}
