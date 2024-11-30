package dados;

public class Registro {

	private Data data;
	private Gerente responsavel;
	private Funcionario funcionario;
	private Maquina maquina;

	public Registro(Data data, Gerente gerente, Funcionario funcionario, Maquina maquina) {
		this.data = data;
		this.responsavel = gerente;
		this.funcionario = funcionario;
		this.maquina = maquina;
	}

	public Data getData() {
		return data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public Gerente getResponsavel() {
		return responsavel;
	}
}
