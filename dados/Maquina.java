package dados;

import java.util.ArrayList;
import java.util.List;

public class Maquina {

	private String nome;
	private int codigoIdentificacao;
	private EstadoDaMaquina status;
	private ContratoMaquinas contrato;
	private List<RegistroDeUso> historicoUso = new ArrayList<RegistroDeUso>();
	private List<RegistroManutencao> historicoManutencao = new ArrayList<RegistroManutencao>();
	private Gerente responsavel;

	public Maquina(String nome, int codigo, ContratoMaquinas contrato, Gerente responsavel, EstadoDaMaquina estado) {
		this.nome = nome;
		this.codigoIdentificacao = codigo;
		this.status = estado;
		this.contrato = contrato;
		this.responsavel = responsavel;
	}

	public int getCodigoIdentificacao() {
		return codigoIdentificacao;
	}

	public List<RegistroManutencao> getHistoricoManutencao() {
		return historicoManutencao;
	}

	public List<RegistroDeUso> getHistoricoUso() {
		return historicoUso;
	}

	public Gerente getResponsavel() {
		return responsavel;
	}

	public ContratoMaquinas getSituacao() {
		return contrato;
	}

	public EstadoDaMaquina getStatus() {
		return status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setResponsavel(Gerente responsavel) {
		this.responsavel = responsavel;
	}

	public void novoContrato(ContratoMaquinas contrato) {
		this.contrato = contrato;
	}

	public void setStatus(EstadoDaMaquina status) {
		this.status = status;
	}

	public int adicionaRegistroUso(RegistroDeUso r) {
		if (historicoUso.add(r)) {
			return 1;
		}
		return 0;
	}

	public int adicionaRegistroManutencao(RegistroManutencao r) {
		if (historicoManutencao.add(r)) {
			return 1;
		}
		return 0;

	}

	public int removeRegistroUso(RegistroDeUso r) {
		if (historicoUso.remove(r)) {
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

	@Override
	public String toString() {
		return "Maquina:\nNome: " + this.getNome() + this.getSituacao().toString() + this.getStatus().toString()
				+ "Codigo: " + this.getCodigoIdentificacao() + "\nGerente Responsavel: "
				+ this.getResponsavel().getNome() + "\n";
	}
}
