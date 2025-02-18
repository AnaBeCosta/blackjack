# Blackjack - Projeto em Java

Bem-vindo ao projeto **Blackjack**! Este é um jogo simples de Blackjack desenvolvido em **Java 17**. O objetivo desse repositório é fornecer uma implementação funcional para que você possa rodar o projeto localmente.

## Requisitos

- **Java 17** ou superior
- **IntelliJ IDEA** (recomendado) ou qualquer outra IDE compatível com Java
- **Maven** para gerenciamento de dependências e build do projeto

## Como rodar o projeto

Siga os passos abaixo para rodar o projeto localmente:

### 1. Clone o repositório

Abra o terminal e execute o comando:

```bash
git clone https://github.com/seu_usuario/blackjack.git
cd blackjack
```

### 2. Abra o projeto no IntelliJ IDEA

- Abra o IntelliJ IDEA e escolha a opção **Open**.
- Selecione a pasta do repositório clonado.
- O IntelliJ detectará automaticamente que o projeto utiliza Maven e irá baixar as dependências.

### 3. Instale as dependências

Caso precise instalar as dependências manualmente, rode o seguinte comando no terminal:

```bash
mvn install
```

### 4. Compile e rode o projeto

O arquivo principal do projeto é `Blackjack.java`, localizado em `src/main/java/Blackjack.java`.

No IntelliJ IDEA, clique com o botão direito no arquivo `Blackjack.java` e escolha **Run 'Blackjack.main()'** para executar o jogo.

### 5. Visualize a análise no SonarCloud

A análise de qualidade do código está disponível no [SonarCloud](https://sonarcloud.io/project/overview?id=AnaBeCosta_blackjack).

## Estrutura do Projeto

O código está estruturado da seguinte forma:

```plaintext
src/
 └── main/
     └── java/
         └── Blackjack.java     # Arquivo principal do jogo
         └── ...
```

## Contribuições

Se você deseja contribuir para o projeto, siga os passos abaixo:

1. Fork este repositório.
2. Crie uma branch para a sua feature (`git checkout -b feature/feature-name`).
3. Faça o commit das suas alterações (`git commit -am 'Add new feature'`).
4. Faça push para a sua branch (`git push origin feature/feature-name`).
5. Abra um pull request.

## Dependências

O projeto utiliza as seguintes dependências:

- **JUnit** para testes (não implementados atualmente).
