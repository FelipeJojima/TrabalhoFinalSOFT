package testesNegocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import negocio.CadastroUsuario;
import negocio.Login;

public class TestesLogin {

	private Gerente gerente;
	private Funcionario funcionario;
	private CadastroUsuario cadastroFuncionario;
	private Login login;
	private CadastroUsuario cadastroGerente;

	@BeforeEach
	void setUp() {
		// Setup básico para os testes
		gerente = new Gerente("Gerente Teste", "123.456.789-00", "gerente@test.com", 5000, new Data(10, 10, 1980), 1,
				"senha123");
		funcionario = new Funcionario("Funcionario Teste", "987.654.321-00", "funcionario@test.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha123");

		cadastroGerente = new CadastroUsuario(gerente);
		cadastroFuncionario = new CadastroUsuario(funcionario);
	}

	// Teste de login com credenciais corretas
	@Test
	void testLoginValido() {
		login = new Login("gerente@test.com", "senha123");
		assertEquals(1, login.getLog()); // Login válido, o status de login deve ser 1
		assertNotNull(login.getUsuarioLogado()); // O usuário logado deve ser não-nulo
		assertEquals("gerente@test.com", login.getUsuarioLogado().getEmail()); // O email do usuário logado deve ser o
																				// correto
	}

	// Teste de login com credenciais incorretas
	@Test
	void testLoginInvalido() {
		login = new Login("gerente@test.com", "senhaErrada");
		assertEquals(0, login.getLog()); // Login inválido, o status de login deve ser 0
		assertNull(login.getUsuarioLogado()); // O usuário logado deve ser nulo
	}

	// Teste de novo cadastro de usuário
	@Test
	void testCadastroUsuario() {
		login = new Login(cadastroGerente);
		assertEquals(1, login.getLog()); // O cadastro deve ser bem-sucedido, status de login deve ser 1
		assertEquals("gerente@test.com", login.getUsuarioLogado().getEmail()); // O email do gerente deve ser correto
	}

	// Teste de cadastro de usuário já existente (mesmo nome)
	@Test
	void testCadastroUsuarioExistente() {
		login = new Login(cadastroGerente); // Primeira vez que o gerente se cadastra
		assertEquals(1, login.getLog()); // O gerente foi cadastrado com sucesso

		CadastroUsuario cadastroGerente2 = new CadastroUsuario(gerente); // Novo objeto com os mesmos dados
		int resultado = login.novoCadastro(cadastroGerente2); // Tentando cadastrar o mesmo gerente novamente
		assertEquals(0, resultado); // Não deve permitir cadastro de usuário com o mesmo nome
	}

	// Teste de cadastro de funcionário
	@Test
	void testCadastroFuncionario() {
		login = new Login(cadastroFuncionario);
		assertEquals(1, login.getLog()); // O funcionário foi cadastrado com sucesso
		assertEquals("funcionario@test.com", login.getUsuarioLogado().getEmail()); // O email do funcionário deve ser o
																					// correto
	}

	// Teste de exclusão de cadastro
	@Test
	void testExclusaoCadastro() {
		login = new Login(cadastroGerente);
		assertEquals(1, login.getLog()); // O gerente foi cadastrado com sucesso

		int resultado = login.excluiCadastro(cadastroGerente); // Tentando excluir o cadastro do gerente
		assertEquals(1, resultado); // A exclusão deve ser bem-sucedida
		assertEquals(0, login.getLog()); // O login deve ser desfeito após a exclusão
		assertNull(login.getUsuarioLogado()); // Não deve haver usuário logado após a exclusão
	}

	// Teste de tentativa de login com dados de cadastro excluído
	@Test
	void testLoginComCadastroExcluido() {
		login = new Login(cadastroGerente);
		assertEquals(1, login.getLog()); // O gerente foi cadastrado com sucesso

		login.excluiCadastro(cadastroGerente); // Exclui o cadastro do gerente
		login = new Login("gerente@test.com", "senha123"); // Tentando logar com o mesmo email e senha
		assertEquals(0, login.getLog()); // Não deve conseguir logar, pois o cadastro foi excluído
		assertNull(login.getUsuarioLogado()); // O usuário logado deve ser nulo
	}

	// Teste de login com email não cadastrado
	@Test
	void testLoginComEmailNaoCadastrado() {
		login = new Login("naoexistente@test.com", "senha123");
		assertEquals(0, login.getLog()); // Login deve falhar, pois o email não está cadastrado
		assertNull(login.getUsuarioLogado()); // Não deve haver usuário logado
	}

	// Teste de login com senha não cadastrada
	@Test
	void testLoginComSenhaNaoCadastrada() {
		login = new Login("gerente@test.com", "senhaErrada");
		assertEquals(0, login.getLog()); // Login deve falhar, pois a senha está incorreta
		assertNull(login.getUsuarioLogado()); // Não deve haver usuário logado
	}

	// Teste de exclusão de cadastro com usuário não cadastrado
	@Test
	void testExclusaoCadastroNaoExistente() {
		login = new Login(cadastroGerente); // Cadastra o gerente
		assertEquals(1, login.getLog());

		// Exclui o cadastro
		login.excluiCadastro(cadastroGerente);
		int resultado = login.excluiCadastro(cadastroGerente); // Tentando excluir novamente
		assertEquals(0, resultado); // Não deve ser possível excluir um cadastro que já foi excluído
	}
}
