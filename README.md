# API Produtos

API REST de cadastro de produtos, com deploy na Azure e CI/CD via GitHub Actions.

**URL em produção:** https://bootcamp-produtos-caioruan.azurewebsites.net/produtos  
**Documentação (Swagger):** https://bootcamp-produtos-caioruan.azurewebsites.net/swagger-ui.html

---

## Como rodar na sua máquina

Java 8 ou 11. Na pasta do projeto:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

Aplicação em `http://localhost:8080`. Endpoints em `/produtos`, documentação em `/swagger-ui.html`.

Console H2 (só em ambiente local): `/h2-console` — URL `jdbc:h2:mem:produtosdb`, user `sa`, senha vazia.

Testes:

```bash
.\mvnw.cmd test
```

---

## Deploy (Azure)

A API está publicada no **Azure App Service** (Java 11, Linux). Configuração usada:

- Variáveis de ambiente no App Service: `SPRING_PROFILES_ACTIVE=prod`, `SPRING_H2_CONSOLE_ENABLED=false`
- H2 em arquivo em produção (dados persistem entre reinícios)
- Deploy automatizado pelo workflow em `.github/workflows/deploy-azure.yml` (push na `main`)

Detalhes do passo a passo que usei estão em [DEPLOY-AZURE.md](./DEPLOY-AZURE.md).

---

## Endpoints

| Método | Caminho        | Descrição          |
|--------|----------------|--------------------|
| POST   | /produtos      | Cadastrar produto  |
| GET    | /produtos      | Listar todos       |
| GET    | /produtos/{id} | Buscar por ID      |
| PUT    | /produtos/{id} | Atualizar produto  |
| DELETE | /produtos/{id} | Remover produto    |

Exemplo de corpo (POST/PUT):

```json
{
  "nome": "Notebook",
  "preco": 3500.00
}
```

Validação: nome obrigatório; preço obrigatório e maior que zero. Erros de validação retornam 400 com mensagem no corpo (Bean Validation + tratamento global de exceções).

---

## Tecnologias

- Spring Boot 2.7, Spring Web, Spring Data JPA, Hibernate
- H2 (memória em dev, arquivo em prod)
- Bean Validation, Swagger (springdoc-openapi)
- Testes com JUnit 5 e Mockito

---

## Estrutura

- **controller** — REST, delega para os services (sem regra de negócio)
- **service / service.impl** — regras de negócio; interfaces de comando e consulta
- **repository** — Spring Data JPA
- **dto** — request, response e formato de erro
- **mapper** — entidade ↔ DTO
- **model** — entidade JPA
- **exception** — exceções de negócio e `GlobalExceptionHandler`
- **validation** — validações de nome e preço
- **config** — OpenAPI (Swagger)
