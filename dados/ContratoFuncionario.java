package dados;

public class ContratoFuncionario extends Contrato {

	// Atributo para armazenar o funcionário contratado no contrato.
	private Funcionario contratado;

	// Atributo para armazenar o salário do funcionário.
	private float salario;

	/**
	 * Construtor da classe que inicializa um contrato de funcionário.
	 * 
	 * @param inicio      Data de início do contrato.
	 * @param fim         Data de fim do contrato.
	 * @param numero      Número do contrato.
	 * @param gerente     Gerente responsável pelo contrato.
	 * @param funcionario Funcionário contratado pelo contrato.
	 * @param salario     Salário do funcionário contratado.
	 */
	public ContratoFuncionario(Data inicio, Data fim, int numero, Gerente gerente, Funcionario funcionario,
			float salario) {
		// Chamada ao construtor da classe base Contrato.
		super(inicio, fim, numero, gerente);

		// Inicializa o funcionário contratado.
		this.contratado = funcionario;

		// Inicializa o salário do funcionário.
		this.salario = salario;
	}

	/**
	 * Retorna o funcionário contratado no contrato.
	 * 
	 * @return Funcionario contratado.
	 */
	public Funcionario getContratado() {
		return contratado;
	}

	/**
	 * Retorna o salário do funcionário contratado.
	 * 
	 * @return Salário do funcionário.
	 */
	public float getSalario() {
		return salario;
	}

	/**
	 * Atualiza o salário do funcionário contratado.
	 * 
	 * @param salario Novo salário do funcionário.
	 */
	public void setSalario(float salario) {
		this.salario = salario;
	}

	/**
	 * Sobrescreve o método toString da classe base para exibir informações
	 * detalhadas sobre o contrato de funcionário.
	 * 
	 * @return Representação textual do contrato de funcionário.
	 */
	@Override
	public String toString() {
		return "Contrato de funcionario:\n" + "Funcionario contratado: " + this.getContratado().getNome() + "\n" + // Nome
																													// do
																													// funcionário.
				"Salario: " + this.getSalario() + "\n" + // Salário do funcionário.
				"Meses de contrato: " + this.getMesesContrato() + "\n" + // Duração do contrato.
				"Data de inicio: " + this.getDataInicio().toStringFormato1() + // Data de início.
				"Data de fim: " + this.getDataFim().toStringFormato1() + // Data de fim.
				"Gerente responsavel: " + this.getResponsavel().getNome() + "\n" + // Nome do gerente responsável.
				"Numero do contrato: " + this.getNumeroDeContrato() + "\n"; // Número do contrato.
	}

}
