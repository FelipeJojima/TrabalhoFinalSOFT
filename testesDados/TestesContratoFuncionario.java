package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dados.ContratoFuncionario;
import dados.Data;
import dados.Funcionario;
import dados.Gerente;

public class TestesContratoFuncionario {
	private ContratoFuncionario contrato;
	private Data dataInicio;
	private Data dataFim;
	private Gerente gerente;
	private Funcionario funcionario;

	@BeforeEach
	public void setUp() {
		// Configurando os objetos necessários para os testes

		// Suponhamos que o Gerente e Funcionario são instâncias válidas
		Data nascGerente = new Data(3, 2, 2000);
		gerente = new Gerente("João", "123456", "joaozinho123@gmail.com", 3451f, nascGerente, 63, "joao123"); // Exemplo
																												// de
																												// criação
																												// do
																												// gerente
		Data nascFunc = new Data(5, 10, 2002);
		funcionario = new Funcionario("Maria", "654321", "maria.123@gmail.com", 4569f, nascFunc, 99, gerente, "m4ri4"); // Exemplo
																														// de
																														// criação
																														// do
																														// funcionário

		// Criando datas de início e fim válidas
		dataInicio = new Data(1, 1, 2023); // 01/01/2023
		dataFim = new Data(1, 6, 2023); // 01/06/2023

		// Criando um contrato com a data de início, data de fim, número do contrato,
		// gerente e funcionário
		contrato = new ContratoFuncionario(dataInicio, dataFim, 101, gerente, funcionario, 4000f);
	}

	@Test
	public void testContratoFuncionario() {
		// Verificando se o contrato foi criado corretamente
		assertNotNull(contrato);
		assertEquals(4000f, contrato.getSalario());
		assertEquals("Maria", contrato.getContratado().getNome());
		assertEquals("João", contrato.getResponsavel().getNome());
		assertEquals(5, contrato.getMesesContrato()); // De 01/01/2023 a 01/06/2023 são 5 meses
	}

	@Test
	public void testToString() {
		// Teste se o método toString está formatando a string corretamente
		String esperado = "Contrato de funcionario:\nFuncionario contratado: Maria\nSalario: 4000.0\nMeses de contrato: 5\nData de inicio: 1/1/2023\nData de fim: 1/6/2023\nGerente responsavel: João\nNumero do contrato: 101\n";
		assertEquals(esperado, contrato.toString());
	}

	@Test
	public void testAtualizacaoSalario() {
		// Testando a atualização do salário
		contrato.setSalario(4500f);
		assertEquals(4500f, contrato.getSalario());
	}

	@Test
	public void testAlteracaoDataFim() {
		// Testando a alteração da data de fim
		Data novaDataFim = new Data(1, 12, 2023); // Alterando para 01/12/2023
		int resultado = contrato.setDataFim(novaDataFim);
		assertEquals(1, resultado); // Espera-se que a data seja alterada com sucesso
		assertEquals(11, contrato.getMesesContrato()); // De 01/01/2023 a 01/12/2023 são 11 meses
	}

	@Test
	public void testNaoAlteracaoDataFimInvalida() {
		// Testando tentativa de alterar a data de fim para uma data anterior à data de
		// início
		Data dataInvalida = new Data(1, 12, 2022); // Tentando alterar para 01/12/2022 (antes da data de início)
		int resultado = contrato.setDataFim(dataInvalida);
		assertEquals(0, resultado); // Espera-se que a data não seja alterada
	}

	@Test
	public void testContratoComMesesNegativos() {
		// Definindo a data de início como posterior à data de fim
		Data dataInicioInvalida = new Data(1, 6, 2023); // 01/06/2023
		Data dataFimInvalida = new Data(1, 1, 2023); // 01/01/2023 (antes da data de início)

		// Criando o contrato
		ContratoFuncionario contratoInvalido = new ContratoFuncionario(dataInicioInvalida, dataFimInvalida, 102,
				gerente, funcionario, 4000f);

		// Verificando que o contrato possui meses negativos ou zero
		assertEquals(0, contratoInvalido.getMesesContrato()); // Espera-se que o contrato tenha 0 meses
	}

	@Test
	public void testContratoDeLongaDuracao() {
		// Definindo a data de início e fim com anos distantes
		Data dataInicioLonga = new Data(1, 1, 1900); // 01/01/1900
		Data dataFimLonga = new Data(1, 1, 2100); // 01/01/2100

		// Criando o contrato de longa duração
		ContratoFuncionario contratoLongo = new ContratoFuncionario(dataInicioLonga, dataFimLonga, 103, gerente,
				funcionario, 4000f);

		// Verificando que a duração do contrato foi calculada corretamente (duração de
		// 200 anos)
		assertEquals(2400, contratoLongo.getMesesContrato()); // 200 anos * 12 meses = 2400 meses
	}

	@Test
	public void testAlteracaoDeDataFimIgualAoInicio() {
		// Definindo a data de início e fim como a mesma
		Data dataInicioFimIgual = new Data(1, 1, 2023); // 01/01/2023
		Data dataFimIgual = new Data(1, 1, 2023); // 01/01/2023 (igual ao início)

		// Criando o contrato com a mesma data de início e fim
		ContratoFuncionario contratoIgual = new ContratoFuncionario(dataInicioFimIgual, dataFimIgual, 104, gerente,
				funcionario, 4000f);

		// Verificando se a duração do contrato é zero meses (pois a data de fim é igual
		// à de início)
		assertEquals(0, contratoIgual.getMesesContrato()); // Espera-se que o contrato tenha 0 meses
	}
}
