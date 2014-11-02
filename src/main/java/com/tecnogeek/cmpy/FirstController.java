package com.tecnogeek.cmpy;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tecnogeek.cmpy.HomeController;

@Controller
@RequestMapping("/primerControlador")
public class FirstController {

	private static final Logger logger = LoggerFactory.getLogger(FirstController.class);
	
	@RequestMapping(value="holamundo")
	public String hola(Locale locale, Model model){
		logger.info("Imprimiendo hola mundo...", locale);
		return "holamundo";		
	}
	
	
}
