package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Amizade;
import model.BloqueioAmizade;
import model.SolicAmizade;
import model.Post;
import model.PostUsuario;

public class PostController {

	//int id_post, int id_user_post, byte[] imagem, String conteudo, String visibilidade
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
	
	
	public boolean excluirPost(Post post, EntityManager manager) {
		if (post != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.merge(post);
			manager.remove(post); // talvez usar o manager.find usando o ID
			return true;
		}
		return false;		
	}
	  public void excluirPost2(int  id, EntityManager manager) {
		  	  EntityTransaction transaction	= manager.getTransaction();
		    try {
		      // Inicia uma transação com o banco de dados.
		    transaction.begin();
		     // Consulta o post no bd através do seu ID.
		      Post post = manager.find(Post.class, id);		      
		      // Remove o post do bd.
		      manager.remove(post);
		      // Finaliza a transação.
		      transaction.commit();
		    } finally {
		      manager.close();
		    }
		  }
	
	
	  public Post consultarPost(int id, EntityManager manager) {
		  EntityTransaction transaction	= manager.getTransaction();
		  transaction.begin();
		    Post post = null;
		    try {
		      //Consulta post pelo seu ID.
		      post = manager.find(Post.class, id);
		    } finally {
		      manager.close();
		    }
		    return post;
		  }
		
	
	

	}



			
	
	

