package dados;

import java.util.ArrayList;
import java.util.List;

public class RelatorioFuncionario {

	private Funcionario funcionario;
	private List<Registro> registros = new ArrayList<Registro>();

	public RelatorioFuncionario(Funcionario f) {
		this.funcionario = f;
		int totalRegistros = f.getHistoricoManutencao().size() + f.getHistoricoUso().size();
		for (int i = 0; i < totalRegistros; i++) {
			if (i >= f.getHistoricoUso().size()) {
				registros.add(f.getHistoricoManutencao().get(i - f.getHistoricoUso().size()));
			} else {
				registros.add(f.getHistoricoUso().get(i));
			}
		}
		this.ordenar(registros);
	}
	
	public void ordenar(List<Registro> r) {
		for (int i = 0; i < r.size(); i++) {
			Registro rI = r.get(i);
			Data d1 = rI.getData();
			for (int j = 0; j < r.size() && j != i; j++) {
				Registro rJ = r.get(j);
				Data d2 = rJ.getData();
				if (i > j) {
					if (d1.comparaData(d2) == -1) {
						Registro aux = r.get(j);
						r.set(j, r.get(i));
						r.set(i, aux);
					}
				}else {
					if (d1.comparaData(d2) == 1) {
						Registro aux = r.get(j);
						r.set(j, r.get(i));
						r.set(i, aux);
					}
				}
			}
		}
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	@Override
	public String toString() {
		String ret = "Relatorio do funcionario: " + this.getFuncionario().getNome() + "\n";
		for (int i = 0; i < this.registros.size(); i++) {
			if (this.registros.get(i) instanceof RegistroDeUso) {
				RegistroDeUso aux = (RegistroDeUso)this.registros.get(i);
				ret += aux.toString();
			}else {
				RegistroManutencao aux = (RegistroManutencao)this.registros.get(i);
				ret += aux.toString();
			}
		}
		return ret;
	}
}
