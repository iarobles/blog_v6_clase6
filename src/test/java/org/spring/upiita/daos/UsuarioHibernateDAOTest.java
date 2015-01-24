package org.spring.upiita.daos;

import static org.springframework.util.Assert.notEmpty;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.spring.upiita.entidades.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsuarioHibernateDAOTest {

	private static ApplicationContext contexto;

	private static UsuarioDAO usuarioDAO;

	// este annotaino es de junit
	// e indica que este metodo se debe ejecutar antes
	// que cualquier test
	@BeforeClass
	public static void inicializar() {

		contexto = new ClassPathXmlApplicationContext("/daos-context.xml");
		usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
	}

	@Test
	public void guardarTest() {

		Usuario usuario = new Usuario();
		// usuario.setId(1000);
		usuario.setNombre("nombre prueba");
		usuario.setPassword("1234");
		usuario.setEmail("email prueba");

		Integer usuarioId = usuarioDAO.guardar(usuario);

		Usuario usuarioGuardado = usuarioDAO.buscaPorId(usuarioId);

		// ALT+SHIFT+X y luego oprimen T
		// assertions de junit
		Assert.assertNotNull(usuarioGuardado);
	}

	@Test
	public void buscaPorEmailYPasswordTest() {

		Usuario usuario = usuarioDAO.buscaPorEmailYPassword("juan@email.com",
				"1234");

		Assert.assertNotNull(usuario);

	}

	@Test
	public void mapeoUsuarioDatosUsuarioTest() {

		Usuario usuario = usuarioDAO.buscaPorId(1);
		Assert.assertNotNull(usuario.getDatosUsuario());

		System.out.println("datos usuario:" + usuario.getDatosUsuario());
	}

	@Test
	public void mapeoUsuarioArticulosTest() {

		Usuario usuario = usuarioDAO.buscaPorId(1);

		Assert.assertNotNull(usuario);
		notEmpty(usuario.getArticulos());
		System.out.println("articulos:" + usuario.getArticulos());

	}

	@Test
	public void mapeoUsuarioDepartamentosTest() {
		Usuario usuario = usuarioDAO.buscaPorId(1);

		Assert.assertNotNull(usuario);
		notEmpty(usuario.getDepartamentos());
	}

}
