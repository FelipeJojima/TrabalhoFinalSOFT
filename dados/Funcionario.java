package dados;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario {

	// Contrato associado ao funcionário, inicialmente nulo.
	private ContratoFuncionario contrato = null;

	// Lista de registros de uso associados ao funcionário.
	private List<RegistroDeUso> historicoUso = new ArrayList<>();

	// Lista de registros de manutenção associados ao funcionário.
	private List<RegistroManutencao> historicoManutencao = new ArrayList<>();

	// Gerente responsável por supervisionar o funcionário.
	private Gerente responsavel;

	/**
	 * Construtor para criar um funcionário.
	 *
	 * @param nome             Nome do funcionário.
	 * @param cpf              CPF do funcionário.
	 * @param email            Email do funcionário.
	 * @param salario          Salário do funcionário.
	 * @param dataDeNascimento Data de nascimento do funcionário.
	 * @param codigo           Código de identificação do funcionário.
	 * @param responsavel      Gerente responsável pelo funcionário.
	 * @param senha            Senha de acesso do funcionário.
	 */
	public Funcionario(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo,
			Gerente responsavel, String senha) {
		super(nome, cpf, email, salario, dataDeNascimento, codigo, senha); // Chamada ao construtor da classe base.
		this.responsavel = responsavel; // Define o gerente responsável.
	}

	/**
	 * Recupera o contrato associado ao funcionário.
	 *
	 * @return O contrato do funcionário.
	 */
	public ContratoFuncionario getContrato() {
		return contrato;
	}

	/**
	 * Recupera o gerente responsável pelo funcionário.
	 *
	 * @return O gerente responsável.
	 */
	public Gerente getResponsavel() {
		return responsavel;
	}

	/**
	 * Define o contrato do funcionário.
	 *
	 * @param contrato O contrato a ser associado ao funcionário.
	 */
	public void setContrato(ContratoFuncionario contrato) {
		this.contrato = contrato;
	}

	/**
	 * Define o gerente responsável pelo funcionário.
	 *
	 * @param responsavel O gerente a ser definido como responsável.
	 */
	public void setResponsavel(Gerente responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * Adiciona um registro de uso ao histórico do funcionário.
	 *
	 * @param registro O registro de uso a ser adicionado.
	 * @return 1 se o registro foi adicionado com sucesso, 0 caso contrário.
	 */
	public int adicionaRegistroUso(RegistroDeUso registro) {
		if (historicoUso.add(registro)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Remove um registro de uso do histórico do funcionário.
	 *
	 * @param registro O registro de uso a ser removido.
	 * @return 1 se o registro foi removido com sucesso, 0 caso contrário.
	 */
	public int removeRegistroUso(RegistroDeUso registro) {
		if (historicoUso.remove(registro)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Recupera o histórico de uso do funcionário.
	 *
	 * @return A lista de registros de uso.
	 */
	public List<RegistroDeUso> getHistoricoUso() {
		return historicoUso;
	}

	/**
	 * Adiciona um registro de manutenção ao histórico do funcionário.
	 *
	 * @param r O registro de manutenção a ser adicionado.
	 * @return 1 se o registro foi adicionado com sucesso, 0 caso contrário.
	 */
	public int adicionaRegistroManutencao(RegistroManutencao r) {
		if (historicoManutencao.add(r)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Remove um registro de manutenção do histórico do funcionário.
	 *
	 * @param r O registro de manutenção a ser removido.
	 * @return 1 se o registro foi removido com sucesso, 0 caso contrário.
	 */
	public int removeRegistroManutencao(RegistroManutencao r) {
		if (historicoManutencao.remove(r)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Recupera o histórico de manutenção do funcionário.
	 *
	 * @return A lista de registros de manutenção.
	 */
	public List<RegistroManutencao> getHistoricoManutencao() {
		return historicoManutencao;
	}

	/**
	 * Retorna uma representação em string do funcionário, contendo informações como
	 * nome, CPF, email, salário, data de nascimento, código de identificação,
	 * gerente responsável e número do contrato.
	 *
	 * @return Uma string representando o funcionário.
	 */
	@Override
	public String toString() {
		if (this.getContrato() != null) {
			return "Funcionario:\nNome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nEmail: " + this.getEmail()
					+ "\nSalario: " + this.getSalario() + "\nData de nascimento: "
					+ this.getDataDeNascimento().toStringSomenteData() + "Codigo: " + this.getCodigoDeIdentificacao()
					+ "\nGerente responsavel: " + this.getResponsavel().getNome() + "\nNumero do contrato: "
					+ this.getContrato().getNumeroDeContrato() + "\n";
		} else {
			return "Funcionario:\nNome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nEmail: " + this.getEmail()
					+ "\nSalario: " + this.getSalario() + "\nData de nascimento: "
					+ this.getDataDeNascimento().toStringSomenteData() + "Codigo: " + this.getCodigoDeIdentificacao()
					+ "\nGerente responsavel: " + this.getResponsavel().getNome() + "\n";
		}

	}
}
