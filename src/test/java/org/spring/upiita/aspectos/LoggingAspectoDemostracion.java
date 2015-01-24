package org.spring.upiita.aspectos;

import org.spring.upiita.daos.ArticuloDAO;
import org.spring.upiita.daos.UsuarioDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoggingAspectoDemostracion {
	
	public static void main(String[] args) {
		
		ApplicationContext contexto = new ClassPathXmlApplicationContext("daos-context.xml","aspectos-context.xml");
		
		ArticuloDAO articuloDAO = (ArticuloDAO) contexto.getBean("articuloDAO");		
		articuloDAO.buscaPorId(1);
		
		//alt + shift + x  y luego j
		UsuarioDAO usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
		usuarioDAO.buscaPorId(1);
		
		
		articuloDAO.buscaPorTitulo("titulo 1");
		
	}

}
