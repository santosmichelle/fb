package model;

public class RetornoPost {
	private int id_post;
	private int id_comentario;
	private String nome;
	private String conteudo;
	
	
	
	public RetornoPost(int id_post, String nome, String conteudo) {
		super();
		this.id_post = id_post;
		this.nome = nome;
		this.conteudo = conteudo;
	}
	
	
	
	public RetornoPost(int id_post, int id_comentario, String nome, String conteudo) {
		super();
		this.id_post = id_post;
		this.id_comentario = id_comentario;
		this.nome = nome;
		this.conteudo = conteudo;
	}



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
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


}
