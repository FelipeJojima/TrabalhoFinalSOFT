package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.RegistroDeUso;
import dados.RegistroManutencao;
import dados.RelatorioMaquina;
import dados.Registro;

public class TestesRelatorioMaquina {

    private Maquina maquina;
    private Gerente gerente;
    private Funcionario funcionario;
    private Data dataInicio;
    private Data dataFim;

    @BeforeEach
    void setUp() {
        // Configuração comum para os testes
        gerente = new Gerente("Gerente 1", "12345678901", "gerente@example.com", 5000, new Data(1, 1, 1980), 1, "senha123");
        funcionario = new Funcionario("Funcionario 1", "23456789012", "funcionario@example.com", 2000, new Data(1, 1, 1990), 2, gerente, "senha456");
        maquina = new Maquina("Máquina 1", 101, gerente);
        dataInicio = new Data(1, 1, 2024);
        dataFim = new Data(2, 1, 2024);
        
        // Adicionando registros de uso e manutenção
        RegistroDeUso registroUso = new RegistroDeUso(dataInicio, gerente, funcionario, maquina, dataFim, 500);
        RegistroManutencao registroManutencao = new RegistroManutencao(dataInicio, gerente, funcionario, maquina, dataFim);
        
        maquina.adicionaRegistroUso(registroUso);
        maquina.adicionaRegistroManutencao(registroManutencao);
    }

    @Test
    void testRelatorioMaquinaComRegistros() {
        RelatorioMaquina relatorio = new RelatorioMaquina(maquina);
        
        // Verificar se o relatorio contém registros e se a ordenação foi realizada corretamente
        assertNotNull(relatorio);
        assertEquals("Máquina 1", relatorio.getMaquina().getNome());
        
        List<Registro> registros = relatorio.getRegistros();
        assertEquals(2, registros.size()); // Deveria ter 2 registros (uso e manutenção)

        // Testando o conteúdo dos registros
        assertTrue(registros.get(0) instanceof RegistroDeUso);
        assertTrue(registros.get(1) instanceof RegistroManutencao);
    }

    @Test
    void testOrdenacaoDeRegistros() {
        // Adicionando mais registros para garantir que a ordenação funcione corretamente
        Data dataAntiga = new Data(31, 12, 2023);
        RegistroDeUso registroAntigo = new RegistroDeUso(dataAntiga, gerente, funcionario, maquina, dataFim, 300);
        maquina.adicionaRegistroUso(registroAntigo);
        
        // Criar o relatorio e testar a ordenação
        RelatorioMaquina relatorio = new RelatorioMaquina(maquina);
        List<Registro> registros = relatorio.getRegistros();
        
        // Verificar que os registros estão ordenados pela data
        assertEquals(dataAntiga, registros.get(0).getData()); // O registro mais antigo deve ser o primeiro
        assertEquals(dataInicio, registros.get(1).getData()); // O registro de uso deve ser o segundo
    }

    @Test
    void testRelatorioSemRegistros() {
        Maquina maquinaSemRegistros = new Maquina("Máquina sem Registros", 102, gerente);
        RelatorioMaquina relatorio = new RelatorioMaquina(maquinaSemRegistros);

        // Verificar se o relatorio é gerado corretamente mesmo sem registros
        assertNotNull(relatorio);
        assertEquals("Máquina sem Registros", relatorio.getMaquina().getNome());
        assertEquals(0, relatorio.getRegistros().size()); // Não deve haver registros
    }

    @Test
    void testRelatorioComRegistrosDeTiposDiferentes() {
        // Criar registros com diferentes tipos e testar o relatorio
        RegistroDeUso registroUso = new RegistroDeUso(dataInicio, gerente, funcionario, maquina, dataFim, 500);
        RegistroManutencao registroManutencao = new RegistroManutencao(dataInicio, gerente, funcionario, maquina, dataFim);
        
        // Adicionando os registros manualmente
        maquina.adicionaRegistroUso(registroUso);
        maquina.adicionaRegistroManutencao(registroManutencao);
        
        // Gerar o relatório
        RelatorioMaquina relatorio = new RelatorioMaquina(maquina);
        
        // Verificar a composição correta do relatório
        String relatorioTexto = relatorio.toString();
        assertTrue(relatorioTexto.contains("Registro de uso"));
        assertTrue(relatorioTexto.contains("Registro de manutencao"));
    }

    // Teste de borda: Adicionar um único registro e verificar o comportamento do relatório
    @Test
    void testRelatorioComUmUnicoRegistro() {
        RegistroDeUso unicoRegistro = new RegistroDeUso(dataInicio, gerente, funcionario, maquina, dataFim, 300);
        maquina.adicionaRegistroUso(unicoRegistro);
        
        RelatorioMaquina relatorio = new RelatorioMaquina(maquina);
        
        // Verificar se o relatório contém apenas um registro
        assertNotNull(relatorio);
        assertEquals(1, relatorio.getRegistros().size());
    }

    // Teste de borda: Adicionar registros com a mesma data e verificar a ordenação
    @Test
    void testRelatorioComRegistrosNaMesmaData() {
        Data mesmaData = new Data(1, 1, 2024);
        RegistroDeUso registro1 = new RegistroDeUso(mesmaData, gerente, funcionario, maquina, dataFim, 100);
        RegistroManutencao registro2 = new RegistroManutencao(mesmaData, gerente, funcionario, maquina, dataFim);
        
        // Adicionando ambos os registros
        maquina.adicionaRegistroUso(registro1);
        maquina.adicionaRegistroManutencao(registro2);
        
        // Gerar o relatório
        RelatorioMaquina relatorio = new RelatorioMaquina(maquina);
        
        // Verificar se os dois registros estão presentes no relatório
        List<Registro> registros = relatorio.getRegistros();
        assertEquals(2, registros.size());
        assertTrue(registros.get(0) instanceof RegistroDeUso);
        assertTrue(registros.get(1) instanceof RegistroManutencao);
    }
}
