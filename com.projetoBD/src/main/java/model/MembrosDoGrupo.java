package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_membros_grupo")
public class MembrosDoGrupo {
	
	
	@Id
	private int id_memb_grup;
	@Column
	private int id_grupo;
	@Column
	private int id_usuario;
	@Column
	private String administrador;
	
	public int getId_memb_grup() {
		return id_memb_grup;
	}
	public void setId_membr_grup(int id_memb_grup) {
		this.id_memb_grup = id_memb_grup;
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
	public String getAdmnistrador() {
		return administrador;
	}
	public void setAdmnistrador(String admnistrador) {
		this.administrador = admnistrador;
	}

}
