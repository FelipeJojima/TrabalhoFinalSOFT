package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Funcionario;
import dados.Gerente;
import dados.Usuario;

public class Login {

	private List<CadastroUsuario> todosUsuarios = new ArrayList<CadastroUsuario>();
	private Usuario usuarioLogado = null;
	private int log = 0;

	public Login(String email, String senha) {
		verificaLogin(email, senha);
	}

	public Login(CadastroUsuario novoLogin) {
		novoCadastro(novoLogin);
	}

	public int verificaLogin(String email, String senha) {
		for (int i = 0; i < this.todosUsuarios.size(); i++) {
			CadastroUsuario cadastroAnalisado = this.todosUsuarios.get(i);
			Usuario user = cadastroAnalisado.getUsuario();
			if (email == user.getEmail()) {
				if (senha == user.getSenha()) {
					log = 1;
					this.usuarioLogado = user;
					return 1;
				}
			}
		}
		return 0;
	}

	public int getLog() {
		return log;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public int novoCadastro(CadastroUsuario novo) {
		Usuario newUser = novo.getUsuario();
		for (int i = 0; i < this.todosUsuarios.size(); i++) {
			CadastroUsuario cadastroComparado = this.todosUsuarios.get(i);
			Usuario comparado = cadastroComparado.getUsuario();
			if (comparado.getNome() == newUser.getNome()) {
				return 0;
			}
		}

		if (newUser instanceof Funcionario) {
			Gerente g = ((Funcionario) newUser).getResponsavel();
			if (g.adicionaFuncionario((Funcionario) newUser) == 0) {
				return 0;
			}
		}
		this.todosUsuarios.add(novo);
		log = 1;
		this.usuarioLogado = newUser;
		return 1;
	}

	public int excluiCadastro(CadastroUsuario excluido) {
		if (this.todosUsuarios.remove(excluido)) {
			return 1;
		}
		return 0;
	}

}
