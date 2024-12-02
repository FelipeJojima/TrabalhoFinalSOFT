package testesNegocio;

import  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dados.*;
import negocio.CadastroUsuario;

public class CadastroUsuarioTest {

    private CadastroUsuario cadastroComGerente;
    private CadastroUsuario cadastroComFuncionario;
    private Gerente gerente;
    private Funcionario funcionario;

    @BeforeEach
    void setUp() {
        gerente = new Gerente("Gerente Teste", "12345678900", "gerente@empresa.com", 5000.0f,
                new Data(1, 1, 1980), "G001", "senha123");
        funcionario = new Funcionario("Funcionario Teste", "09876543211", "funcionario@empresa.com", 2000.0f,
                new Data(15, 6, 1990), "F001", gerente, "senha456");

        cadastroComGerente = new CadastroUsuario(gerente);
        cadastroComFuncionario = new CadastroUsuario(funcionario);
    }

    @Test
    void testGetUsuario() {
        assertEquals(gerente, cadastroComGerente.getUsuario());
        assertEquals(funcionario, cadastroComFuncionario.getUsuario());
    }

    @Test
    void testAlteraNome() {
        cadastroComGerente.alteraNome("Novo Nome Gerente");
        assertEquals("Novo Nome Gerente", cadastroComGerente.getUsuario().getNome());

        cadastroComFuncionario.alteraNome("Novo Nome Funcionario");
        assertEquals("Novo Nome Funcionario", cadastroComFuncionario.getUsuario().getNome());
    }

    @Test
    void testAlteraCpf() {
        assertEquals(1, cadastroComGerente.alteraCpf("11111111111"));
        assertEquals("11111111111", cadastroComGerente.getUsuario().getCpf());

        assertEquals(1, cadastroComFuncionario.alteraCpf("22222222222"));
        assertEquals("22222222222", cadastroComFuncionario.getUsuario().getCpf());
    }

    @Test
    void testAlteraEmail() {
        cadastroComGerente.alteraEmail("novoemail@empresa.com");
        assertEquals("novoemail@empresa.com", cadastroComGerente.getUsuario().getEmail());

        cadastroComFuncionario.alteraEmail("emailatualizado@empresa.com");
        assertEquals("emailatualizado@empresa.com", cadastroComFuncionario.getUsuario().getEmail());
    }

    @Test
    void testAlteraSalario() {
        cadastroComGerente.alteraSalario(5500.0f);
        assertEquals(5500.0f, cadastroComGerente.getUsuario().getSalario());

        cadastroComFuncionario.alteraSalario(2500.0f);
        assertEquals(2500.0f, cadastroComFuncionario.getUsuario().getSalario());
    }

    @Test
    void testAlteraContrato() {
        ContratoFuncionario novoContrato = new ContratoFuncionario(); // Assumindo construtor padrão
        cadastroComFuncionario.alteraContrato(novoContrato);
        assertEquals(novoContrato, ((Funcionario) cadastroComFuncionario.getUsuario()).getContrato());
    }

    @Test
    void testAlteraResponsavel() {
        Gerente novoGerente = new Gerente("Novo Gerente", "33333333333", "novo@empresa.com", 6000.0f,
                new Data(10, 10, 1985), "G002", "senha999");
        cadastroComFuncionario.alteraResponsavel(novoGerente);
        assertEquals(novoGerente, ((Funcionario) cadastroComFuncionario.getUsuario()).getResponsavel());
    }

    @Test
    void testAlteraDataNascimento() {
        Data novaData = new Data(1, 1, 2000);
        cadastroComGerente.alteraDataNascimento(novaData);
        assertEquals(novaData, cadastroComGerente.getUsuario().getDataDeNascimento());

        cadastroComFuncionario.alteraDataNascimento(novaData);
        assertEquals(novaData, cadastroComFuncionario.getUsuario().getDataDeNascimento());
    }
}
