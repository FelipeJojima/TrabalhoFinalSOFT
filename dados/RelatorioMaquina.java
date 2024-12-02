package dados;

import java.util.ArrayList;
import java.util.List;

public class RelatorioMaquina {

	private Maquina maquina; // Máquina associada ao relatório
	private List<Registro> registros = new ArrayList<Registro>(); // Lista de registros (uso e manutenção)

	/**
	 * Construtor que gera o relatório para a máquina especificada.
	 * 
	 * @param m Máquina para a qual o relatório será gerado.
	 */
	public RelatorioMaquina(Maquina m) {
		this.maquina = m;
		int totalRegistros = m.getHistoricoManutencao().size() + m.getHistoricoUso().size();

		// Adiciona todos os registros de uso e manutenção à lista de registros.
		for (int i = 0; i < totalRegistros; i++) {
			if (i >= m.getHistoricoUso().size()) {
				registros.add(m.getHistoricoManutencao().get(i - m.getHistoricoUso().size()));
			} else {
				registros.add(m.getHistoricoUso().get(i));
			}
		}

		// Ordena os registros cronologicamente.
		this.ordenar(registros);
	}

	/**
	 * Método que ordena uma lista de registros com base na data de início dos
	 * registros. A ordenação é feita in-place, modificando a lista fornecida.
	 * 
	 * @param r Lista de registros a ser ordenada.
	 */
	public void ordenar(List<Registro> r) {
		for (int i = 0; i < r.size(); i++) {
			Registro rI = r.get(i);
			Data d1 = rI.getData();
			for (int j = 0; j < r.size() && j != i; j++) {
				Registro rJ = r.get(j);
				Data d2 = rJ.getData();

				// Realiza a troca se necessário, para ordenar em ordem cronológica.
				if (i > j) {
					if (d1.comparaData(d2) == -1) {
						Registro aux = r.get(j);
						r.set(j, r.get(i));
						r.set(i, aux);
					}
				} else {
					if (d1.comparaData(d2) == 1) {
						Registro aux = r.get(j);
						r.set(j, r.get(i));
						r.set(i, aux);
					}
				}
			}
		}
	}

	/**
	 * Retorna a máquina associada ao relatório.
	 * 
	 * @return Máquina do relatório.
	 */
	public Maquina getMaquina() {
		return maquina;
	}

	/**
	 * Retorna os registros associados a maquina.
	 * 
	 * @return Registros da maquina.
	 */
	public List<Registro> getRegistros() {
		return registros;
	}

	/**
	 * Representação textual do relatório.
	 * 
	 * @return String contendo os detalhes da máquina e seus registros ordenados.
	 */
	@Override
	public String toString() {
		String ret = "Relatorio da maquina: " + this.getMaquina().getNome() + "\n";

		// Itera pelos registros, chamando o método toString apropriado para cada tipo
		// de registro.
		for (int i = 0; i < this.registros.size(); i++) {
			if (this.registros.get(i) instanceof RegistroDeUso) {
				RegistroDeUso aux = (RegistroDeUso) this.registros.get(i);
				ret += aux.toString();
			} else {
				RegistroManutencao aux = (RegistroManutencao) this.registros.get(i);
				ret += aux.toString();
			}
		}
		return ret;
	}

}
