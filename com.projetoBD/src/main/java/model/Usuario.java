package model;

//import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario  {

	//private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue
	private int id_usuario;
	
	@Column
	private String nome;
	@Column
	private Date data_nascimento;
	@Column
	private byte[] foto;
	@Column
	private String senha;
	@Column
	private String login;
	@Column
	private String visibilidade;
	
	
	
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
		
	


}
