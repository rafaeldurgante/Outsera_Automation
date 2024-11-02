package modulos.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicinarProdutoTela extends BaseTela{

    public FormularioAdicinarProdutoTela (WebDriver app){
        super(app);
    }

    public FormularioAdicinarProdutoTela preencherNomeProduto(String produtoNome){
        app.findElement(By.id("com.lojinha:id/productName")).click();
        app.findElement(By.id("com.lojinha:id/productName")).findElement(By.id("com.lojinha:id/editText")).sendKeys(produtoNome);
        return this;
    }

    public FormularioAdicinarProdutoTela preencherValorProduto (String produtoValue){
        app.findElement(By.id("com.lojinha:id/productValue")).click();
        app.findElement(By.id("com.lojinha:id/productValue")).findElement(By.id("com.lojinha:id/editText")).sendKeys(produtoValue);
        return this;
    }
    public FormularioAdicinarProdutoTela preencherCoresProduto( String corProduto ){
        app.findElement(By.id("com.lojinha:id/productColors")).click();
        app.findElement(By.id("com.lojinha:id/productColors")).findElement(By.id("com.lojinha:id/editText")).sendKeys(corProduto);
        return this;
    }
    public FormularioAdicinarProdutoTela submissaoSucesso(){
        app.findElement(By.id("com.lojinha:id/saveButton")).click();
        return this;
    }

    public String ObterMensagemSucesso (){
        return app.findElement(By.xpath("//android.widget.Toast")).getText();
    }
}
