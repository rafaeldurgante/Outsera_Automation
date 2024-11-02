# Teste Automatizado de Aplicativo Móvel com Appium e Genymotion

Este projeto realiza testes automatizados em um aplicativo móvel Android utilizando o **Appium** para automação e **Genymotion** para emulação do dispositivo.

## Pré-requisitos

1. **Java JDK 8 ou superior**
2. **Node.js e npm** (necessário para instalar o Appium)
3. **Appium Server** instalado globalmente
   ```bash
   npm install -g appium
   
4. **Genymotion** instalado e configurado com um dispositivo virtual Android.
5. **Android SDK** configurado no ambiente para que o Appium possa se conectar ao dispositivo.
6. **Certifique-se** que o Emulador está Ativo no Genymotion e corresponde ao udid especificado (192.168.56.102:5555). 
7. Verifique isso na interface do Genymotion ou usando o comando:adb devices no cmd para listar dispositivos disponíveis:
   Rode o comando no cmd: adb devices
   Valide esta mensagem: List of devices attached
                      192.168.56.102:5555     device

8. CLique no botão Run do arquivo ProdutoTest.java para executar a automação.
9. Verifique o resultado do teste.

## Descrição do Teste
ProdutoTest: O teste valida o cadastro de um produto com sucesso.
O fluxo inclui:

Login com credenciais de administrador.
Navegação até a tela de adição de produtos.
Preenchimento dos campos do produto.
Validação da mensagem de sucesso.

## Solução de Problemas
Caso o teste não seja executado, verifique:
Se o servidor Appium está rodando.
Se o dispositivo Genymotion está ativo.
Se as dependências Maven foram baixadas corretamente.


