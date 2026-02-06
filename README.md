# API Produtos

API REST para cadastro de produtos, desenvolvida em **Spring Boot** e publicada na **Microsoft Azure** com deploy contínuo via **GitHub Actions**.

---

## Aplicação em produção

A API está no ar e pode ser usada diretamente:

| Recurso | Link |
| ------- | ---- |
| **Endpoint de produtos** | https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/produtos |
| **Documentação (Swagger)** | https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/swagger-ui/index.html#/produto-controller/salvar |

No Swagger é possível testar todos os endpoints (cadastrar, listar, buscar por ID, atualizar e remover produtos).

---

## Sobre o projeto

Backend REST com CRUD de produtos: separação entre controller, camada de serviço (comando e consulta), repositório JPA, DTOs e validação. Tratamento global de exceções com respostas em JSON e status HTTP adequados. Em produção o H2 usa armazenamento em arquivo para os dados persistirem entre reinícios do App Service.

---

## Como rodar localmente

**Requisitos:** Java 8 ou 11.

```bash
# Windows (PowerShell)
.\mvnw.cmd spring-boot:run

# Linux / Mac
./mvnw spring-boot:run
```

A aplicação sobe em **http://localhost:8080**.

- **API:** http://localhost:8080/produtos
- **Swagger:** http://localhost:8080/swagger-ui.html
- **Console H2 (apenas local):** http://localhost:8080/h2-console — JDBC URL: `jdbc:h2:mem:produtosdb`, user: `sa`, senha: vazia

**Testes unitários:**

```bash
.\mvnw.cmd test
```

---

## Deploy na Azure

- Publicado no **Azure App Service** (Java 11, Linux, região Brazil South).
- Variáveis de ambiente: `SPRING_PROFILES_ACTIVE=prod`, `SPRING_H2_CONSOLE_ENABLED=false`.
- Deploy automático a cada push na branch `main` (GitHub Actions).
- Detalhes: [DEPLOY-AZURE.md](./DEPLOY-AZURE.md).

---

## Endpoints

| Método | Caminho | Descrição |
| ------ | ------- | --------- |
| POST | `/produtos` | Cadastrar produto |
| GET | `/produtos` | Listar todos |
| GET | `/produtos/{id}` | Buscar por ID |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Remover produto |

**Exemplo de corpo (POST e PUT):**

```json
{
  "nome": "Notebook",
  "preco": 3500.00
}
```

Nome obrigatório (não vazio); preço obrigatório e maior que zero. Erros de validação retornam 400 com mensagem em JSON.

---

## Tecnologias

| Área | Tecnologia |
| ---- | ---------- |
| Backend | Spring Boot 2.7, Spring Web, Spring Data JPA, Hibernate |
| Banco | H2 (memória em dev, arquivo em prod) |
| Validação | Bean Validation |
| Documentação | Swagger (springdoc-openapi) |
| Testes | JUnit 5, Mockito |
| Deploy | Azure App Service, GitHub Actions |

---

## Arquitetura

| Camada | Responsabilidade |
| ------ | ---------------- |
| controller | Endpoints REST; delega lógica para os services |
| service / service.impl | Regras de negócio; interfaces Command e Query |
| repository | Spring Data JPA |
| dto | Request, response e formato de erro |
| mapper | Conversão entidade ↔ DTO |
| model | Entidade JPA (Produto) |
| exception | Exceções e GlobalExceptionHandler |
| validation | Nome obrigatório, preço maior que zero |
| config | OpenAPI (Swagger) |

