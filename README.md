# Product-ms
Microserviço de produtos - Desafio

![Endpoints de produto](./pictures/swagger.png "Swagger")


Tecnologias utilizadas:

- Java - Linguagem de programação utilizada
- Webflux - Módulo inserido no framework que possibilita aplicações web com Spring trabalhar de forma reativa
- Spring Boot - Framework utilizado para desenvolvimento de serviços com java
- MongoDB - Banco de dados não relacional utilizado para persistência
- Spotless - Identação de código e remoção de imports não utilizados
- Swagger - Documentação da API
- Junit - Testes unitários


## Banco de dados

Durante o desenvolvimento do projeto foi utilizado uma imagem docker do MongoDB. Para execução do projeto com sucesso deverão ser realizados os seguintes passos:

- Instalar docker na máquina local. Após instalado, executar os comandos abaixo através da linha de comando
- Baixar imagem docker: ***docker pull mongo***
- Podemos verificar se imagem foi baixada com sucesso através do comando: ***docker images***
- Colocar em execução a imagem através da porta 27017: ***docker run -p 27017:27017 mongo***
- Confirmar que imagem está executando: ***docker ps***
