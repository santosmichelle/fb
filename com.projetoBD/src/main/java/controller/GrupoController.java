package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.BloqueioMembGrupo;
import model.Grupo;
import model.MembrosDoGrupo;
import model.SolicMembGrupo;
import model.Usuario;


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
	
	public Grupo pesquisarGrupo(String nome, int idUserLogado, EntityManager manager) {

		Grupo grupo;
		EntityTransaction transaction	= manager.getTransaction();
		transaction.begin();
		
		try {
			
			grupo =  manager.createQuery(" select u from Grupo u "
					+ " ,MembrosDoGrupo mg " + 
					" where mg.id_grupo = u.id_grupo "
					+ " and upper(u.nome) like :nome " + 
					" and not exists " + 
					" ( select b from BloqueioMembGrupo b " + 
					" where b.id_usuario = mg.id_usuario " + 
					" and b.id_usuario = :idUserLogado ) ",Grupo.class)
					.setParameter("nome", "%"+nome.toUpperCase()+"%")
					.setParameter("idUserLogado", idUserLogado)
					.getSingleResult();
			manager.close();
			return grupo;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
			
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
	
	
	public MembrosDoGrupo pesquisarMembroGrupo(int idGrupo, int id_usuario, EntityManager manager) {
		
		MembrosDoGrupo m;
		EntityTransaction transaction	= manager.getTransaction();
		transaction.begin();
		
		try {
			
			m =  manager.createQuery(" select mg from MembrosDoGrupo mg " + 
					" where mg.id_grupo = :idGrupo "
					+ " and mg.id_usuario = :idUserLogado ",MembrosDoGrupo.class)
					.setParameter("idGrupo", idGrupo)
					.setParameter("idUserLogado", id_usuario)
					.getSingleResult();
			manager.close();
			return m;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
			
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
	
	public boolean removerMembroGrupo(MembrosDoGrupo m, EntityManager manager) {
		int i = 0 ;
		if (m != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			
			i = manager.createQuery("delete MembrosDoGrupo m " + 
					" where m.id_grupo = :id_grupo and m.id_usuario = :id_usuario  ")
					.setParameter("id_grupo", m.getId_grupo())
					.setParameter("id_usuario", m.getId_usuario())
					.executeUpdate();
			transaction.commit();
			
		}	
		return i>0;

	}
	
	public boolean participarGrupo(SolicMembGrupo g, EntityManager manager) {
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
