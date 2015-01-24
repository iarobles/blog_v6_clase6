package org.spring.upiita.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControlador {
	
	@RequestMapping(value="/login")
	public String muestraLogin(Boolean valido, Model modelo){
		
		modelo.addAttribute("valido",valido);
		
		return "login";
	}
	
	@RequestMapping(value="/error-403")
	public String muestraError403(){
		
		return "error-403";
	}

}
