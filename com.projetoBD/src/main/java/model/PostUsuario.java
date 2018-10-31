package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_post_usuario")
public class PostUsuario extends Post {
	
	
	@Column
	private int id_user_logado;

	public int getId_user_logado() {
		return id_user_logado;
	}

	public void setId_user_logado(int id_user_logado) {
		this.id_user_logado = id_user_logado;
	}

	
	


}
