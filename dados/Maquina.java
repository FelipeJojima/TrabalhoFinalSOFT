package dados;

import java.util.ArrayList;
import java.util.List;

public class Maquina {

	// Nome da máquina.
	private String nome;

	// Código de identificação único da máquina.
	private int codigoIdentificacao;

	// Estado atual da máquina (OCIOSA, ATIVA, INOPERANTE ou MANUTENCAO).
	private EstadoDaMaquina status = new EstadoDaMaquina(1); // Define como OCIOSA por padrão.

	// Contrato associado à máquina, pode ser nulo caso ainda não exista contrato.
	private ContratoMaquinas contrato = null;

	// Histórico de uso da máquina (lista de registros de uso).
	private List<RegistroDeUso> historicoUso = new ArrayList<RegistroDeUso>();

	// Histórico de manutenção da máquina (lista de registros de manutenção).
	private List<RegistroManutencao> historicoManutencao = new ArrayList<RegistroManutencao>();

	// Gerente responsável pela máquina.
	private Gerente responsavel;

	/**
	 * Construtor da classe Maquina.
	 * 
	 * @param nome        O nome da máquina.
	 * @param codigo      O código único de identificação da máquina.
	 * @param responsavel O gerente responsável pela máquina.
	 */
	public Maquina(String nome, int codigo, Gerente responsavel) {
		this.nome = nome;
		this.codigoIdentificacao = codigo;
		this.responsavel = responsavel;
	}

	// Métodos de acesso (getters)

	/**
	 * Obtém o código de identificação da máquina.
	 * 
	 * @return O código de identificação da máquina.
	 */
	public int getCodigoIdentificacao() {
		return codigoIdentificacao;
	}

	/**
	 * Obtém o histórico de manutenção da máquina.
	 * 
	 * @return Uma lista de registros de manutenção.
	 */
	public List<RegistroManutencao> getHistoricoManutencao() {
		return historicoManutencao;
	}

	/**
	 * Obtém o histórico de uso da máquina.
	 * 
	 * @return Uma lista de registros de uso.
	 */
	public List<RegistroDeUso> getHistoricoUso() {
		return historicoUso;
	}

	/**
	 * Obtém o gerente responsável pela máquina.
	 * 
	 * @return O gerente responsável.
	 */
	public Gerente getResponsavel() {
		return responsavel;
	}

	/**
	 * Obtém o contrato associado à máquina.
	 * 
	 * @return O contrato da máquina, ou null se não houver contrato.
	 */
	public ContratoMaquinas getSituacao() {
		return contrato;
	}

	/**
	 * Obtém o estado atual da máquina.
	 * 
	 * @return O estado da máquina (OCIOSA, ATIVA, INOPERANTE ou MANUTENCAO).
	 */
	public EstadoDaMaquina getStatus() {
		return status;
	}

	/**
	 * Obtém o nome da máquina.
	 * 
	 * @return O nome da máquina.
	 */
	public String getNome() {
		return nome;
	}

	// Métodos de modificação (setters)

	/**
	 * Define o nome da máquina.
	 * 
	 * @param nome O novo nome da máquina.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Define o gerente responsável pela máquina.
	 * 
	 * @param responsavel O novo gerente responsável.
	 */
	public void setResponsavel(Gerente responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * Associa um novo contrato à máquina.
	 * 
	 * @param contrato O novo contrato da máquina.
	 */
	public void novoContrato(ContratoMaquinas contrato) {
		this.contrato = contrato;
	}

	/**
	 * Altera o estado da máquina.
	 * 
	 * @param status O novo estado da máquina.
	 */
	public void setStatus(EstadoDaMaquina status) {
		this.status = status;
	}

	// Métodos de manipulação de registros

	/**
	 * Adiciona um novo registro de uso ao histórico.
	 * 
	 * @param r O registro de uso a ser adicionado.
	 * @return 1 se o registro foi adicionado com sucesso, 0 caso contrário.
	 */
	public int adicionaRegistroUso(RegistroDeUso r) {
		if (historicoUso.add(r)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Adiciona um novo registro de manutenção ao histórico.
	 * 
	 * @param r O registro de manutenção a ser adicionado.
	 * @return 1 se o registro foi adicionado com sucesso, 0 caso contrário.
	 */
	public int adicionaRegistroManutencao(RegistroManutencao r) {
		if (historicoManutencao.add(r)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Remove um registro de uso do histórico.
	 * 
	 * @param r O registro de uso a ser removido.
	 * @return 1 se o registro foi removido com sucesso, 0 caso contrário.
	 */
	public int removeRegistroUso(RegistroDeUso r) {
		if (historicoUso.remove(r)) {
			return 1;
		}
		return 0;
	}

	/**
	 * Remove um registro de manutenção do histórico.
	 * 
	 * @param r O registro de manutenção a ser removido.
	 * @return 1 se o registro foi removido com sucesso, 0 caso contrário.
	 */
	public int removeRegistroManutencao(RegistroManutencao r) {
		if (historicoManutencao.remove(r)) {
			return 1;
		}
		return 0;
	}

	// Representação textual da máquina

	/**
	 * Retorna uma string representando os dados da máquina, incluindo seu nome,
	 * situação contratual, estado atual, código de identificação e gerente
	 * responsável.
	 * 
	 * @return Uma string com os dados da máquina.
	 */
	@Override
	public String toString() {
		return "Maquina:\nNome: " + this.getNome() + this.getSituacao().toString() + this.getStatus().toString()
				+ "Codigo: " + this.getCodigoIdentificacao() + "\nGerente Responsavel: "
				+ this.getResponsavel().getNome() + "\n";
	}
}
