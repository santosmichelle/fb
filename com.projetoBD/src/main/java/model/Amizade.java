package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_amizade")

public class Amizade {

	@Id
	private int id_amigo;
	
	@Id
	private int id_user_logado;
	

}
