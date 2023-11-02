# TechChallenge
Tech Challenge Fiap SOAT 4 - grupo 48

## Pré requisitos

Tanto para executar como para desenvolver são necessários os seguintes itens:

1. Instalar o docker;
2. Instalar o docker-compose;
3. Instalar o git.

## Executar aplicação via docker compose

1. Clonar o projeto git; 
2. Criar o arquivo **.env** no mesmo diretório que o arquivo **docker-compose.yml** com o seguinte conteúdo:
   ```
   POSTGRES_DB=NOME_DATABASE
   POSTGRES_USER=NOME_USUARIO_ACESSO
   POSTGRES_PASSWORD=SENHA_ACESSO
   SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/NOME_DATABASE
   SPRING_DATASOURCE_USERNAME=NOME_USUARIO_ACESSO
   SPRING_DATASOURCE_PASSWORD=SENHA_ACESSO
   ```
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
   1. construa imagem 
    ``` sh
    docker build -t postgres-soat4g48 -f Dockerfile-postgres .
    ```
   2. execute o container
    ``` sh
    docker run --name postgres-db -p 5432:5432 -d postgres-soat4g48
    ```
   3. parar o container
    ``` sh
    docker stop postgres-db
    ```
   4. para executar o container novamente
    ``` sh
    docker start postgres-db
    ```
2. Para executar a aplicação a partir da IDE IntelliJ
   1. no arquivo _src/main/resources/application.properties_ descomente a propriedade _spring.profiles.active_;
      2. isto vai habilitar as configurações do perfil de desenvolvimento;
   2. procure na árvore do projeto o arquivo _src/main/java/br/com/fiap/soat/grupo48/Grupo48Application.java_;
   3. clique com o botão direito do mouse sobre ele e selecione a opção _run 'Grupo48Applica.....main()'_
   4. se tudo der certo será possível acessar a url abaixo;
      1. http://localhost:8081/saudacoes
      2. deverá aparecer um json similar a este
      ``` json
      {"id":1,"conteudo":"Olá, Mundo!"} 
      ```