package org.spring.upiita.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

//EJERCICIO MAPEAR ESTA CLASE CON la tablas usuarios
@Entity(name = "usuarios")
public class Usuario {

	// muy importante poner un ID!!
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "idUsuarioSecuencia", sequenceName = "usuarios_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idUsuarioSecuencia")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@OneToOne
	//join columna va en la entidad que es duena (la que tiene
	//la llave foranea de la relacion)
	@JoinColumn(name="datos_autor_id")
	private DatosUsuario datosUsuario;
	
	@OneToMany(mappedBy="usuario",fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Articulo> articulos;
	
	@ManyToMany(mappedBy="usuarios")
	private List<Departamento> departamentos;
	
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	
	public List<Articulo> getArticulos() {
		return articulos;
	}
	
	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public DatosUsuario getDatosUsuario() {
		return datosUsuario;
	}

	public void setDatosUsuario(DatosUsuario datosUsuario) {
		this.datosUsuario = datosUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
