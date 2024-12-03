package dados;

import java.util.ArrayList;
import java.util.List;

public class RelatorioFuncionario {

	private Funcionario funcionario; // Funcionário relacionado ao relatório
	private List<Registro> registros = new ArrayList<Registro>(); // Lista consolidada de registros (uso e manutenção)

	/**
	 * Construtor que inicializa o relatório para o funcionário fornecido. Os
	 * registros são obtidos do histórico de uso e manutenção do funcionário, e
	 * organizados em ordem cronológica.
	 *
	 * @param f O funcionário para o qual o relatório será gerado.
	 */
	public RelatorioFuncionario(Funcionario f) {
		this.funcionario = f;

		// Obtém o número total de registros de uso e manutenção do funcionário
		int totalRegistros = f.getHistoricoManutencao().size() + f.getHistoricoUso().size();

		// Consolida os registros de uso e manutenção na lista 'registros'
		for (int i = 0; i < totalRegistros; i++) {
			if (i >= f.getHistoricoUso().size()) {
				// Adiciona registros de manutenção quando os de uso já foram processados
				registros.add(f.getHistoricoManutencao().get(i - f.getHistoricoUso().size()));
			} else {
				// Adiciona registros de uso
				registros.add(f.getHistoricoUso().get(i));
			}
		}

		// Ordena os registros em ordem cronológica
		this.ordenar(registros);
	}

	/**
	 * Ordena os registros em ordem cronológica com base nas datas. Implementa uma
	 * lógica similar ao Bubble Sort.
	 *
	 * @param r A lista de registros a ser ordenada.
	 */
	public void ordenar(List<Registro> r) {
		for (int i = 0; i < r.size(); i++) {
			Registro rI = r.get(i);
			Data d1 = rI.getData(); // Data do registro atual

			for (int j = 0; j < r.size() && j != i; j++) {
				Registro rJ = r.get(j);
				Data d2 = rJ.getData(); // Data do registro comparado

				// Troca os registros de lugar caso estejam fora de ordem
				if (i > j) {
					if (d1.comparaData(d2) == -1) { // d1 ocorre antes de d2
						Registro aux = r.get(j);
						r.set(j, r.get(i));
						r.set(i, aux);
					}
				} else {
					if (d1.comparaData(d2) == 1) { // d1 ocorre depois de d2
						Registro aux = r.get(j);
						r.set(j, r.get(i));
						r.set(i, aux);
					}
				}
			}
		}
	}

	/**
	 * Obtém o funcionário associado ao relatório.
	 *
	 * @return O funcionário do relatório.
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	/**
	 * Obtém os registros associado ao funcionario.
	 *
	 * @return Os registros do funcionario.
	 */
	public List<Registro> getRegistros() {
		return registros;
	}

	/**
	 * Retorna uma representação em string do relatório. Inclui informações do
	 * funcionário e todos os registros associados.
	 *
	 * @return Uma string formatada representando o relatório.
	 */
	@Override
	public String toString() {
		// Cabeçalho do relatório com o nome do funcionário
		String ret = "Relatorio do funcionario: " + this.getFuncionario().getNome() + "\n";

		// Adiciona cada registro à string, diferenciando entre uso e manutenção
		for (int i = 0; i < this.registros.size(); i++) {
			if (this.registros.get(i) instanceof RegistroDeUso) {
				RegistroDeUso aux = (RegistroDeUso) this.registros.get(i);
				ret = ret + aux.toString();
			} else {
				RegistroManutencao aux = (RegistroManutencao) this.registros.get(i);
				ret += aux.toString();
			}
		}
		return ret;
	}
}
