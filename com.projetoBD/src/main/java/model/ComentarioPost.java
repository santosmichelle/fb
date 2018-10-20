package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tb_comentario_post")

public class ComentarioPost {
	
	@Id
	private int id_comentario;
	
	@Id
	private int id_post;
	
	@Id
	private int id_user_coment;
	
	@Column	
	private byte[] imagem;
	
	@Column
	private String conteudo;

	
	//Gerando getters and setters
	
	public int getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}

	public int getId_user_coment() {
		return id_user_coment;
	}

	public void setId_user_coment(int id_user_coment) {
		this.id_user_coment = id_user_coment;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}	
	
	
	//métodos
	
	public void criarComentario(int id_comentario, int id_user_coment, int id_post, String conteudo) {
		
	}
	
	public void encontraComentario(int id_comentario) {
		
	}
	
	public void removeComentario(int id_comentario) {
		
	}
}
