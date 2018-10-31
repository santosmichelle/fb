

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import controller.GrupoController;
import controller.UsuarioController;
import model.Amizade;
import model.BloqueioAmizade;
import model.BloqueioMembGrupo;
import model.Grupo;
import model.MembrosDoGrupo;
import model.Post;
import model.PostUsuario;
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
		post.setId_user_post(2);
		post.setId_user_logado(1);
		
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
		EntityManager manager = GerarSql.createEntityManager();
		GerarSql gs = new GerarSql();
		UsuarioController u = new UsuarioController();
		GrupoController c = new GrupoController();
		Grupo g = new Grupo();
		MembrosDoGrupo m = new MembrosDoGrupo();
		SolicMembGrupo s = new SolicMembGrupo();
		BloqueioMembGrupo b = new BloqueioMembGrupo();
		g.setNome("Grupo teste");
		g.setVisibilidade("P");
		
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
		System.out.println(u.pesquisarUsuario("michelle", 1, manager).getNome());
		
		//b.login(manager, "login");
		//b.criarPost(manager);
//		b.testarInsert(manager);
//		b.testarInsertAmizade(manager);
//		b.testarInsSolicAmizade(manager);
//		b.testarInsBloqAmizade(manager);
		
		//manager.close();
		
		
		
	

	}


	
}
