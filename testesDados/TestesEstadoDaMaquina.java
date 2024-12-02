package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dados.EstadoDaMaquina;

public class TestesEstadoDaMaquina {

	@Test
	void testEstadoValidoOciosa() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.OCIOSA);
		assertEquals(EstadoDaMaquina.OCIOSA, estado.getEstado());
		assertEquals("Status da maquina: OCIOSA\n", estado.toString());
	}

	@Test
	void testEstadoValidoAtiva() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.ATIVA);
		assertEquals(EstadoDaMaquina.ATIVA, estado.getEstado());
		assertEquals("Status da maquina: ATIVA\n", estado.toString());
	}

	@Test
	void testEstadoValidoInoperante() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.INOPERANTE);
		assertEquals(EstadoDaMaquina.INOPERANTE, estado.getEstado());
		assertEquals("Status da maquina: INOPERANTE\n", estado.toString());
	}

	@Test
	void testEstadoValidoManutencao() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.MANUTENCAO);
		assertEquals(EstadoDaMaquina.MANUTENCAO, estado.getEstado());
		assertEquals("Status da maquina: MANUTENCAO\n", estado.toString());
	}

	@Test
	void testEstadoInvalidoNegativo() {
		EstadoDaMaquina estado = new EstadoDaMaquina(-1);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	@Test
	void testEstadoInvalidoMaiorQueTres() {
		EstadoDaMaquina estado = new EstadoDaMaquina(5);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	@Test
	void testEstadoInvalidoLimiteSuperior() {
		EstadoDaMaquina estado = new EstadoDaMaquina(4);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	@Test
	void testVerificaEstadoValido() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.OCIOSA);
		assertEquals(1, estado.verifica(EstadoDaMaquina.ATIVA));
	}

	@Test
	void testVerificaEstadoInvalido() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.OCIOSA);
		assertEquals(0, estado.verifica(-1));
		assertEquals(0, estado.verifica(4));
	}

	@Test
	void testNomeEstadoSincronizado() {
		assertEquals("OCIOSA", EstadoDaMaquina.nomeEstado[EstadoDaMaquina.OCIOSA]);
		assertEquals("ATIVA", EstadoDaMaquina.nomeEstado[EstadoDaMaquina.ATIVA]);
		assertEquals("INOPERANTE", EstadoDaMaquina.nomeEstado[EstadoDaMaquina.INOPERANTE]);
		assertEquals("MANUTENCAO", EstadoDaMaquina.nomeEstado[EstadoDaMaquina.MANUTENCAO]);
	}

	@Test
	void testToStringEstadoIndefinido() {
		EstadoDaMaquina estado = new EstadoDaMaquina(10);
		assertEquals("Status da maquina: \n", estado.toString());
	}

	// Teste do limite inferior válido
	@Test
	void testEstadoLimiteInferiorValido() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.OCIOSA);
		assertEquals(EstadoDaMaquina.OCIOSA, estado.getEstado());
		assertEquals("Status da maquina: OCIOSA\n", estado.toString());
	}

	// Teste do limite superior válido
	@Test
	void testEstadoLimiteSuperiorValido() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.MANUTENCAO);
		assertEquals(EstadoDaMaquina.MANUTENCAO, estado.getEstado());
		assertEquals("Status da maquina: MANUTENCAO\n", estado.toString());
	}

	// Teste do menor valor inválido (-1)
	@Test
	void testEstadoMenorInvalido() {
		EstadoDaMaquina estado = new EstadoDaMaquina(-1);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	// Teste do maior valor inválido (4)
	@Test
	void testEstadoMaiorInvalido() {
		EstadoDaMaquina estado = new EstadoDaMaquina(4);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	// Teste com um valor muito grande positivo
	@Test
	void testEstadoMuitoGrande() {
		EstadoDaMaquina estado = new EstadoDaMaquina(Integer.MAX_VALUE);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	// Teste com um valor muito pequeno negativo
	@Test
	void testEstadoMuitoPequeno() {
		EstadoDaMaquina estado = new EstadoDaMaquina(Integer.MIN_VALUE);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	// Teste de transição entre válidos e inválidos (limite superior + 1)
	@Test
	void testEstadoLimiteSuperiorMaisUm() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.MANUTENCAO + 1);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	// Teste de transição entre válidos e inválidos (limite inferior - 1)
	@Test
	void testEstadoLimiteInferiorMenosUm() {
		EstadoDaMaquina estado = new EstadoDaMaquina(EstadoDaMaquina.OCIOSA - 1);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

	// Teste com valor zero (que é válido)
	@Test
	void testEstadoZero() {
		EstadoDaMaquina estado = new EstadoDaMaquina(0);
		assertEquals(EstadoDaMaquina.OCIOSA, estado.getEstado());
		assertEquals("Status da maquina: OCIOSA\n", estado.toString());
	}

	// Teste com um estado próximo de zero mas inválido (-0.1 não existe em int, mas
	// -1 pode ser representado)
	@Test
	void testEstadoQuaseZeroNegativo() {
		EstadoDaMaquina estado = new EstadoDaMaquina(-1);
		assertEquals(4, estado.getEstado());
		assertEquals("Status da maquina: \n", estado.toString());
	}

}
