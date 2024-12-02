package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dados.Data;

public class TestesData {

	@Test
	public void testConstrutorDataValida() {
		Data data = new Data(15, Data.JANEIRO, 2023, 10, 30);
		assertEquals(15, data.getDia());
		assertEquals(Data.JANEIRO, data.getMes());
		assertEquals(2023, data.getAno());
		assertEquals(10, data.getHoras());
		assertEquals(30, data.getMinutos());
	}

	@Test
	public void testConstrutorDataInvalida() {
		Data data = new Data(31, Data.FEVEREIRO, 2023, 10, 30); // Fevereiro não tem 31 dias
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
		assertEquals(0, data.getHoras());
		assertEquals(0, data.getMinutos());
	}

	@Test
	public void testConstrutorDataFutura() {
		int anoFuturo = LocalDate.now().getYear() + 1;
		Data data = new Data(10, Data.JULHO, anoFuturo, 15, 45);
		assertEquals(10, data.getDia());
		assertEquals(Data.JULHO, data.getMes());
		assertEquals(anoFuturo, data.getAno());
	}

	@Test
	public void testVerificaDataValida() {
		Data data = new Data(15, Data.JANEIRO, 2023, 10, 30);
		assertEquals(1, data.verificaData(15, Data.JANEIRO, 2023, 10, 30));
	}

	@Test
	public void testVerificaDataInvalida() {
		Data data = new Data(1, Data.JANEIRO, 2023);
		assertEquals(0, data.verificaData(31, Data.FEVEREIRO, 2023, 10, 30));
	}

	@Test
	public void testMesesDiferenca() {
		Data data1 = new Data(1, Data.JANEIRO, 2023);
		Data data2 = new Data(1, Data.MARCO, 2023);
		assertEquals(2, data1.mesesDiferenca(data2));
	}

	@Test
	public void testDiferencaDias() {
		Data data1 = new Data(28, Data.FEVEREIRO, 2024); // Ano bissexto
		Data data2 = new Data(1, Data.MARCO, 2024);
		assertEquals(2, data1.diferencaDias(data2));
	}

	@Test
	public void testDiferencaHoras() {
		Data data1 = new Data(1, Data.JANEIRO, 2023, 10, 0);
		Data data2 = new Data(1, Data.JANEIRO, 2023, 15, 0);
		assertEquals(5, data1.diferencaHoras(data2));
	}

	@Test
	public void testComparaData() {
		Data data1 = new Data(15, Data.JANEIRO, 2023);
		Data data2 = new Data(16, Data.JANEIRO, 2023);
		assertEquals(-1, data1.comparaData(data2));
	}

	@Test
	public void testToStringFormato1() {
		Data data = new Data(15, Data.JANEIRO, 2023, 10, 30);
		assertEquals("Data: 15/1/2023, Horario: 10:30.\n",
				data.toStringFormato1());
	}

	@Test
	public void testToStringFormato2() {
		Data data = new Data(15, Data.JANEIRO, 2023, 10, 30);
		assertEquals(
				"15 de JANEIRO de 2023, as 10 horas e 30 minutos.\n",
				data.toStringFormato2());
	}

	@Test
	public void testToStringSomenteData() {
		Data data = new Data(15, Data.JANEIRO, 2023);
		assertEquals("15/1/2023\n", data.toStringSomenteData());
	}

	@Test
	public void testDiaMinimoInvalido() {
		Data data = new Data(0, 1, 2023);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testDiaMaximoInvalido() {
		Data data = new Data(32, 1, 2023);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testMesMinimoInvalido() {
		Data data = new Data(15, 0, 2023);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testMesMaximoInvalido() {
		Data data = new Data(15, 13, 2023);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testAnoMinimoInvalido() {
		Data data = new Data(15, 10, 999);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testHorasMinimasInvalidas() {
		Data data = new Data(15, 10, 2023, -1, 0);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testHorasMaximasInvalidas() {
		Data data = new Data(15, 10, 2023, 25, 0);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testMinutosMinimosInvalidos() {
		Data data = new Data(15, 10, 2023, 0, -1);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testMinutosMaximosInvalidos() {
		Data data = new Data(15, 10, 2023, 0, 60);
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testDiaValidoNoAnoBissexto() {
		Data data = new Data(29, 2, 2024); // Ano bissexto
		assertEquals(29, data.getDia());
		assertEquals(2, data.getMes());
		assertEquals(2024, data.getAno());
	}

	@Test
	public void testDiaInvalidoNoAnoNaoBissexto() {
		Data data = new Data(29, 2, 2023); // Ano não bissexto
		assertEquals(0, data.getDia());
		assertEquals(0, data.getMes());
		assertEquals(0, data.getAno());
	}

	@Test
	public void testDataFutura() {
		Data data = new Data(31, 12, LocalDate.now().getYear() + 1);
		assertEquals(31, data.getDia());
		assertEquals(12, data.getMes());
		assertEquals(LocalDate.now().getYear() + 1, data.getAno());
	}

	@Test
	public void testDiferencaEmMesesBorda() {
		Data data1 = new Data(31, 12, 2023);
		Data data2 = new Data(1, 1, 2024);
		assertEquals(1, data1.mesesDiferenca(data2));
	}

	@Test
	public void testDiferencaEmDiasBorda() {
		Data data1 = new Data(28, 2, 2023);
		Data data2 = new Data(1, 3, 2023);
		assertEquals(1, data1.diferencaDias(data2));
	}

	@Test
	public void testDiferencaEmHorasBorda() {
		Data data1 = new Data(1, 1, 2023, 23, 0);
		Data data2 = new Data(2, 1, 2023, 0, 0);
		assertEquals(1, data1.diferencaHoras(data2));
	}

}
