package org.spring.upiita.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "articulos")
public class Articulo {

	// @Id es para la columna de su tabla que es la llave primaria
	// hibernante los obliga a que siempre tengan una llave primaria
	// en sus tablas
	@Id
	@Column(name = "id")
	//name = es un alias que hace referencia a @GeneratedValue
	// sequenceName = es el nombre real de la secuencia en la base
	// allocationSize = se refiere al incremento para el siguiente ID  
	@SequenceGenerator(name = "idArticuloSecuencia", sequenceName = "articulo_id_seq", allocationSize = 1)
	// valor generado lo configuran con @GeneratedValue
	// EN UNA BASE PUEDE GENERAR UN CONJUNTO DE NUMEROS AUTOMATICOS
	// USANDO LAS SIGUIENTES ESTRATEGIAS:
	// - secuencias: Postgres, Oracle, HSQLDB
	// - columnas identidad: Mysql, SQL Server
	// SI ES IDENTITY: @GeneratedValue(strategy = GenerationType.IDENTITY)
	// - tablas:
	//
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idArticuloSecuencia")	
	private Integer id;

	@Column(name = "titulo")
	@NotEmpty //valida que una cadena, o un arreglo o una lista
	          //no sean vacios (que tenga al menos un elemento)
	@Size(min=10, max = 1000) //valida que una cadena, o una lista, o un arreglo esten
	                          //en el rango especificado
	private String titulo;

	@Size(min=10)
	@Column(name = "contenido")
	private String contenido;

	// el parametro name es el nombre real de la columna
	// de su tabla
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	//mappedBy se rellena con el nombre de la propiedad
	// DE LA CLASE (entidad duena) con la que nos referimos
	//A ESTA ENTIDAD 
	//fecht = propiedad que me permite indicar 
	//        si esta coleccion debe de cargarse de manera
	//        lazy o eager(prematura)
	@OneToMany(mappedBy="articulo",fetch=FetchType.EAGER) //para un renglon de articulo le corresponden muchos comentarios
	@Fetch(FetchMode.SELECT)
	private List<Comentario> comentarios;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToMany(mappedBy="articulos")
	private List<Categoria> categorias;
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	@Override //obligamos al compilador que cheque que este 
	//metodo este definido en la clase o interfaz padre
	public String toString(){
		String cadena = "[";
		
		cadena = "titulo:" + this.titulo;		
		cadena = cadena + "]";
		
		return cadena;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
