package dados;

import java.util.ArrayList;
import java.util.List;

public class Maquina {

	private String nome;
	private int codigoIdentificacao;
	private EstadoDaMaquina status;
	private SituacaoMaquina situacao;
	private List<RegistroDeUso> historicoUso = new ArrayList<RegistroDeUso>();
	private List<RegistroManutencao> historicoManutencao = new ArrayList<RegistroManutencao>();
	private Gerente responsavel;

	public Maquina(String nome, int codigo, SituacaoMaquina situacao, Gerente responsavel, EstadoDaMaquina estado) {
		this.nome = nome;
		this.codigoIdentificacao = codigo;
		this.status = estado;
		this.situacao = situacao;
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

	public SituacaoMaquina getSituacao() {
		return situacao;
	}

	public EstadoDaMaquina getStatus() {
		return status;
	}

	public String getNome() {
		return nome;
	}

	public void adicionaRegistroUso(RegistroDeUso r) {
		historicoUso.add(r);
	}

	public void adicionaRegistroManutencao(RegistroManutencao r) {
		historicoManutencao.add(r);
	}

	public void removeRegistroUso(RegistroDeUso r) {
		historicoUso.remove(r);
	}

	public void removeRegistroManutencao(RegistroManutencao r) {
		historicoManutencao.remove(r);
	}

	@Override
	public String toString() {
		return "Maquina:\nNome: " + this.getNome() + this.getSituacao().toString() + this.getStatus().toString()
				+ "Codigo: " + this.getCodigoIdentificacao() + "\nGerente Responsavel: "
				+ this.getResponsavel().getNome() + "\n";
	}
}
