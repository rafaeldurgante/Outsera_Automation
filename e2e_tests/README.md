# Automação de Testes E2E com Playwright

Este projeto utiliza o Playwright para executar testes de ponta a ponta (E2E) em aplicações web.

A seguir, estão as instruções para configurar e rodar os testes.

## Pré-requisitos

Antes de rodar os testes, certifique-se de que você tenha as seguintes ferramentas instaladas:

- [Node.js](https://nodejs.org/) (versão 14 ou superior)
- [npm](https://www.npmjs.com/) (geralmente já vem com o Node.js)
- [Playwright](https://playwright.dev/) (instalado via npm)

## Instalando Dependências

Navegue até o diretório do projeto e instale as dependências:

## Rodando os testes
Este comando executa todos os testes definidos e gera um relatório em HTML que será salvo no diretório playwright-report.

npx playwright test --headed --reporter=html

## Após a execução dos testes, você pode visualizar o relatório gerado com o seguinte comando:
npx playwright show-report


