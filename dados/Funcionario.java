package dados;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario{

	private ContratoFuncionario contrato;
	private List<Maquina> historico = new ArrayList<Maquina>();
	private Gerente responsavel;
}
