/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.utils.FileManager;
import java.io.IOException;
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
    
    @RequestMapping(value = "/getImage/{folder}/{imageId:.+}")
    @ResponseBody
    public byte[] getImage(@PathVariable String folder,@PathVariable String imageId, HttpServletRequest request){
        byte[] bytes = null;
        try {
            bytes = FileManager.getFile(folder+"/"+imageId);
        } catch (IOException ex) {            
            Logger.getLogger(ResourcesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bytes;
    }
    
    @RequestMapping(value = "/getThumbnail/{folder}/{imageId:.+}")
    @ResponseBody
    public byte[] getThumbnail(@PathVariable String folder,@PathVariable String imageId, HttpServletRequest request){
        byte[] bytes = null;
        try {
            bytes = FileManager.getFile(FileManager.getThumbnailPrefix()+folder+"/"+imageId);
        } catch (IOException ex) {            
            Logger.getLogger(ResourcesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bytes;
    }
    
}
