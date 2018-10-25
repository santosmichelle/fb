package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Amizade;
import model.BloqueioAmizade;
import model.SolicAmizade;
import model.Post;

public class PortController {

	
	public boolean criarPost(Post post, EntityManager manager) {
		if (post != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(post);
			transaction.commit();
			
			return true;
		}
		return false;		
	}
	
	
	
}
