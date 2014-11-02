package com.tecnogeek.cmpy;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/primeControlador")

public class PrimeController {
	
private static final Logger logger = LoggerFactory.getLogger(FirstController.class);
	
	@RequestMapping(value="prime")
	public String hola(Locale locale, Model model){
		logger.info("prime controlador", locale);
		return "prime";		
	}

}
