package dados;

public class RegistroDeUso extends Registro {

	public RegistroDeUso(Data data, Gerente gerente, Funcionario funcionario, Maquina maquina) {
		super(data, gerente, funcionario, maquina);
	}

	@Override
	public String toString() {
		return "Registro de uso da maquina: " + this.getMaquina().getNome() + "\nNo dia: "
				+ this.getData().toStringFormato2() + "Por: " + this.getFuncionario().getNome() + "\nResponsavel: "
				+ this.getResponsavel().getNome() + "\n";
	}
}
