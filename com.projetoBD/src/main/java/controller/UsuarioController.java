package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Amizade;
import model.BloqueioAmizade;
import model.SolicAmizade;
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
	
	
	public boolean insAmizade(Amizade insAmizade , EntityManager manager) {
		if (insAmizade != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(insAmizade);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	public boolean insSolicAmizade(SolicAmizade obj, EntityManager manager) {
		if (obj != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(obj);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	public boolean insBloqAmizade(BloqueioAmizade obj, EntityManager manager) {
		if (obj != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(obj);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
}
