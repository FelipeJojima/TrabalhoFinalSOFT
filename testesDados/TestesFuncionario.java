package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dados.ContratoFuncionario;
import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.RegistroDeUso;
import dados.RegistroManutencao;

public class TestesFuncionario {

	private Funcionario funcionario;
	private Gerente gerente;
	private Data dataNascimento;
	private ContratoFuncionario contrato;

	@BeforeEach
	void setUp() {
		// Configuração inicial
		gerente = new Gerente("Gerente Nome", "12345678900", "gerente@email.com", 10000, new Data(1, 1, 1980), 1,
				"senha123");
		dataNascimento = new Data(15, 5, 1990);
		funcionario = new Funcionario("Funcionario Nome", "09876543211", "funcionario@email.com", 3000, dataNascimento,
				2, gerente, "senha456");

		contrato = new ContratoFuncionario(new Data(1, 1, 2023), new Data(1, 6, 2023), 101, gerente, funcionario,
				4000f);
		funcionario.setContrato(contrato);
	}

	@Test
	void testFuncionarioConstrutor() {
		assertNotNull(funcionario);
		assertEquals("Funcionario Nome", funcionario.getNome());
		assertEquals("09876543211", funcionario.getCpf());
		assertEquals(gerente, funcionario.getResponsavel());
	}

	@Test
	void testGetAndSetContrato() {
		ContratoFuncionario novoContrato = new ContratoFuncionario(new Data(1, 1, 2023), new Data(1, 6, 2023), 465,
				gerente, funcionario, 6000f);
		funcionario.setContrato(novoContrato);
		assertEquals(465, funcionario.getContrato().getNumeroDeContrato());
	}

	@Test
	void testGetAndSetResponsavel() {
		Gerente novoGerente = new Gerente("Novo Gerente", "22233344455", "novo@email.com", 12000, new Data(1, 1, 1975),
				3, "novaSenha");
		funcionario.setResponsavel(novoGerente);
		assertEquals("Novo Gerente", funcionario.getResponsavel().getNome());
	}

	@Test
	void testAdicionaRegistroUso() {
		RegistroDeUso registro = new RegistroDeUso(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0), 2000);
		assertEquals(1, funcionario.adicionaRegistroUso(registro));
		assertEquals(1, funcionario.getHistoricoUso().size());
	}

	@Test
	void testRemoveRegistroUso() {
		RegistroDeUso registro = new RegistroDeUso(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0), 2000);
		funcionario.adicionaRegistroUso(registro);
		assertEquals(1, funcionario.removeRegistroUso(registro));
		assertTrue(funcionario.getHistoricoUso().isEmpty());
	}

	@Test
	void testAdicionaRegistroManutencao() {
		RegistroManutencao registro = new RegistroManutencao(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0));
		assertEquals(1, funcionario.adicionaRegistroManutencao(registro));
		assertEquals(1, funcionario.getHistoricoManutencao().size());
	}

	@Test
	void testRemoveRegistroManutencao() {
		RegistroManutencao registro = new RegistroManutencao(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0));
		funcionario.adicionaRegistroManutencao(registro);
		assertEquals(1, funcionario.removeRegistroManutencao(registro));
		assertTrue(funcionario.getHistoricoManutencao().isEmpty());
	}

	@Test
	void testToString() {
		String resultado = funcionario.toString();
		assertTrue(resultado.contains("Funcionario:"));
		assertTrue(resultado.contains("Nome: Funcionario Nome"));
		assertTrue(resultado.contains("CPF: 09876543211"));
		assertTrue(resultado.contains("Gerente responsavel: Gerente Nome"));
	}

	@Test
	void testContratoNulo() {
		funcionario.setContrato(null);
		assertNull(funcionario.getContrato());
	}

	@Test
	void testGerenteNulo() {
		funcionario.setResponsavel(null);
		assertNull(funcionario.getResponsavel());
	}
}
