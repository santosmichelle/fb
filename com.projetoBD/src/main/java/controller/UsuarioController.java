package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Amizade;
import model.BloqueioAmizade;
import model.SolicAmizade;
import model.SolicMembGrupo;
import model.Usuario;

public class UsuarioController {

	private Usuario u;
	
	public boolean criarUsuario(Usuario user, EntityManager manager) {
		if (user != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	
	public boolean inserirAmizade(Amizade insAmizade , EntityManager manager) {
		if (insAmizade != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(insAmizade);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	public boolean inserirSolicAmizade(SolicAmizade obj, EntityManager manager) {
		if (obj != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(obj);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	public boolean inserirBloqAmizade(BloqueioAmizade obj, EntityManager manager) {
		if (obj != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(obj);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	
	public Usuario pesquisarUsuario(String nome, int idUserLogado, EntityManager manager) {
		
		EntityTransaction transaction	= manager.getTransaction();
		transaction.begin();
		
		try {
			
			return manager.createQuery("select u from Usuario u " + 
					" where upper(u.nome) like :nome " + 
					" and not exists " + 
					" (select b from SolicAmizade b " + 
					" where b.id_amigo = u.id_usuario " + 
					" and b.id_user_logado = :idUserLogado ) ",Usuario.class)
					.setParameter("nome", "%"+nome.toUpperCase()+"%")
					.setParameter("idUserLogado", idUserLogado)
					.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
			
	}
	
	public boolean inserirSolicGrupo(SolicMembGrupo solic, EntityManager manager) {
		if (solic != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(solic);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
}
