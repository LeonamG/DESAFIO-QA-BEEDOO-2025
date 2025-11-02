
---

### 🟨 **USER_STORY.md**
> (arquivo separado para clareza e leitura limpa)

```markdown
# 🧠 User Story – Desafio QA Beedoo 2025

**Como QA,**  
quero validar o comportamento do módulo de “Cadastro de Cursos”  
para garantir que os campos obrigatórios sejam respeitados,  
as validações de integridade sejam aplicadas,  
e o sistema mantenha consistência entre os dados cadastrados, listados e excluídos.

---

## 🧩 Decisões Tomadas
- Foram priorizados **cenários críticos**, evitando apenas o “caminho feliz”.  
- Foram incluídas **validações negativas** (ex: datas inválidas, valores negativos).  
- A abordagem **BDD (Behavior Driven Development)** foi aplicada no formato Given/When/Then.  
- O código foi modularizado em métodos auxiliares para permitir **reutilização e manutenção simples**.  
- O navegador Edge foi escolhido pela estabilidade e compatibilidade com Selenium 4.

---

## 📜 Critérios de Aceite (Given / When / Then)

**Cenário 1 – Cadastro de curso válido**  
Dado que o usuário preenche corretamente os campos obrigatórios,  
Quando clicar em “Cadastrar curso”,  
Então o sistema deve exibir a mensagem “Curso cadastrado com sucesso!”.

---

**Cenário 2 – Cadastro com valores negativos**  
Dado que o usuário preencha o campo “Número de vagas” com valores negativos,  
Quando clicar em “Cadastrar curso”,  
Então o sistema deve impedir o cadastro e exibir uma mensagem de erro.

---

**Cenário 3 – Data final anterior à data inicial**  
Dado que o usuário insira uma data de início maior que a data final,  
Quando tentar salvar o curso,  
Então o sistema deve exibir uma mensagem de erro e bloquear o envio do formulário.

---

**Cenário 4 – Exclusão de curso**  
Dado que o usuário tenha um curso cadastrado,  
Quando clicar em “Excluir curso”,  
Então o curso deve ser removido da lista e o sistema deve exibir “Curso excluído com sucesso!”.
