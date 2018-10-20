package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GerarSql {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoBD");

	public static EntityManager createEntityManager() {
		
		return factory.createEntityManager();
	}
	
	public void testarInsert(EntityManager manager){
		EntityTransaction transaction	= manager.getTransaction();
		transaction.begin();
		Usuario p = new Usuario();
		
		p.setNome("Fabia1");
		p.setSenha("senha");
		p.setLogin("login");
		manager.persist(p);
		transaction.commit();
		
		
	}


	
	
	public static void main(String[] args) {
		EntityManager manager = GerarSql.createEntityManager();
		GerarSql b = new GerarSql();
		
     	b.testarInsert(manager);
		manager.close();

	}


	
}
