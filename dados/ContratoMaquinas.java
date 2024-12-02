package dados;

public class ContratoMaquinas extends Contrato {

	private Maquina maquina;
	private SituacaoMaquina situacao;
	private float valorMensal;

	public ContratoMaquinas(Data inicio, Data fim, int numero, Gerente gerente, Maquina maquina,
			SituacaoMaquina situacao, float valor) {
		super(inicio, fim, numero, gerente);
		this.maquina = maquina;
		this.situacao = situacao;
		this.valorMensal = valor;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public SituacaoMaquina getSituacao() {
		return situacao;
	}

	public float getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(float valorMensal) {
		this.valorMensal = valorMensal;
	}

	public void setSituacao(SituacaoMaquina situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Contrato de maquina:\nMaquina: " + this.getMaquina().getNome() + "\n" + this.getSituacao().toString()
				+ "Valor: " + this.getValorMensal() + "\nData de inicio: " + this.getDataInicio().toStringFormato1()
				+ "Data de fim: " + this.getDataFim().toStringFormato1() + "Meses de contrato: "
				+ this.getMesesContrato() + "\nGerente responsavel: " + this.getResponsavel().getNome()
				+ "\nNumero do contrato: " + this.getNumeroDeContrato() + "\n";
	}
}
