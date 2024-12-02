package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dados.Data;
import dados.Usuario;

public class TestesUsuario {


    // Teste Geral 1: Criar um Usuario com CPF válido
    @Test
    public void testCriaUsuarioComCpfValido() {
        Data dataNascimento = new Data(12, 12, 1990); // Exemplo de data válida
        Usuario usuario = new Usuario("João Silva", "123.456.789-09", "joao@example.com", 2000.0f, dataNascimento, 123, "senha123");
        
        assertEquals("João Silva", usuario.getNome());
        assertEquals("123.456.789-09", usuario.getCpf());
        assertEquals("joao@example.com", usuario.getEmail());
        assertEquals(2000.0f, usuario.getSalario());
    }

    // Teste Geral 2: Criar um Usuario com CPF inválido
    @Test
    public void testCriaUsuarioComCpfInvalido() {
        Data dataNascimento = new Data(12, 12, 1990); // Exemplo de data válida
        Usuario usuario = new Usuario("Maria Oliveira", "123.456.789-00", "maria@example.com", 2500.0f, dataNascimento, 456, "senha456");
        
        // CPF inválido será atribuído como "000.000.000-00"
        assertEquals("000.000.000-00", usuario.getCpf());
    }

    // Teste de borda 1: CPF com caracteres diferentes
    @Test
    public void testCpfComCaracteresInvalidos() {
        Data dataNascimento = new Data(15, 10, 1995); // Exemplo de data válida
        Usuario usuario = new Usuario("Carlos Santos", "111-222-333.44", "carlos@example.com", 3000.0f, dataNascimento, 789, "senha789");
        
        // CPF inválido, então o valor atribuído deve ser "000.000.000-00"
        assertEquals("000.000.000-00", usuario.getCpf());
    }

    // Teste de borda 2: Criar um Usuario com CPF válido e verificação de código de identificação
    @Test
    public void testCodigoDeIdentificacao() {
        Data dataNascimento = new Data(22, 03, 1985); // Exemplo de data válida
        Usuario usuario = new Usuario("Lucas Pereira", "987.654.321-00", "lucas@example.com", 3500.0f, dataNascimento, 999, "senha123");

        // Verificando se o código de identificação foi atribuído corretamente
        assertEquals(999, usuario.getCodigoDeIdentificacao());
    }

    // Teste de borda 3: Testando a validação do CPF no método setCpf
    @Test
    public void testSetCpfInvalido() {
        Data dataNascimento = new Data(10, 10, 1992); // Exemplo de data válida
        Usuario usuario = new Usuario("Ana Costa", "111.222.333-44", "ana@example.com", 2800.0f, dataNascimento, 101, "senha101");

        // Tentando setar um CPF inválido
        assertEquals(0, usuario.setCpf("123.456.789-00")); // CPF inválido
        assertEquals("000.000.000-00", usuario.getCpf()); // CPF não modificado para "000.000.000-00"
    }

    // Teste Geral 3: Alterar os dados de um usuário
    @Test
    public void testAlterarDadosUsuario() {
        Data dataNascimento = new Data(05, 05, 1987); // Exemplo de data válida
        Usuario usuario = new Usuario("Juliana Lima", "444.555.666-77", "juliana@example.com", 4000.0f, dataNascimento, 202, "senha202");

        // Alterando o nome, email e salário
        usuario.setNome("Juliana Souza");
        usuario.setEmail("juliana.souza@example.com");
        usuario.setSalario(4500.0f);
        
        // Verificando se as mudanças foram feitas corretamente
        assertEquals("Juliana Souza", usuario.getNome());
        assertEquals("juliana.souza@example.com", usuario.getEmail());
        assertEquals(4500.0f, usuario.getSalario());
    }

    // Teste de borda 4: Validar CPF vazio (sem números)
    @Test
    public void testCpfVazio() {
        Data dataNascimento = new Data(01, 01, 1998); // Exemplo de data válida
        Usuario usuario = new Usuario("Roberta Almeida", "02", "roberta@example.com", 5000.0f, dataNascimento, 303, "senha303");

        // CPF inválido será atribuído como "000.000.000-00"
        assertEquals("000.000.000-00", usuario.getCpf());
    }
}
