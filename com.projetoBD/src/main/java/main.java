import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import controller.GrupoController;
import controller.PostController;
import controller.UsuarioController;
import model.BloqueioAmizade;
import model.Grupo;
import model.MembrosDoGrupo;
import model.Post;
import model.PostUsuario;
import model.SolicAmizade;
import model.SolicMembGrupo;
import model.Usuario;

public class main {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoBD");

	public static EntityManager createEntityManager() {
		
		return factory.createEntityManager();
	}
	private static Usuario userLogado;
	
	public static void main(String[] args) {
		EntityManager manager;
		String dados = null;
		Integer opt = null;
		Usuario user = null;
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		UsuarioController userController;
		GrupoController grupoController;
		Grupo grupo;
		Post post;
		PostController postController;
		
		boolean menuInicial = true;


		do {
			if (menuInicial) { 	
				opt = Integer.parseInt(JOptionPane.showInputDialog("Digite um número! \n"
						+ "1 - Logar no sistema \n"
						+ "2 - Cadastrar Usuario \n"
						+ "3 - Sair"));

				switch (opt) {
				case 1:
					manager = main.createEntityManager();
					user = new Usuario();
					userController = new UsuarioController();
					
					user.setLogin(JOptionPane.showInputDialog("Digite o login! \n"));
					user.setSenha(JOptionPane.showInputDialog("Digite a senha! \n"));
					
					userLogado = userController.login(user, manager);
					if (userLogado != null ) {
						
						menuInicial = false;
					}
					break;
				case 2:
					manager = main.createEntityManager();
					user = new Usuario();
					userController = new UsuarioController();
					
					user.setNome(JOptionPane.showInputDialog("Digite o nome! \n"));
					String d = JOptionPane.showInputDialog("Digite a data de nascimento! \n");
					try {
						user.setData_nascimento( formatoData.parse(d));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.setFoto(JOptionPane.showInputDialog("Insira uma foto! \n"));
					user.setLogin(JOptionPane.showInputDialog("Digite o login! \n"));
					user.setSenha(JOptionPane.showInputDialog("Digite a senha! \n"));
					user.setVisibilidade(JOptionPane.showInputDialog("Digite a visibilidade! \n"));
					
					
					
					if (userController.criarUsuario(user, manager)) {
						JOptionPane.showMessageDialog(null,"Usuario criado com sucesso");
					}else {
						JOptionPane.showMessageDialog(null,"Erro ao criar usuario");
					}

				default:
					break;
				}}

			if(!user.getLogin().equals("")) {
				
				opt = Integer.parseInt(JOptionPane.showInputDialog("Digite um número! \n"
						+ "1  - Digite o nome do usuario para pesquisar \n"
						+ "2  - Adicionar usuario \n"
						+ "3  - Bloquear usuario \n"
						+ "4  - Postar no mural de usuario \n"
						+ "5  - Criar grupos \n"
						+ "6  - Pesquisar grupos \n"
						+ "7  - Participar de um grupo \n"
						+ "8  - Remover usuario do grupo \n"
						+ "9  - Bloquear usuario do grupo \n"
						+ "10 - Ver mural de amigo \n"
						+ "digite 999 p sair"));

				
				switch (opt) {
				case 1:
					
					manager = main.createEntityManager();					
					userController = new UsuarioController();
					String pesquisa = null;		
					pesquisa = JOptionPane.showInputDialog("Digite o usuario para pesquisar! \n");
		
					user = userController.pesquisarUsuario(pesquisa,
							userLogado.getId_usuario(), manager);
							
					if (user != null ) {
						
						JOptionPane.showMessageDialog(null, "id ="+user.getId_usuario()
						+"\n Nome ="+user.getNome());
					}

					break;
				case 2:
					
					manager = main.createEntityManager();					
					userController = new UsuarioController();
					String idAmigo = null;	
					idAmigo = JOptionPane.showInputDialog("Digite o id do amigo para add! \n");
					SolicAmizade s = new SolicAmizade();
					s.setId_amigo(Integer.parseInt(idAmigo));
					s.setId_user_logado(userLogado.getId_usuario());
					

					if (userController.SolicitarAmizade(s, manager)) {
							
						JOptionPane.showMessageDialog(null, "Solicitação de amizade enviada !!");
					}else {
						JOptionPane.showMessageDialog(null, "Erro ao adicionar !!");
					}
					
					break;
				case 3:
					
					manager = main.createEntityManager();					
					userController = new UsuarioController();
					idAmigo = JOptionPane.showInputDialog("Digite o id do amigo para bloquear ! \n");
					BloqueioAmizade b = new BloqueioAmizade();
					b.setId_user_logado(userLogado.getId_usuario());
					b.setId_amigo(Integer.parseInt(idAmigo));
					

					if (userController.inserirBloqAmizade(b, manager)) {
							
						JOptionPane.showMessageDialog(null, "Usuario bloqueado !!");
					}else {
						JOptionPane.showMessageDialog(null, "Erro !!");
					}
					break;
				case 4:
					manager = main.createEntityManager();					
					userController = new UsuarioController();
					postController = new PostController();
					idAmigo = JOptionPane.showInputDialog("Digite o id do amigo publicar ! \n");
					post = new  Post();
					//System.out.println(userController.verificaAmizade(userLogado.getId_usuario(), Integer.parseInt(idAmigo), manager));
					if (userController.verificaAmizade(userLogado.getId_usuario(), Integer.parseInt(idAmigo), manager)>0) {
						post.setId_user_post(userLogado.getId_usuario());
						post.setConteudo(JOptionPane.showInputDialog("Digite o conteudo da publicação ! \n"));
						post.setVisibilidade(JOptionPane.showInputDialog("Digite o visibilidade da publicação ! \n"));
						manager = main.createEntityManager();	
						boolean t1 = postController.criarPost(post, manager);
						manager = main.createEntityManager();	
						int ultimoPost = postController.buscaUltimoPost(manager);
						manager = main.createEntityManager();	
						PostUsuario pu = new PostUsuario();
						pu.setId_post(ultimoPost);
						pu.setId_user_logado(Integer.parseInt(idAmigo));
						boolean t2 = postController.criarPostUsuario(pu, manager);
					
						
						if ((t1) && (t2)) {
								
							JOptionPane.showMessageDialog(null, "Post criado !!");
						}else {
							JOptionPane.showMessageDialog(null, "Erro !!");
						}
					
					}			
					
				
					break;
				case 5:
					
					manager = main.createEntityManager();					
					grupoController = new GrupoController();
					Grupo g = new Grupo();
					g.setNome(JOptionPane.showInputDialog("Digite o nome do grupo ! \n"));
					g.setConteudo(JOptionPane.showInputDialog("Digite o conteudo do grupo ! \n"));
					g.setVisibilidade(JOptionPane.showInputDialog("Digite a visibilidade do grupo ! \n"));
					
					if (grupoController.criarGrupo(g, manager)) {
							
						JOptionPane.showMessageDialog(null, "Grupo criado !!");
					}else {
						JOptionPane.showMessageDialog(null, "Erro !!");
					}
					
					break;
				case 6:
					manager = main.createEntityManager();					
					grupoController = new GrupoController();
							
					pesquisa = JOptionPane.showInputDialog("Digite o grupo para pesquisar! \n");
		
					grupo = grupoController.pesquisarGrupo(pesquisa,
							userLogado.getId_usuario(), manager);
							
					if (grupo != null ) {
						
						JOptionPane.showMessageDialog(null, "id ="+grupo.getId_grupo()
						+"\n Nome ="+grupo.getNome());
					}
					break;
				case 7:
					
					manager = main.createEntityManager();					
					grupoController = new GrupoController();
					String idGrupo;
					
					idGrupo = JOptionPane.showInputDialog("Digite o grupo para pesquisar! \n");
					SolicMembGrupo sg = new SolicMembGrupo();
					sg.setId_grupo(Integer.parseInt(idGrupo));
					sg.setUsuario(userLogado.getId_usuario());
							
					if (grupoController.participarGrupo(sg, manager)) {
						
						JOptionPane.showMessageDialog(null, "Solicitação do grupo enviada!!");
					}else {
						JOptionPane.showMessageDialog(null, "Erro !!");
					}
					
					break;
				case 8:
					manager = main.createEntityManager();					
					grupoController = new GrupoController();
					
					
					idGrupo = JOptionPane.showInputDialog("Digite o id do grupo ! \n");
					idAmigo = JOptionPane.showInputDialog("Digite o id do membro ! \n");
					MembrosDoGrupo mgb;
					mgb = grupoController.
							pesquisarMembroGrupo(Integer.parseInt(idGrupo), Integer.parseInt(idAmigo),manager);
					
					manager = main.createEntityManager();	
					if (grupoController.removerMembroGrupo(mgb, manager)) {
						
						JOptionPane.showMessageDialog(null, "Membro removido!!");
					}else {
						JOptionPane.showMessageDialog(null, "Erro !!");
					}
					break;
				case 9:
					break;
				case 10:
					manager = main.createEntityManager();					
					userController = new UsuarioController();
					postController = new PostController();
					idAmigo = JOptionPane.showInputDialog("Digite o id do amigo para ver o mural ! \n");

					if (userController.verificaAmizade(userLogado.getId_usuario(), Integer.parseInt(idAmigo), manager)>0) {
						manager = main.createEntityManager();	
					List<Post> lp;
					lp = postController.verMuralUsuario(Integer.parseInt(idAmigo), manager);
					String t = "Mural de posts ! \n";
					if(lp !=null){
						for (Post p : lp) {
							t = t + p.getConteudo()+"\n";
						}
					}
						
					JOptionPane.showMessageDialog(null, t);
					
					}			
					
				
					break;
				case 11:
				default:
					break;
				}
			}

		} while (opt!=999);


	}

}
