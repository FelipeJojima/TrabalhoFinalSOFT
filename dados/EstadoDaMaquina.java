package dados;

public class EstadoDaMaquina {

	private int estado;
	public static final int OCIOSA = 0;
	public static final int ATIVA = 1;
	public static final int INOPERANTE = 2;
	public static final int MANUTENCAO = 3;
	
	public EstadoDaMaquina(int estado) {
		this.estado = estado;
	}
	
	public int getEstado() {
		return estado;
	}
	
	protected static final String[] nomeEstado = {
		"OCIOSA",
		"ATIVA",
		"INOPERANTE",
		"MANUTENCAO",
	};
	
	@Override
	public String toString() {
		return "Status da maquina: " + nomeEstado[this.getEstado()] + "\n";
	}
}
