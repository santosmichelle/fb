package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_solic_memb_grupo")

public class SolicMembGrupo {
	
	@Id
	private int id_solic_memb;
	
	@Id
	private int id_grupo;
	
	@Id
	private int usuario;
	
	//Gerando getters and setters

	public int getId_solic_memb() {
		return id_solic_memb;
	}

	public void setId_solic_memb(int id_solic_memb) {
		this.id_solic_memb = id_solic_memb;
	}

	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

}
