package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tb_resposta_coment")
public class RespostaComent {
	
	@Id
	private int id_resposta;
	
	@Column	
	private int id_comentario;
	
	@Column	
	private int id_user_resp;
	
	@Column	
	private byte[] imagem;
	
	@Column
	private String conteudo;
	
	//Gerando getters and setters

	public int getId_resposta() {
		return id_resposta;
	}

	public void setId_resposta(int id_resposta) {
		this.id_resposta = id_resposta;
	}

	public int getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}

	public int getId_user_resp() {
		return id_user_resp;
	}

	public void setId_user_resp(int id_user_resp) {
		this.id_user_resp = id_user_resp;
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
	

}
