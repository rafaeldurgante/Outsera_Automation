package modulos.produto;

import modulos.telas.LoginTela;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@DisplayName("Testes Mobile do Módulo de Produto")
public class ProdutoTest {
    private WebDriver app;

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Capacidade de aplicação e dispositivo
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:app", "C:\\Users\\Rafael Durgante\\Desktop\\DURGA\\Outsera\\Outsera_Tests\\mobile_tests\\src\\main\\java\\lojinha-nativa.apk");
        capabilities.setCapability("appium:appActivity", "com.lojinha.ui.MainActivity");
        capabilities.setCapability("appium:appPackage", "com.lojinha");
        capabilities.setCapability("appium:deviceName", "Custom Phone");
        capabilities.setCapability("appium:udid", "192.168.56.102:5555");

        // Crie a conexão com o servidor Appium/Selenium
        this.app = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        app.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void afterEach(){
        app.quit();
    }

    @DisplayName("Valida Produto Cadastrado com Sucesso")
    @Test
    public void testValidarProdutoCadastradoComSucesso() {
        try {
            String mensagemApresentada= new LoginTela(app)
                    .preencherUsuario("admin")
                    .preencherSenha("admin")
                    .submeterLogin()
                    .abrirTelaAdicaoProduto()
                    .preencherNomeProduto("Iphone18")
                    .preencherValorProduto("2500")
                    .preencherCoresProduto("Preto e Cinza")
                    .submissaoSucesso()
                    .ObterMensagemSucesso();
            Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Erro durante o teste de cadastro do produto: " + e.getMessage());
        }
    }
}
