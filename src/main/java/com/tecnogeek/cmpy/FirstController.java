package com.tecnogeek.cmpy;

import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tecnogeek.cmpy.dao.SistemaDAO;
import com.tecnogeek.cmpy.utils.HibernateUtils;
import com.tecnogeek.cmpy.utils.WriteLogs;
import com.tecnogeek.model.Sistema;


@Controller
@RequestMapping("/primerControlador")
public class FirstController {

	private static final Logger logger = LoggerFactory.getLogger(FirstController.class);
	
	@RequestMapping(value="holamundo")
	public String hola(Locale locale, Model model){
		logger.info("Imprimiendo hola mundo...", locale);
		return "holamundo";		
	}
	
	@RequestMapping(value="/getLogsBoeing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Sistema> getSistema(){
		logger.debug("@FirstController.getSistema() devuelve informacion de la tabla sistema");
		WriteLogs.writeLogsLine("@FirstController.getSistema() devuelve informacion de la tabla sistema");
		List<Sistema> respuesta = (List<Sistema>) new Sistema();
		SistemaDAO sisdao = new SistemaDAO();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();			
			@SuppressWarnings("unchecked")
			List<Sistema> sistemas = sisdao.findAll(Sistema.class);
			session.getTransaction().commit();
			respuesta = sistemas;
			String var;
		}catch(HibernateException hex){
			logger.error("@JobStatisticsController.getLogsBoeing():  It wasn't possible to retrieve the information" + hex.getMessage());
			WriteLogs.writeLogsLine("@JobStatisticsController.getLogsBoeing(): It wasn't possible to retrieve the information\n"+ hex.getMessage());			
		}
		return respuesta;
	}
	
}
