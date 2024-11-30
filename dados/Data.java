package dados;

import java.time.LocalDate;

public class Data {

	private int dia, mes, ano, horas, minutos;
	private LocalDate dataDoDia = LocalDate.now();

	/**
	 * Valor do mês de Janeiro
	 */
	public static final int JANEIRO = 1;
	/**
	 * Valor do mês de Fevereiro
	 */
	public static final int FEVEREIRO = 2;
	/**
	 * Valor do mês de Março
	 */
	public static final int MARCO = 3;
	/**
	 * Valor do mês de Abril
	 */
	public static final int ABRIL = 4;
	/**
	 * Valor do mês de Maio
	 */
	public static final int MAIO = 5;
	/**
	 * Valor do mês de Junho
	 */
	public static final int JUNHO = 6;
	/**
	 * Valor do mês de Julho
	 */
	public static final int JULHO = 7;
	/**
	 * Valor do mês de Agosto
	 */
	public static final int AGOSTO = 8;
	/**
	 * Valor do mês de Setembro
	 */
	public static final int SETEMBRO = 9;
	/**
	 * Valor do mês de Outubro
	 */
	public static final int OUTUBRO = 10;
	/**
	 * Valor do mês de Novembro
	 */
	public static final int NOVEMBRO = 11;
	/**
	 * Valor do mês de Dezembro
	 */
	public static final int DEZEMBRO = 12;

	/**
	 * Construtor de uma data comum.
	 * 
	 * @param dia     O valor do dia.
	 * @param mes     O valor do mês.
	 * @param ano     O valor do ano.
	 * @param horas   O valor das horas.
	 * @param minutos O valor dos minutos.
	 */
	public Data(int dia, int mes, int ano, int horas, int minutos) {
		if (this.verificaData(dia, mes, ano, horas, minutos) == 1 || this.verificaData(dia, mes, ano, horas, minutos) == 2) {
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
			this.horas = horas;
			this.minutos = minutos;
		} else {
			this.dia = 0;
			this.mes = 0;
			this.ano = 0;
			this.horas = 0;
			this.minutos = 0;
		}

	}

	/**
	 * Método para verificação de data.
	 * 
	 * @param dia     O valor do dia.
	 * @param mes     O valor do mes.
	 * @param ano     O valor do ano.
	 * @param horas   O valor das horas.
	 * @param minutos O valor dos minutos.
	 * @return 0 para data invalida e 1 para data valida e 2 para data futura.
	 */
	public int verificaData(int dia, int mes, int ano, int horas, int minutos) {
		// Verificacao de parametros validos
		if (dia > 31 || dia < 1 || mes > 12 || mes < 1 || ano < 2000 || horas < 0 || horas > 24 || minutos < 0
				|| minutos > 59) {
			return 0;
		}

		int anoAtual = this.dataDoDia.getYear(), mesAtual = this.dataDoDia.getMonthValue(),
				diaAtual = this.dataDoDia.getDayOfMonth();

		// Verificacao se o ano passado como parametro é menor que o ano atual
		if (anoAtual < ano) {
			return 2;
		}

		// Ano atual (data do SO)
		if (anoAtual == ano) {
			// Mes atual (data do SO)
			if (mes == mesAtual) {
				if (dia > diaAtual) {
					return 2;
				}
				return 1;
			}
		}

		// Mes até dia 30
		if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			if (dia > 30) {
				return 0;
			}
			return 1;
		}

		// Verificacao de ano bissexto
		if (ano % 4 == 0) {
			// Fevereiro
			if (mes == 2) {
				if (dia > 29) {
					return 0;
				}
				return 1;
			}
		}
		// Fevereiro
		if (mes == 2) {
			if (dia > 28) {
				return 0;
			}
			return 1;
		}

		return 1;

	}

	/**
	 * Metodo que retorna a diferenca entre duas datas em meses.
	 * 
	 * @param data A data a ser comparada.
	 * @return A diferenca em meses.
	 */
	public int mesesDiferenca(Data data) {
		if (this.comparaData(data) == -1) {
			if (this.getAno() == data.getAno()) {
				return data.getMes() - this.getMes();
			}
			int anos = data.getAno() - this.getAno();
			int meses = 0;
			if (data.getMes() < this.getMes()) {
				meses = (12 + data.getMes()) - this.getMes();
			} else {
				meses = data.getMes() - this.getMes();
			}
			return (12 * anos) + meses;
		}
		if (this.getAno() == data.getAno()) {
			return this.getMes() - data.getMes();
		}
		int anos = this.getAno() - data.getAno();
		int meses = 0;
		if (this.getMes() < data.getMes()) {
			meses = (12 + this.getMes()) - data.getMes();
		} else {
			meses = this.getMes() - data.getMes();
		}
		return (12 * anos) + meses;
	}

	/**
	 * Recupera o valor do mes.
	 * 
	 * @return O valor do mes.
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Recupera o valor do dia.
	 * 
	 * @return O valor do dia.
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * Recupera o valor do ano.
	 * 
	 * @return O valor do ano.
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * Recupera o valor das horas.
	 * 
	 * @return O valor das horas.
	 */
	public int getHoras() {
		return horas;
	}

	/**
	 * Recupera o valor dos minutos.
	 * 
	 * @return O valor dos minutos.
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * Metodo de comparacao de datas sem levar em consideracao o horario.
	 * 
	 * @param data A data a ser comparada.
	 * @return -1 para data (parametro) mais recente, 0 para dias iguais e 1 para
	 *         data (parametro) mais antiga.
	 */
	public int comparaData(Data data) {
		if (this.getAno() > data.getAno()) {
			return 1;
		}
		if (this.getAno() < data.getAno()) {
			return -1;
		}

		if (this.getMes() > data.getMes()) {
			return 1;
		}
		if (this.getMes() < data.getMes()) {
			return -1;
		}
		if (this.getDia() > data.getDia()) {
			return 1;
		}
		if (this.getDia() < data.getDia()) {
			return -1;
		}
		return 0;
	}

	/**
	 * Metodo de comparacao de horas.
	 * 
	 * @param data A data cujo horario sera comparado.
	 * @return 1 para horario menor da data passada como parametro, 0 para horario
	 *         igual e -1 para horario maior da data passada como parametro.
	 */
	public int comparaHoras(Data data) {
		if (this.getHoras() > data.getHoras()) {
			return 1;
		}
		if (this.getHoras() < data.getHoras()) {
			return -1;
		}
		return 0;
	}

	/**
	 * Representa a data como String.
	 * 
	 * @return Uma string que representa a data no formato dd/mm/yyyy e o horario no
	 *         formato hh:mm
	 */
	public String toStringFormato1() {
		return "Data e hora nos formatos: dd/mm/yyyy e hh:mm\n" + "Data: " + this.getDia() + "/" + this.getMes() + "/"
				+ this.getAno() + ", Horario: " + this.getHoras() + ":" + this.getMinutos() + ".\n";
	}

	/**
	 * Metodo para "transformar" o valor do mes com uma string (nome do mes).
	 */
	protected static final String[] nomeDoMes = { "", "JANEIRO", "FEVEREIRO", "MARCO", "ABRIL", "MAIO", "JUNHO",
			"JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO", };

	/**
	 * Representa a data como string.
	 * 
	 * @return Uma string que representa a data no formato: DIA de MES de ANO, as
	 *         HORAS horas e MINUTOS minutos.
	 */
	public String toStringFormato2() {
		return "Data e hora nos formatos: DIA de MES de ANO, as HORAS horas e MINUTOS minutos\n" + this.getDia()
				+ " de " + nomeDoMes[this.getMes()] + " de " + this.getAno() + ", as " + this.getHoras() + " horas e "
				+ this.getMinutos() + "minutos.\n";
	}

	/**
	 * Representa somente a data como uma string.
	 * 
	 * @return Uma string que representa somente a data como string.
	 */
	public String toStringSomenteData() {
		return this.getDia() + "/" + nomeDoMes[this.getMes()] + "/" + this.getAno() + "\n";
	}

}
