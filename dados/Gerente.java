package dados;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Usuario {

	// Lista que armazena os funcionários sob a responsabilidade do gerente.
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	/**
	 * Construtor para criar um objeto Gerente.
	 *
	 * @param nome             Nome do gerente.
	 * @param cpf              CPF do gerente.
	 * @param email            Email do gerente.
	 * @param salario          Salário do gerente.
	 * @param dataDeNascimento Data de nascimento do gerente.
	 * @param codigo           Código de identificação do gerente.
	 * @param senha            Senha do gerente para autenticação no sistema.
	 */
	public Gerente(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo,
			String senha) {
		super(nome, cpf, email, salario, dataDeNascimento, codigo, senha);
	}

	/**
	 * Adiciona um funcionário à lista de funcionários gerenciados por este gerente.
	 *
	 * @param f O funcionário a ser adicionado.
	 * @return Retorna 1 se o funcionário foi adicionado com sucesso, caso
	 *         contrário, retorna 0.
	 */
	public int adicionaFuncionario(Funcionario f) {
		if (funcionarios.add(f)) {
			return 1; // Indica sucesso na adição.
		}
		return 0; // Indica falha na adição.
	}

	/**
	 * Obtém a lista de funcionários gerenciados por este gerente.
	 *
	 * @return Uma lista contendo os funcionários.
	 */
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * Remove um funcionário da lista de funcionários gerenciados por este gerente.
	 *
	 * @param f O funcionário a ser removido.
	 * @return Retorna 1 se o funcionário foi removido com sucesso, caso contrário,
	 *         retorna 0.
	 */
	public int removeFuncionario(Funcionario f) {
		if (this.funcionarios.remove(f)) {
			return 1; // Indica sucesso na remoção.
		}
		return 0; // Indica falha na remoção.
	}

	/**
	 * Retorna uma representação textual do gerente, incluindo suas principais
	 * informações.
	 *
	 * @return Uma string formatada com as informações do gerente.
	 */
	@Override
	public String toString() {
		return "Gerente:\nNome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nEmail: " + this.getEmail()
				+ "\nSalario: " + this.getSalario() + "Data de nascimento: "
				+ this.getDataDeNascimento().toStringSomenteData() + "Codigo: " + this.getCodigoDeIdentificacao()
				+ "\n";
	}
}
