package org.spring.upiita.daos;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.spring.upiita.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usuarioDAO")
@Transactional
public class UsuarioHibernateDAO implements UsuarioDAO {

	@Autowired
	// si usan annotation para injectar la dependencia
	// no necesitan los getters y setters
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.spring.upiita.daos.UsuarioDAO#guardar(org.spring.upiita.entidades
	 * .Usuario)
	 */
	@Override
	public Integer guardar(Usuario usuario) {

		System.out.println("guardando usuario");

		// Session sesion = sessionFactory.openSession();
		// abrimos una transaccion de la base(esto es por que
		// vamos a modificar datos)
		// sesion.beginTransaction();
		// guardamos los datos
		Session sesion = sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(usuario);

		// obtenemos la transaccion sobre la corre este metodo
		// y hacemos commit para reflejar los cambios
		// sesion.getTransaction().commit();
		// terminado todo cerramos la sesion de hibernate
		// sesion.close();

		return usuario.getId();
	}

	@Override
	public Usuario buscaPorId(Integer usuarioId) {
		Usuario usuario = null;

		// Session sesion = sessionFactory.openSession();
		Session sesion = sessionFactory.getCurrentSession();
		usuario = (Usuario) sesion.get(Usuario.class, usuarioId);
		
		//Hibernate.initialize(usuario.getArticulos());
		Hibernate.initialize(usuario.getDepartamentos());

		return usuario;
	}

	// ---- SOLUCION EJERCICIO CRITERIOS HIBERNATE
	@Override
	public Usuario buscaPorEmailYPassword(String email, String password){
		Usuario usuario = null;
	
		Criteria criterio = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		
		//-- SELECT * FROM usuarios where email=? AND password=?
		criterio.add(Restrictions.eq("email", email));
		criterio.add(Restrictions.eq("password", password));
		
		//-- SELECT * FROM usuarios where email=? OR password=?
		//desde java 1.5 estan los vargars
		//criterio.add(Restrictions.or(Restrictions.eq("email", email),Restrictions.eq("password", password)));
		
		usuario = (Usuario) criterio.uniqueResult();
		
		
		return usuario;
	}

}
