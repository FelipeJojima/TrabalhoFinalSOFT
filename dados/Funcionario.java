package dados;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario {

	private ContratoFuncionario contrato = null;
	private List<RegistroDeUso> historicoUso = new ArrayList<RegistroDeUso>();
	private List<RegistroManutencao> historicoManutencao = new ArrayList<RegistroManutencao>();
	private Gerente responsavel;

	public Funcionario(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo,
			Gerente responsavel, String senha) {
		super(nome, cpf, email, salario, dataDeNascimento, codigo, senha);
		this.responsavel = responsavel;
	}

	public ContratoFuncionario getContrato() {
		return contrato;
	}

	public Gerente getResponsavel() {
		return responsavel;
	}

	public void setContrato(ContratoFuncionario contrato) {
		this.contrato = contrato;
	}

	public void setResponsavel(Gerente responsavel) {
		this.responsavel = responsavel;
	}

	public int adicionaRegistroUso(RegistroDeUso registro) {
		if (historicoUso.add(registro)) {
			return 1;
		}
		return 0;
	}

	public int removeRegistroUso(RegistroDeUso registro) {
		if (historicoUso.remove(registro)) {
			return 1;
		}
		return 0;
	}

	public List<RegistroDeUso> getHistoricoUso() {
		return historicoUso;
	}

	public int adicionaRegistroManutencao(RegistroManutencao r) {
		if (historicoManutencao.add(r)) {
			return 1;
		}
		return 0;
	}

	public int removeRegistroManutencao(RegistroManutencao r) {
		if (historicoManutencao.remove(r)) {
			return 1;
		}
		return 0;
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
