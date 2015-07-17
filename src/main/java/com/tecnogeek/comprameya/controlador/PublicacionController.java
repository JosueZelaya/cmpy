/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.controlador;

import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.TipoPublicacion;
import com.tecnogeek.comprameya.utils.FileManager;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jzelaya
 */
@Controller("/publicacion")
public class PublicacionController {
 
//    @Autowired
//    PublicacionDAO publicacionDAO;
    
    @RequestMapping(value = "/agregarAnuncio", method = RequestMethod.POST)    
    public String agregarAnuncio(@RequestParam("titulo") String titulo,
                                 @RequestParam("descripcion") String descripcion,
                                 @RequestParam(value = "multipleFiles", required = false) List<MultipartFile> files,
                                 Model model,HttpServletRequest req)
    {           
        String userDir = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        userDir += fileSeparator+"src"+fileSeparator+"images"+fileSeparator;        
        Path directory = Paths.get(userDir);        
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(descripcion);
        publicacion.setDescripcion(descripcion);
        List<Recurso> recursos = new ArrayList();
        for (MultipartFile multipartFile : files ){
            String fileName = FileManager.saveFile(multipartFile, directory);
            Recurso recurso = new Recurso();
            recurso.setNombre(fileName);
            recurso.setRuta(userDir+fileName);
            recurso.setFkPublicacion(publicacion);
            recursos.add(recurso);
            System.out.println("cargado: "+userDir+fileName);
        }
        publicacion.setRecursoList(recursos);
        Integer tipoPublicacion=1;
        publicacion.setFkTipoPublicacion(new TipoPublicacion(tipoPublicacion.longValue()));
//        publicacionDAO.save(publicacion);
        
        return new HomeController().welcomePage(model);        
    }
    
}
