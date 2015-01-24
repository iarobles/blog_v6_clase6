package org.spring.upiita.daos;

import java.util.List;

import org.spring.upiita.entidades.Articulo;

public interface ArticuloDAO {

	public abstract Integer guardar(Articulo articulo);

	Articulo buscaPorId(Integer articuloId);

	List<Articulo> buscaPorTitulo(String titulo);

}