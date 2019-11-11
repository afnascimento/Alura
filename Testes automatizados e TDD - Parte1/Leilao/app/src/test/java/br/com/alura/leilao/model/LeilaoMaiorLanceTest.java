package br.com.alura.leilao.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class LeilaoMaiorLanceTest {

    @Parameterized.Parameter
    public List<Double> lances;

    @Parameterized.Parameter(value=1)
    public Double maiorLance;

    @Parameterized.Parameter(value=2)
    public String cenario;

    @Parameterized.Parameters(name="{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][] {
                {Collections.singletonList(100.0), 100.0, "Devolver o maior lance quando recebe apenas um lance"},
                {Arrays.asList(100.0, 200.0, 300.0, 400.0 , 500.0), 500.0, "Devolver o maior lance quando recebe lances em ordem crescente"},
                {Arrays.asList(300.0, 200.0, 100.0), 300.0, "Devolver o maior lance quando recebe lances em ordem decrescente"}
        });
    }

    @Test
    public void maiorLanceTest() {
        // cenario
        Leilao leilao = new Leilao("PS4");
        for (double lance: lances) {
            leilao.propoe(new Lance(new Usuario("Andre"), lance));
        }
        //acao
        double maiorLanceRecebido = leilao.getMaiorLance();
        // verificacao
        assertThat(maiorLanceRecebido, is(maiorLance));
    }
}