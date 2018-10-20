package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tb_post")

public class Post {
	
	
	@Id
	private int id_post;
	
	@Id
	private int id_user_post;
	
	@Column	
	private byte[] imagem;
	@Column
	private String conteudo;	
	@Column
	private String visibilidade;


}
