package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dados.SituacaoMaquina;

public class TestesSituacaoMaquina {

	   @Test
	   public void testConstrutorValidoFinanciada() {
	        // Testa se o construtor aceita a situação FINANCIADA (1)
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.FINANCIADA);
	        assertEquals(SituacaoMaquina.FINANCIADA, situacao.getSituacaoMaquina(), "A situação deve ser FINANCIADA");
	    }

	    @Test
	    public void testConstrutorValidoAlugada() {
	        // Testa se o construtor aceita a situação ALUGADA (2)
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.ALUGADA);
	        assertEquals(SituacaoMaquina.ALUGADA, situacao.getSituacaoMaquina(), "A situação deve ser ALUGADA");
	    }

	    @Test
	    public void testConstrutorValidoComprada() {
	        // Testa se o construtor aceita a situação COMPRADA (3)
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.COMPRADA);
	        assertEquals(SituacaoMaquina.COMPRADA, situacao.getSituacaoMaquina(), "A situação deve ser COMPRADA");
	    }

	    @Test
	    public void testConstrutorInvalido() {
	        // Testa se o construtor lida corretamente com um valor inválido
	        SituacaoMaquina situacao = new SituacaoMaquina(0);  // valor inválido
	        assertEquals(0, situacao.getSituacaoMaquina(), "A situação deve ser 0 para um valor inválido");
	    }

	    @Test
	    public void testVerificaSituacaoValida() {
	        // Testa o método 'verifica' para um valor válido
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.FINANCIADA);
	        assertEquals(1, situacao.verifica(SituacaoMaquina.FINANCIADA), "Deve retornar 1 para uma situação válida");
	    }

	    @Test
	    public void testVerificaSituacaoInvalida() {
	        // Testa o método 'verifica' para um valor inválido
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.FINANCIADA);
	        assertEquals(0, situacao.verifica(4), "Deve retornar 0 para uma situação inválida");
	    }

	    @Test
	    public void testToStringFinanciada() {
	        // Testa o método 'toString' para a situação FINANCIADA
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.FINANCIADA);
	        assertEquals("Situacao da maquina: FINANCIADA\n", situacao.toString(), "A string não corresponde ao esperado para FINANCIADA");
	    }

	    @Test
	    public void testToStringAlugada() {
	        // Testa o método 'toString' para a situação ALUGADA
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.ALUGADA);
	        assertEquals("Situacao da maquina: ALUGADA\n", situacao.toString(), "A string não corresponde ao esperado para ALUGADA");
	    }

	    @Test
	    public void testToStringComprada() {
	        // Testa o método 'toString' para a situação COMPRADA
	        SituacaoMaquina situacao = new SituacaoMaquina(SituacaoMaquina.COMPRADA);
	        assertEquals("Situacao da maquina: COMPRADA\n", situacao.toString(), "A string não corresponde ao esperado para COMPRADA");
	    }

	    @Test
	    public void testToStringInvalido() {
	        // Testa o método 'toString' para a situação inválida (0)
	        SituacaoMaquina situacao = new SituacaoMaquina(0);
	        assertEquals("Situacao da maquina: \n", situacao.toString(), "A string não corresponde ao esperado para uma situação inválida");
	    }
}
