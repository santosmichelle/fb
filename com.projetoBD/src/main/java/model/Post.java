package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;



@Entity
@Table(name = "tb_post")
public  class Post implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	private int id_post;	
	@Column	
	private int id_user_post;	
	@Column	
	private byte[] imagem;
	@Column
	private String conteudo;	
	@Column
	private String visibilidade;
	
	
	//Gerando getters and setters
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
	public String getVisibilidade() {
		return visibilidade;
	}
	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
	}
	

	

	
	
	
}