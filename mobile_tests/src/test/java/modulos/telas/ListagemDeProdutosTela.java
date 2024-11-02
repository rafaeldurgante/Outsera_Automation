package modulos.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListagemDeProdutosTela extends BaseTela{

    public ListagemDeProdutosTela (WebDriver app){
        super(app);
    }

    public FormularioAdicinarProdutoTela abrirTelaAdicaoProduto (){
        //Abrir o Formulário de cadastro de novo Produto
        app.findElement(By.id("com.lojinha:id/floatingActionButton")).click();
        return new FormularioAdicinarProdutoTela(app);
    }
}
