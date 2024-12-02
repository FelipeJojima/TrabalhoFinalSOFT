package testesDados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import dados.Data;
import dados.Funcionario;
import dados.Gerente;
import dados.Maquina;
import dados.RegistroDeUso;

public class TestesRegistroDeUso {


    @Test
    void testCriacaoRegistroDeUsoValido() {
        Data inicio = new Data(1, 12, 2024, 8, 0); // 1º de dezembro de 2024, 8h
        Data fim = new Data(1, 12, 2024, 16, 0);  // 1º de dezembro de 2024, 16h
        Gerente gerente = new Gerente("Carlos", "12345678901", "carlos@empresa.com", 8000.0f, 
                                      new Data(15, 1, 1980), 101, "senha123");
        Funcionario funcionario = new Funcionario("Ana", "98765432100", "ana@empresa.com", 3000.0f, 
                                                   new Data(25, 5, 1990), 202, gerente, "senha321");
        Maquina maquina = new Maquina("Furadeira", 303, gerente);
        int producao = 80;

        RegistroDeUso registro = new RegistroDeUso(inicio, gerente, funcionario, maquina, fim, producao);

        assertEquals(inicio, registro.getData());
        assertEquals(fim, registro.getDataFim());
        assertEquals(gerente, registro.getResponsavel());
        assertEquals(funcionario, registro.getFuncionario());
        assertEquals(maquina, registro.getMaquina());
        assertEquals(producao, registro.getProducao());
        assertEquals(10, registro.getPorHora()); // Produção por hora: 80 / 8 = 10
    }

    @Test
    void testProducaoPorHoraComHorasMinimas() {
        Data inicio = new Data(1, 12, 2024, 8, 0);
        Data fim = new Data(1, 12, 2024, 9, 0); // Diferença de 1 hora
        Gerente gerente = new Gerente("Carlos", "12345678901", "carlos@empresa.com", 8000.0f, 
                                      new Data(15, 1, 1980), 101, "senha123");
        Funcionario funcionario = new Funcionario("Ana", "98765432100", "ana@empresa.com", 3000.0f, 
                                                   new Data(25, 5, 1990), 202, gerente, "senha321");
        Maquina maquina = new Maquina("Furadeira", 303, gerente);
        int producao = 20;

        RegistroDeUso registro = new RegistroDeUso(inicio, gerente, funcionario, maquina, fim, producao);

        assertEquals(20, registro.getPorHora()); // Produção por hora: 20 / 1 = 20
    }

    @Test
    void testProducaoZerada() {
        Data inicio = new Data(1, 12, 2024, 8, 0);
        Data fim = new Data(1, 12, 2024, 12, 0); // Diferença de 4 horas
        Gerente gerente = new Gerente("Carlos", "12345678901", "carlos@empresa.com", 8000.0f, 
                                      new Data(15, 1, 1980), 101, "senha123");
        Funcionario funcionario = new Funcionario("Ana", "98765432100", "ana@empresa.com", 3000.0f, 
                                                   new Data(25, 5, 1990), 202, gerente, "senha321");
        Maquina maquina = new Maquina("Furadeira", 303, gerente);
        int producao = 0;

        RegistroDeUso registro = new RegistroDeUso(inicio, gerente, funcionario, maquina, fim, producao);

        assertEquals(0, registro.getProducao());
        assertEquals(0, registro.getPorHora()); // Produção por hora: 0 / 4 = 0
    }

    @Test
    void testDiferencaNegativaEntreDatas() {
        Data inicio = new Data(1, 12, 2024, 16, 0); // Data de início posterior
        Data fim = new Data(1, 12, 2024, 8, 0);     // Data de término anterior
        Gerente gerente = new Gerente("Carlos", "12345678901", "carlos@empresa.com", 8000.0f, 
                                      new Data(15, 1, 1980), 101, "senha123");
        Funcionario funcionario = new Funcionario("Ana", "98765432100", "ana@empresa.com", 3000.0f, 
                                                   new Data(25, 5, 1990), 202, gerente, "senha321");
        Maquina maquina = new Maquina("Furadeira", 303, gerente);
        int producao = 50;

        Exception exception = assertThrows(ArithmeticException.class, () -> 
            new RegistroDeUso(inicio, gerente, funcionario, maquina, fim, producao)
        );

        assertEquals("/ by zero", exception.getMessage()); // Diferença negativa pode gerar divisão por zero.
    }

    @Test
    void testToString() {
        Data inicio = new Data(1, 12, 2024, 8, 0);
        Data fim = new Data(1, 12, 2024, 16, 0);
        Gerente gerente = new Gerente("Carlos", "12345678901", "carlos@empresa.com", 8000.0f, 
                                      new Data(15, 1, 1980), 101, "senha123");
        Funcionario funcionario = new Funcionario("Ana", "98765432100", "ana@empresa.com", 3000.0f, 
                                                   new Data(25, 5, 1990), 202, gerente, "senha321");
        Maquina maquina = new Maquina("Furadeira", 303, gerente);
        int producao = 80;

        RegistroDeUso registro = new RegistroDeUso(inicio, gerente, funcionario, maquina, fim, producao);

        String esperado = "Registro de uso da máquina: Furadeira\n" +
                          "No dia: 01/12/2024 Até: 01/12/2024\n" +
                          "Operada por: Ana\nResponsável: Carlos\n" +
                          "Produção total: 80\nProdução média por hora: 10\n";

        assertEquals(esperado, registro.toString());
    }
}
