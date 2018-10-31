import javax.swing.JOptionPane;

import controller.UsuarioController;
import model.Usuario;

public class main {

	public static void main(String[] args) {
		String dados = null;
		Integer opt = null;
		Usuario user = null;
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

					user = new Usuario();

					user.setLogin(JOptionPane.showInputDialog("Digite o login! \n"));
					user.setSenha(JOptionPane.showInputDialog("Digite a senha! \n"));
					
					if (!user.getLogin().equals("")) {
						
						menuInicial = false;
					}
					break;
				case 2:
					user = new Usuario();
					user.setId_usuario(Integer.parseInt(JOptionPane.showInputDialog("Digite o id \n"))); // n é auto incremental?
					user.setNome(JOptionPane.showInputDialog("Digite o nome! \n"));
					user.setData_nascimento(JOptionPane.showInputDialog("Digite a data de nascimento! \n"));
					user.setFoto(JOptionPane.showInputDialog("Insira uma foto! \n"));
					user.setLogin(JOptionPane.showInputDialog("Digite o login! \n"));
					user.setSenha(JOptionPane.showInputDialog("Digite a senha! \n"));
					user.setVisibilidade(JOptionPane.showInputDialog("Digite a visibilidade! \n"));


				default:
					break;
				}}

			if(!user.getLogin().equals("")) {
				
				opt = Integer.parseInt(JOptionPane.showInputDialog("Digite um número! \n"
						+ "1 - Digite o nome do usuario para pesquisar \n"
						+ "2 - Postar no mural de usuario \n"
						+ "3 - Criar grupos \n"
						+ "4 - Pesquisar grupos \n"
						+ "5 - Participar de um grupo \n"
						+ "6 - Gerenciar grupos \n"
						+ "digite 999 p sair"));

				
				switch (opt) {
				case 1:
					
				
					user = new Usuario();

					user.setLogin(JOptionPane.showInputDialog("Digite o login! \n"));
					user.setSenha(JOptionPane.showInputDialog("Digite a senha! \n"));

					break;
				case 2:
					user = new Usuario();
					user.setNome(JOptionPane.showInputDialog("Digite o nome! \n"));
					user.setData_nascimento(JOptionPane.showInputDialog("Digite a data de nascimento! \n"));
					user.setFoto(JOptionPane.showInputDialog("Insira uma foto! \n"));
					user.setLogin(JOptionPane.showInputDialog("Digite o login! \n"));
					user.setSenha(JOptionPane.showInputDialog("Digite a senha! \n"));
					user.setVisibilidade(JOptionPane.showInputDialog("Digite a visibilidade! \n"));
					menuInicial = false;

				default:
					break;
				}
			}

		} while (opt!=999);


	}

}
