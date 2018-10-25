

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import controller.UsuarioController;
import model.Amizade;
import model.BloqueioAmizade;
import model.SolicAmizade;
import model.Usuario;

public class GerarSql {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoBD");

	public static EntityManager createEntityManager() {
		
		return factory.createEntityManager();
	}
	
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
		GerarSql b = new GerarSql();

		b.login(manager, "login");
//		b.testarInsert(manager);
//		b.testarInsertAmizade(manager);
//		b.testarInsSolicAmizade(manager);
//		b.testarInsBloqAmizade(manager);
		manager.close();
		
		
		
		
//		String dados = null;
//		int opt = -1;

		
/*		dados= JOptionPane.showInputDialog("Digite o login!",dados);
		
		
		if ((b.login(manager,dados))) {
			
			do {
				opt = Integer.parseInt(JOptionPane.showInputDialog("Digite um codigo! \n"
						+ "1 - Solicitar amizade",opt));
				switch (opt) {
				case 1:
					System.out.println("entrou");
					break;

				default:
					System.out.println("entrou2");
					break;
				}
				System.out.println("entrou3");
			} while (opt==99);
			
		}*/
		

	}


	
}
