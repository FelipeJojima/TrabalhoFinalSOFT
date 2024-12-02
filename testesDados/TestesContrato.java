package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dados.Contrato;
import dados.Data;
import dados.Gerente;

public class TestesContrato {

	private Data dataInicio;
	private Data dataFim;
	private Data novaDataFim;
	private Gerente gerente;
	private Contrato contrato;

	@BeforeEach
	public void setup() {
		// Instancia objetos de teste.
		dataInicio = new Data(1, 1, 2024); // Exemplo de data válida
		dataFim = new Data(31, 12, 2024); // Exemplo de data válida
		novaDataFim = new Data(30, 6, 2025); // Exemplo de nova data válida
		Data nascGerente = new Data(3, 2, 2000);
		gerente = new Gerente("João", "123456", "joaozinho123@gmail.com", 3451f, nascGerente, 63, "joao123"); // Exemplo
																												// de
																												// gerente
																												// válido
		contrato = new Contrato(dataInicio, dataFim, 1, gerente); // Instancia um contrato.
	}

	@Test
	public void testConstrutor() {
		assertEquals(dataInicio, contrato.getDataInicio(), "Data de início deve ser inicializada corretamente.");
		assertEquals(dataFim, contrato.getDataFim(), "Data de fim deve ser inicializada corretamente.");
		assertEquals(1, contrato.getNumeroDeContrato(), "Número do contrato deve ser inicializado corretamente.");
		assertEquals(gerente, contrato.getResponsavel(), "Gerente deve ser inicializado corretamente.");
		assertEquals(11, contrato.getMesesContrato(), "Meses do contrato devem ser calculados corretamente.");
	}

	@Test
	public void testSetDataFimValida() {
		int resultado = contrato.setDataFim(novaDataFim);
		assertEquals(1, resultado, "Deve retornar 1 ao alterar a data de fim para uma data válida.");
		assertEquals(novaDataFim, contrato.getDataFim(), "Data de fim deve ser atualizada corretamente.");
		assertEquals(17, contrato.getMesesContrato(), "Meses do contrato devem ser recalculados corretamente.");
	}

	@Test
	public void testSetDataFimInvalida() {
		Data dataInvalida = new Data(31, 12, 2023); // Data anterior ao início do contrato.
		int resultado = contrato.setDataFim(dataInvalida);
		assertEquals(0, resultado, "Deve retornar 0 ao tentar alterar para uma data inválida.");
		assertEquals(dataFim, contrato.getDataFim(), "Data de fim não deve ser alterada para uma data inválida.");
		assertEquals(11, contrato.getMesesContrato(), "Meses do contrato devem permanecer inalterados.");
	}

	@Test
	public void testSetResponsavel() {
		Data nascNovoGerente = new Data(5, 4, 1980);
		Gerente novoGerente = new Gerente("Maria", "654321", "maria.123@gmail.com", 4569f, nascNovoGerente, 99,
				"m4ri4"); // Exemplo
							// de
							// novo
							// gerente.
		contrato.setResponsavel(novoGerente);
		assertEquals(novoGerente, contrato.getResponsavel(), "Responsável deve ser alterado corretamente.");
	}

	@Test
	public void testGetters() {
		assertEquals(dataInicio, contrato.getDataInicio(), "getDataInicio deve retornar a data correta.");
		assertEquals(dataFim, contrato.getDataFim(), "getDataFim deve retornar a data correta.");
		assertEquals(gerente, contrato.getResponsavel(), "getResponsavel deve retornar o gerente correto.");
		assertEquals(1, contrato.getNumeroDeContrato(), "getNumeroDeContrato deve retornar o número correto.");
		assertEquals(11, contrato.getMesesContrato(), "getMesesContrato deve retornar o número correto de meses.");
	}

	// Testes de borda
	@Test
	public void testDataInicioIgualDataFim() {
		Data mesmaData = new Data(1, 1, 2024); // Data início e fim iguais.
		Contrato contratoBorda = new Contrato(mesmaData, mesmaData, 2, gerente);
		assertEquals(0, contratoBorda.getMesesContrato(),
				"Meses do contrato devem ser 0 quando início e fim são iguais.");
	}

	@Test
	public void testSetDataFimParaMesmoValor() {
		int resultado = contrato.setDataFim(dataFim); // Mesma data.
		assertEquals(1, resultado, "Deve retornar 1 ao atualizar a data de fim para a mesma data atual.");
		assertEquals(dataFim, contrato.getDataFim(), "Data de fim deve permanecer inalterada ao passar a mesma data.");
		assertEquals(11, contrato.getMesesContrato(), "Meses do contrato devem permanecer inalterados.");
	}

	@Test
	public void testGerenteNulo() {
		Contrato contratoSemGerente = new Contrato(dataInicio, dataFim, 4, null);
		assertNull(contratoSemGerente.getResponsavel(), "Gerente deve ser nulo se não for fornecido no construtor.");
	}

	@Test
	public void testSetResponsavelParaNulo() {
		contrato.setResponsavel(null);
		assertNull(contrato.getResponsavel(), "Gerente deve ser ajustado para nulo ao ser passado como parâmetro.");
	}

	@Test
	public void testSetDataFimMaxima() {
		Data dataMaxima = new Data(31, 12, 9999); // Limite superior de data.
		int resultado = contrato.setDataFim(dataMaxima);
		assertEquals(1, resultado, "Deve retornar 1 ao ajustar para uma data válida máxima.");
		assertEquals(dataMaxima, contrato.getDataFim(), "Data de fim deve ser ajustada para a data máxima.");
	}

	@Test
	public void testConstrutorComMesesMaximos() {
		Data dataFimExtremo = new Data(1, 1, 3024); // Diferença de 1000 anos.
		Contrato contratoExtremo = new Contrato(dataInicio, dataFimExtremo, 5, gerente);
		assertEquals(12000, contratoExtremo.getMesesContrato(),
				"Meses do contrato devem ser calculados corretamente para grandes intervalos.");
	}

	@Test
	public void testDataInicioNula() {
		assertThrows(NullPointerException.class, () -> {
			new Contrato(null, dataFim, 6, gerente);
		}, "Deve lançar NullPointerException se a data de início for nula.");
	}

	@Test
	public void testDataFimNula() {
		Contrato contratoDataFimNula = new Contrato(dataInicio, null, 7, gerente);
		assertEquals(dataInicio, contratoDataFimNula.getDataFim(),
				"Se a data de fim for nula, deve ser ajustada para a data de início.");
		assertEquals(0, contratoDataFimNula.getMesesContrato(),
				"Meses do contrato devem ser 0 se a data de fim for ajustada para a de início.");
	}

}
