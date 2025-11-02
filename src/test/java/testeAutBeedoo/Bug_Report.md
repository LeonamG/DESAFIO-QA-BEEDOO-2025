# 🐞 Relatório de Bugs – Desafio QA Beedoo 2025

**Autor:** Leonam Galvão  
**Módulo Avaliado:** Cursos — https://creative-sherbet-a51eac.netlify.app/
* Link das Evidencias : https://drive.google.com/file/d/1adY1mN1j_I9gbGRtSPUcOXZW-me_S78h/view?usp=sharing

---

## 🔹 BUG 001 – Ausência de validação de campos obrigatórios

**Descrição:**  
O sistema permite cadastrar um curso com todos os campos em branco.

**Impacto:** Alto  
**Severidade:** Crítica  
**Recomendação:** Implementar validação obrigatória para os campos essenciais (nome, descrição, datas, tipo e número de vagas).

**Gherkin (reprodução):**
```gherkin
Feature: Validação de campos obrigatórios no cadastro de curso

  Scenario: Não permitir cadastro sem campos obrigatórios preenchidos
    Given que eu estou na tela de cadastro de curso
    When eu clico em "Cadastrar curso" sem preencher nenhum campo
    Then o sistema deve exibir mensagens de validação para os campos obrigatórios
    And o curso não deve ser salvo
```

---

## 🔹 BUG 002 – Inconsistência na validação de datas

**Descrição:**  
Permite cadastrar curso com data de início posterior à data de fim.

**Impacto:** Alto  
**Severidade:** Alta  
**Recomendação:** Aplicar validação lógica de datas (data início <= data fim).

**Gherkin (reprodução):**
```gherkin
Feature: Validação de período do curso

  Scenario: Não permitir data de início posterior à data de fim
    Given que eu estou na tela de cadastro de curso
    When eu preencho "Data de início" com "01/11/2025" e "Data de fim" com "23/10/2025"
    And eu clico em "Cadastrar curso"
    Then o sistema deve exibir uma mensagem de erro indicando que a data inicial não pode ser posterior à data final
    And o curso não deve ser salvo
```

---

## 🔹 BUG 003 – Aceita valores negativos em "Número de vagas"

**Descrição:**  
Sistema aceita números negativos no campo de vagas.

**Impacto:** Alto  
**Severidade:** Alta  
**Recomendação:** Limitar o campo para aceitar apenas valores positivos inteiros.

**Gherkin (reprodução):**
```gherkin
Feature: Validação do campo número de vagas

  Scenario: Não permitir número de vagas negativo
    Given que eu estou na tela de cadastro de curso
    When eu preencho "Número de vagas" com "-10"
    And eu clico em "Cadastrar curso"
    Then o sistema deve exibir mensagem de erro informando que o valor é inválido
    And o curso não deve ser salvo
```

---

## 🔹 BUG 004 – Exclusão sem remoção real

**Descrição:**  
Ao clicar em "Excluir curso", o sistema exibe "Curso excluído com sucesso", mas o curso permanece na tela.

**Impacto:** Alto  
**Severidade:** Crítica  
**Recomendação:** Corrigir a lógica de exclusão (front-end e/ou back-end) para remover o registro de fato e atualizar a listagem; adicionar confirmação (modal).

**Gherkin (reprodução):**
```gherkin
Feature: Exclusão de curso

  Scenario: Excluir curso cadastrado
    Given que eu cadastrei um curso com sucesso
    When eu clico no botão "Excluir curso" do item cadastrado
    Then o sistema deve remover o curso da listagem
    And deve exibir a mensagem "Curso excluído com sucesso!"
```

---

## 🔹 BUG 005 – Campo "Descrição" com limite excessivo

**Descrição:**  
Campo de descrição permite texto muito longo, causando distorção no layout.

**Impacto:** Médio  
**Severidade:** Média  
**Recomendação:** Definir limite de 300–500 caracteres e truncar/mostrar resumo nos cards; usar tooltip ou modal para visualizar descrição completa.

**Gherkin (reprodução):**
```gherkin
Feature: Limite do campo descrição

  Scenario: Campo descrição com texto excessivamente longo
    Given que eu estou na tela de cadastro de curso
    When eu insiro uma descrição com mais de 500 caracteres
    And eu clico em "Cadastrar curso"
    Then o sistema deve exibir validação de tamanho ou truncar a descrição na listagem
```

---

## 🔹 BUG 006 – Falta de visualização e edição de curso

**Descrição:**  
Após cadastrar, o usuário não pode visualizar nem editar o curso; dados não ficam acessíveis.

**Impacto:** Médio  
**Severidade:** Alta  
**Recomendação:** Criar página de detalhe do curso e botão de edição para cada item listado.

**Gherkin (reprodução):**
```gherkin
Feature: Visualização e edição de curso

  Scenario: Visualizar e editar curso cadastrado
    Given que eu cadastrei um curso com sucesso
    When eu clico no curso na listagem
    Then o sistema deve exibir a tela de detalhes com informações completas
    And deve apresentar um botão "Editar" que permita atualizar os dados
```

---

## 💡 Melhorias Recomendadas (resumo)

- Tornar campos obrigatórios visuais (asterisco e mensagens dinâmicas).
- Implementar modal de confirmação antes da exclusão.
- Aplicar validações de integridade de negócios (datas, número de vagas).
- Melhorar layout e truncamento do campo descrição; permitir visualizar completo via modal.
- Adicionar paginação, filtros e busca na listagem de cursos.