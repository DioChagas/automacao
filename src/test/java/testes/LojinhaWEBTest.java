package testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LojinhaWEBTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        // Preparação
        System.setProperty("webdriver.chrome.driver", "c:\\temp\\driver\\chromedriver2.exe");
        navegador = new ChromeDriver();
        navegador.get("http://165.227.93.41/lojinha-web/");

        // login com usuario e senha
        navegador.findElement(By.cssSelector("#usuario")).sendKeys("diogenes");
        navegador.findElement(By.id("senha")).sendKeys("diogenes123");
        navegador.findElement(By.cssSelector(".btn")).click();
    }

    @Test
    public void testValidarDadosDeUmProduto(){

        // Acessar o produto PS1 na lista de produtos
        navegador.findElement(By.linkText("PS1")).click();
        // navegador.findElements(By.linkText("PS1")).get(0).click(); //Quando há mais de um elemento

        // Validação do nome do Produto e do primeiro componente
        String produtonome = navegador.findElement(By.cssSelector("#produtonome")).getAttribute("value");
        Assert.assertEquals("PS1", produtonome);

        String componentenome = navegador.findElements(By.cssSelector(".title")).get(0).getText();
        Assert.assertEquals("Lojinha Controle", componentenome);
    }

    @Test
    public void testCadastrarUmNovoProduto(){
        // 'Adicionar Produto'
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Informações do Produto
        navegador.findElement(By.cssSelector("#produtonome")).sendKeys("Notebook Lenovo");
        navegador.findElement(By.id("produtovalor")).sendKeys("120000");
        navegador.findElement(By.id("produtocores")).sendKeys("Cinza, preto");

        // Salvar Novo produto
        navegador.findElements(By.cssSelector(".btn")).get(0).click();

        // Validar msg de Sucesso
        String msgSucesso = navegador.findElement(By.cssSelector(".toast")).getText();
        Assert.assertEquals("Produto adicionado com sucesso",msgSucesso);

    }

    // fechar navegador
    @After
    public void tearDown(){
        navegador.quit();
    }
}
