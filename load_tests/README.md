# Teste de Carga com K6

Este documento fornece instruções sobre como executar um teste de carga usando o K6 e como analisar os resultados.

## Pré-requisitos

Antes de começar, verifique se você possui os seguintes requisitos:

- [K6](https://grafana.com/docs/k6/latest/) instalado em sua máquina.
- Se não tiver pode executar este comando no cmd da sua máquina: winget install k6 --source winget
- Um arquivo de teste `load_test.js` deve estar na pasta do projeto.

## Estrutura do Projeto

O projeto contém a seguinte estrutura de diretórios para este teste:

Outsera_Automation_Suite/
└── load_tests/
    ├── load_test.js
    └── pom.xml
    └── README.md

## Executando o Teste de Carga

Para executar o teste de carga, siga estas etapas:

1. Abra o terminal Git Bash e navegue até o diretório onde o arquivo `load_test.js` está localizado, por exemplo:

   ```bash
   cd ~/Desktop/DURGA/Outsera/Outsera_Automation_Suite/load_tests

2. Execute o comando a seguir para rodar o teste:
    ```bash
    k6 run load_test.js
    
3. Observe a saída no terminal enquanto o teste é executado.

## Analisando os Resultados

Após a execução do teste, você verá um resumo com várias métricas.
Aqui estão algumas das principais métricas a serem observadas:

VUs (Virtual Users): O número de usuários virtuais em execução durante o teste. 
O teste foi configurado para ter até 500 VUs por 5 minutos.

Taxa de Erros: Indica quantas requisições falharam durante o teste.
Exemplo: errors: 13 0.043098/s significa que 13 requisições falharam, com uma taxa de falhas de aproximadamente 0.043 por segundo.

http_req_duration: A duração média das requisições HTTP, que mostra o tempo que leva para completar uma solicitação.
Exemplo: avg=167.77ms indica que a média de tempo para responder foi de 167.77 milissegundos.

http_reqs: O total de requisições feitas durante o teste.
Exemplo: 180124 597.157296/s significa que 180.124 requisições foram feitas a uma taxa de aproximadamente 597.16 requisições por segundo.

iteration_duration: O tempo médio que leva para completar uma iteração de teste.
Exemplo: avg=1.67s indica que, em média, cada iteração durou 1.67 segundos.

