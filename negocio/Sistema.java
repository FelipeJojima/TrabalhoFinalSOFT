package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.ContratoFuncionario;
import dados.Funcionario;
import dados.Maquina;
import dados.RegistroDeUso;
import dados.ContratoMaquinas;

public class Sistema {

	private static Sistema instance = null;
	private List<Maquina> maquinas = new ArrayList<Maquina>();
	private Login login;

	public Sistema() {

	}

	public static Sistema getInstance() {
		if (instance == null) {
			instance = new Sistema();
		}
		return instance;
	}
	
	public void novoContratoFuncionario(Funcionario f, ContratoFuncionario contrato) {
		
	}
	
	public void novoContratoMaquina(Maquina m, ContratoMaquinas contrato) {
		
	}
	
	public void novaMaquina(Maquina m) {
		
	}
	
	public void alteraMaquina(Maquina nova, Maquina antiga) {
		
	}
	
	public void iniciaUso(Maquina m, CadastroUsuario u) {
		
	}
	
	public void finalizaUso(Maquina m, CadastroUsuario u) {
		
	}
	
	public void inativaMaquina(Maquina m, CadastroUsuario u) {
		
	}
	
	public void manutencaoMaquina(Maquina m, CadastroUsuario u) {
		
	}
	
	public void geraRelatorioMaquina(Maquina m) {
		
	}
	
	public void geraRelatorioFuncionario(Funcionario f) {
		
	}
	
	public void login(String usuario, String senha) {
		
	}
	
	public void novoUsuario(CadastroUsuario novo) {
		
	}
	
	public void exclueUsuario(CadastroUsuario remover, CadastroUsuario gerente) {
		
	}
	
	public void exclueMaquina(Maquina m, CadastroUsuario u) {
		
	}
	
	public void encerrarConta(CadastroUsuario excluir) {
		
	}
	
	public void informacoesPessoais(CadastroUsuario u) {
		
	}

	public void informacoesMaquina(Maquina m) {
		
	}
	
}
