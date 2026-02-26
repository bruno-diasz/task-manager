# Task Manager

Aplicação web para gerenciamento de tarefas desenvolvida com Java EE
utilizando JSF, PrimeFaces, Hibernate e PostgreSQL.

O objetivo do projeto é praticar desenvolvimento web com Java, aplicar
arquitetura em camadas e integrar aplicação com banco de dados
utilizando JTA no WildFly.

------------------------------------------------------------------------

# Tecnologias Utilizadas

-   Java 8
-   JSF 2.3
-   PrimeFaces 8
-   Hibernate 5.4.x
-   PostgreSQL 14
-   WildFly 23
-   Maven
-   Docker

------------------------------------------------------------------------

# Arquitetura do Projeto

Model → Entidades JPA\
Service → Regras de negócio + Acesso a dados\
Bean → Controladores JSF\
View → XHTML + PrimeFaces

------------------------------------------------------------------------

# Pré-requisitos

-   Java 8
-   Maven
-   Docker
-   WildFly 23

------------------------------------------------------------------------

# Banco de Dados (PostgreSQL via Docker)

## Subir container:

docker run --name postgres-task\
-e POSTGRES_DB=task_manager\
-e POSTGRES_USER=postgres\
-e POSTGRES_PASSWORD=postgres\
-p 5432:5432\
-d postgres:14

------------------------------------------------------------------------

# Configuração do WildFly

Criar Datasource:

-   JNDI Name: java:/jdbc/TaskDS
-   URL: jdbc:postgresql://localhost:5432/task_manager
-   User: postgres
-   Password: postgres

------------------------------------------------------------------------

# Como Executar

1)  Clonar projeto\
    git clone https://github.com/seu-usuario/task-manager.git

2)  Gerar WAR\
    mvn clean package

3)  Deploy no WildFly\
    Copiar target/task-manager.war para standalone/deployments/

4)  Acessar\
    http://localhost:8080/task-manager

------------------------------------------------------------------------

# Funcionalidades

-   Criar tarefa
-   Listar tarefas
-   Atualizar tarefa
-   Remover tarefa
-   Concluir Tarefas
-   Filtrar Tarefas
-   Criar Responsaveis
-   Remover Responsaveis
-   Listar Responsaveis

------------------------------------------------------------------------

# Conceitos Aplicados

-   JSF Lifecycle
-   CDI
-   JPA + Hibernate
-   JTA
-   Arquitetura em camadas
-   Docker para ambiente local

------------------------------------------------------------------------

# Autor

Bruno Dias\
Estudante de Análise e Desenvolvimento de Sistemas
