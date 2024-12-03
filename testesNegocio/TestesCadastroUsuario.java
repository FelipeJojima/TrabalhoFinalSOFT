package testesNegocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import dados.ContratoFuncionario;
import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Usuario;
import negocio.CadastroUsuario;

public class TestesCadastroUsuario {

	private Gerente gerente;
	private Funcionario funcionario;
	private CadastroUsuario cadastroGerente;
	private CadastroUsuario cadastroFuncionario;

	@BeforeEach
	public void setUp() {
		// Inicializando dados necessários para os testes
		Data dataNascimento = new Data(15, 6, 1985);
		gerente = new Gerente("João", "123.456.789-00", "joao@empresa.com", 5000, dataNascimento, 1, "senha123");
		funcionario = new Funcionario("Maria", "987.654.321-00", "maria@empresa.com", 3000, dataNascimento, 2, gerente,
				"senha456");

		// Criando objetos CadastroUsuario
		cadastroGerente = new CadastroUsuario(gerente);
		cadastroFuncionario = new CadastroUsuario(funcionario);
	}

	// Teste geral: Verifica se o cadastro de gerente foi criado corretamente
	@Test
	public void testCadastroGerente() {
		Usuario usuario = cadastroGerente.getUsuario();
		assertTrue(usuario instanceof Gerente, "O usuário deve ser um gerente");
		assertEquals("João", usuario.getNome(), "O nome do gerente deve ser João");
		assertEquals("joao@empresa.com", usuario.getEmail(), "O e-mail do gerente deve ser joao@empresa.com");
	}

	// Teste geral: Verifica se o cadastro de funcionario foi criado corretamente
	@Test
	public void testCadastroFuncionario() {
		Usuario usuario = cadastroFuncionario.getUsuario();
		assertTrue(usuario instanceof Funcionario, "O usuário deve ser um funcionario");
		assertEquals("Maria", usuario.getNome(), "O nome do funcionario deve ser Maria");
		assertEquals("maria@empresa.com", usuario.getEmail(), "O e-mail do funcionario deve ser maria@empresa.com");
	}

	// Teste de borda: Altera o nome do gerente
	@Test
	public void testAlteraNomeGerente() {
		cadastroGerente.alteraNome("Carlos");
		assertEquals("Carlos", cadastroGerente.getUsuario().getNome(),
				"O nome do gerente deve ser alterado para Carlos");
	}

	// Teste de borda: Tenta alterar o CPF de um gerente para um CPF inválido
	@Test
	public void testAlteraCpfInvalido() {
		int resultado = cadastroGerente.alteraCpf("000.000.000-00");
		assertEquals(0, resultado, "O CPF deve ser inválido");
	}

	// Teste de borda: Altera o CPF do gerente para um CPF válido
	@Test
	public void testAlteraCpfValido() {
		int resultado = cadastroGerente.alteraCpf("111.222.333-96");
		assertEquals(1, resultado, "O CPF deve ser alterado com sucesso");
		assertEquals("111.222.333-96", cadastroGerente.getUsuario().getCpf(),
				"O CPF do gerente deve ser alterado para 111.222.333-96");
	}

	// Teste geral: Altera o e-mail do funcionario
	@Test
	public void testAlteraEmailFuncionario() {
		cadastroFuncionario.alteraEmail("maria.nova@empresa.com");
		assertEquals("maria.nova@empresa.com", cadastroFuncionario.getUsuario().getEmail(),
				"O e-mail do funcionario deve ser alterado");
	}

	// Teste de borda: Altera o salário do funcionario para 0
	@Test
	public void testAlteraSalarioZero() {
		cadastroFuncionario.alteraSalario(0);
		assertEquals(0, cadastroFuncionario.getUsuario().getSalario(),
				"O salário do funcionário deve ser alterado para 0");
	}

	// Teste geral: Altera o salário do gerente
	@Test
	public void testAlteraSalarioGerente() {
		cadastroGerente.alteraSalario(7000);
		assertEquals(7000, cadastroGerente.getUsuario().getSalario(),
				"O salário do gerente deve ser alterado para 7000");
	}

	// Teste de borda: Tenta alterar contrato de um gerente (deve falhar, pois ele
	// não é funcionário)
	@Test
	public void testAlteraContratoGerente() {
		ContratoFuncionario contrato = new ContratoFuncionario(null, null, 0, gerente, funcionario, 0);
		// Não deve alterar, pois o usuário não é um funcionario
		assertEquals(0, cadastroGerente.alteraContrato(contrato),
				"O gerente nao deve ter contrato alterado. (Nao tem contrato).");
	}

	// Teste geral: Altera o responsável do funcionário
	@Test
	public void testAlteraResponsavelFuncionario() {
		Gerente novoGerente = new Gerente("Carlos", "111.222.333-44", "carlos@empresa.com", 8000, new Data(15, 5, 1980),
				3, "senha789");
		cadastroFuncionario.alteraResponsavel(novoGerente);
		assertEquals(novoGerente, ((Funcionario) cadastroFuncionario.getUsuario()).getResponsavel(),
				"O responsável do funcionário deve ser alterado para Carlos");
	}

	// Teste de borda: Tenta alterar o responsável do gerente (deve falhar, pois ele
	// não tem responsável)
	@Test
	public void testAlteraResponsavelGerente() {
		Gerente novoGerente = new Gerente("Carlos", "111.222.333-44", "carlos@empresa.com", 8000, new Data(15, 5, 1980),
				3, "senha789");
		// Não deve fazer nada, pois o gerente não tem responsável
		assertEquals(0, cadastroGerente.alteraResponsavel(novoGerente), "O gerente não deve ter responsável alterado");
	}

	// Teste geral: Altera a data de nascimento do funcionario
	@Test
	public void testAlteraDataNascimentoFuncionario() {
		Data novaData = new Data(1, 1, 1990);
		cadastroFuncionario.alteraDataNascimento(novaData);
		assertEquals(novaData, cadastroFuncionario.getUsuario().getDataDeNascimento(),
				"A data de nascimento do funcionario deve ser alterada");
	}
}
