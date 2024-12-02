package dados;

public class RegistroManutencao extends Registro {

	// Data prevista para o término da manutenção
	private Data dataFim;

	/**
	 * Construtor para criar um registro de manutenção.
	 * 
	 * @param data        A data de início da manutenção.
	 * @param gerente     O gerente responsável pelo registro.
	 * @param funcionario O funcionário que realizou a manutenção.
	 * @param maquina     A máquina que passou pela manutenção.
	 * @param dataFim     A data prevista para o término da manutenção.
	 */
	public RegistroManutencao(Data data, Gerente gerente, Funcionario funcionario, Maquina maquina, Data dataFim) {
		super(data, gerente, funcionario, maquina); // Inicializa os atributos da superclasse
		this.dataFim = dataFim; // Define a data de término
	}

	/**
	 * Obtém a data prevista para o término da manutenção.
	 * 
	 * @return Um objeto {@code Data} representando a data de término.
	 */
	public Data getDataFim() {
		return dataFim;
	}

	/**
	 * Define ou atualiza a data de término da manutenção.
	 * 
	 * @param dataFim Um objeto {@code Data} representando a nova data de término.
	 */
	public void setDataFim(Data dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Retorna uma representação textual do registro de manutenção, incluindo
	 * informações como a máquina, os responsáveis e as datas envolvidas.
	 * 
	 * @return Uma string contendo os detalhes do registro de manutenção.
	 */
	@Override
	public String toString() {
		return "Registro de manutencao na maquina: " + this.getMaquina().getNome() + "\nRealizada por: "
				+ this.getFuncionario().getNome() + "\nInicio: " + this.getData().toStringFormato1()
				+ "Data final prevista: " + this.getDataFim().toStringFormato1() + "Responsavel: "
				+ this.getResponsavel().getNome() + "\n";
	}

}
