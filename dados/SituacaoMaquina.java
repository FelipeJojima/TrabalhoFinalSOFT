package dados;

public class SituacaoMaquina {

	// Variável de instância para armazenar a situação da máquina
	private int situacaoMaquina;

	// Constantes para representar as diferentes situações da máquina
	public static final int FINANCIADA = 1; // Situação para máquina financiada
	public static final int ALUGADA = 2; // Situação para máquina alugada
	public static final int COMPRADA = 3; // Situação para máquina comprada

	/**
	 * Construtor que define a situação da máquina.
	 * 
	 * @param situacao O valor inteiro que representa a situação da máquina. Aceita
	 *                 1 (FINANCIADA), 2 (ALUGADA), 3 (COMPRADA). Caso o valor seja
	 *                 inválido, a situação será definida como 0.
	 */
	public SituacaoMaquina(int situacao) {
		// Verifica se a situação fornecida é válida
		if (this.verifica(situacao) == 0) {
			this.situacaoMaquina = 0; // Se inválido, define como 0
		} else {
			this.situacaoMaquina = situacao; // Se válido, define a situação
		}
	}

	/**
	 * Método getter para obter a situação atual da máquina.
	 * 
	 * @return A situação da máquina, representada como um valor inteiro.
	 */
	public int getSituacaoMaquina() {
		return situacaoMaquina;
	}

	/**
	 * Método para verificar se o valor fornecido é uma situação válida.
	 * 
	 * @param situacao O valor a ser verificado.
	 * @return 1 se a situação for válida (1, 2 ou 3); 0 caso contrário.
	 */
	public int verifica(int situacao) {
		// Verifica se a situação é uma das opções válidas
		if (situacao == 1 || situacao == 2 || situacao == 3) {
			return 1; // Situação válida
		}
		return 0; // Situação inválida
	}

	// Array de Strings para representar a situação de forma legível
	protected static final String[] situacao = { "", "FINANCIADA", "ALUGADA", "COMPRADA" };

	/**
	 * Método toString que retorna a representação textual da situação da máquina.
	 * 
	 * @return Uma string descrevendo a situação da máquina.
	 */
	@Override
	public String toString() {
		return "Situacao da maquina: " + situacao[this.getSituacaoMaquina()] + "\n";
	}
}
