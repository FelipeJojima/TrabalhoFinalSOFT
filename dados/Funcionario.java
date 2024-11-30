package dados;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario {

	private ContratoFuncionario contrato;
	private List<RegistroDeUso> historicoUso = new ArrayList<RegistroDeUso>();
	private List<RegistroManutencao> historicoManutencao = new ArrayList<RegistroManutencao>();
	private Gerente responsavel;

	public Funcionario(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo,
			ContratoFuncionario contrato, Gerente responsavel) {
		super(nome, cpf, email, salario, dataDeNascimento, codigo);
		this.contrato = contrato;
		this.responsavel = responsavel;
	}

	public ContratoFuncionario getContrato() {
		return contrato;
	}

	public Gerente getResponsavel() {
		return responsavel;
	}

	public void adicionaRegistroUso(RegistroDeUso registro) {
		historicoUso.add(registro);
	}

	public void removeRegistroUso(RegistroDeUso registro) {
		historicoUso.remove(registro);
	}

	public List<RegistroDeUso> getHistoricoUso() {
		return historicoUso;
	}

	public void adicionaRegistroManutencao(RegistroManutencao r) {
		historicoManutencao.add(r);
	}

	public void removeRegistroManutencao(RegistroManutencao r) {
		historicoManutencao.remove(r);
	}

	public List<RegistroManutencao> getHistoricoManutencao() {
		return historicoManutencao;
	}

	@Override
	public String toString() {
		return "Funcionario:\nNome: " + this.getNome() + "\nCPF: " + this.getCpf() + "\nEmail: " + this.getEmail()
				+ "\nSalario: " + this.getSalario() + "\nData de nascimento: "
				+ this.getDataDeNascimento().toStringSomenteData() + "Codigo: " + this.getCodigoDeIdentificacao()
				+ "\nGerente responsavel: " + this.getResponsavel().getNome() + "\nNumero do contrato: "
				+ this.getContrato().getNumeroDeContrato() + "\n";
	}
}
