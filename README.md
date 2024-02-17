## Arquitetura da Aplicação
A aplicação é organizada da seguinte forma:
```
_src
 |__main
 |  |__csv
 |     |__dataReport.csv
 |  |__java
 |     |__com
 |        |__davison
 |           |__web_screping
 |              |__bot
 |                 |__Scraper.java
 |              |__utils
 |                 |__CsvWriter.java
 |              |__Main.java
 |  |__resources
 |     |__config.properties
 |  |__screenshots
|__test
_target
_pom.xml
-.gitignore
```
- Scraper.java: Classe responsável por executar o scraping da web.
- CsvWriter.java: Classe utilitária para escrever dados em um arquivo CSV.
- Main.java: Classe principal que inicia a aplicação.
- config.properties: Arquivo de configuração para definir URL, tempo de espera e caminho para salvar capturas de tela.
- dataReport.csv: Arquivo CSV para armazenar os dados coletados.
- screenshots: Pasta para salvar capturas de tela.
- pom.xml: Arquivo de configuração do Maven.
- gitignore: Arquivo para especificar quais arquivos e diretórios devem ser ignorados pelo Git.
  
## Uso do Bot
Para utilizar o bot de web scraping, você precisa seguir os seguintes passos:

1. Certifique-se de ter o JDK (Java Development Kit) e o Maven instalados em seu sistema.

2. Clone o repositório do projeto ou baixe o código-fonte.

3. Defina as configurações necessárias no arquivo config.properties:

  > url: URL da página da web que deseja fazer scraping.

  > wait_time: Tempo de espera em segundos para aguardar o carregamento da página.

  > screenshot_path: Caminho para salvar as capturas de tela.

4. Compile e execute o projeto usando o Maven:
```
mvn clean install
java -jar <nome-do-arquivo-jar-gerado>.jar
```
5. O bot irá iniciar o scraping da web de acordo com as configurações fornecidas e salvará os dados coletados no arquivo dataReport.csv.

## Rotina de Agendamento
Para agendar a execução periódica do bot, você pode usar ferramentas como o Cron no Linux ou o Agendador de Tarefas no Windows.

### Cron (Linux)
1. Abra o terminal.

2. Execute o comando crontab -e para editar o arquivo de tarefas cron.

3. Adicione uma linha ao arquivo cron para agendar a execução do bot. Por exemplo, para executar o bot todos os dias às 8h da manhã, você pode adicionar:
```
0 8 * * * java -jar /caminho/para/o/arquivo-jar.jar
```
Certifique-se de substituir /caminho/para/o/arquivo-jar.jar pelo caminho real para o arquivo JAR do seu bot.

4. Salve e feche o arquivo.

### Agendador de Tarefas (Windows)
1. Abra o Agendador de Tarefas.

2. Crie uma nova tarefa agendada.

3. Especifique o nome, a descrição e configure a periodicidade desejada para a execução da tarefa.

4. Na aba "Ações", adicione uma nova ação para executar o comando Java com o caminho para o arquivo JAR do bot.

5. Salve a tarefa agendada.
