package dados;

public class RegistroDeUso extends Registro {

	private Data dataFim;
	private int producao;
	private int porHora;

	public RegistroDeUso(Data data, Gerente gerente, Funcionario funcionario, Maquina maquina, Data dataFim,
			int producao) {
		super(data, gerente, funcionario, maquina);
		this.dataFim = dataFim;
		this.producao = producao;
		this.porHora =  producao/(data.diferencaHoras(dataFim));
	}

	public Data getDataFim() {
		return dataFim;
	}

	public void setProducao(int producao) {
		this.producao = producao;
	}

	public int getProducao() {
		return producao;
	}

	public float getPorHora() {
		return porHora;
	}

	public void setDataFim(Data dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
		return "Registro de uso da maquina: " + this.getMaquina().getNome() + "\nNo dia: "
				+ this.getData().toStringFormato2() + "Até: " + this.getData().toStringFormato2() + "Por: "
				+ this.getFuncionario().getNome() + "\nResponsavel: " + this.getResponsavel().getNome()
				+ "\nRendimento aproximado de: " + this.getProducao() + " por hora\n";
	}
}
