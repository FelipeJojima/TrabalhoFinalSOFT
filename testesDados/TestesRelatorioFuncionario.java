package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;

import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.RegistroDeUso;
import dados.RegistroManutencao;
import dados.RelatorioFuncionario;
import dados.Registro;

public class TestesRelatorioFuncionario {

	@Test
	public void testRelatorioFuncionarioGeracao() {
		// Criação de objetos auxiliares
		Data dataInicio = new Data(1, 12, 2024, 10, 0);
		Data dataFim = new Data(1, 12, 2024, 14, 0);
		Gerente gerente = new Gerente("Carlos Silva", "12345678901", "carlos@email.com", 5000.0f,
				new Data(20, 5, 1980, 0, 0), 1, "senha123");
		Funcionario funcionario = new Funcionario("Ana Costa", "98765432109", "ana@email.com", 3000.0f,
				new Data(15, 6, 1990, 0, 0), 2, gerente, "senha456");
		Maquina maquina = new Maquina("Torno CNC", 101, gerente);

		// Adiciona registros de uso e manutenção
		RegistroDeUso uso1 = new RegistroDeUso(dataInicio, gerente, funcionario, maquina, dataFim, 100);
		RegistroManutencao manutencao1 = new RegistroManutencao(dataInicio, gerente, funcionario, maquina, dataFim);

		funcionario.adicionaRegistroUso(uso1);
		funcionario.adicionaRegistroManutencao(manutencao1);

		// Criação do relatório
		RelatorioFuncionario relatorio = new RelatorioFuncionario(funcionario);

		// Verifica se os registros foram adicionados e ordenados corretamente
		List<Registro> registros = relatorio.getRegistros();
		assertEquals(2, registros.size(), "O relatório deve conter 2 registros.");
		assertTrue(registros.get(0) instanceof RegistroDeUso, "O primeiro registro deve ser de uso.");
		assertTrue(registros.get(1) instanceof RegistroManutencao, "O segundo registro deve ser de manutenção.");
	}

	@Test
	public void testRelatorioOrdenacao() {
		// Criação de objetos auxiliares
		Data dataInicio1 = new Data(1, 12, 2024, 10, 0);
		Data dataFim1 = new Data(1, 12, 2024, 14, 0);
		Data dataInicio2 = new Data(2, 12, 2024, 8, 0);
		Data dataFim2 = new Data(2, 12, 2024, 12, 0);
		Gerente gerente = new Gerente("Carlos Silva", "12345678901", "carlos@email.com", 5000.0f,
				new Data(20, 5, 1980, 0, 0), 1, "senha123");
		Funcionario funcionario = new Funcionario("Ana Costa", "98765432109", "ana@email.com", 3000.0f,
				new Data(15, 6, 1990, 0, 0), 2, gerente, "senha456");
		Maquina maquina = new Maquina("Torno CNC", 101, gerente);

		// Adiciona registros fora de ordem cronológica
		RegistroDeUso uso1 = new RegistroDeUso(dataInicio2, gerente, funcionario, maquina, dataFim2, 120);
		RegistroManutencao manutencao1 = new RegistroManutencao(dataInicio1, gerente, funcionario, maquina, dataFim1);

		funcionario.adicionaRegistroUso(uso1);
		funcionario.adicionaRegistroManutencao(manutencao1);

		// Criação do relatório
		RelatorioFuncionario relatorio = new RelatorioFuncionario(funcionario);

		// Verifica se os registros foram ordenados corretamente
		List<Registro> registros = relatorio.getRegistros();
		assertEquals(2, registros.size(), "O relatório deve conter 2 registros.");
		assertTrue(registros.get(0) instanceof RegistroManutencao, "O primeiro registro deve ser de manutenção.");
		assertTrue(registros.get(1) instanceof RegistroDeUso, "O segundo registro deve ser de uso.");
	}

	@Test
	public void testRelatorioSemRegistros() {
		// Criação de objetos auxiliares
		Gerente gerente = new Gerente("Carlos Silva", "12345678901", "carlos@email.com", 5000.0f,
				new Data(20, 5, 1980, 0, 0), 1, "senha123");
		Funcionario funcionario = new Funcionario("Ana Costa", "98765432109", "ana@email.com", 3000.0f,
				new Data(15, 6, 1990, 0, 0), 2, gerente, "senha456");

		// Criação do relatório
		RelatorioFuncionario relatorio = new RelatorioFuncionario(funcionario);

		// Verifica se o relatório é gerado corretamente mesmo sem registros
		List<Registro> registros = relatorio.getRegistros();
		assertEquals(0, registros.size(), "O relatório deve conter 0 registros.");
	}

	@Test
	public void testToString() {
		// Criação de objetos auxiliares
		Data dataInicio = new Data(1, 12, 2024, 10, 0);
		Data dataFim = new Data(1, 12, 2024, 14, 0);
		Gerente gerente = new Gerente("Carlos Silva", "12345678901", "carlos@email.com", 5000.0f,
				new Data(20, 5, 1980, 0, 0), 1, "senha123");
		Funcionario funcionario = new Funcionario("Ana Costa", "98765432109", "ana@email.com", 3000.0f,
				new Data(15, 6, 1990, 0, 0), 2, gerente, "senha456");
		Maquina maquina = new Maquina("Torno CNC", 101, gerente);

		// Adiciona registros
		RegistroDeUso uso1 = new RegistroDeUso(dataInicio, gerente, funcionario, maquina, dataFim, 100);
		funcionario.adicionaRegistroUso(uso1);

		// Criação do relatório
		RelatorioFuncionario relatorio = new RelatorioFuncionario(funcionario);

		// Verifica a representação textual do relatório
		String relatorioTexto = relatorio.toString();
		assertTrue(relatorioTexto.contains("Relatorio do funcionario: Ana Costa"),
				"O relatório deve conter o nome do funcionário.");
		assertTrue(relatorioTexto.contains("Registro de uso da máquina: Torno CNC"),
				"O relatório deve conter informações dos registros.");
	}
}
