package testes;

import numeros.Numeros;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumerosTest {

    private Numeros numeros;

    @Before
    public void setUp() {
        Numeros numeros = new Numeros();
    }

    @Test
    public void validarSeNumeroEhUnidade() {
        // Vou utilizar o método eUmaUnidade passando o valor 9, que se trata de uma unidade
        boolean eUnidade = numeros.eUmaUnidade(9);

        // Vou validar que a resposta é verdadeira
        Assert.assertTrue(eUnidade);
    }


    @Test
    public void validarSeUmNumeroEhDezena() {
        // Vou validar se o número passado (10) é considerado uma dezena
        boolean eUnidade = numeros.eUmaUnidade(100);

        // vou validar se a resposta é falsa
        Assert.assertFalse(eUnidade);
    }

}
