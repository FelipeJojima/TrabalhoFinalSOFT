package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;


import org.junit.jupiter.api.Test;

import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.Registro;

public class TestesRegistro {

    // Teste Geral: Criação de um Registro
    @Test
    public void testCriacaoRegistro() {
        // Dados de teste
        Data data = new Data(1, 12, 2024);
        Gerente gerente = new Gerente("Carlos", "123456789", "carlos@empresa.com", 5000f, new Data(15, 6, 1980), 1001, "senha123");
        Funcionario funcionario = new Funcionario("João", "987654321", "joao@empresa.com", 2000f, new Data(20, 8, 1990), 2001, gerente, "senha456");
        Maquina maquina = new Maquina("Máquina A", 3001, gerente);

        // Criação do registro
        Registro registro = new Registro(data, gerente, funcionario, maquina);

        // Verificando se os dados foram corretamente atribuídos
        assertEquals(data, registro.getData());
        assertEquals(funcionario, registro.getFuncionario());
        assertEquals(maquina, registro.getMaquina());
        assertEquals(gerente, registro.getResponsavel());
    }

    // Teste Geral: Testar os Métodos `get`
    @Test
    public void testGetters() {
        Data data = new Data(2024, 12, 1);
        Gerente gerente = new Gerente("Carlos", "123456789", "carlos@empresa.com", 5000f, new Data(15, 6, 1980), 1001, "senha123");
        Funcionario funcionario = new Funcionario("João", "987654321", "joao@empresa.com", 2000f, new Data(20, 8, 1990), 2001, gerente, "senha456");
        Maquina maquina = new Maquina("Máquina A", 3001, gerente);

        Registro registro = new Registro(data, gerente, funcionario, maquina);

        // Verificando se os métodos getters retornam corretamente os valores
        assertEquals(data, registro.getData());
        assertEquals(funcionario, registro.getFuncionario());
        assertEquals(maquina, registro.getMaquina());
        assertEquals(gerente, registro.getResponsavel());
    }

    // Teste de Borda: Testar `null` em Atributos
    @Test
    public void testRegistroComValoresNulos() {
        Data data = new Data(2024, 12, 1);
        Gerente gerente = new Gerente("Carlos", "123456789", "carlos@empresa.com", 5000f, new Data(15, 6, 1980), 1001, "senha123");

        // Criando o registro com funcionário e máquina nulos
        Registro registro = new Registro(data, gerente, null, null);

        // Verificando se os valores nulos são aceitos e retornados corretamente
        assertNull(registro.getFuncionario());
        assertNull(registro.getMaquina());
        assertEquals(gerente, registro.getResponsavel());
    }


    // Teste de Borda: Testar o Registro com Vários Valores Idênticos
    @Test
    public void testRegistroComValoresIdenticos() {
        Data data1 = new Data(1, 12, 2024);
        Data data2 = new Data(2, 12, 2024);
        Gerente gerente = new Gerente("Carlos", "123456789", "carlos@empresa.com", 5000f, new Data(15, 6, 1980), 1001, "senha123");
        Funcionario funcionario = new Funcionario("João", "987654321", "joao@empresa.com", 2000f, new Data(20, 8, 1990), 2001, gerente, "senha456");
        Maquina maquina = new Maquina("Máquina A", 3001, gerente);

        // Criando dois registros com os mesmos dados, mas em momentos diferentes
        Registro registro1 = new Registro(data1, gerente, funcionario, maquina);
        Registro registro2 = new Registro(data2, gerente, funcionario, maquina);

        // Verificando se ambos os registros foram criados corretamente
        assertNotNull(registro1);
        assertNotNull(registro2);
        assertNotSame(registro1.getData(), registro2.getData());
        assertEquals(gerente, registro1.getResponsavel());
        assertEquals(funcionario, registro1.getFuncionario());
        assertEquals(maquina, registro1.getMaquina());
    }
}
