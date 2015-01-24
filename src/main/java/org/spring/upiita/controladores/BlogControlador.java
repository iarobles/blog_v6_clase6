package org.spring.upiita.controladores;

import java.util.Date;

import javax.validation.Valid;

import org.spring.upiita.daos.ArticuloDAO;
import org.spring.upiita.daos.UsuarioDAO;
import org.spring.upiita.entidades.Articulo;
import org.spring.upiita.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//CONTROL + SHIFT + 0 = resolver paquetes
@Controller
public class BlogControlador {

	// TENDRIAN QUE CONSTRUIR FINALMENTE UN FACTORY

	@Autowired
	@Qualifier("articuloDAO")
	private ArticuloDAO articuloDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	// http://localhost:8022/upiita/blog?cadena=<script>alert("ataque")</script>

	// definimos una RUTA para este controlador
	// esta ruta despacha peticiones con /blog
	@RequestMapping(value = "/blog")
	public String muestraBlog(String cadena, Integer offset,
			@RequestParam(value = "lim", required = false) Integer limite,
			Model modelo) {

		System.out.println("offset:" + offset + ", limite:" + limite);

		modelo.addAttribute("fecha", new Date());
		modelo.addAttribute("cadena", cadena);

		return "blog";
	}

	// EJERCICIO BREVE:
	@RequestMapping(value = "/usuario/{usuarioId:[0-9]+}")
	public String muestraUsuario(@PathVariable Integer usuarioId, Model modelo) {

		System.out.println("usuario id:" + usuarioId);

		// hacemos la consulta usando hibernate
		Usuario usuario = usuarioDAO.buscaPorId(usuarioId);

		modelo.addAttribute("nombre", "usuario prueba");
		// aventamos el resultado de la consulta a la vista
		modelo.addAttribute("usuario", usuario);

		return "usuario";
	}

	@RequestMapping(value = "/blog/{articuloId:[0-9]+}")
	public String muestraArticulo(@PathVariable Integer articuloId, Model modelo) {

		System.out.println("articuloId:" + articuloId);

		// hacemos una consulta para buscar el articulo
		// usando el DAO (que a su vez usa Hibernate)
		Articulo articulo = articuloDAO.buscaPorId(articuloId);

		// una vez que tenemos el objeto de la consulta, se lo aventamos
		// a la vista
		modelo.addAttribute("articulo", articulo);

		return "articulo";
	}

	@RequestMapping(value = "/blog/{articuloId:[0-9]+}/editar")
	public String editarArticulo(@PathVariable Integer articuloId,
			Boolean actualizado, Model modelo) {

		Articulo articulo = articuloDAO.buscaPorId(articuloId);
		modelo.addAttribute("articulo", articulo);
		// LA BANDERA actualizado solo viene cuando previamente guardaron
		modelo.addAttribute("actualizado", actualizado);

		return "editar-articulo";
	}

	@RequestMapping(value = "/usuario/{usuarioId:[0-9]+}/editar")
	public String editarUsuario(@PathVariable Integer usuarioId,
			Boolean actualizado, Model modelo) {

		Usuario usuario = usuarioDAO.buscaPorId(usuarioId);
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("actualizado", actualizado);

		return "editar-usuario";
	}

	@RequestMapping(value = "/blog/guardar", method = RequestMethod.POST)
	public String guardarArticulo(@ModelAttribute @Valid Articulo articulo,
			BindingResult validacion, Model modelo) {
		String ruta = null;

		if (validacion.hasErrors()) {
			//si hay errores de validacion
						
			modelo.addAttribute("articulo",articulo);
			ruta = "editar-articulo";			

		} else {
			//cuando no hubo errores de validacion

			System.out.println("titulo:" + articulo.getTitulo()
					+ ", contenido:" + articulo.getContenido());

			Integer articuloId = articuloDAO.guardar(articulo);

			// implementamos patron post-redirect-get
			// REDIRECT A /blob/NUMERO/editar
			ruta = "redirect:/blog/" + articuloId + "/editar?actualizado=true";

		}
		
		return ruta;
	}

	// EJERCICIO
	@RequestMapping(value = "/usuario/guardar", method = RequestMethod.POST)
	public String guardarUsuario(@ModelAttribute Usuario usuario) {

		System.out.println("nombre:" + usuario.getNombre() + ", email:"
				+ usuario.getEmail());

		// GUARDAMOS DATOS USANDO CLASE DE UTILERIA
		Integer idUsuario = usuarioDAO.guardar(usuario);

		return "redirect:/usuario/" + idUsuario + "/editar?actualizado=true";
	}

}
