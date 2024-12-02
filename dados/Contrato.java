package dados;

public class Contrato {

	private Data dataInicio;
	private Data dataFim;
	private int numeroDeContrato;
	private Gerente responsavel;
	private int mesesContrato;

	public Contrato(Data inicio, Data fim, int numero, Gerente gerente) {
		this.dataInicio = inicio;
		if (inicio.comparaData(fim) == 1) {
			this.dataFim = inicio;
			this.mesesContrato = 0;
		} else {
			this.dataFim = fim;
			this.mesesContrato = inicio.mesesDiferenca(fim);
		}

		this.numeroDeContrato = numero;
		this.responsavel = gerente;

	}

	public Data getDataFim() {
		return dataFim;
	}

	public Data getDataInicio() {
		return dataInicio;
	}

	public Gerente getResponsavel() {
		return responsavel;
	}

	public int getNumeroDeContrato() {
		return numeroDeContrato;
	}

	public int getMesesContrato() {
		return mesesContrato;
	}

	public int setDataFim(Data dataFim) {
		if (this.dataFim.comparaData(dataFim) == -1) {
			this.dataFim = dataFim;
			this.mesesContrato = this.dataInicio.mesesDiferenca(this.dataFim);
			return 1;
		} else {
			if (this.dataInicio.comparaData(dataFim) == -1) {
				this.dataFim = dataFim;
				this.mesesContrato = this.dataInicio.mesesDiferenca(this.dataFim);
				return 1;
			}
			return 0;
		}

	}

	public void setResponsavel(Gerente responsavel) {
		this.responsavel = responsavel;
	}
}
