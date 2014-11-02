package primerPaquete;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tecnogeek.cmpy.FirstController;

@Controller
@RequestMapping("/segundoControlador")
public class SecondController {

	private static final Logger logger = LoggerFactory.getLogger(FirstController.class);
	
	@RequestMapping(value="segundo")
	public String hola(Locale locale, Model model){
		logger.info("Mostrar segunda vista...", locale);
		return "segundoControlador/salu";		
	}
	
}
