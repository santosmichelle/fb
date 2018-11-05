package controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.ComentarioPost;
import model.Post;
import model.PostUsuario;
import model.RetornoPost;

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

	public int buscaUltimoPost(EntityManager manager) {
		int i;
		if (manager.isOpen()) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			i = (int) manager.createNativeQuery("SELECT max(p.id_post) FROM tb_post p").getSingleResult();


			return i;
		}
		return 0;		
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

	public boolean criarPostUsuario(PostUsuario p , EntityManager manager) {
		if (manager.isOpen()) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(p);
			transaction.commit();

			return true;
		}
		return false;		
	}


	public List<RetornoPost> verMuralUsuario(int idUsuario, int idUserLogado , EntityManager manager) {
		List<RetornoPost> p;
		if (idUsuario>0) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();

			p = manager.createQuery(" select new model.RetornoPost(p.id_post,u.nome,p.conteudo)  from Post p, PostUsuario pu, Usuario u "
					+ " where pu.id_post = p.id_post " + 
					" and u.id_usuario = p.id_user_post " + 
					" and pu.id_user_post = :idUsuario " + 
					" and ((p.visibilidade = 'A' and (select COUNT(a) from Amizade a " + 
					" where a.id_amigo = :idUserLogado)>0) or "+  
					" (p.visibilidade = 'AA' and " + 
					"	(select count(a) from Amizade a " + 
					"	where a.id_amigo = pu.id_user_post " + 
					"	  and (select count(aa) from Amizade aa " + 
					"	where aa.id_amigo = a.id_amigo " + 
					"	  and aa.id_user_logado = :idUserLogado)>0)>0) or "
					+ " (p.visibilidade = 'P' and 1=1 )) " 
					,RetornoPost.class)
					.setParameter("idUsuario", idUsuario)
					.setParameter("idUserLogado", idUserLogado)
					.getResultList();

			return p;
		}
		return null;		
	}


	public List<RetornoPost> buscaComentPostUser(EntityManager manager) {
		List<RetornoPost> p;
		if (manager.isOpen()) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();

			p = manager.createQuery(" select new model.RetornoPost(c.id_post,c.id_comentario,u.nome,c.conteudo) " + 
					" from ComentarioPost c, Usuario u " + 
					" where c.id_user_coment=u.id_usuario " 
					,RetornoPost.class)
					.getResultList();

			return p;
		}
		return null;		
	}
	
	public List<RetornoPost> buscaRespComentUser(EntityManager manager) {
		List<RetornoPost> p;
		if (manager.isOpen()) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();

			p = manager.createQuery(" select new model.RetornoPost(r.id_comentario,u.nome,r.conteudo) "
					+ " from RespostaComent r ,Usuario u " + 
					" where r.id_user_resp=u.id_usuario " 
					,RetornoPost.class)
					.getResultList();

			return p;
		}
		return null;		
	}

}







