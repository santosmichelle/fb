package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_solic_amizade")

public class SolicAmizade {
	
	@Id
	private int id_amigo;
	
	@Id
	private int id_user_logado;
	

}
