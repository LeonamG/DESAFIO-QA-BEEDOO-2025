
# 🧪 Desafio QA Beedoo 2025

## 📘 Descrição do Projeto
Repositório criado para o **Desafio QA Beedoo 2025**, com o objetivo de validar o módulo **“Cursos”** da aplicação disponível em: 
🔗 [https://creative-sherbet-a51eac.netlify.app/](https://creative-sherbet-a51eac.netlify.app/)

Os testes foram desenvolvidos com **Selenium WebDriver + JUnit 5**, cobrindo fluxos de **cadastro, validações de integridade e exclusão de cursos**.

---

## ⚙️ Tecnologias Utilizadas
- **Linguagem:** Java
- **Framework de Teste:** JUnit 5
- **Automação Web:** Selenium WebDriver
- **Navegador:** Microsoft Edge
- **IDE:** IntelliJ IDEA

---

## 🧩 Casos de Teste Automatizados

| ID | Cenário de Teste | Descrição | Resultado Esperado |
|----|------------------|-----------|--------------------|
| CT001 | Cadastro de Curso Presencial | Verifica o cadastro de curso presencial com dados válidos. | Mensagem “Curso cadastrado com sucesso!”. |
| CT002 | Cadastro de Curso Online | Valida o cadastro de curso online com dados válidos. | Mensagem “Curso cadastrado com sucesso!”. |
| CT003 | Cadastro com Valores Negativos | Verifica se o sistema impede cadastro com número de vagas negativo. | Cadastro deve ser bloqueado. |
| CT004 | Exclusão de Curso | Cadastra um curso e tenta excluí-lo. | Mensagem “Curso excluído com sucesso!”. |

---

## ▶️ Como Executar os Testes Automatizados no IntelliJ IDEA

### 🧩 Pré-requisitos
- Java JDK 17 ou superior instalado.
- IntelliJ IDEA instalado.
- Microsoft Edge atualizado.
- WebDriver do Edge configurado no caminho:

### 2️⃣ Abrir o projeto no IntelliJ IDEA:

- Vá em File > Open
- Selecione a pasta DESAFIO-QA-BEEDOO-2025

### 3️⃣ Instalar dependências (JUnit e Selenium):
- Se estiver usando Maven, adicione ao pom.xml:
- "selenium-java" na sua versão 4.20.0 
Link: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.38.0
- "junit.jupiter" na sua versão "6.0.0" Link: https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/6.0.0

### 4️⃣ Executar os testes:
- Vá até src/test/java/testeAutBeedoo/Beedoo.java
- Clique com o botão direito na classe
- Selecione Run 'Beedoo'

### 🧾 Resultado Esperado
- Durante a execução, o Selenium abrirá o navegador Edge em modo anonimo
- Fará o cadastro e exclusão automática de cursos.
- Validará mensagens exibidas na tela.
- Finalizará o teste exibindo o status verde (PASS) no IntelliJ, ou vermelho (FAIL) em caso de erro.

## 📄 Documentação Complementar
- USER_STORY.md → Contém a user story e justificativas.
- BUGS_REPORT.md → Contém o relatório de bugs identificados (com cenários em Gherkin).

## 👨‍💻 Autor
- Leonam Galvão
- Quality Assurance | Desafio Beedoo 2025
- 📅 Novembro de 2025


