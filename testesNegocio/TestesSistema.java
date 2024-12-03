package testesNegocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dados.ContratoFuncionario;
import dados.ContratoMaquinas;
import dados.Data;
import dados.EstadoDaMaquina;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.SituacaoMaquina;
import negocio.CadastroUsuario;
import negocio.Sistema;

public class TestesSistema {

	private Sistema sistema;
	private Funcionario funcionario;
	private Gerente gerente;
	private Maquina maquina;
	private CadastroUsuario cadastroFuncionario;
	private CadastroUsuario cadastroGerente;
	private Data dataAtual;
	private Data expectativa;

	@BeforeEach
	void setUp() {
		// Configuração inicial antes de cada teste
		sistema = Sistema.getInstance();
		gerente = new Gerente("João Silva", "457.556.161-40", "joao@empresa.com", 8000.0f, new Data(10, 5, 1980), 1,
				"senha123");
		funcionario = new Funcionario("Funcionario Nome", "573.791.347-36", "funcionario@email.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha456");
		maquina = new Maquina("Maquina A", 101, gerente);
		cadastroFuncionario = new CadastroUsuario(funcionario);
		cadastroGerente = new CadastroUsuario(gerente);
		expectativa = new Data(10, 12, 2024);
		dataAtual = new Data(2, 12, 2024); // Data de exemplo
		sistema.novaMaquina(maquina, dataAtual, cadastroGerente);
	}

	@Test
	void testNovoContratoFuncionario() {
		// Criando datas de início e fim válidas
		Data dataInicio = new Data(1, 1, 2023); // 01/01/2023
		Data dataFim = new Data(1, 6, 2023); // 01/06/2023

		// Criando um contrato com a data de início, data de fim, número do contrato,
		// gerente e funcionário
		ContratoFuncionario contrato = new ContratoFuncionario(dataInicio, dataFim, 101, gerente, funcionario, 4000f);
		int resultado = sistema.novoContratoFuncionario(funcionario, contrato);
		assertEquals(1, resultado); // Teste básico: Contrato associado corretamente.

		Funcionario outroFuncionario = new Funcionario("Maria", "654321", "maria.123@gmail.com", 4569f,
				new Data(5, 10, 2002), 99, gerente, "m4ri4");
		resultado = sistema.novoContratoFuncionario(outroFuncionario, contrato);
		assertEquals(0, resultado); // Teste de borda: Funcionário diferente do contrato.
	}

	@Test
	void testNovoContratoMaquina() {
		SituacaoMaquina situacao = new SituacaoMaquina(2);
		ContratoMaquinas contrato = new ContratoMaquinas(new Data(1, 1, 2023), new Data(31, 12, 2023), 1001, gerente,
				maquina, situacao, 2000.0f);
		int resultado = sistema.novoContratoMaquina(maquina, contrato);
		assertEquals(1, resultado); // Teste básico: Contrato associado à máquina corretamente.

		Maquina outraMaquina = new Maquina("Maquina 1", 55, gerente);
		resultado = sistema.novoContratoMaquina(outraMaquina, contrato);
		assertEquals(0, resultado); // Teste de borda: Máquina diferente do contrato.
	}

	@Test
	void testNovaMaquina() {
		int resultado = sistema.novaMaquina(maquina, dataAtual, cadastroGerente);
		assertEquals(1, resultado); // Teste básico: Máquina adicionada com sucesso.

		resultado = sistema.novaMaquina(maquina, dataAtual, cadastroFuncionario);
		assertEquals(0, resultado); // Teste de borda: Funcionário não pode adicionar máquina.
	}

	@Test
	void testIniciaUso() {
		sistema.novaMaquina(maquina, dataAtual, cadastroGerente); // Adiciona a máquina ao sistema.
		maquina.setStatus(new EstadoDaMaquina(EstadoDaMaquina.OCIOSA));
		int resultado = sistema.iniciaUso(maquina, cadastroGerente, dataAtual);
		assertEquals(1, resultado); // Teste básico: Máquina ativada corretamente.

		maquina.setStatus(new EstadoDaMaquina(EstadoDaMaquina.ATIVA));
		resultado = sistema.iniciaUso(maquina, cadastroGerente, dataAtual);
		assertEquals(0, resultado); // Teste de borda: Máquina já está ativa.
	}

	@Test
	void testFinalizaUso() {
		sistema.novaMaquina(maquina, dataAtual, cadastroGerente); // Adiciona a máquina ao sistema.
		sistema.finalizaUso(maquina, cadastroGerente, dataAtual, 123);
		assertEquals(EstadoDaMaquina.OCIOSA, maquina.getStatus().getEstado()); // Verifica se a máquina foi marcada como
																				// "ociosa".
	}

	@Test
	void testInativaMaquina() {
		sistema.novaMaquina(maquina, dataAtual, cadastroGerente); // Adiciona a máquina ao sistema.
		sistema.inativaMaquina(maquina, cadastroGerente, dataAtual, 456);
		assertEquals(EstadoDaMaquina.INOPERANTE, maquina.getStatus().getEstado()); // Verifica se a máquina foi marcada
																					// como "inoperante".
	}

	@Test
	void testGeraRelatorioFuncionario() {
		sistema.novoUsuario(cadastroFuncionario); // Registra o funcionário no sistema.
		String relatorio = sistema.geraRelatorioFuncionario(cadastroFuncionario);
		assertNotNull(relatorio); // Teste básico: Relatório foi gerado.

		String relatorioGerente = sistema.geraRelatorioFuncionario(cadastroGerente);
		assertEquals("Nao eh permitido gerar relatorios de gerentes.\n", relatorioGerente); // Teste de borda: Não é
																							// permitido gerar relatório
																							// de gerente.
	}

	@Test
	void testLogin() {
		sistema.novoUsuario(cadastroFuncionario); // Registra o funcionário no sistema.
		int resultado = sistema.login("funcionario@email.com", "senha456");
		assertEquals(1, resultado); // Teste básico: Login bem-sucedido.

		resultado = sistema.login("funcionario@email.com", "senhaErrada");
		assertEquals(0, resultado); // Teste de borda: Senha incorreta.
	}

	@Test
	void testExclueMaquina() {
		sistema.novaMaquina(maquina, dataAtual, cadastroGerente); // Adiciona a máquina ao sistema.
		int resultado = sistema.exclueMaquina(maquina, cadastroGerente);
		assertEquals(1, resultado); // Teste básico: Máquina excluída corretamente.

		resultado = sistema.exclueMaquina(maquina, cadastroFuncionario);
		assertEquals(0, resultado); // Teste de borda: Funcionário não pode excluir máquina.
	}

	@Test
	void testInformacoesPessoais() {
		String info = sistema.informacoesPessoais(cadastroFuncionario);
		assertNotNull(info); // Teste básico: Informações foram retornadas.
	}

	@Test
	void testInformacoesMaquina() {
		sistema.novaMaquina(maquina, dataAtual, cadastroGerente); // Adiciona a máquina ao sistema.
		String info = sistema.informacoesMaquina(maquina);
		assertNotNull(info); // Teste básico: Informações da máquina foram retornadas.
	}

	@Test
	public void testManutencaoMaquinaComFuncionario() {
		// Realiza a manutenção com um funcionário
		int result = sistema.manutencaoMaquina(maquina, cadastroFuncionario, dataAtual, expectativa, 1001);

		assertEquals(1, result, "Deveria ter realizado a operacao!");
		// Verifica se o status da máquina mudou para MANUTENCAO
		assertEquals(EstadoDaMaquina.MANUTENCAO, maquina.getStatus().getEstado());

		// Verifica se o registro de manutenção foi adicionado
		assertEquals(1, maquina.getHistoricoManutencao().size());
	}

	@Test
	public void testManutencaoMaquinaComGerente() {
		// Realiza a manutenção com um gerente
		sistema.manutencaoMaquina(maquina, cadastroGerente, dataAtual, expectativa, 1002);

		// Verifica se o status da máquina mudou para MANUTENCAO
		assertEquals(EstadoDaMaquina.MANUTENCAO, maquina.getStatus().getEstado());

		// Verifica se o registro de manutenção foi adicionado
		assertEquals(1, maquina.getHistoricoManutencao().size());
	}

	@Test
	public void testManutencaoMaquinaComEstadoInativo() {
		// Máquina já inativa, não deve realizar a manutenção
		maquina.setStatus(new EstadoDaMaquina(EstadoDaMaquina.INOPERANTE));
		int verify = maquina.getHistoricoManutencao().size();
		// Tenta realizar a manutenção
		sistema.manutencaoMaquina(maquina, cadastroFuncionario, dataAtual, expectativa, 1003);

		// Verifica que a manutenção foi registrada
		assertEquals(verify+1, maquina.getHistoricoManutencao().size());
	}

	@Test
	public void testGeraRelatorioMaquina() {
		// Cria um objeto Maquina com nome e identificador
		Maquina maquina = new Maquina("Maquina 1", 55, gerente);

		// Chama o método para gerar o relatório
		String relatorio = sistema.geraRelatorioMaquina(maquina);

		// Verifica se o relatório contém as informações esperadas
		assertTrue(relatorio.contains("Maquina 1"));
	}

	@Test
	public void testExclueUsuarioFuncionario() {
		// Cria um funcionário e um gerente
		Funcionario funcionario = new Funcionario("João", "12345", "userFunc", 0, dataAtual, 0, gerente, "123");
		Gerente gerente = new Gerente("Carlos", "67890", "userGerente", 0, dataAtual, 100, "456");

		CadastroUsuario usuarioFuncionario = new CadastroUsuario(funcionario);
		CadastroUsuario usuarioGerente = new CadastroUsuario(gerente);

		// Cadastra o usuário
		sistema.novoUsuario(usuarioFuncionario);

		// Exclui o usuário
		int resultado = sistema.exclueUsuario(usuarioFuncionario, usuarioGerente);

		// Verifica se a exclusão foi bem-sucedida
		assertEquals(1, resultado);

		// Verifica que o usuário foi removido
		assertFalse(sistema.getLogin().getTodosUsuarios().contains(usuarioFuncionario));
	}

	@Test
	public void testExclueUsuarioSemPermissao() {
		// Cria um funcionário que tenta excluir outro usuário
		Funcionario funcionario = new Funcionario("João", "12345", "userFunc", 0, dataAtual, 0, gerente, "123");
		Gerente gerente = new Gerente("Carlos", "67890", "userGerente", 0, dataAtual, 100, "456");

		CadastroUsuario usuarioFuncionario = new CadastroUsuario(funcionario);
		CadastroUsuario usuarioGerente = new CadastroUsuario(gerente);

		// Tenta excluir o gerente
		int resultado = sistema.exclueUsuario(usuarioGerente, usuarioFuncionario);

		// Verifica que a exclusão não foi permitida
		assertEquals(0, resultado);
	}
}
