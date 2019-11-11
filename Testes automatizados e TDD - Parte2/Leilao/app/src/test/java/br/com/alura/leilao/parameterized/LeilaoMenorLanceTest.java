package br.com.alura.leilao.parameterized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class LeilaoMenorLanceTest {

    @Parameterized.Parameter
    public List<Double> lances;

    @Parameterized.Parameter(value=1)
    public Double menorLance;

    @Parameterized.Parameter(value=2)
    public String cenario;

    @Parameterized.Parameters(name="{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][] {
                {Collections.singletonList(100.0), 100.0, "Devolver o menor lance quando recebe apenas um lance"},
                {Arrays.asList(100.0, 200.0, 300.0), 100.0, "Devolver o menor lance quando recebe lances em ordem crescente"},
                {Arrays.asList(300.0, 200.0, 100.0), 100.0, "Devolver o menor lance quando recebe lances em ordem decrescente"}
        });
    }

    @Test
    public void menorLanceTest() {
        // cenario
        Leilao leilao = new Leilao("PS4");
        for (double lance: lances) {
            leilao.propoe(new Lance(new Usuario("Andre"), lance));
        }
        //acao
        double menorLanceRecebido = leilao.getMenorLance();
        // verificacao
        assertThat(menorLanceRecebido, is(menorLance));
    }
}