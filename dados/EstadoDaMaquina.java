package dados;

public class EstadoDaMaquina {

	private int estado;
	/**
	 * Valor para maquina ociosa.
	 */
	public static final int OCIOSA = 0;

	/**
	 * Valor para maquina ativa.
	 */
	public static final int ATIVA = 1;

	/**
	 * Valor para maquina inoperante.
	 */
	public static final int INOPERANTE = 2;

	/**
	 * Valor para maquina em manutencao.
	 */
	public static final int MANUTENCAO = 3;

	/**
	 * Construtor simples para o estado da maquina.
	 * 
	 * @param estado O valor do estado da maquina (0 - Ociosa, 1 - Ativa, 2 -
	 *               Inoperante e 3 - em manutencao).
	 */
	public EstadoDaMaquina(int estado) {
		if (this.verifica(estado) == 1) {
			this.estado = estado;
		} else {
			this.estado = 4;
		}
	}

	/**
	 * Metodo que verifica o valor do estado passado.
	 * 
	 * @param estado O estado passado.
	 * @return Retorna 0 ou 1. 0 para entrada invalida e 1 para entrada valida.
	 */
	public int verifica(int estado) {
		if (estado < 0 || estado > 3) {
			return 0;
		}
		return 1;
	}

	/**
	 * Recupera o valor do estado da maquina.
	 * 
	 * @return O valor do estado da maquina (0 a 3).
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Sincronizando o valor de estado com a string respectiva.
	 */
	protected static final String[] nomeEstado = { "OCIOSA", "ATIVA", "INOPERANTE", "MANUTENCAO", "", };

	@Override
	/**
	 * Metodo para transformar o estado da maquina em string.
	 * 
	 * @return Retorna uma string representando o estado da maquina.
	 */
	public String toString() {
		return "Status da maquina: " + nomeEstado[this.getEstado()] + "\n";
	}
}
