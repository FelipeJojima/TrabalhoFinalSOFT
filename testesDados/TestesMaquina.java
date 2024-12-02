package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dados.ContratoMaquinas;
import dados.EstadoDaMaquina;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.RegistroDeUso;
import dados.RegistroManutencao;
import dados.SituacaoMaquina;
import dados.Data;

public class TestesMaquina {

	@Test
	public void testConstrutorMaquina() {
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Maquina maquina = new Maquina("Maquina A", 101, gerente);

		assertEquals("Maquina A", maquina.getNome());
		assertEquals(101, maquina.getCodigoIdentificacao());
		assertEquals(gerente, maquina.getResponsavel());
		assertNotNull(maquina.getStatus());
		assertNull(maquina.getContrato());
	}

	@Test
	public void testSetNome() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		maquina.setNome("Nova Maquina");

		assertEquals("Nova Maquina", maquina.getNome());
	}

	@Test
	public void testSetResponsavel() {
		Gerente gerente1 = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Gerente gerente2 = new Gerente("Gerente 2", "98765432100", "gerente2@email.com", 6000.0f, new Data(1, 1, 1975),
				2, "senha456");
		Maquina maquina = new Maquina("Maquina A", 101, gerente1);
		maquina.setResponsavel(gerente2);

		assertEquals(gerente2, maquina.getResponsavel());
	}

	@Test
	public void testNovoContrato() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.ALUGADA);
		ContratoMaquinas contrato = new ContratoMaquinas(new Data(1, 1, 2023), new Data(31, 12, 2023), 1001, gerente,
				maquina, situacao, 2000.0f);

		maquina.novoContrato(contrato);

		assertEquals(contrato, maquina.getContrato());
	}

	@Test
	public void testSetStatus() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		EstadoDaMaquina novoStatus = new EstadoDaMaquina(EstadoDaMaquina.ATIVA);

		maquina.setStatus(novoStatus);

		assertEquals(novoStatus, maquina.getStatus());
	}

	@Test
	public void testAdicionaRegistroUso() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Funcionario funcionario = new Funcionario("Funcionario Nome", "09876543211", "funcionario@email.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha456");
		RegistroDeUso registro = new RegistroDeUso(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0), 2000);

		int resultado = maquina.adicionaRegistroUso(registro);

		assertEquals(1, resultado);
		assertTrue(maquina.getHistoricoUso().contains(registro));
	}

	@Test
	public void testRemoveRegistroUso() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Funcionario funcionario = new Funcionario("Funcionario Nome", "09876543211", "funcionario@email.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha456");
		RegistroDeUso registro = new RegistroDeUso(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0), 2000);
		maquina.adicionaRegistroUso(registro);

		int resultado = maquina.removeRegistroUso(registro);

		assertEquals(1, resultado);
		assertFalse(maquina.getHistoricoUso().contains(registro));
	}

	@Test
	public void testAdicionaRegistroManutencao() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Funcionario funcionario = new Funcionario("Funcionario Nome", "09876543211", "funcionario@email.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha456");
		RegistroManutencao registro = new RegistroManutencao(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0));

		int resultado = maquina.adicionaRegistroManutencao(registro);

		assertEquals(1, resultado);
		assertTrue(maquina.getHistoricoManutencao().contains(registro));
	}

	@Test
	public void testRemoveRegistroManutencao() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Funcionario funcionario = new Funcionario("Funcionario Nome", "09876543211", "funcionario@email.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha456");
		RegistroManutencao registro = new RegistroManutencao(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0));
		maquina.adicionaRegistroManutencao(registro);

		int resultado = maquina.removeRegistroManutencao(registro);

		assertEquals(1, resultado);
		assertFalse(maquina.getHistoricoManutencao().contains(registro));
	}

	@Test
	public void testToString() {
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Maquina maquina = new Maquina("Maquina A", 101, gerente);

		String esperado = "Maquina:\nNome: Maquina A\n" + "null" + "\nStatus da maquina: OCIOSA\n"
				+ "Codigo: 101\nGerente Responsavel: Gerente 1\n";

		assertEquals(esperado, maquina.toString());
	}

	// Testes de borda
	@Test
	public void testConstrutorComValoresNulos() {
		Maquina maquina = new Maquina(null, -1, null);

		assertNull(maquina.getNome());
		assertEquals(-1, maquina.getCodigoIdentificacao());
		assertNull(maquina.getResponsavel());
	}

	@Test
	public void testRemoveRegistroUsoInexistente() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Funcionario funcionario = new Funcionario("Funcionario Nome", "09876543211", "funcionario@email.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha456");
		RegistroDeUso registroInexistente = new RegistroDeUso(new Data(15, 11, 2024, 8, 0), gerente, funcionario,
				new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0), 2000);

		int resultado = maquina.removeRegistroUso(registroInexistente);

		assertEquals(0, resultado);
	}

	@Test
	public void testRemoveRegistroManutencaoInexistente() {
		Maquina maquina = new Maquina("Maquina A", 101, null);
		Gerente gerente = new Gerente("Gerente 1", "12345678901", "gerente1@email.com", 5000.0f, new Data(1, 1, 1980),
				1, "senha123");
		Funcionario funcionario = new Funcionario("Funcionario Nome", "09876543211", "funcionario@email.com", 3000,
				new Data(15, 5, 1990), 2, gerente, "senha456");
		RegistroManutencao registroInexistente = new RegistroManutencao(new Data(15, 11, 2024, 8, 0), gerente,
				funcionario, new Maquina("Maquina 1", 55, gerente), new Data(15, 11, 2024, 20, 0));

		int resultado = maquina.removeRegistroManutencao(registroInexistente);

		assertEquals(0, resultado);
	}
}
