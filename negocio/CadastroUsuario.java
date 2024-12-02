package negocio;

import dados.ContratoFuncionario;
import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Usuario;

public class CadastroUsuario {

	private Usuario usuario;

	public CadastroUsuario(Gerente g) {
		this.usuario = new Gerente(g.getNome(), g.getCpf(), g.getEmail(), g.getSalario(), g.getDataDeNascimento(),
				g.getCodigoDeIdentificacao(),g.getSenha());
	}

	public CadastroUsuario(Funcionario f) {
		this.usuario = new Funcionario(f.getNome(), f.getCpf(), f.getEmail(), f.getSalario(), f.getDataDeNascimento(),
				f.getCodigoDeIdentificacao(), f.getResponsavel(), f.getSenha());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void alteraNome(String novoNome) {
		this.usuario.setNome(novoNome);
	}

	public int alteraCpf(String novoCpf) {
		if (this.usuario.setCpf(novoCpf) == 1) {
			return 1;
		}
		return 0;
	}

	public void alteraEmail(String novoEmail) {
		this.usuario.setEmail(novoEmail);
	}
	
	public void alteraSalario(float novoSalario) {
		this.usuario.setSalario(novoSalario);
	}
	
	public void alteraContrato(ContratoFuncionario novoContrato) {
		if (this.usuario instanceof Funcionario) {
			((Funcionario) this.usuario).setContrato(novoContrato);
		}
	}
	
	public void alteraResponsavel(Gerente novoGerente) {
		if (this.usuario instanceof Funcionario) {
			((Funcionario) this.usuario).setResponsavel(novoGerente);
		}
	}
	
	public void alteraDataNascimento(Data novaData) {
		this.usuario.setDataDeNascimento(novaData);
	}

}
