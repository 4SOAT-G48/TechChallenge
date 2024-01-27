# TechChallenge
Tech Challenge Fiap SOAT 4 - grupo 48

Para demais documentações acessar a [wiki deste projeto](https://github.com/4SOAT-G48/TechChallenge/wiki)

## Pré requisitos

Tanto para executar como para desenvolver são necessários os seguintes itens:

1. Instalar o docker;
2. Instalar o docker-compose;
3. Instalar o git.

## Executar aplicação via docker compose

1. Clonar o projeto git; 
2. Crie o arquivo **.env** no mesmo diretório que o arquivo **docker-compose.yml** com o a estrutura do arquivo **.env.example**
   - _Iremos passar as informacoes contidas no .env via email e/ou discord._
3. Via terminal entrar na pasta do projeto;
4. Executar o comando;
    ``` sh
    docker-compose up --build -d
    ```

## Desenvolvimento

### Pré requisitos adicionais
1. Instalar o java versão 17;
    1. Link para download e instruções https://adoptium.net/installation/;
2. Maven versão 3.9.5;
3. Instalar a IDE IntelliJ; 
   1. https://www.jetbrains.com/idea/;
   2. Baixe a versão _*IntelliJ IDEA Community Edition*_;
4. dbeaver como cliente de conexão de banco;

### Para executar a aplicação direto na máquina

1. Para rodar o postgres na máquina
   1. construa o container de banco de dados com o seguinte comando:
    ``` sh
    docker run --rm -d --name postgres --network fiap -e 
   POSTGRES_PASSWORD={SENHA} -e POSTGRES_USER={USUARIO} -e POSTGRES_DB={BANCO_DE_DADOS} 
   -p 5432:5432 -v ./pgdata:/var/lib/postgresql/data postgres
    ```
   2. para parar o container
    ``` sh
    docker stop postgres
    ```
   3. para executar o container novamente
    ``` sh
    docker start postgres
    ```
2. Para executar a aplicação a partir da IDE IntelliJ
   1. procure na árvore do projeto o arquivo _src/main/java/br/com/fiap/soat/grupo48/Grupo48Application.java_;
   2. clique com o botão direito do mouse sobre ele e selecione a opção _run 'Grupo48Applica.....main()'_
   3. se tudo der certo será possível acessar a url abaixo;
      1. http://localhost:8081/saudacoes
      2. deverá aparecer um json similar a este
      ``` json
      {"id":1,"conteudo":"Olá, Mundo!"} 
      ```