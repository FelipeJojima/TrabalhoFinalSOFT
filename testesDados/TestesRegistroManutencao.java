package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.RegistroManutencao;

public class TestesRegistroManutencao {


    private Data dataInicio;
    private Data dataFim;
    private Gerente gerente;
    private Funcionario funcionario;
    private Maquina maquina;
    private RegistroManutencao registroManutencao;

    @BeforeEach
    public void setUp() {
        // Configuração inicial para os testes
        dataInicio = new Data(1, 12, 2024, 9, 0); // Data de início: 1 de dezembro de 2024, 09:00
        dataFim = new Data(2, 12, 2024, 17, 0); // Data de término: 2 de dezembro de 2024, 17:00
        gerente = new Gerente("Ana Silva", "12345678901", "ana@empresa.com", 8000.0f, 
                new Data(20, 5, 1980), 101, "senha123");
        funcionario = new Funcionario("João Santos", "98765432100", "joao@empresa.com", 3500.0f, 
                new Data(15, 8, 1990), 201, gerente, "senha123");
        maquina = new Maquina("Torno CNC", 301, gerente);

        // Inicializa o registro de manutenção
        registroManutencao = new RegistroManutencao(dataInicio, gerente, funcionario, maquina, dataFim);
    }

    @Test
    public void testConstrutor() {
        // Verifica se os valores passados ao construtor foram atribuídos corretamente
        assertEquals(dataInicio, registroManutencao.getData());
        assertEquals(dataFim, registroManutencao.getDataFim());
        assertEquals(gerente, registroManutencao.getResponsavel());
        assertEquals(funcionario, registroManutencao.getFuncionario());
        assertEquals(maquina, registroManutencao.getMaquina());
    }

    @Test
    public void testGetDataFim() {
        // Verifica se a data de término é retornada corretamente
        assertEquals(dataFim, registroManutencao.getDataFim());
    }

    @Test
    public void testSetDataFim() {
        // Testa a modificação da data de término
        Data novaDataFim = new Data(3, 12, 2024, 12, 0); // Nova data de término
        registroManutencao.setDataFim(novaDataFim);
        assertEquals(novaDataFim, registroManutencao.getDataFim());
    }

    @Test
    public void testToString() {
        // Verifica o formato da string retornada pelo método toString
        String expected = "Registro de manutencao na maquina: Torno CNC\n" +
                "Realizada por: João Santos\n" +
                "Inicio: " + dataInicio.toStringFormato1() +
                "Data final prevista: " + dataFim.toStringFormato1() +
                "Responsavel: Ana Silva\n";
        assertEquals(expected, registroManutencao.toString());
    }

    @Test
    public void testDataFimAntesDataInicio() {
        // Testa um caso onde a data de término é anterior à data de início
        Data dataFimInvalida = new Data(30, 11, 2024, 18, 0); // Data de término anterior à data de início
        registroManutencao.setDataFim(dataFimInvalida);
        assertEquals(dataFimInvalida, registroManutencao.getDataFim());
    }

    @Test
    public void testFuncionarioNulo() {
        // Testa se a classe permite um funcionário nulo
        RegistroManutencao registroComFuncionarioNulo = 
                new RegistroManutencao(dataInicio, gerente, null, maquina, dataFim);
        assertNull(registroComFuncionarioNulo.getFuncionario());
    }

    @Test
    public void testGerenteNulo() {
        // Testa se a classe permite um gerente nulo
        RegistroManutencao registroComGerenteNulo = 
                new RegistroManutencao(dataInicio, null, funcionario, maquina, dataFim);
        assertNull(registroComGerenteNulo.getResponsavel());
    }

    @Test
    public void testMaquinaNula() {
        // Testa se a classe permite uma máquina nula
        RegistroManutencao registroComMaquinaNula = 
                new RegistroManutencao(dataInicio, gerente, funcionario, null, dataFim);
        assertNull(registroComMaquinaNula.getMaquina());
    }

    @Test
    public void testDatasIguais() {
        // Testa um caso onde as datas de início e término são iguais
        Data dataIgual = new Data(1, 12, 2024, 9, 0);
        RegistroManutencao registroComDatasIguais = 
                new RegistroManutencao(dataIgual, gerente, funcionario, maquina, dataIgual);
        assertEquals(dataIgual, registroComDatasIguais.getData());
        assertEquals(dataIgual, registroComDatasIguais.getDataFim());
    }
}
