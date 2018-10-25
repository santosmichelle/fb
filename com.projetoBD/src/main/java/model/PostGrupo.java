package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_post_grupo")
public class PostGrupo{

	
	@Id
	private int id_post;
	
	@Column	
	private int id_grupo_post;
	
	
	//Gerando getters and setters

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}

	public int getId_grupo_post() {
		return id_grupo_post;
	}

	public void setId_grupo_post(int id_grupo_post) {
		this.id_grupo_post = id_grupo_post;
	}
	
}
