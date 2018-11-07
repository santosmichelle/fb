package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Amizade;
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
			
			grupo =  manager.createQuery("From Grupo u " + 
					" where upper(u.nome) like :nome",Grupo.class)
					.setParameter("nome", "%"+nome.toUpperCase()+"%")
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

	public List<String> getSolicitMembros(int idUserAdmin, EntityManager manager) {
		List<String> p;
		if (manager.isOpen()) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();

			p = manager.createQuery(" select 'id_grupo = '||s.id_grupo||' Nome Grupo = '||g.nome "
					+ "||' // Id User = '||u.id_usuario||' Nome = '||u.nome " + 
					" from SolicMembGrupo s,Grupo g,Usuario u " + 
					" where s.id_grupo=g.id_grupo and "
					+ " u.id_usuario = s.id_usuario and "
					+ " exists ( select mg from MembrosDoGrupo mg " + 
					" where mg.id_grupo = s.id_grupo " + 
					"and mg.id_usuario = :idUserAdmin )" 
					,String.class)
					.setParameter("idUserAdmin", idUserAdmin)
					.getResultList();

			return p;
		}
		return null;		
	}
	
	
	public boolean aceitarMembro(MembrosDoGrupo m, EntityManager manager) {

		if (m != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(m);
			transaction.commit();
			return true;
		}	
		return false;

	}

	public boolean removeSolicitMembro(int id_grupo ,int id_usuario, EntityManager manager) {

		int i = 0 ;
		if (manager.isOpen()) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			
			i = manager.createQuery("delete SolicMembGrupo m " + 
					" where m.id_grupo = :id_grupo and m.id_usuario = :id_usuario  ")
					.setParameter("id_grupo", id_grupo)
					.setParameter("id_usuario",id_usuario)
					.executeUpdate();
			transaction.commit();
			
		}	
		return i>0;

	}

}
