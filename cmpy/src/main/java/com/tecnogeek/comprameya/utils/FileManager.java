/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jzelaya
 */

public abstract class FileManager {
    
    /**
     * Guarda el archivo (imagen) dentro del sistema de archivos del SO.
     * Para evitar que la carpeta con imagenes crezca de manera desmensurada
     * se crea una carpeta nueva por cada semana del año.
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public static String saveFile(MultipartFile file) throws IOException{
        
        //Ruta de la imagen guardada en la BD
        String relativeFilePath = "";
        String imageName;
        
        //Obtenemos la semana del año, y el año al que nos referimos.
        Calendar now = Calendar.getInstance(); 
        int semana = now.get(Calendar.WEEK_OF_MONTH);
        int mes = now.get(Calendar.MONTH);
        int año = now.get(Calendar.YEAR);
        
        //Creamos la ruta del directorio raiz
//        String rootDir = System.getProperty("user.home");        
        String fileSeparator = System.getProperty("file.separator");        
//        rootDir += fileSeparator+"src"+fileSeparator+"images"+fileSeparator;
        String rootDir = getImageBaseDir();
        
        
        //Construir la ruta relativa del archivo
        relativeFilePath = semana +"-"+ mes +"-"+ año + fileSeparator;
        
        //Ruta del directorio a usar para guardar la imagen original
        String originalDir = rootDir + relativeFilePath;        
        Path directory = Paths.get(originalDir);
        
        //Ruta del directorio a usar para guardar el thumbnail de la imagen original
        String thumbDir = rootDir + getThumbnailPrefix() + relativeFilePath;        
        Path thumbnailDirectory = Paths.get(thumbDir);    
        
        //Si no existe la carpeta debe crearse...
        if(!Files.isDirectory(directory,LinkOption.NOFOLLOW_LINKS)) 
        {
            System.out.println("Creating directory: "+directory);
            Files.createDirectories(directory);
        }
        
        //Si no existe la carpeta debe crearse...
        if(!Files.isDirectory(thumbnailDirectory,LinkOption.NOFOLLOW_LINKS)) 
        {
            System.out.println("Creating directory: "+thumbnailDirectory);
            Files.createDirectories(thumbnailDirectory);
        }
        
        // Procedemos a guardar la imagen        
        String originalName = FilenameUtils.getBaseName(file.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());  
        
        imageName  = FileManager.genImageName("cmpy-","",fileExtension);
        if (!file.isEmpty()) 
        {                                           
            try
            {
                //Convertimos el multipartfile en un inputstream
                InputStream originalImage = file.getInputStream();
                BufferedImage originalBufferedImage = ImageIO.read(originalImage);

                //guardamos la imagen original
                File originalFile = new File(directory.toString()+fileSeparator+imageName);  
                ImageIO.write(originalBufferedImage,fileExtension, originalFile);
                
                //Obtenemos una imagen reducida
                BufferedImage thumbImage = FileManager.getScaleImage(originalBufferedImage, fileExtension);                

                //guardamos la imagen reducida
                File thumbFile = new File(thumbnailDirectory.toString()+fileSeparator+imageName);  
                ImageIO.write(thumbImage,fileExtension, thumbFile);                    
            }catch(Exception e)
            {
                System.out.println("Falla al cargar el archivo: " + originalName + ": "+e.getMessage());
            }            
                
        } else {
            System.out.println("Falla al cargar el archivo: " + originalName + ", porque el archivo está vacío.");
        }
        return relativeFilePath+""+imageName;
    }
    
    public static String genImageName(String prefix,String suffix,String fileExtension)
    {
        String name;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName(); //get logged in username        
        name = prefix+userName+System.currentTimeMillis()+suffix+"."+fileExtension;        
        return name;    
    }
    
    
    public static BufferedImage getScaleImage(BufferedImage originalImage,String fileExtension) throws IOException
    {        
        BufferedImage thumbImage = Scalr.resize(originalImage, Method.BALANCED,Mode.FIT_TO_HEIGHT, 150, 150, Scalr.OP_ANTIALIAS);                   
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(thumbImage,fileExtension,os);                    
        return thumbImage;        
    }    
    
    public static byte[] getFile(String fileId) throws IOException
    {
        byte[] bytes=null;
        String userDir = getImageBaseDir()+fileId;
        Path path = Paths.get(userDir);
        bytes = Files.readAllBytes(path);     
       return bytes;
    }
    
    public static String getImageBaseDir(){
        byte[] bytes=null;
        String userDir = System.getProperty("user.home");                
        String fileSeparator = System.getProperty("file.separator");
        userDir = userDir+fileSeparator+"src"+fileSeparator+"images"+fileSeparator;
        return userDir;        
    }
    
    public static String getThumbnailPrefix()
    {
        return "thumbnail-";
    }
    
    
}
