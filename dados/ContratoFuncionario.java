package dados;

public class ContratoFuncionario extends Contrato {

	private Funcionario contratado;
	private float salario;

	public ContratoFuncionario(Data inicio, Data fim, int numero, Gerente gerente, Funcionario funcionario,
			float salario) {
		super(inicio, fim, numero, gerente);
		this.contratado = funcionario;
		this.salario = salario;
	}

	public Funcionario getContratado() {
		return contratado;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Contrato de funcionario:\nFuncionario contratado: " + this.getContratado().getNome() + "\nSalario: "
				+ this.getSalario() + "\nMeses de contrato: " + this.getMesesContrato() + "\nData de inicio: "
				+ this.getDataInicio().toStringFormato1() + "Data de fim: " + this.getDataFim().toStringFormato1()
				+ "Gerente responsavel: " + this.getResponsavel().getNome() + "\nNumero do contrato: "
				+ this.getNumeroDeContrato() + "\n";
	}

}
