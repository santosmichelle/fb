package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_post_usuario")
public class PostUsuario{
	
	@Id
	private int id_post;
	
	@Column
	private int id_user_post;

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}

	public int getId_user_post() {
		return id_user_post;
	}

	public void setId_user_post(int id_user_post) {
		this.id_user_post = id_user_post;
	}


}
