package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dados.Data;
import dados.Funcionario;
import dados.Gerente;

public class TestesGerente {

	// Criação de objetos auxiliares
	private Gerente criarGerenteBase() {
		return new Gerente("João Silva", "09606320936", "joao@empresa.com", 8000.0f, new Data(10, 5, 1980), 1,
				"senha123");
	}

	private Funcionario criarFuncionario(String nome, int codigo) {
		return new Funcionario(nome, "98765432100", nome.toLowerCase() + "@empresa.com", 3000.0f, new Data(15, 3, 1995),
				codigo, null, "senha456");
	}

	@Test
	public void testGerenteConstrutor() {
		Gerente gerente = criarGerenteBase();
		assertEquals("João Silva", gerente.getNome());
		assertEquals("09606320936", gerente.getCpf());
		assertEquals(8000.0f, gerente.getSalario());
		assertEquals(1, gerente.getCodigoDeIdentificacao());
		assertEquals("joao@empresa.com", gerente.getEmail());
		assertEquals("senha123", gerente.getSenha());
	}

	@Test
	public void testAdicionarFuncionario() {
		Gerente gerente = criarGerenteBase();
		Funcionario funcionario = criarFuncionario("Maria Souza", 2);

		// Teste de adição
		assertEquals(1, gerente.adicionaFuncionario(funcionario));
		List<Funcionario> funcionarios = gerente.getFuncionarios();
		assertEquals(1, funcionarios.size());
		assertEquals(funcionario, funcionarios.get(0));
	}

	@Test
	public void testAdicionarFuncionarioDuplicado() {
		Gerente gerente = criarGerenteBase();
		Funcionario funcionario = criarFuncionario("Maria Souza", 2);

		// Adicionar o mesmo funcionário duas vezes
		gerente.adicionaFuncionario(funcionario);
		assertEquals(1, gerente.adicionaFuncionario(funcionario));
		assertEquals(2, gerente.getFuncionarios().size()); // Permite duplicados
	}

	@Test
	public void testRemoverFuncionarioExistente() {
		Gerente gerente = criarGerenteBase();
		Funcionario funcionario = criarFuncionario("Carlos Almeida", 3);

		gerente.adicionaFuncionario(funcionario);

		// Teste de remoção
		assertEquals(1, gerente.removeFuncionario(funcionario));
		assertTrue(gerente.getFuncionarios().isEmpty());
	}

	@Test
	public void testRemoverFuncionarioNaoExistente() {
		Gerente gerente = criarGerenteBase();
		Funcionario funcionario = criarFuncionario("Carlos Almeida", 3);

		// Remoção sem adicionar
		assertEquals(0, gerente.removeFuncionario(funcionario));
	}


	@Test
	public void testRemoverFuncionarioNull() {
		Gerente gerente = criarGerenteBase();

		// Teste de remoção com funcionário nulo
		assertEquals(0, gerente.removeFuncionario(null)); // Não lança exceção
	}

	@Test
	public void testGetFuncionariosListaVazia() {
		Gerente gerente = criarGerenteBase();
		assertTrue(gerente.getFuncionarios().isEmpty());
	}

	@Test
	public void testToString() {
		Gerente gerente = criarGerenteBase();

		String expected = "Gerente:\nNome: João Silva\nCPF: 09606320936\nEmail: joao@empresa.com\nSalario: 8000.0\nData de nascimento: 10/5/1980\nCodigo: 1\n";
		assertEquals(expected, gerente.toString());
	}

	// Casos de borda
	@Test
	public void testAdicionarVariosFuncionarios() {
		Gerente gerente = criarGerenteBase();

		for (int i = 1; i <= 100; i++) {
			Funcionario funcionario = criarFuncionario("Funcionario " + i, i);
			assertEquals(1, gerente.adicionaFuncionario(funcionario));
		}

		assertEquals(100, gerente.getFuncionarios().size());
	}

}
