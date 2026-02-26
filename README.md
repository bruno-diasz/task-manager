# Task Manager

Aplicação web para gerenciamento de tarefas desenvolvida com Java EE (JSF, PrimeFaces, JPA) e implantada no ambiente cloud da Oracle Cloud Infrastructure (Always Free Tier).

Este projeto foi desenvolvido como parte de um desafio técnico para demonstrar habilidades em desenvolvimento Java para web, persistência de dados, e deployment em nuvem.

## ✨ Funcionalidades

- ✅ **Criar, listar, atualizar e remover tarefas.**
- ✅ **Concluir tarefas.**
- ✅ **Filtrar tarefas por status e responsável.**
- ✅ **Cadastrar, listar e remover responsáveis.**
- ✅ **Interface amigável com PrimeFaces.**

## 🛠️ Tecnologias Utilizadas

- **Backend:** Java 8, Jakarta EE, CDI, JPA, Hibernate, JTA
- **Frontend:** JSF 2.3, PrimeFaces 8
- **Banco de Dados:** PostgreSQL 14
- **Servidor de Aplicação:** WildFly 23
- **Build:** Maven
- **Versionamento:** Git, GitHub
- **Cloud:** Oracle Cloud Infrastructure (Always Free Tier: VM.Standard.E2.1.Micro, Ubuntu 22.04)
- **Ferramentas de Sistema:** Linux (Ubuntu)

## 📝 Itens do Desafio Implementados

- ✅ **a) Aplicação Java Web com JSF:** Interface completa com JSF e PrimeFaces.
- ✅ **b) Persistência com PostgreSQL:** Banco de dados rodando na mesma VM.
- ✅ **c) JPA:** Utilizado para mapeamento objeto-relacional (Hibernate como provider).
- ✅ **e) Publicação em ambiente cloud:** Projeto implantado e acessível publicamente na Oracle Cloud.

## 🚀 Acesse a Aplicação Online

A aplicação está publicada e pode ser acessada diretamente pelo link abaixo (ambiente de homologação/produção):

🔗 **[http://152.67.35.49:8080/task-manager/](http://152.67.35.49:8080/task-manager/)**

> **Nota:** Por ser um ambiente *Always Free*, a performance pode ser limitada, mas é totalmente funcional para testes e demonstração.

## 💻 Como Executar o Projeto Localmente (Ambiente de Desenvolvimento)

Siga os passos abaixo para configurar e rodar o projeto em sua máquina.

### Pré-requisitos

- **Java 8** (JDK)
- **Maven**
- **Docker** (para subir o PostgreSQL)
- **WildFly 23** (ou versão compatível)
- **Git**

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/bruno-diasz/task-manager.git
    cd task-manager
    ```

2.  **Inicie o banco de dados PostgreSQL com Docker:**
    ```bash
    docker run --name postgres-task -e POSTGRES_DB=task_manager -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:14
    ```

3.  **Configure o Datasource no WildFly:**
    - Inicie o WildFly: `./bin/standalone.sh`
    - Conecte ao CLI: `./bin/jboss-cli.sh --connect`
    - Adicione o datasource (os nomes `PostgreSQLDS` e `java:/PostgreSQLDS` são obrigatórios):
      ```bash
      /subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql, driver-module-name=org.postgresql, driver-class-name=org.postgresql.Driver)

      data-source add --name=PostgreSQLDS --jndi-name=java:/PostgreSQLDS --driver-name=postgresql --connection-url=jdbc:postgresql://localhost:5432/task_manager --user-name=postgres --password=postgres --min-pool-size=5 --max-pool-size=20
      ```
    - *Nota: Você precisará ter baixado e adicionado o driver JDBC do PostgreSQL como um módulo no WildFly.*

4.  **Compile e faça o deploy da aplicação:**
    ```bash
    mvn clean package
    ```
    Copie o arquivo `target/task-manager.war` para a pasta `standalone/deployments/` do seu WildFly.

5.  **Acesse a aplicação localmente:**
    Abra o navegador em `http://localhost:8080/task-manager/`.
