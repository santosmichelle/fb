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

}
