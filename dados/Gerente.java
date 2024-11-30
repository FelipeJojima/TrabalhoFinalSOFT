package dados;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Usuario {

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public Gerente(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo) {
		super(nome, cpf, email, salario, dataDeNascimento, codigo);
	}

	public void adicionaFuncionario(Funcionario f) {
		funcionarios.add(f);
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void removeFuncionario(Funcionario f) {
		this.funcionarios.remove(f);
	}

	@Override
	public String toString() {
		return "Gerente:\nNome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nEmail: " + this.getEmail()
				+ "\nSalario: " + this.getSalario() + "Data de nascimento: "
				+ this.getDataDeNascimento().toStringSomenteData() + "Codigo: " + this.getCodigoDeIdentificacao()
				+ "\n";
	}
}
