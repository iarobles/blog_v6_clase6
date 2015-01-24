package org.spring.upiita.daos;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.spring.upiita.daos.utilerias.UtileriasDAO;
import org.spring.upiita.entidades.Articulo;
import org.springframework.transaction.annotation.Transactional;

// ESTE ANNOTATION REGISTRA UNA INSTANCIA DE ESTA CLASE
// EN EL CONTEXTO DE SPRING
//@Component("articuloDAO")
@Transactional
public class ArticuloHibernateDAO implements ArticuloDAO {

	// -------------- EJEMPLOS DE INYECCION USANDO XML ------------------
	// cuando inyectan dependencias usando XML
	// SI ES OBLIGATORIO DEFINIR LOS GETTERS Y SETTER
	private UtileriasDAO utileriasDAO;

	private String cadena;

	private Integer numero;

	private Map<String, String> mapa;
	// ------------------------------------------------------------------

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Map<String, String> getMapa() {
		return mapa;
	}

	public void setMapa(Map<String, String> mapa) {
		this.mapa = mapa;
	}

	public UtileriasDAO getUtileriasDAO() {
		return utileriasDAO;
	}

	public void setUtileriasDAO(UtileriasDAO utileriasDAO) {
		this.utileriasDAO = utileriasDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.spring.upiita.daos.ArticuloDAO#guardar(org.spring.upiita.entidades
	 * .Articulo)
	 */
	@Override
	public Integer guardar(Articulo articulo) {

		System.out.println("guardando articulo");

		System.out.println("cadena:" + cadena + ",numero:" + numero + ", mapa:"
				+ mapa);
		utileriasDAO.procesar();

		//-- USAMOS HIBERNATE PARA GUARDAR
		
		//Session sesion = sessionFactory.openSession();
		//abrimos una transaccion de la base(esto es por que
		//vamos a modificar datos)
		//sesion.beginTransaction();
		//guardamos los datos
		
		//PARA TRANSACCIONES DECLARATIVAS:
		Session sesion = sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(articulo);
		
		//obtenemos la transaccion sobre la corre este metodo
		//y hacemos commit para reflejar los cambios
		//sesion.getTransaction().commit();
		//terminado todo cerramos la sesion de hibernate
		//sesion.close();

		//siempre regresamos el id del elemento que guardamos
		return articulo.getId();
	}
	
	@Override
	public Articulo buscaPorId(Integer articuloId){
		Articulo articulo = null;
		
		System.out.println("buscando articulo con id:" + articuloId);
		
		//para hacer consultas necesitamos una sesion de hibernate
		//Session sesion = sessionFactory.openSession();
		
		Session sesion = sessionFactory.getCurrentSession();
		
		//get obtiene de la tabla articulos(Articulo.class)
		//el renglon cuya columna id coinicida con el numero que nos
		//pasaron
		//=== recuerden que get regresa Object, por eso es necesario castearlo
		articulo = (Articulo)sesion.get(Articulo.class, articuloId);
		
		//hacemos que hibernate carge los comentarios 
		//de este articulo de acuerdo al mapeo que realizamos previamente
		//Hibernate.initialize(articulo.getComentarios());
		
		//lazy loading para cargar categorias de este articulo
		Hibernate.initialize(articulo.getCategorias());
		
		return articulo;
	}
	
	@Override
	public List<Articulo> buscaPorTitulo(String titulo){
		List<Articulo> articulosEncontrados = null;
		
		System.out.println("buscando articulos con titulo:" + titulo);
		
		Criteria criterio = sessionFactory.getCurrentSession().createCriteria(Articulo.class);
		
		//eq = la columna debe ser igual al valor que nos pasan
		//EN EL RESTRICTION SIEMPRE VA EL NOMBRE DE LA PROPIEDAD DE LA CLASE!!
		//NO DE LA TABLA
		criterio.add(Restrictions.like("titulo", "%" + titulo + "%"));
		//PARA NEGAR SERIA:		
		//criterio.add(Restrictions.not(Restrictions.like("titulo", "%" + titulo + "%")));
		//PARA BUSCAR VALORES EXACTOS
		//criterio.add(Restrictions.eq("contenido","algun valor"));
		
		//list() se usa cuando esperan muchos resultados
		//SI LA BUSQUEDA NO PRODUCE NINGUN RESULTADO
		//list() regresa una lista VACIA
		articulosEncontrados = criterio.list();
		
		return articulosEncontrados;
	}

}
