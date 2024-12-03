package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.ContratoFuncionario;
import dados.Funcionario;
import dados.Maquina;
import dados.RegistroDeUso;
import dados.RegistroManutencao;
import dados.RelatorioFuncionario;
import dados.RelatorioMaquina;
import dados.ContratoMaquinas;
import dados.Data;
import dados.EstadoDaMaquina;
import dados.Usuario;
import dados.Gerente;

public class Sistema {

	// Singleton: Garante que apenas uma instância do Sistema seja criada.
	private static Sistema instance = null;

	// Lista de máquinas registradas no sistema.
	private List<Maquina> maquinas = new ArrayList<>();

	// Gerencia autenticação e controle de usuários.
	private Login login = new Login();

	// Lista de usuários que modificaram as máquinas recentemente.
	private List<CadastroUsuario> ultimoAMexer = new ArrayList<>();

	// Lista de datas das últimas modificações em máquinas.
	private List<Data> ultimaModificacao = new ArrayList<>();

	// Construtor padrão. Necessário para inicializar o singleton.
	public Sistema() {
	}

	/**
	 * Obtém a instância única do Sistema (padrão Singleton).
	 * 
	 * @return instância do Sistema.
	 */
	public static Sistema getInstance() {
		if (instance == null) {
			instance = new Sistema();
		}
		return instance;
	}

	/**
	 * Associa um contrato a um funcionário.
	 * 
	 * @param f        Funcionário a ser associado ao contrato.
	 * @param contrato Contrato a ser vinculado.
	 * @return 1 se o contrato foi associado com sucesso, 0 caso contrário.
	 */
	public int novoContratoFuncionario(Funcionario f, ContratoFuncionario contrato) {
		if (f == contrato.getContratado()) {
			f.setContrato(contrato); // Associa o contrato ao funcionário.
			return 1; // Sucesso.
		}
		return 0; // Falha: o contrato não pertence ao funcionário fornecido.
	}

	/**
	 * Associa um contrato a uma máquina.
	 * 
	 * @param m        Máquina a ser associada ao contrato.
	 * @param contrato Contrato a ser vinculado.
	 * @return 1 se o contrato foi associado com sucesso, 0 caso contrário.
	 */
	public int novoContratoMaquina(Maquina m, ContratoMaquinas contrato) {
		if (contrato.getMaquina() == m) {
			m.novoContrato(contrato); // Associa o contrato à máquina.
			return 1; // Sucesso.
		}
		return 0; // Falha: o contrato não pertence à máquina fornecida.
	}

	/**
	 * Adiciona uma nova máquina ao sistema e associa a um gerente.
	 * 
	 * @param m          Máquina a ser registrada.
	 * @param diaCriacao Data de criação da máquina.
	 * @param gerente    Gerente responsável pela máquina.
	 * @return 1 se a máquina foi registrada com sucesso, 0 caso contrário.
	 */
	public int novaMaquina(Maquina m, Data diaCriacao, CadastroUsuario gerente) {
		if (gerente.getUsuario() instanceof Funcionario) {
			return 0; // Falha: apenas gerentes podem adicionar máquinas.
		}
		Gerente g = (Gerente) gerente.getUsuario();
		g.adicionaMaquina(m); // Registra a máquina no nome do gerente.
		maquinas.add(m); // Adiciona à lista de máquinas do sistema.
		ultimaModificacao.add(diaCriacao); // Registra a data de criação.
		ultimoAMexer.add(gerente); // Associa o usuário responsável.
		return 1; // Sucesso.
	}

	/**
	 * Substitui uma máquina existente por outra.
	 * 
	 * @param nova   Máquina que substituirá a existente.
	 * @param antiga Máquina a ser substituída.
	 * @return 1 se a substituição foi bem-sucedida, 0 caso contrário.
	 */
	public int alteraMaquina(Maquina nova, Maquina antiga) {
		for (Maquina maquina : maquinas) {
			if (maquina == antiga) {
				int index = maquinas.indexOf(maquina);
				maquinas.set(index, nova); // Substitui a máquina.
				return 1; // Sucesso.
			}
		}
		return 0; // Falha: a máquina antiga não foi encontrada.
	}

	/**
	 * Ativa o uso de uma máquina, alterando seu estado para "ativa".
	 * 
	 * @param m      Máquina a ser ativada.
	 * @param u      Usuário que está ativando a máquina.
	 * @param inicio Data de início do uso.
	 * @return 1 se a máquina foi ativada, 0 caso contrário.
	 */
	public int iniciaUso(Maquina m, CadastroUsuario u, Data inicio) {
		if (m.getStatus().getEstado() == EstadoDaMaquina.OCIOSA) { // Apenas máquinas ociosas podem ser ativadas.
			int index = maquinas.indexOf(m);
			ultimaModificacao.set(index, inicio); // Atualiza a última modificação.
			ultimoAMexer.set(index, u); // Registra o responsável.
			m.setStatus(new EstadoDaMaquina(EstadoDaMaquina.ATIVA)); // Altera o estado para "ativa".
			return 1; // Sucesso.
		}
		return 0; // Falha: máquina não está ociosa.
	}

	/**
	 * Finaliza o uso de uma máquina, alterando seu estado para "ociosa" e
	 * registrando o uso.
	 * 
	 * @param m      Máquina cujo uso será finalizado.
	 * @param u      Usuário que está finalizando o uso.
	 * @param fim    Data de finalização.
	 * @param codigo Código associado ao uso.
	 * @return 0 para falha e 1 para sucesso.
	 * 
	 */
	public int finalizaUso(Maquina m, CadastroUsuario u, Data fim, int codigo) {
		int index = maquinas.indexOf(m);
		if (index == -1) {
			return 0;
		}
		EstadoDaMaquina estadoFinal = new EstadoDaMaquina(EstadoDaMaquina.OCIOSA); // Define estado final.
		Usuario usuario = u.getUsuario();
		RegistroDeUso registro;

		if (usuario instanceof Funcionario) {
			Funcionario f = (Funcionario) usuario;
			registro = new RegistroDeUso(ultimaModificacao.get(index), f.getResponsavel(), f, m, fim, codigo);
			f.adicionaRegistroUso(registro);
		} else {
			Gerente g = (Gerente) usuario;
			registro = new RegistroDeUso(ultimaModificacao.get(index), g, null, m, fim, codigo);
		}
		m.adicionaRegistroUso(registro); // Registra o uso na máquina.
		ultimaModificacao.set(index, fim); // Atualiza a data de modificação.
		ultimoAMexer.set(index, u); // Atualiza quem foi o último a mexer.
		m.setStatus(estadoFinal); // Define a máquina como "ociosa".
		return 1;
	}

	/**
	 * Inativa uma máquina, alterando seu estado para "inoperante".
	 * 
	 * @param m      Máquina a ser inativada.
	 * @param u      Usuário que está inativando a máquina.
	 * @param fim    Data de inativação.
	 * @param codigo Código associado à inativação.
	 * @return 0 para falha e 1 para sucesso.
	 */
	public int inativaMaquina(Maquina m, CadastroUsuario u, Data fim, int codigo) {
		int index = maquinas.indexOf(m);
		if (index == -1) {
			return 0;
		}
		EstadoDaMaquina estadoFinal = new EstadoDaMaquina(EstadoDaMaquina.INOPERANTE); // Define estado final.
		Usuario usuario = u.getUsuario();

		if (m.getStatus().getEstado() == EstadoDaMaquina.ATIVA) { // Se estava ativa, registra o uso anterior.
			RegistroDeUso registro;
			if (usuario instanceof Funcionario) {
				Funcionario f = (Funcionario) usuario;
				registro = new RegistroDeUso(ultimaModificacao.get(index), f.getResponsavel(), f, m, fim, codigo);
				f.adicionaRegistroUso(registro);
			} else {
				Gerente g = (Gerente) usuario;
				registro = new RegistroDeUso(ultimaModificacao.get(index), g, null, m, fim, codigo);
			}
			m.adicionaRegistroUso(registro); // Registra o uso na máquina.
		}

		ultimaModificacao.set(index, fim); // Atualiza a última modificação.
		ultimoAMexer.set(index, u); // Atualiza quem foi o último a mexer.
		m.setStatus(estadoFinal); // Define a máquina como "inoperante".
		return 1;
	}

	/**
	 * Coloca uma máquina em manutenção.
	 * 
	 * @param m           Máquina a ser colocada em manutenção.
	 * @param u           Usuário que está colocando a máquina em manutenção.
	 * @param inicio      Data de início da manutenção.
	 * @param expectativa Data prevista de término.
	 * @param codigo      Código associado à manutenção.
	 * @return 0 se existe a maquina ou a operação deu falha e 1 para sucesso.
	 */
	public int manutencaoMaquina(Maquina m, CadastroUsuario u, Data inicio, Data expectativa, int codigo) {
		int indexM = this.maquinas.indexOf(m);
		if (indexM == -1) {
			return 0;
		}
		EstadoDaMaquina estadoFinal = new EstadoDaMaquina(EstadoDaMaquina.MANUTENCAO);
		EstadoDaMaquina estadoAnterior = new EstadoDaMaquina(EstadoDaMaquina.ATIVA);
		Usuario x = u.getUsuario();
		if (m.getStatus() == estadoAnterior) {
			RegistroDeUso novoRegistro;
			if (x instanceof Funcionario) {
				Funcionario f = (Funcionario) x;
				novoRegistro = new RegistroDeUso(this.ultimaModificacao.get(indexM), f.getResponsavel(), f, m, inicio,
						codigo);
				f.adicionaRegistroUso(novoRegistro);
			} else {
				Gerente g = (Gerente) x;
				novoRegistro = new RegistroDeUso(this.ultimaModificacao.get(indexM), g, null, m, inicio, codigo);
			}
			m.adicionaRegistroUso(novoRegistro);
		}
		RegistroManutencao novaManutencao;
		if (x instanceof Funcionario) {
			Funcionario f = (Funcionario) x;
			novaManutencao = new RegistroManutencao(inicio, f.getResponsavel(), f, m, expectativa);
			f.adicionaRegistroManutencao(novaManutencao);
		} else {
			Gerente g = (Gerente) x;
			novaManutencao = new RegistroManutencao(inicio, g, null, m, expectativa);
		}
		m.adicionaRegistroManutencao(novaManutencao);
		this.ultimaModificacao.set(indexM, inicio);
		this.ultimoAMexer.set(indexM, u);
		m.setStatus(estadoFinal);
		return 1;
	}

	/**
	 * Gera um relatório detalhado sobre uma máquina específica.
	 *
	 * @param m A máquina para a qual o relatório será gerado.
	 * @return Uma string contendo as informações da máquina no formato de
	 *         relatório.
	 */
	public String geraRelatorioMaquina(Maquina m) {
		RelatorioMaquina relatorio = new RelatorioMaquina(m);
		return relatorio.toString();
	}

	/**
	 * Gera um relatório detalhado sobre um funcionário.
	 *
	 * @param f O cadastro do usuário que representa o funcionário.
	 * @return Uma string contendo o relatório do funcionário ou uma mensagem de
	 *         erro se o usuário for um gerente.
	 */
	public String geraRelatorioFuncionario(CadastroUsuario f) {
		Usuario u = f.getUsuario();
		if (u instanceof Gerente) {
			return "Nao eh permitido gerar relatorios de gerentes.\n";
		} else {
			RelatorioFuncionario relatorio = new RelatorioFuncionario((Funcionario) u);
			return relatorio.toString();
		}
	}

	/**
	 * Verifica as credenciais do usuário para autenticação.
	 *
	 * @param usuario O nome de usuário.
	 * @param senha   A senha correspondente ao nome de usuário.
	 * @return 1 se o login for bem-sucedido, 0 caso contrário.
	 */
	public int login(String usuario, String senha) {
		if (login.verificaLogin(usuario, senha) == 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * Registra um novo usuário no sistema.
	 *
	 * @param novo O objeto que contém as informações do novo usuário.
	 * @return 1 se o cadastro for bem-sucedido, 0 caso contrário.
	 */
	public int novoUsuario(CadastroUsuario novo) {
		if (login.novoCadastro(novo) == 1) {
			return 1;
		}
		return 0;
	}

	/**
	 * Exclui um usuário do sistema.
	 *
	 * @param remover O cadastro do usuário que será removido.
	 * @param gerente O cadastro do gerente que está autorizando a exclusão.
	 * @return 1 se a exclusão for bem-sucedida, 0 caso contrário.
	 */
	public int exclueUsuario(CadastroUsuario remover, CadastroUsuario gerente) {
		Usuario x = gerente.getUsuario();
		if (x instanceof Funcionario) {
			return 0; // Apenas gerentes podem excluir usuários.
		}
		Gerente g = (Gerente) x;
		Usuario y = remover.getUsuario();
		if (y instanceof Funcionario) {
			Funcionario f = (Funcionario) y;
			g.removeFuncionario(f); // Remove o funcionário da lista do gerente.
			login.excluiCadastro(remover); // Exclui o cadastro do sistema.
			return 1;
		}
		Gerente novo = (Gerente) y;
		for (Funcionario a : g.getFuncionarios()) {
			novo.adicionaFuncionario(a); // Transfere funcionários para o novo gerente.
			a.setResponsavel(novo); // Atualiza o responsável dos funcionários.
		}
		login.excluiCadastro(remover);
		return 1;
	}

	/**
	 * Remove uma máquina do sistema.
	 *
	 * @param m A máquina a ser excluída.
	 * @param u O cadastro do usuário que está tentando realizar a exclusão.
	 * @return 1 se a exclusão for bem-sucedida, 0 caso contrário.
	 */
	public int exclueMaquina(Maquina m, CadastroUsuario u) {
		if (u.getUsuario() instanceof Funcionario) {
			return 0; // Apenas gerentes podem excluir máquinas.
		}
		Gerente g = (Gerente) u.getUsuario();
		int index = this.maquinas.indexOf(m);
		this.maquinas.remove(m); // Remove a máquina da lista.
		this.ultimaModificacao.remove(index); // Remove a última data de modificação correspondente.
		this.ultimoAMexer.remove(index); // Remove o último usuário que modificou a máquina.
		g.removeMaquina(m); // Remove a máquina da lista de máquinas do gerente.
		return 1;
	}

	/**
	 * Obtém as informações detalhadas de um usuário.
	 *
	 * @param u O cadastro do usuário.
	 * @return Uma string representando as informações do usuário.
	 */
	public String informacoesPessoais(CadastroUsuario u) {
		Usuario usuario = u.getUsuario();
		return usuario.toString();
	}

	/**
	 * Obtém as informações detalhadas de uma máquina.
	 *
	 * @param m A máquina para a qual as informações serão recuperadas.
	 * @return Uma string representando as informações da máquina.
	 */
	public String informacoesMaquina(Maquina m) {
		return m.toString();
	}
	/**
	 * Recupera a classe login do sistema. Fins de teste.
	 * @return A classe Login. (O objeto).
	 */
	public Login getLogin() {
		return login;
	}

}
