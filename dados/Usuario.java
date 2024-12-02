package dados;

public class Usuario {
	// Atributos do usuário
	private String nome;
	private String cpf;
	private String email;
	private Data dataDeNascimento;
	private int codigoDeIdentificacao;
	private float salario;
	private String senha;

	/**
	 * Construtor da classe Usuario
	 * 
	 * @param nome             String nome.
	 * @param cpf              String CPF.
	 * @param email            String email.
	 * @param salario          Salário.
	 * @param dataDeNascimento Data de nascimento.
	 * @param codigo           Código de identificação.
	 * @param senha            Senha do usuário.
	 */
	public Usuario(String nome, String cpf, String email, float salario, Data dataDeNascimento, int codigo,
			String senha) {
		this.nome = nome; // Atribui o nome
		// Valida o CPF, caso inválido, atribui um CPF padrão
		if (this.validaCpf(cpf) == 1) {
			this.cpf = cpf;
		} else {
			this.cpf = "000.000.000-00"; // CPF inválido
		}
		this.email = email; // Atribui o e-mail
		this.salario = salario; // Atribui o salário
		this.dataDeNascimento = dataDeNascimento; // Atribui a data de nascimento
		this.codigoDeIdentificacao = codigo; // Atribui o código de identificação
		this.senha = senha; // Atribui a senha
	}

	/**
	 * Método para validar o CPF (simulação de validação simplificada. Verifica a
	 * validade do CPF através de um cálculo específico que verifica os dígitos de
	 * validação - 2 últimos dígitos).
	 * 
	 * @param cpf String CPF
	 * @return 0 para cpf inválido e 1 para cpf vãlido.
	 */
	int validaCpf(String cpf) {
		int j = 10, soma = 0, i;
		// Soma para o primeiro dígito verificador
		for (i = 0; i < cpf.length() - 2; i++) {
			if (cpf.charAt(i) != '.' && cpf.charAt(i) == '-') {
				soma += Integer.valueOf(cpf.charAt(i)) * j;
				j--;
			}
		}
		// Verifica se o primeiro dígito verificador está correto
		int digitoCerto = 11 - (soma % 11);
		int digitoColocado = Integer.valueOf(i);
		if (digitoCerto > 9) {
			digitoCerto = 0;
		}

		if (digitoCerto != digitoColocado) {
			return 0; // CPF inválido
		}
		// Soma para o segundo dígito verificador
		j = 11;
		soma = 0;
		for (i = 0; i < cpf.length() - 2; i++) {
			if (cpf.charAt(i) != '.' && cpf.charAt(i) == '-') {
				soma += Integer.valueOf(cpf.charAt(i)) * j;
				j--;
			}
		}
		soma += digitoCerto * j;
		int segDigitoCerto = 11 - (soma % 11);
		int segDigitoColocado = Integer.valueOf(cpf.charAt(i + 1));

		if (segDigitoCerto != segDigitoColocado) {
			return 0; // CPF inválido
		}

		return 1; // CPF válido
	}

	// Métodos Getter para acessar os atributos do usuário
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

	// Método Setter para definir a senha
	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Método Getter para obter a senha
	public String getSenha() {
		return senha;
	}

	// Método Getter para obter o nome
	public String getNome() {
		return nome;
	}

	// Método Getter para obter o salário
	public float getSalario() {
		return salario;
	}

	// Método Setter para modificar o nome
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método Setter para modificar o CPF com validação
	 * 
	 * @param cpf String cpf.
	 * @return Retorna 1 se o CPF for válido, 0 caso contrário.
	 */
	public int setCpf(String cpf) {
		if (this.validaCpf(cpf) == 1) {
			this.cpf = cpf;
			return 1; // CPF válido
		}
		return 0; // CPF inválido
	}

	// Método Setter para modificar a data de nascimento
	public void setDataDeNascimento(Data dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	// Método Setter para modificar o e-mail
	public void setEmail(String email) {
		this.email = email;
	}

	// Método Setter para modificar o salário
	public void setSalario(float salario) {
		this.salario = salario;
	}
}
