package org.spring.upiita.daos;

import org.spring.upiita.entidades.Usuario;

public interface UsuarioDAO {

	public abstract Integer guardar(Usuario usuario);

	Usuario buscaPorId(Integer usuarioId);

	Usuario buscaPorEmailYPassword(String email, String password);

}