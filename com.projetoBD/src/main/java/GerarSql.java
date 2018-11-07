

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import controller.GrupoController;
import controller.PostController;
import controller.UsuarioController;
import model.Amizade;
import model.BloqueioAmizade;
import model.BloqueioMembGrupo;
import model.ComentarioPost;
import model.Grupo;
import model.MembrosDoGrupo;
import model.Post;
import model.PostUsuario;
import model.RetornoPost;
import model.SolicAmizade;
import model.SolicMembGrupo;
import model.Usuario;

public class GerarSql {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoBD");

	public static EntityManager createEntityManager() {
		
		return factory.createEntityManager();
	}
	/*
	public void testarInsert(EntityManager manager){
		UsuarioController user = new UsuarioController();
		Usuario p = new Usuario();
		
		p.setNome("Michelle");
		p.setSenha("senha");
		p.setLogin("login");
		p.setVisibilidade("P");
		
		user.criarUsuario(p, manager);
		
	}
	
	public void testarInsertAmizade(EntityManager manager){
		UsuarioController user = new UsuarioController();
		Amizade p = new Amizade();
		
		p.setId_user_logado(1);
		p.setId_amigo(2);
		
		user.insAmizade(p, manager);
		
	}
	
	public void testarInsSolicAmizade(EntityManager manager){
		UsuarioController user = new UsuarioController();
		SolicAmizade p = new SolicAmizade();
		
		p.setId_user_logado(1);
		p.setId_amigo(4);
		
		user.insSolicAmizade(p, manager);
		
	}
	
	public void testarInsBloqAmizade(EntityManager manager){
		UsuarioController user = new UsuarioController();
		BloqueioAmizade p = new BloqueioAmizade();
		
		p.setId_user_logado(1);
		p.setId_amigo(3);
		
		user.insBloqAmizade(p, manager);
		
	}	
	*/
	
	public boolean criarPost(EntityManager manager) {
		
		PostUsuario post = new PostUsuario();
		
		
		if (post != null) {
			EntityTransaction transaction	= manager.getTransaction();
			transaction.begin();
			manager.persist(post);
			transaction.commit();
			return true;
		}
		return false;		
	}
	public boolean login(EntityManager manager,String login){
		EntityTransaction transaction	= manager.getTransaction();
		transaction.begin();
		try {
			
			return Usuario.class.isInstance(manager.createQuery("from Usuario where login = :login",Usuario.class)
					.setParameter("login", login)
					.getSingleResult());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		EntityManager manager; 
		GerarSql gs = new GerarSql();
		UsuarioController u = new UsuarioController();
		GrupoController g = new GrupoController();
		MembrosDoGrupo m = new MembrosDoGrupo();
		SolicMembGrupo s = new SolicMembGrupo();
		BloqueioMembGrupo b = new BloqueioMembGrupo();

		PostController p = new PostController();
		
		m.setId_grupo(1);
		m.setId_usuario(3);
		m.setAdmnistrador("N");
		
		s.setId_grupo(1);
		s.setUsuario(4);
		
		b.setId_grupo(1);
		b.setId_usuario(1);
		
		//c.criarGrupo(g, manager);
		//c.aceitarMembrosGrupos(m, manager);
		//c.solicitarMembrosGrupos(s, manager);
		//c.bloquearMembrosGrupos(b, manager);
//		MembrosDoGrupo m1;
//		manager = GerarSql.createEntityManager();
//		m1 = c.pesquisarMembroGrupo(1, 3, manager);
		manager = GerarSql.createEntityManager();
//RetornoPost r = RetornoPost.class.cast(p.verMuralUsuario(4, 1, manager).get(0).getClass().getName());
//			System.out.println(u.getSolicitAmizades(4, manager).listIterator());

//		MembrosDoGrupo m1 = new MembrosDoGrupo();
//		
//		m1.setAdmnistrador("N");
//		m1.setId_grupo(1);
//		m1.setId_usuario(2);
//		
//			System.out.println(p.verMuralGrupo(1, 1, manager));

	
		
		
		
		manager = GerarSql.createEntityManager();
		List<RetornoPost>	listRetornoPost =p.verMuralGrupo(1, 4, manager);
		manager = GerarSql.createEntityManager();
		List<RetornoPost> listComentPost =p.buscaComentPostUser(manager);
		manager = GerarSql.createEntityManager();
		List<RetornoPost> listRespComent = p.buscaRespComentUser(manager);
		
			String msg = "";
			for (RetornoPost pt : listRetornoPost) {
			msg = msg+pt.getNome()+": "+pt.getConteudo() + " \n  ";
				for (RetornoPost cp : listComentPost) {
					if (cp.getId_post()==pt.getId_post()) {
						msg = msg+"\t __ "+cp.getNome()+": "+cp.getConteudo() + " \n ";
						
					}
					
					for (RetornoPost rc : listRespComent) {
						if (rc.getId_post()==cp.getId_comentario()&&cp.getId_post()==pt.getId_post()) {
							msg = msg+"\t\t ______"+rc.getNome()+": "+rc.getConteudo() + " \n ";
							
						}
					}
					
				}
				
			}
			
			JOptionPane.showMessageDialog(null, msg);
			
			
			
			
			//b.login(manager, "login");
		//b.criarPost(manager);
//		b.testarInsert(manager);
//		b.testarInsertAmizade(manager);
//		b.testarInsSolicAmizade(manager);
//		b.testarInsBloqAmizade(manager);
		
		//manager.close();
		
		
		
	

	}


	
}
