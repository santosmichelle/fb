package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_bloqueio_amizade")

public class BloqueioAmizade {
	
	@Id
	private int id_amigo;
	
	@Id
	private int id_user_logado;
	
	
	//Gerando getters and setters

	public int getId_amigo() {
		return id_amigo;
	}

	public void setId_amigo(int id_amigo) {
		this.id_amigo = id_amigo;
	}

	public int getId_user_logado() {
		return id_user_logado;
	}

	public void setId_user_logado(int id_user_logado) {
		this.id_user_logado = id_user_logado;
	}
	


}
