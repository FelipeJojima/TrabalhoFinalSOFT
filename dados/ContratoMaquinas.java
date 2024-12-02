package dados;

public class ContratoMaquinas extends Contrato {

	// Atributos específicos da classe ContratoMaquinas
	private Maquina maquina; // A máquina associada ao contrato
	private SituacaoMaquina situacao; // A situação atual da máquina (ex: disponível, em manutenção, etc.)
	private float valorMensal; // O valor mensal do contrato

	/**
	 * Construtor da classe ContratoMaquinas. Inicializa todos os atributos da
	 * classe.
	 * 
	 * @param inicio   Data de início do contrato
	 * @param fim      Data de fim do contrato
	 * @param numero   Número do contrato
	 * @param gerente  Gerente responsável pelo contrato
	 * @param maquina  Máquina associada ao contrato
	 * @param situacao Situação atual da máquina
	 * @param valor    Valor mensal do contrato
	 */
	public ContratoMaquinas(Data inicio, Data fim, int numero, Gerente gerente, Maquina maquina,
			SituacaoMaquina situacao, float valor) {
		// Chama o construtor da classe pai (Contrato) para inicializar os atributos
		// gerais
		super(inicio, fim, numero, gerente);

		// Inicializa os atributos específicos da classe ContratoMaquinas
		this.maquina = maquina;
		this.situacao = situacao;
		this.valorMensal = valor;
	}

	/**
	 * Retorna a máquina associada ao contrato.
	 * 
	 * @return A máquina do contrato
	 */
	public Maquina getMaquina() {
		return maquina;
	}

	/**
	 * Retorna a situação atual da máquina.
	 * 
	 * @return A situação da máquina
	 */
	public SituacaoMaquina getSituacao() {
		return situacao;
	}

	/**
	 * Retorna o valor mensal do contrato.
	 * 
	 * @return O valor mensal
	 */
	public float getValorMensal() {
		return valorMensal;
	}

	/**
	 * Define um novo valor mensal para o contrato.
	 * 
	 * @param valorMensal O novo valor mensal
	 */
	public void setValorMensal(float valorMensal) {
		this.valorMensal = valorMensal;
	}

	/**
	 * Define a situação atual da máquina.
	 * 
	 * @param situacao A nova situação da máquina
	 */
	public void setSituacao(SituacaoMaquina situacao) {
		this.situacao = situacao;
	}

	/**
	 * Retorna uma representação em string do contrato da máquina, incluindo
	 * detalhes como o nome da máquina, situação, valor mensal, datas, e outras
	 * informações.
	 * 
	 * @return A string representando o contrato da máquina
	 */
	@Override
	public String toString() {
		// Retorna uma string com as informações do contrato formatadas
		return "Contrato de maquina:\nMaquina: " + this.getMaquina().getNome() + "\n" + this.getSituacao().toString()
				+ "Valor: " + this.getValorMensal() + "\nData de inicio: " + this.getDataInicio().toStringFormato1()
				+ "Data de fim: " + this.getDataFim().toStringFormato1() + "Meses de contrato: "
				+ this.getMesesContrato() + "\nGerente responsavel: " + this.getResponsavel().getNome()
				+ "\nNumero do contrato: " + this.getNumeroDeContrato() + "\n";
	}
}
