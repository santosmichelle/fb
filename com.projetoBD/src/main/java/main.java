import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import controller.GrupoController;
import controller.PostController;
import controller.UsuarioController;
import model.Amizade;
import model.BloqueioAmizade;
import model.ComentarioPost;
import model.Grupo;
import model.MembrosDoGrupo;
import model.Post;
import model.PostUsuario;
import model.RespostaComent;
import model.RetornoPost;
import model.SolicAmizade;
import model.SolicMembGrupo;
import model.Usuario;

public class main {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoBD");

	public static EntityManager createEntityManager() {

		return factory.createEntityManager();
	}
	private static Usuario userLogado=null;

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
				try {
					opt = Integer.parseInt(JOptionPane.showInputDialog("Digite um número! \n"
							+ "1 - Logar no sistema \n"
							+ "2 - Cadastrar Usuario \n"
							+ "999 - Sair"));

				} catch (NullPointerException e) {
					throw new RuntimeException("erro ao logar");
				}
				if (opt!=null ) {

					switch (opt) {
					case 1:
						manager = main.createEntityManager();
						user = new Usuario();
						userController = new UsuarioController();

						user.setLogin(JOptionPane.showInputDialog("Digite o login! \n"));
						user.setSenha(JOptionPane.showInputDialog("Digite a senha! \n"));

						try {
							userLogado = userController.login(user, manager);

						}catch (NullPointerException e) {
							throw new RuntimeException("erro ao logar");

						}

						if (userLogado != null ) {

							menuInicial = false;
						}else {
							JOptionPane.showMessageDialog(null,"Erro ao logar");
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
					}
				}
			}

			if(userLogado !=null) {

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
						+ "11 - Aceitar amizade \n"
						+ "12 - Aceitar membros do grupo \n"
						+ "13 - Ver mural do grupo \n"
						+ "14 - Comentar post \n"
						+ "15 - Responder comentário \n"
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
						pu.setId_user_post(Integer.parseInt(idAmigo));
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
					System.out.println(grupo.getNome());		
					if (grupo != null ) {

						JOptionPane.showMessageDialog(null, "id ="+grupo.getId_grupo()
						+"\n Nome ="+grupo.getNome());
					}
					break;
				case 7:

					manager = main.createEntityManager();					
					grupoController = new GrupoController();
					String idGrupo;

					idGrupo = JOptionPane.showInputDialog("Digite o id do grupo para pesquisar! \n");
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
					userController = new UsuarioController();
					postController = new PostController();
					idAmigo = JOptionPane.showInputDialog("Digite o id do amigo para ver o mural ! \n");

					manager = GerarSql.createEntityManager();
					List<RetornoPost>	listRetornoPost =postController.
							verMuralUsuario(Integer.parseInt(idAmigo),userLogado.getId_usuario(), manager);
					manager = GerarSql.createEntityManager();
					List<RetornoPost> listComentPost =postController.buscaComentPostUser(manager);
					manager = GerarSql.createEntityManager();
					List<RetornoPost> listRespComent = postController.buscaRespComentUser(manager);
					
						String msg = "";
						if (listRetornoPost != null) {
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
						} else {
							JOptionPane.showMessageDialog(null,  "não permitido ver mural");
						}
					

					break;
				case 11:
					manager = main.createEntityManager();					
					userController = new UsuarioController();
					List<String>lista = userController.getSolicitAmizades(userLogado.getId_usuario(), manager);
					 msg = "";
					for (String la : lista) {
						msg = msg+ la +" \n";
					}
					
					idAmigo = JOptionPane.showInputDialog("Digite o id do amigo para adicionar :\n\n "+ msg);
					Amizade a = new Amizade(); 
					manager = main.createEntityManager();	
					a.setId_user_logado(userLogado.getId_usuario());
					a.setId_amigo(Integer.parseInt(idAmigo));
					boolean t1 = userController.aceitarAmizade(a, manager);
					manager = main.createEntityManager();
					System.out.println(userController.
							removeSolicitAmizadeAmizade(userLogado.getId_usuario(),Integer.parseInt(idAmigo), manager));
					manager = main.createEntityManager();	
					a.setId_user_logado(Integer.parseInt(idAmigo));
					a.setId_amigo(userLogado.getId_usuario());
					boolean t2 = userController.aceitarAmizade(a, manager);
						

					if (t1 && t2) {

						JOptionPane.showMessageDialog(null, "Amigo add!!");
					}else {
						JOptionPane.showMessageDialog(null, "Erro !!");
					}
					break;
				case 12:
					manager = main.createEntityManager();					
					grupoController = new GrupoController();
					List<String>listaGrupo = grupoController.getSolicitMembros(userLogado.getId_usuario(), manager);
					 msg = "";
					for (String la : listaGrupo) {
						msg = msg+ la +" \n";
					}
					
					idAmigo = JOptionPane.showInputDialog("Digite o id do membro para adicionar :\n\n "+ msg);
					idGrupo = JOptionPane.showInputDialog("Digite o id do grupo : \n\n"+ msg);
					String ehAdmin = JOptionPane.showInputDialog("é admin do grupo (S/N) ? : \n\n"+ msg);
					
					MembrosDoGrupo m = new MembrosDoGrupo();
					
					manager = main.createEntityManager();	
					m.setAdmnistrador(ehAdmin);
					m.setId_grupo(Integer.parseInt(idGrupo));
					m.setId_usuario(Integer.parseInt(idAmigo));
					
					t1 = grupoController.aceitarMembro(m, manager);
					manager = main.createEntityManager();
					grupoController.
					removeSolicitMembro(Integer.parseInt(idGrupo),Integer.parseInt(idAmigo), manager);
					
					if (t1) {

						JOptionPane.showMessageDialog(null, "Membro add!!");
					}else {
						JOptionPane.showMessageDialog(null, "Erro !!");
					}
					break;
				case 13:				
					postController = new PostController();
					idGrupo = JOptionPane.showInputDialog("Digite o id do grupo para ver o mural ! \n");

					manager = GerarSql.createEntityManager();
					List<RetornoPost>	listRetornoPostGrupo =postController.
							verMuralGrupo(Integer.parseInt(idGrupo),userLogado.getId_usuario(), manager);
					manager = GerarSql.createEntityManager();
					List<RetornoPost> listComentPostGrupo =postController.buscaComentPostGrupo(manager);
					manager = GerarSql.createEntityManager();
					List<RetornoPost> listRespComentGrupo = postController.buscaRespComentGrupo(manager);
					
						msg = "";
						if (listRetornoPostGrupo != null) {
							for (RetornoPost pt : listRetornoPostGrupo) {
								msg = msg+pt.getNome()+": "+pt.getConteudo() + " \n  ";
									for (RetornoPost cp : listComentPostGrupo) {
										if (cp.getId_post()==pt.getId_post()) {
											msg = msg+"\t __ "+cp.getNome()+": "+cp.getConteudo() + " \n ";
											
										}
										for (RetornoPost rc : listRespComentGrupo) {
											if (rc.getId_post()==cp.getId_comentario()&&cp.getId_post()==pt.getId_post()) {
												msg = msg+"\t\t ______"+rc.getNome()+": "+rc.getConteudo() + " \n ";
												
											}
										}
										
									}
					
									
								}
								
								JOptionPane.showMessageDialog(null, msg);
						} else {
							JOptionPane.showMessageDialog(null,  "não permitido ver mural");
						}
					

					break;
					
				case 14:				
					postController = new PostController();
					String idPost = JOptionPane.showInputDialog("Digite o id do post para fazer um comentário ! \n");
					ComentarioPost c = new ComentarioPost();
					String conteudoComent = JOptionPane.showInputDialog("Digite  um comentário ! \n");
					c.setConteudo(conteudoComent);
					c.setId_post(Integer.parseInt(idPost));
					c.setId_user_coment(userLogado.getId_usuario());
					
					manager = GerarSql.createEntityManager();
					t1 = postController.salvarComentario(c, manager);
					if(t1) {
						
								JOptionPane.showMessageDialog(null, "Comentário salvo!");
						} else {
							JOptionPane.showMessageDialog(null,  "não permitido ver mural");
						}
					

					break;
					
				case 15:				
					postController = new PostController();
					String idComent = JOptionPane.showInputDialog("Digite o id do comentário para responder ! \n");
					RespostaComent r = new RespostaComent();
					String conteudoResposta = JOptionPane.showInputDialog("Digite uma reposta ! \n");
					r.setConteudo(conteudoResposta);
					r.setId_comentario(Integer.parseInt(idComent));
					r.setId_user_resp(userLogado.getId_usuario());
					
					manager = GerarSql.createEntityManager();
					t1 = postController.salvarResposta(r, manager);
					if(t1) {
						
								JOptionPane.showMessageDialog(null, "Resposta salvo!");
						} else {
							JOptionPane.showMessageDialog(null,  "não permitido ver mural");
						}
					

					break;
					
				default:
					break;
				}
			}

		} while (opt!=999);


	}

}
