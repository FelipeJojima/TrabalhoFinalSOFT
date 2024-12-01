package dados;

public class SituacaoMaquina {

	// aqui eu tinha pensado em situação de máquina operante e inoperante.

	private int situacaoMaquina;
	public static final int FINANCIADA = 1;
	public static final int ALUGADA = 2;
	public static final int COMPRADA = 3;

	public SituacaoMaquina(int situacao) {
		if (this.verifica(situacao) == 0) {
			this.situacaoMaquina = 0;
		} else {
			this.situacaoMaquina = situacao;
		}
	}

	public int getSituacaoMaquina() {
		return situacaoMaquina;
	}

	public int verifica(int situacao) {
		if (situacao == 1 || situacao == 2 || situacao == 3) {
			return 1;
		}
		return 0;
	}

	protected static final String[] situacao = { "", "FINANCIADA", "ALUGADA", "COMPRADA", };

	@Override
	public String toString() {
		return "Situacao da maquina: " + situacao[this.getSituacaoMaquina()] + "\n";
	}

}
