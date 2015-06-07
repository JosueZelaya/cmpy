/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jzelaya
 */

public abstract class FileManager {
    
    public static String saveFile(MultipartFile file,Path directory){
        Path newFile = directory;
        String originalName = FilenameUtils.getBaseName(file.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());                
        if (!file.isEmpty()) {                           
                try {                                        
                    newFile = Files.createTempFile(directory,"cmpy", "." + fileExtension);                     
                    System.out.println("CARGANDO ARCHIVO: "+newFile.getFileName());                  
                    try (InputStream input = file.getInputStream()) {
                        Files.copy(input,newFile, StandardCopyOption.REPLACE_EXISTING);
                    }           
                } catch (Exception e) {
                    System.out.println("Falla al cargar el archivo " + originalName + " => " + e.getMessage());
                }
            } else {
                System.out.println("Falla al cargar el archivo: " + originalName + ", porque el archivo está vacío.");
            }
        return newFile.getFileName().toString();
    }
    
}
