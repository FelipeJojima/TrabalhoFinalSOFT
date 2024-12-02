package dados;

public class RegistroManutencao extends Registro {

	private Data dataFim;

	public RegistroManutencao(Data data, Gerente gerente, Funcionario funcionario, Maquina maquina, Data dataFim) {
		super(data, gerente, funcionario, maquina);
		this.dataFim = dataFim;
	}

	public Data getDataFim() {
		return dataFim;
	}

	public void setDataFim(Data dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
		return "Registro de manutencao na maquina: " + this.getMaquina().getNome() + "\nRealizada por: "
				+ this.getFuncionario().getNome() + "\nInicio: " + this.getData().toStringFormato1()
				+ "Data final prevista: " + this.getDataFim().toStringFormato1() + "Responsavel: "
				+ this.getResponsavel().getNome() + "\n";
	}

}
