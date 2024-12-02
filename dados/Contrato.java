package dados;

public class Contrato {

	// Data de início do contrato.
	private Data dataInicio;

	// Data de fim do contrato.
	private Data dataFim;

	// Número identificador do contrato.
	private int numeroDeContrato;

	// Gerente responsável pelo contrato.
	private Gerente responsavel;

	// Duração do contrato em meses.
	private int mesesContrato;

	/**
	 * Construtor da classe Contrato.
	 * 
	 * @param inicio  Data de início do contrato.
	 * @param fim     Data de fim do contrato.
	 * @param numero  Número identificador do contrato.
	 * @param gerente Gerente responsável pelo contrato.
	 */
	public Contrato(Data inicio, Data fim, int numero, Gerente gerente) {
		// Inicializa a data de início.
		this.dataInicio = inicio;

		// Verifica se a data de início é posterior à data de fim.
		if (inicio.comparaData(fim) == 1) {
			// Caso a data de início seja posterior, ajusta a data de fim para ser igual à
			// de início e define a duração como 0.
			this.dataFim = inicio;
			this.mesesContrato = 0;
		} else {
			// Caso contrário, inicializa a data de fim e calcula a duração em meses.
			this.dataFim = fim;
			this.mesesContrato = inicio.mesesDiferenca(fim);
		}

		// Inicializa o número do contrato.
		this.numeroDeContrato = numero;

		// Inicializa o gerente responsável.
		this.responsavel = gerente;
	}

	/**
	 * Retorna a data de fim do contrato.
	 * 
	 * @return Data de fim do contrato.
	 */
	public Data getDataFim() {
		return dataFim;
	}

	/**
	 * Retorna a data de início do contrato.
	 * 
	 * @return Data de início do contrato.
	 */
	public Data getDataInicio() {
		return dataInicio;
	}

	/**
	 * Retorna o gerente responsável pelo contrato.
	 * 
	 * @return Gerente responsável.
	 */
	public Gerente getResponsavel() {
		return responsavel;
	}

	/**
	 * Retorna o número identificador do contrato.
	 * 
	 * @return Número do contrato.
	 */
	public int getNumeroDeContrato() {
		return numeroDeContrato;
	}

	/**
	 * Retorna a duração do contrato em meses.
	 * 
	 * @return Duração do contrato em meses.
	 */
	public int getMesesContrato() {
		return mesesContrato;
	}

	/**
	 * Atualiza a data de fim do contrato e recalcula a duração, se necessário.
	 * 
	 * @param dataFim Nova data de fim do contrato.
	 * @return 1 se a data foi atualizada com sucesso, ou 0 se a atualização não foi
	 *         realizada.
	 */
	public int setDataFim(Data dataFim) {
		// Verifica se a nova data de fim é posterior à data de fim atual.
		if (this.dataFim.comparaData(dataFim) == -1) {
			this.dataFim = dataFim;
			this.mesesContrato = this.dataInicio.mesesDiferenca(this.dataFim);
			return 1;
		} else {
			// Verifica se a nova data de fim é posterior à data de início.
			if (this.dataInicio.comparaData(dataFim) == -1) {
				this.dataFim = dataFim;
				this.mesesContrato = this.dataInicio.mesesDiferenca(this.dataFim);
				return 1;
			}
			// Retorna 0 se a nova data de fim não for válida.
			return 0;
		}
	}

	/**
	 * Atualiza o gerente responsável pelo contrato.
	 * 
	 * @param responsavel Novo gerente responsável.
	 */
	public void setResponsavel(Gerente responsavel) {
		this.responsavel = responsavel;
	}
}