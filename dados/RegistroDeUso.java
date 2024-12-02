package dados;

public class RegistroDeUso extends Registro {

	// Data que marca o fim do uso da máquina.
	private Data dataFim;
	// Quantidade total produzida durante o uso da máquina.
	private int producao;
	// Produção média por hora durante o período de uso.
	private int porHora;

	/**
	 * Construtor para inicializar os detalhes do registro de uso.
	 * 
	 * @param data        A data inicial do uso.
	 * @param gerente     O gerente responsável pelo registro.
	 * @param funcionario O funcionário que operou a máquina.
	 * @param maquina     A máquina utilizada.
	 * @param dataFim     A data final do uso.
	 * @param producao    A quantidade total produzida durante o uso.
	 */
	public RegistroDeUso(Data data, Gerente gerente, Funcionario funcionario, Maquina maquina, Data dataFim,
			int producao) {
		super(data, gerente, funcionario, maquina);
		this.dataFim = dataFim;
		this.producao = producao;
		// Calcula a produção média por hora com base na diferença entre data inicial e
		// final.
		this.porHora = producao / (data.diferencaHoras(dataFim));
	}

	/**
	 * Retorna a data de término do uso da máquina.
	 * 
	 * @return A data de término.
	 */
	public Data getDataFim() {
		return dataFim;
	}

	/**
	 * Define a quantidade total produzida durante o uso.
	 * 
	 * @param producao A nova quantidade de produção.
	 */
	public void setProducao(int producao) {
		this.producao = producao;
	}

	/**
	 * Retorna a quantidade total produzida durante o uso.
	 * 
	 * @return A produção total.
	 */
	public int getProducao() {
		return producao;
	}

	/**
	 * Retorna a produção média por hora calculada durante o período de uso.
	 * 
	 * @return A produção por hora.
	 */
	public float getPorHora() {
		return porHora;
	}

	/**
	 * Define a data de término do uso da máquina.
	 * 
	 * @param dataFim A nova data de término.
	 */
	public void setDataFim(Data dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Representação textual do registro de uso, contendo informações detalhadas
	 * sobre o uso da máquina, operador, responsável, e eficiência.
	 * 
	 * @return Uma string descrevendo o registro de uso.
	 */
	@Override
	public String toString() {
		return "Registro de uso da máquina: " + this.getMaquina().getNome() + "\nNo dia: "
				+ this.getData().toStringFormato2() + " Até: " + this.getDataFim().toStringFormato2()
				+ "Operada por: " + this.getFuncionario().getNome() + "\nResponsável: "
				+ this.getResponsavel().getNome() + "\nProdução total: " + this.getProducao()
				+ "\nProdução média por hora: " + this.getPorHora() + "\n";
	}
}
