package testes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;

public class LojinhaAPITest {
    private String token;

    @Before
    public void setUp(){
        // Servidor da API
        RestAssured.baseURI = "http://165.227.93.41";
        RestAssured.basePath = "lojinha";

        token = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body("{\n" +
                        "\t\"usuariologin\": \"diogenes\",\n" +
                        "\t\"usuariosenha\": \"diogenes123\"\n" +
                        "}")
                .when()
                    .post("login")

                .then()
                    .extract()
                        .path("data.token");
    }


    @Test
    public void buscarDadosDeUmProdutoEspecifico(){
        RestAssured
                .given()
                    .header("token",token)
                .when()
                    .get("produto/6787")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("data.produtonome", Matchers.equalTo("PS1"))
                        .body("data.componentes[0].componentenome", Matchers.equalTo("Controle"))
                        .body("message",Matchers.equalTo("Detalhando dados do produto"));
//        System.out.println(token);
    }
    @Test
    public void buscarDadosDeUmComponenteDeUmProduto(){

        RestAssured
                .given()
                    .header("token",token)
                .when()
                    .get("produto/6787/componente/1712")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("data.componentenome", Matchers.equalTo("Controle"))
                        .body("data.componentequantidade", Matchers.equalTo(2))
                        .body("message",Matchers.equalTo("Detalhando dados do componente de produto"));
    }
}
