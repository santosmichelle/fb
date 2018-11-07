package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_grupo")
public class Grupo {
	
	@Id
	private int id_grupo;
	@Column
	private String nome;
	@Column	
	private byte[] imagem;
	@Column
	private String conteudo;	
	@Column
	private String visibilidade;
	
	
	//Gerando getters and setters
	
	public int getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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