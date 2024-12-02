package dados;

import java.util.ArrayList;
import java.util.List;

public class Gerente extends Usuario {

	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public Gerente(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo,
			String senha) {
		super(nome, cpf, email, salario, dataDeNascimento, codigo, senha);
	}

	public int adicionaFuncionario(Funcionario f) {
		if (funcionarios.add(f)) {
			return 1;
		}
		return 0;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public int removeFuncionario(Funcionario f) {
		if (this.funcionarios.remove(f)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Gerente:\nNome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nEmail: " + this.getEmail()
				+ "\nSalario: " + this.getSalario() + "Data de nascimento: "
				+ this.getDataDeNascimento().toStringSomenteData() + "Codigo: " + this.getCodigoDeIdentificacao()
				+ "\n";
	}
}
