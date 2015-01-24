package org.spring.upiita.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "categorias")
public class Categoria {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "categoriaIdSecuencia", sequenceName = "categoria_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriaIdSecuencia")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;
	
	@ManyToMany
	//name = es el nombre de la tabla intermedia de la relacion N-N
	@JoinTable(name="categorias_articulos",
		//joinColumnas se rellena de las columnas de la tabla INTERMEDIA
	    //QUE SIRVEN PARA RELACIONAR A LA ENTIDAD DUENA (que elegimos por convencion)
	    joinColumns={@JoinColumn(name="categoria_id")},
	    //inverseJoinColumns se rellena con las columnas de la tabla INTERMEDIA
	    //QUE SIRVE PARA RELACIONAR A LA ENTIDAD QUE NO ES DUENA
	    inverseJoinColumns={@JoinColumn(name="articulo_id")})
	private List<Articulo> articulos;
	
	public List<Articulo> getArticulos() {
		return articulos;
	}
	
	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");
		builder.append("nombre:").append(this.nombre);
		builder.append("}");

		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
