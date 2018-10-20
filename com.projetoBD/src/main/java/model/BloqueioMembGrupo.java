package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_bloqueio_memb_grupo")

public class BloqueioMembGrupo {
	
	@Id
	private int id_bloq_memb;
	
	@Id
	private int id_grupo;
	
	@Id
	private int id_usuario;

	
	//Gerando getters and setters
	
	public int getId_bloq_memb() {
		return id_bloq_memb;
	}

	public void setId_bloq_memb(int id_bloq_memb) {
		this.id_bloq_memb = id_bloq_memb;
	}

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	

}
