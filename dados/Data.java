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
		if (this.verificaData(dia, mes, ano, horas, minutos) == 1
				|| this.verificaData(dia, mes, ano, horas, minutos) == 2) {
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
	 * Construtor de uma data comum.
	 * 
	 * @param dia O valor do dia.
	 * @param mes O valor do mês.
	 * @param ano O valor do ano.
	 */
	public Data(int dia, int mes, int ano) {
		if (this.verificaData(dia, mes, ano, 0, 0) == 1 || this.verificaData(dia, mes, ano, 0, 0) == 2) {
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		} else {
			this.dia = 0;
			this.mes = 0;
			this.ano = 0;
		}
		this.horas = 0;
		this.minutos = 0;
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
		int mes = this.getMes(), ano= this.getAno(), dMes = data.getMes(), dAno = data.getAno();
		if (this.comparaData(data) == -1) {
			if (ano == dAno) {
				return dMes - mes;
			}
			int anos = dAno - ano;
			int meses = 0;
			if (dMes < mes) {
				meses = (12 + dMes) -mes;
			} else {
				meses = dMes - mes;
			}
			return (12 * anos) + meses;
		}
		if (ano == dAno) {
			return mes - dMes;
		}
		int anos = ano - dAno;
		int meses = 0;
		if (mes < dMes) {
			meses = (12 + mes) - dMes;
		} else {
			meses = mes - dMes;
		}
		return (12 * anos) + meses;
	}

	/**
	 * Metodo que retorna a diferenca entre duas datas em dias.
	 * 
	 * @param data A data a ser comparada.
	 * @return A diferenca em dias.
	 */
	public int diferencaDias(Data data) {
		int compara = this.comparaData(data);
		int somaDias = 0;
		if (compara == 0) {
			return 0;
		}
		int difMes = 0;
		difMes = this.mesesDiferenca(data);
		int mes = this.getMes(), dia = this.getDia(), ano = this.getAno();
		int dMes = data.getMes(), dDia = data.getDia(),dAno = data.getAno();
		if (difMes != 0) {
			if (compara == -1) {
				for (int i = 1; i < difMes; i++) {
					if (mes + i > 12) {
						if (mes + i - 12 == 4 || mes + i - 12 == 6 || mes + i - 12 == 9
								|| mes + i - 12 == 11) {
							somaDias += 30;
						} else if (mes + i - 12 == 2) {
							if ((ano + 1) % 4 == 0) {
								somaDias += 29;
							} else {
								somaDias += 28;
							}
						} else {
							somaDias += 31;
						}
					} else {
						if (mes + i == 4 || mes + i == 6 || mes + i == 9
								|| mes + i == 11) {
							somaDias += 30;
						} else if (mes + i == 2) {
							if (ano % 4 == 0) {
								somaDias += 29;
							} else {
								somaDias += 28;
							}
						} else {
							somaDias += 31;
						}
					}
				}
				if (mes == 4 ||mes == 6 || mes == 9 || mes == 11) {
					somaDias += (30 - dia);
				}else if (mes == 2) {
					if (ano % 4 == 0) {
						somaDias += (29 - dia);
					}else {
						somaDias += (28 - dia);
					}
				}else {
					somaDias += (31 - dia);
				}
				somaDias += dDia;
			}else {
				for (int i = 1; i < difMes; i++) {
					if (dMes + i > 12) {
						if (dMes + i - 12 == 4 || dMes + i - 12 == 6 || dMes + i - 12 == 9
								|| dMes + i - 12 == 11) {
							somaDias += 30;
						} else if (dMes + i - 12 == 2) {
							if ((dAno + 1) % 4 == 0) {
								somaDias += 29;
							} else {
								somaDias += 28;
							}
						} else {
							somaDias += 31;
						}
					} else {
						if (dMes + i == 4 || dMes + i == 6 || dMes + i == 9
								|| dMes + i == 11) {
							somaDias += 30;
						} else if (dMes + i == 2) {
							if (dAno % 4 == 0) {
								somaDias += 29;
							} else {
								somaDias += 28;
							}
						} else {
							somaDias += 31;
						}
					}
				}
				if (dMes == 4 ||dMes == 6 || dMes == 9 || dMes == 11) {
					somaDias += (30 - dDia);
				}else if (dMes == 2) {
					if (dAno % 4 == 0) {
						somaDias += (29 - dDia);
					}else {
						somaDias += (28 - dDia);
					}
				}else {
					somaDias += (31 - dDia);
				}
				somaDias += dia;
			}
			return somaDias;
		}else {
			if (compara == -1) {
				return dDia - dia;
			}else {
				return dia - dDia;
			}
		}
	}

	/**
	 * Metodo que retorna a diferenca entre duas datas em horas.
	 * 
	 * @param data A data a ser comparada.
	 * @return A diferenca em horas.
	 */
	public int diferencaHoras(Data data) {
		int compara = this.comparaData(data);
		int horas = this.getHoras(), dHoras = data.getHoras();
		if (compara == 0) {
			if (horas > dHoras) {
				return horas - dHoras;
			}
			return dHoras - horas;
		}
		int difDias = this.diferencaDias(data);
		int somaHoras = (difDias - 1)*24;
		if (compara == -1) {
			somaHoras += (24 - horas);
			somaHoras += dHoras;
		}else {
			somaHoras += (24 - dHoras);
			somaHoras += horas;
		}
		return somaHoras;
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
	 * @return -1 para data (chamada) mais antiga, 0 para dias iguais e 1 para data
	 *         (parametro) mais antiga.
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
