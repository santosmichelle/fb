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
			manager.close();
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
	
	public boolean SolicitarAmizade(SolicAmizade obj, EntityManager manager) {
		if (obj != null ) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(obj);
			transaction.commit();
			manager.close();
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
		System.out.println(nome);
		System.out.println(idUserLogado);
		System.out.println(manager.isOpen());
		Usuario user;
		EntityTransaction transaction	= manager.getTransaction();
		transaction.begin();
		
		try {
			
			user =  manager.createQuery("select u from Usuario u " + 
					" where upper(u.nome) like :nome " + 
					" and not exists " + 
					" (select b from SolicAmizade b " + 
					" where b.id_amigo = u.id_usuario " + 
					" and b.id_user_logado = :idUserLogado ) ",Usuario.class)
					.setParameter("nome", "%"+nome.toUpperCase()+"%")
					.setParameter("idUserLogado", idUserLogado)
					.getSingleResult();
			manager.close();
			return user;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
			
	}
	
public Usuario login(Usuario u, EntityManager manager) {
		
		EntityTransaction transaction	= manager.getTransaction();
		transaction.begin();
		Usuario user;
		try {
			
			user = manager.createQuery("from Usuario where login = :login and senha = :senha ) ",Usuario.class)
					.setParameter("login", u.getLogin())
					.setParameter("senha", u.getSenha())
					.getSingleResult();
			
			manager.close();
			return user;
			
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
	
	public Integer verificaAmizade(int idUserLogado,int idAmigo, EntityManager manager) {
		Integer i;
		if (idUserLogado>0) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			i =  manager.createQuery(" SELECT a FROM Amizade a " + 
					" where a.id_user_logado = :idUserLogado " + 
					" and a.id_amigo = :idAmigo ",Amizade.class)
					.setParameter("idUserLogado", idUserLogado)
					.setParameter("idAmigo", idAmigo)
					.getSingleResult().getId_amigo();
		
			
			return i;
		}
		return 0;		
	}
}
