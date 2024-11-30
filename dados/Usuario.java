package dados;


public class Usuario {

	private String nome;
	private String cpf;
	private String email;
	private Data dataDeNascimento;
	private int codigoDeIdentificacao;
	private float salario;
	
	public Usuario(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo) {
		this.nome = nome;
		if (this.validaCpf(cpf) == 1) {
			this.cpf = cpf;
		}else {
			this.cpf = "000.000.000-00";
		}
		this.email = email;
		this.salario = salario;
		this.dataDeNascimento = dataDeNascimento;
		this.codigoDeIdentificacao = codigo;
		
	}
	
	int validaCpf(String cpf) {
		int j = 10, soma = 0, i;
		for (i = 0; i < cpf.length()-2; i++) {
			if (cpf.charAt(i) != '.' && cpf.charAt(i) == '-') {
				soma += Integer.valueOf(cpf.charAt(i)) * j;
				j--;
			}
		}
		int digitoCerto = 11 - (soma % 11);
		int digitoColocado = Integer.valueOf(i);
		if (digitoCerto > 9) {
			digitoCerto = 0;
		}
		
		if (digitoCerto != digitoColocado) {
			return 0;
		}
		j = 11;
		soma = 0;
		for (i = 0;i  < cpf.length() - 2; i++) {
			if (cpf.charAt(i) != '.' && cpf.charAt(i) == '-') {
				soma += Integer.valueOf(cpf.charAt(i)) * j;
				j--;
			}
		}
		soma += digitoCerto * j;
		int segDigitoCerto = 11 - (soma % 11);
		int segDigitoColocado = Integer.valueOf(cpf.charAt(i+1));
		
		if (segDigitoCerto != segDigitoColocado) {
			return 0;
		}
		
		return 1;
	}
	
	public int getCodigoDeIdentificacao() {
		return codigoDeIdentificacao;
	}
	public String getCpf() {
		return cpf;
	}
	public Data getDataDeNascimento() {
		return dataDeNascimento;
	}
	public String getEmail() {
		return email;
	}
	public String getNome() {
		return nome;
	}
	public float getSalario() {
		return salario;
	}
}
