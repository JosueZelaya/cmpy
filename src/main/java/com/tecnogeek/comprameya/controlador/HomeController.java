/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.controlador;
import com.tecnogeek.comprameya.dao.AbstractGenericDAO;
import com.tecnogeek.comprameya.entidad.Publicacion;
import com.tecnogeek.comprameya.entidad.Recurso;
import com.tecnogeek.comprameya.entidad.Sistema;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.security.krb5.internal.ktab.KeyTabConstants;

/**
 *
 * @author genaro
 */
@Controller
public class HomeController 
{
    @Autowired
    AbstractGenericDAO sisdao;
    
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String welcomePage(Model model)
    {
        System.out.println("AQUI ESTOY");
        
        //List<Publicacion> publicaciones = sisdao.findAll(Publicacion.class);        
        int pageZise = 4;
        int totalPublicaciones = sisdao.getNumberOfRows(Publicacion.class,"fkTipoPublicacion='1'");        
        int limit = Math.round(totalPublicaciones/pageZise);
        Random rnd = new Random();
        int page = (int)(rnd.nextDouble() * limit + 1);
        System.out.println("Publicaciones devueltas: "+pageZise);
        List<Publicacion> publicaciones = sisdao.findByWhereStatement(Publicacion.class,"fkTipoPublicacion='1'", page,pageZise);
        
        for (Publicacion publicacion : publicaciones){
            System.out.println("titulo: "+publicacion.getTitulo());
            List<Recurso> recursos = publicacion.getRecursoList();
            for(Recurso recurso : recursos){
                System.out.println("recurso: "+recurso.getRuta());
            }            
        }        
        model.addAttribute("publicaciones", publicaciones);
        model.addAttribute("parametro", "Pagina Inicio");
        return "boot";
    }
    
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String indexPublico(Model model)
    {   
        @SuppressWarnings("unchecked")
        List<Sistema> sistemas = sisdao.findAll(Sistema.class);
        for (Sistema sistema : sistemas) {
                System.out.println("hola, este es mi id: "+sistema.getSistemaId());                
        }
        System.out.println("AQUI ESTOY");
        model.addAttribute("parametro", "Hola Mundo");
        return "indexCarousel";
    }
    
//    @RequestMapping(value = "/agregarAnuncio", method = RequestMethod.POST)    
//    public String agregarAnuncio(@RequestParam("descripcion") String descripcion,@RequestParam("file") MultipartFile file)
//    {   
//        System.out.println("AQUI ESTOY");        
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                System.out.println(bytes.toString());
//                // store the bytes somewhere
//            } catch (IOException ex) {
//                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//            }            
//        }
//        return "index";
//    }
    
    @RequestMapping(value = "/agregarAnuncio", method = RequestMethod.POST)    
    public String agregarAnuncio(@RequestParam("descripcion") String descripcion,@RequestParam(value = "multipleFiles", required = false) List<MultipartFile> files,Model model,HttpServletRequest req)
    {   
        String originalName = "";
        String fileExtension = "";
        String userDir = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        Path directory = Paths.get(userDir+fileSeparator+"src"+fileSeparator+"images"+fileSeparator);        
        for (MultipartFile multipartFile : files ){
            if (!multipartFile.isEmpty()) {                                
                originalName = FilenameUtils.getBaseName(multipartFile.getOriginalFilename());
                fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());                
                try {                                        
                    Path file = Files.createTempFile(directory,originalName +  "-", "." + fileExtension);                     
                    System.out.println("CARGANDO ARCHIVO: "+file.getFileName());                  
                    try (InputStream input = multipartFile.getInputStream()) {
                        Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
                    }           
                } catch (Exception e) {
                    System.out.println("Falla al cargar el archivo " + originalName + " => " + e.getMessage());
                }
            } else {
                System.out.println("Falla al cargar el archivo: " + originalName + ", porque el archivo está vacío.");
            }
        }
        return welcomePage(model);
    }
}