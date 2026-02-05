
# API ProdutosAPI REST para cadastro de produtos, desenvolvida em **Spring Boot** e publicada na **Microsoft Azure** com deploy contínuo via **GitHub Actions**.---## Aplicação em produção| Recurso | URL ||--------|-----|| **Endpoint de produtos** | [Abrir API](https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/produtos) || **Documentação (Swagger)** | [Abrir Swagger](https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/swagger-ui.html) |No Swagger é possível testar todos os endpoints (cadastrar, listar, buscar por ID, atualizar e remover).---## Sobre o projetoBackend REST com CRUD de produtos: controller, camada de serviço (comando e consulta), repositório JPA, DTOs e validação. Tratamento global de exceções com respostas em JSON e status HTTP adequados. Em produção o H2 usa armazenamento em arquivo para persistir dados entre reinícios do App Service.---## Como rodar localmente**Requisitos:** Java 8 ou 11.ash# Windows (PowerShell).\mvnw.cmd spring-boot:run# Linux / Mac./mvnw spring-boot:run
Aplicação em http://localhost:8080. Endpoints: /produtos e /swagger-ui.html. Console H2 (só local): /h2-console — JDBC URL: jdbc:h2:mem:produtosdb, user: sa, senha vazia.
Testes: .\mvnw.cmd test
Deploy na Azure
Publicado no Azure App Service (Java 11, Linux, Brazil South). Variáveis: SPRING_PROFILES_ACTIVE=prod, SPRING_H2_CONSOLE_ENABLED=false. Deploy automático via GitHub Actions (push na main). Detalhes em DEPLOY-AZURE.md.
Endpoints
Método	Caminho	Descrição
POST	/produtos	Cadastrar produto
GET	/produtos	Listar todos
GET	/produtos/{id}	Buscar por ID
PUT	/produtos/{id}	Atualizar produto
DELETE	/produtos/{id}	Remover produto
Corpo (POST/PUT): {"nome": "Notebook", "preco": 3500.00} — Nome obrigatório; preço obrigatório e > 0. Erros de validação retornam 400 em JSON.
Tecnologias
Backend: Spring Boot 2.7, Spring Web, Spring Data JPA, Hibernate | Banco: H2 (memória em dev, arquivo em prod) | Validação: Bean Validation | Documentação: Swagger (springdoc-openapi) | Testes: JUnit 5, Mockito | Deploy: Azure App Service, GitHub Actions
Arquitetura
Camada	Responsabilidade
controller	REST; delega lógica para os services
service / service.impl	Regras de negócio; interfaces Command e Query
repository	Spring Data JPA
dto	Request, response e erro
mapper	Entidade ↔ DTO
model	Entidade JPA (Produto)
exception	Exceções e GlobalExceptionHandler
validation	Nome obrigatório, preço > 0
config	OpenAPI (Swagger)
