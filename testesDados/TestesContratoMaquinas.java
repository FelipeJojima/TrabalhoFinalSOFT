package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dados.ContratoMaquinas;
import dados.Data;
import dados.Gerente;
import dados.Maquina;
import dados.SituacaoMaquina;

public class TestesContratoMaquinas {

	private Data dataInicio;
	private Data dataFim;
	private Gerente gerente;
	private Maquina maquina;
	private SituacaoMaquina situacao;
	private ContratoMaquinas contratoMaquinas;

	@BeforeEach
	public void setUp() {
		// Inicializando os objetos necessários para os testes
		dataInicio = new Data(1, 1, 2023);
		dataFim = new Data(31, 12, 2023);
		Data nascGerente = new Data(3, 2, 2000);
		gerente = new Gerente("João", "123456", "joaozinho123@gmail.com", 3451f, nascGerente, 63, "joao123"); // Exemplo
																												// de
																												// criação
		situacao = new SituacaoMaquina(2);
		maquina = new Maquina("Máquina 1", 5, gerente);
		  // Exemplo de situação de máquina
		contratoMaquinas = new ContratoMaquinas(dataInicio, dataFim, 1001, gerente, maquina, situacao, 2000.0f);
	}

	@Test
	public void testConstrutor() {
		assertNotNull(contratoMaquinas, "ContratoMaquinas não deve ser nulo.");
		assertEquals(maquina, contratoMaquinas.getMaquina(), "Máquina não está associada corretamente.");
		assertEquals(situacao, contratoMaquinas.getSituacao(), "Situação da máquina não está correta.");
		assertEquals(2000.0f, contratoMaquinas.getValorMensal(), "Valor mensal não está correto.");
	}

	@Test
	public void testGetters() {
		int certo = SituacaoMaquina.ALUGADA;
		assertEquals("Máquina 1", contratoMaquinas.getMaquina().getNome(), "Nome da máquina está incorreto.");
		assertEquals(certo, contratoMaquinas.getSituacao().getSituacaoMaquina(), "Situação da máquina está incorreta.");
		assertEquals(2000.0f, contratoMaquinas.getValorMensal(), "Valor mensal está incorreto.");
	}

	@Test
	public void testSetters() {
		contratoMaquinas.setValorMensal(2500.0f);
		assertEquals(2500.0f, contratoMaquinas.getValorMensal(), "Valor mensal não foi atualizado corretamente.");

		SituacaoMaquina novo = new SituacaoMaquina(1);
		contratoMaquinas.setSituacao(novo);
		assertEquals(novo, contratoMaquinas.getSituacao(), "Situação da máquina não foi atualizada corretamente.");
	}

	@Test
	public void testToString() {
		String expectedString = "Contrato de maquina:\nMaquina: Máquina 1\nSituacao da maquina: ALUGADA\nValor: 2000.0\nData de inicio: 1/1/2023\nData de fim: 31/12/2023\nMeses de contrato: 11\nGerente responsavel: João\nNumero do contrato: 1001\n";
		assertEquals(expectedString, contratoMaquinas.toString(),
				"A string gerada pelo método toString está incorreta.");
	}

	// Testes de borda

	@Test
	public void testConstrutorComDataFimAntesDeInicio() {
		Data dataFimAntes = new Data(1, 1, 2022);
		// Criando contrato com data de fim antes de data de inicio
		ContratoMaquinas contrato = new ContratoMaquinas(dataFimAntes, dataInicio, 1002, gerente, maquina, situacao,
				1500.0f);

		// Verificando se o contrato é criado corretamente, com data de fim ajustada
		assertEquals(dataInicio, contrato.getDataFim(), "A data de fim deve ser ajustada para a data de início.");
	}

	@Test
	public void testAlterarValorMensalComValorNegativo() {
		// Alterando o valor mensal para um valor negativo
		contratoMaquinas.setValorMensal(-1000.0f);
		// Verificando se o valor mensal foi alterado para o valor negativo
		assertEquals(-1000.0f, contratoMaquinas.getValorMensal(),
				"Valor mensal não foi atualizado para valor negativo.");
	}

	@Test
	public void testConstrutorComDataDeFimIgualAInicio() {
		// Criando contrato com data de fim igual à data de início
		Data dataFimIgual = new Data(1, 1, 2023);
		ContratoMaquinas contrato = new ContratoMaquinas(dataInicio, dataFimIgual, 1003, gerente, maquina, situacao,
				5000.0f);

		// Verificando se o contrato é criado corretamente, com o valor mensal como
		// esperado
		assertEquals(0, contrato.getMesesContrato(), "A quantidade de meses de contrato deve ser 0.");
		assertEquals(5000.0f, contrato.getValorMensal(), "O valor mensal deve ser 5000.0.");
	}
}
