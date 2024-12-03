package negocio;

import dados.ContratoFuncionario;
import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Usuario;

public class CadastroUsuario {

	// Atributo para armazenar o usuário, que pode ser um Gerente ou Funcionario
	private Usuario usuario;

	/**
	 * Construtor que cria um novo objeto CadastroUsuario para um Gerente.
	 * Inicializa o objeto 'usuario' com um Gerente.
	 * 
	 * @param g Objeto do tipo Gerente.
	 */
	public CadastroUsuario(Gerente g) {
		this.usuario = new Gerente(g.getNome(), g.getCpf(), g.getEmail(), g.getSalario(), g.getDataDeNascimento(),
				g.getCodigoDeIdentificacao(), g.getSenha());
	}

	/**
	 * Construtor que cria um novo objeto CadastroUsuario para um Funcionario.
	 * Inicializa o objeto 'usuario' com um Funcionario.
	 * 
	 * @param f Objeto do tipo Funcionario.
	 */
	public CadastroUsuario(Funcionario f) {
		this.usuario = new Funcionario(f.getNome(), f.getCpf(), f.getEmail(), f.getSalario(), f.getDataDeNascimento(),
				f.getCodigoDeIdentificacao(), f.getResponsavel(), f.getSenha());
	}

	/**
	 * Retorna o usuário associado ao cadastro. Pode ser um Gerente ou Funcionario.
	 * 
	 * @return O objeto usuario (Gerente ou Funcionario).
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Altera o nome do usuário associado ao cadastro.
	 * 
	 * @param novoNome O novo nome que será atribuído ao usuário.
	 */
	public void alteraNome(String novoNome) {
		this.usuario.setNome(novoNome); // Altera o nome do usuário.
	}

	/**
	 * Altera o CPF do usuário associado ao cadastro. Verifica se o novo CPF é
	 * válido. Retorna 1 se a alteração foi bem-sucedida, ou 0 caso o CPF seja
	 * inválido.
	 * 
	 * @param novoCpf O novo CPF a ser atribuído ao usuário.
	 * @return 1 se o CPF for alterado com sucesso, 0 caso contrário.
	 */
	public int alteraCpf(String novoCpf) {
		if (this.usuario.setCpf(novoCpf) == 1) { // Valida e altera o CPF.
			return 1; // CPF alterado com sucesso.
		}
		return 0; // CPF inválido.
	}

	/**
	 * Altera o e-mail do usuário associado ao cadastro.
	 * 
	 * @param novoEmail O novo e-mail a ser atribuído ao usuário.
	 */
	public void alteraEmail(String novoEmail) {
		this.usuario.setEmail(novoEmail); // Altera o e-mail do usuário.
	}

	/**
	 * Altera o salário do usuário associado ao cadastro.
	 * 
	 * @param novoSalario O novo salário a ser atribuído ao usuário.
	 */
	public void alteraSalario(float novoSalario) {
		this.usuario.setSalario(novoSalario); // Altera o salário do usuário.
	}

	/**
	 * Altera o contrato de um Funcionario. Este método só pode ser chamado se o
	 * usuário associado for um objeto do tipo Funcionario.
	 * 
	 * @param novoContrato O novo contrato a ser atribuído ao funcionário.
	 * @return 0 para falha na alteração do contrato (o usuário é Gerente) e 1 para
	 *         alteração bem-sucedida.
	 */
	public int alteraContrato(ContratoFuncionario novoContrato) {
		if (this.usuario instanceof Funcionario) { // Verifica se o usuário é um Funcionario.
			((Funcionario) this.usuario).setContrato(novoContrato); // Altera o contrato do funcionário.
			return 1;
		}
		return 0;
	}

	/**
	 * Altera o responsável de um Funcionario. Este método só pode ser chamado se o
	 * usuário associado for um objeto do tipo Funcionario.
	 * 
	 * @param novoGerente O novo gerente responsável pelo funcionário.
	 * @return 0 para alteração inválida (o usuário é Gerente) e 1 para alteração
	 *         bem-sucedida.
	 */
	public int alteraResponsavel(Gerente novoGerente) {
		if (this.usuario instanceof Funcionario) { // Verifica se o usuário é um Funcionario.
			((Funcionario) this.usuario).setResponsavel(novoGerente); // Altera o responsável do funcionário.
			return 1;
		}
		return 0;
	}

	/**
	 * Altera a data de nascimento do usuário associado ao cadastro.
	 * 
	 * @param novaData A nova data de nascimento a ser atribuída ao usuário.
	 */
	public void alteraDataNascimento(Data novaData) {
		this.usuario.setDataDeNascimento(novaData); // Altera a data de nascimento do usuário.
	}

}
