package dados;

public class Registro {

	// A data em que o registro foi realizado
	private Data data;

	// O gerente responsável pela supervisão do processo
	private Gerente responsavel;

	// O funcionário que realizou a atividade
	private Funcionario funcionario;

	// A máquina utilizada pelo funcionário para realizar a atividade
	private Maquina maquina;

	/**
	 * Construtor que inicializa um novo registro com os dados fornecidos.
	 * 
	 * @param data        A data em que o registro foi feito.
	 * @param gerente     O gerente responsável pelo processo.
	 * @param funcionario O funcionário que realizou a atividade.
	 * @param maquina     A máquina utilizada na atividade.
	 */
	public Registro(Data data, Gerente gerente, Funcionario funcionario, Maquina maquina) {
		this.data = data;
		this.responsavel = gerente;
		this.funcionario = funcionario;
		this.maquina = maquina;
	}

	/**
	 * Retorna a data do registro.
	 * 
	 * @return O objeto Data que representa a data do registro.
	 */
	public Data getData() {
		return data;
	}

	/**
	 * Retorna o funcionário que realizou a atividade.
	 * 
	 * @return O objeto Funcionario que representa o trabalhador.
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * Retorna a máquina utilizada no registro.
	 * 
	 * @return O objeto Maquina que representa a máquina utilizada.
	 */
	public Maquina getMaquina() {
		return maquina;
	}

	/**
	 * Retorna o gerente responsável pelo processo registrado.
	 * 
	 * @return O objeto Gerente que representa o responsável pelo processo.
	 */
	public Gerente getResponsavel() {
		return responsavel;
	}
}
