import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import controller.UsuarioController;
import model.SolicAmizade;
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
						+ "1 - Digite o nome do usuario para pesquisar \n"
						+ "2 - Adicionar usuario \n"
						+ "3 - Postar no mural de usuario \n"
						+ "4 - Criar grupos \n"
						+ "5 - Pesquisar grupos \n"
						+ "6 - Participar de um grupo \n"
						+ "7 - Gerenciar grupos \n"
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
					

				default:
					break;
				}
			}

		} while (opt!=999);


	}

}
