package dados;

import java.util.ArrayList;
import java.util.List;

public class Maquina {

	private String nome;
	private int codigoIdentificacao;
	private EstadoDaMaquina status;
	private SituacaoMaquina situacao;
	private List<Funcionario> historico = new ArrayList<Funcionario>();
	private Gerente responsavel;
}
