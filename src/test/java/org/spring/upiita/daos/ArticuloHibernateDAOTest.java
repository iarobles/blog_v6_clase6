package org.spring.upiita.daos;

//hacemos un static import
import static org.springframework.util.Assert.notEmpty;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.spring.upiita.entidades.Articulo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArticuloHibernateDAOTest {
	
	private static ApplicationContext contexto;
	
	private static ArticuloDAO articuloDAO;
	
	//este annotaino es de junit
	//e indica que este metodo se debe ejecutar antes
	//que cualquier test
	@BeforeClass
	public static void inicializar(){
		
		contexto = new ClassPathXmlApplicationContext("/daos-context.xml");		
		articuloDAO = (ArticuloDAO) contexto.getBean("articuloDAO");		
	}
	
	@Test
	public void guardarTest(){
		
		Articulo articulo = new Articulo();
		//ya no es necesaria por que la secuencia se lo asignaxt
		//articulo.setId(1000);
		articulo.setTitulo("titulo prueba");
		articulo.setContenido("contenido prueba");
		
		Integer articuloId = articuloDAO.guardar(articulo);
		
		Articulo articuloGuardado =articuloDAO.buscaPorId(articuloId);
		
		//ALT+SHIFT+X  y luego oprimen T
		//assertions de junit
		Assert.assertNotNull(articuloGuardado);
		
		//PRIMER ARGUMENTO: ES EL VALOR ESPERADO (LO CORRECTO)
		//SEGUNDO ARGUMENTO: ES EL VALOR A VERIFICAR
		Assert.assertEquals(articulo.getId(),articuloGuardado.getId());
		Assert.assertEquals(articulo.getTitulo(), articuloGuardado.getTitulo());
		Assert.assertEquals(articulo.getContenido(), articuloGuardado.getContenido());		
	}
	
	@Test
	public void buscaPorTituloTest(){
		
		List<Articulo> articulos = articuloDAO.buscaPorTitulo("titulo");
		//revisa que una coleccion no sea null y que tenga al menos un elemento
		notEmpty(articulos);
		
	}
	
	//test para relacion 1-N
	@Test
	public void mapeoArticuloComentariosTest(){
		
		Articulo articulo = articuloDAO.buscaPorId(1);
		//checamos que el articulo existe
		Assert.assertNotNull(articulo);
		
		notEmpty(articulo.getComentarios());
		
		System.out.println("comentarios:" + articulo.getComentarios());		
	}
	
	//test para relacion N-N
	@Test
	public void mapeoArticuloCategorias(){
		
		Articulo articulo = articuloDAO.buscaPorId(1);
		//checamos que el articulo existe
		Assert.assertNotNull(articulo);
		
		notEmpty(articulo.getCategorias());
		
		
	}
	
	

}
