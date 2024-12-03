package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Funcionario;
import dados.Gerente;
import dados.Usuario;

public class Login {
	private List<CadastroUsuario> todosUsuarios = new ArrayList<CadastroUsuario>(); // Lista de todos os usuários
																					// cadastrados
	private Usuario usuarioLogado = null; // Usuario que está logado no sistema
	private int log = 0; // Indicador do status de login: 0 = não logado, 1 = logado

	/**
	 * Construtor da classe Login que tenta realizar o login com email e senha
	 * fornecidos.
	 */
	public Login() {

	}

	/**
	 * Método para verificar se o login com as credenciais fornecidas é válido.
	 * 
	 * @param email Email fornecido para login
	 * @param senha Senha fornecida para login
	 * @return Retorna 1 se o login for bem-sucedido, 0 caso contrário
	 */
	public int verificaLogin(String email, String senha) {
		for (int i = 0; i < this.todosUsuarios.size(); i++) {
			CadastroUsuario cadastroAnalisado = this.todosUsuarios.get(i);
			Usuario user = cadastroAnalisado.getUsuario();
			// Compara se o email e a senha fornecidos correspondem aos dados do usuário
			if (email == user.getEmail()) {
				if (senha == user.getSenha()) {
					log = 1; // Marca o usuário como logado
					this.usuarioLogado = user; // Define o usuário logado
					return 1; // Login bem-sucedido
				}
			}
		}
		return 0; // Login falhou
	}

	/**
	 * Método que retorna o status de login.
	 * 
	 * @return 1 se o usuário está logado, 0 caso contrário
	 */
	public int getLog() {
		return log;
	}

	/**
	 * Método que retorna o usuário que está logado.
	 * 
	 * @return O usuário logado ou null se não houver usuário logado
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * Método que realiza o cadastro de um novo usuário no sistema. Verifica se o
	 * nome do novo usuário já existe e se o responsável (caso seja funcionário)
	 * pode adicionar o novo funcionário.
	 * 
	 * @param novo CadastroUsuario que contém o novo usuário
	 * @return Retorna 1 se o cadastro for bem-sucedido, 0 caso contrário
	 */
	public int novoCadastro(CadastroUsuario novo) {
		Usuario newUser = novo.getUsuario();
		// Verifica se o nome do usuário já existe no sistema
		for (int i = 0; i < this.todosUsuarios.size(); i++) {
			CadastroUsuario cadastroComparado = this.todosUsuarios.get(i);
			Usuario comparado = cadastroComparado.getUsuario();
			if (comparado.getNome() == newUser.getNome()) {
				return 0; // Nome já existe, cadastro falhou
			}
		}

		// Se o usuário for um funcionário, verifica se o gerente pode adicionar o novo
		// funcionário
		if (newUser instanceof Funcionario) {
			Gerente g = ((Funcionario) newUser).getResponsavel();
			if (g.adicionaFuncionario((Funcionario) newUser) == 0) {
				return 0; // Gerente não conseguiu adicionar o funcionário, cadastro falhou
			}
		}
		this.todosUsuarios.add(novo); // Adiciona o novo usuário à lista
		log = 1; // Marca como logado
		this.usuarioLogado = newUser; // Define o usuário logado
		return 1; // Cadastro bem-sucedido
	}

	/**
	 * Método que exclui um cadastro de usuário do sistema.
	 * 
	 * @param excluido CadastroUsuario que será excluído
	 * @return Retorna 1 se a exclusão for bem-sucedida, 0 caso contrário
	 */
	public int excluiCadastro(CadastroUsuario excluido) {
		if (this.todosUsuarios.remove(excluido)) { // Remove o usuário da lista
			this.log = 0;
			this.usuarioLogado = null;
			return 1; // Exclusão bem-sucedida
		}
		return 0; // Exclusão falhou
	}
	/**
	 * Metodo para fazer logoff.
	 */
	public void sair() {
		log = 0;
		usuarioLogado = null;
	}
	
	/**
	 * Metodo que recupera todos os usuarios cadastrados. Fins de teste.
	 * @return Todos os usuarios cadastrados.
	 */
	public List<CadastroUsuario> getTodosUsuarios() {
		return todosUsuarios;
	}
}
