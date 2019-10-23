# INICIANDO A APLICAÇÃO

## REQUISITOS

Necessário ter o Java instalado na versão 8 ou superior.

## BANCO DE DADOS

O banco de dados é [H2 Database](h2database.com/html/main.html) e está sendo gerado em tempo de execução, assim que a aplicação é iniciada. Você poderá ter acesso aos dados e a estrutura do banco através da url [http://localhost:8080/h2](http://localhost:8080/h2). 

## EXECUTANDO A APLICAÇÃO

O jar está adicionado ao código fonte, sendo necessário apenas executar o seguinte comando: 

```console
  java -jar estoque-0.0.1-SNAPSHOT.jar
```

Caso queira empacotar o projeto, será necessário ter o [Maven](https://maven.apache.org/) instalado na máquina e executar o comando: 

```console
  mvn package 
```

## MODELO ENTIDADE RELACIONAMENTO

Conforme solicitado, foi gerado um diagrama MER da aplicação.

![Modelo Entidade Relacionamento](https://github.com/ibruno-om/test-ithappens-1303/blob/master/documentacao/Diagrama%20MER.png)

## JAVADOC

Os códigos foram documentados e a documentação javadoc produzida está na pasta de documentação.

## CONSULTAS SQL

As consultas do teste estão dentro da pasta de implementação, no arquivo consultas.sql.
