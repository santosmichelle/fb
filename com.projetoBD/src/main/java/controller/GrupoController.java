package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.BloqueioAmizade;
import model.BloqueioMembGrupo;
import model.Grupo;
import model.MembrosDoGrupo;
import model.Post;
import model.SolicMembGrupo;


public class GrupoController {
	
	
	public boolean criarGrupo(Grupo group, EntityManager manager) {
		if (group != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(group);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	public boolean aceitarMembrosGrupos(MembrosDoGrupo g, EntityManager manager) {
		if (g != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(g);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	
	public boolean bloquearMembrosGrupos(BloqueioMembGrupo g, EntityManager manager) {
		if (g != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(g);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	public boolean solicitarMembrosGrupos(SolicMembGrupo g, EntityManager manager) {
		if (g != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(g);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	public void excluirGrupo(int  id, EntityManager manager) {
	  	  EntityTransaction transaction	= manager.getTransaction();
	    try {
	      // Inicia uma transação com o banco de dados.
	    transaction.begin();
	     // Consulta o post no bd através do seu ID.
	      Grupo g = manager.find(Grupo.class, id);		      
	      // Remove o post do bd.
	      manager.remove(g);
	      // Finaliza a transação.
	      transaction.commit();
	    } finally {
	      manager.close();
	    }
	  }


}
