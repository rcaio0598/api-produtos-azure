# 🛒 API de Gerenciamento de Produtos
### Desafio Final - Bootcamp JAVA Deloitte.

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Azure](https://img.shields.io/badge/Azure-Deployed-0078D4?style=for-the-badge&logo=microsoftazure)](https://azure.microsoft.com/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Arquitetura e SOLID](#-arquitetura-e-solid)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades](#-funcionalidades)
- [Endpoints da API](#-endpoints-da-api)
- [Acesso à Aplicação](#-acesso-à-aplicação)
- [Executar Localmente](#-executar-localmente)
- [Deploy na Azure](#-deploy-na-azure)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Tratamento de Erros](#-tratamento-de-erros)
- [Validações](#-validações)
- [Testes](#-testes)
- [Autor](#-autor)

---

## 🎯 Sobre o Projeto

API RESTful desenvolvida em **Spring Boot** para gerenciamento de produtos, implementando operações CRUD completas (Create, Read, Update, Delete) com foco em:

- ✅ **Arquitetura limpa** e separação de responsabilidades
- ✅ **Princípios SOLID** aplicados em todas as camadas
- ✅ **DTOs** para isolamento e segurança de dados
- ✅ **Tratamento global de exceções** com respostas padronizadas
- ✅ **Validações** robustas com Bean Validation
- ✅ **Documentação automática** com Swagger/OpenAPI
- ✅ **Deploy em produção** na Microsoft Azure

**Desafio do Bootcamp:** Integrar toda a aplicação desenvolvida com a Cloud Microsoft Azure, realizando deploy de uma API funcional e acessível publicamente.

---

## 🏗️ Arquitetura e SOLID

A aplicação foi projetada seguindo os **princípios SOLID** e **Clean Architecture**, garantindo um código mais fácil para manutenção e testes.

### Camadas da Aplicação

```
┌─────────────────────────────────────────┐
│           Controller Layer              │  ← Recebe requisições HTTP
│    (Routing, HTTP Status, DTOs)         │     Retorna ResponseEntity
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│            Service Layer                │  ← Regras de negócio
│  (Business Logic, Validações, Mappers)  │     Orquestra operações
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│          Repository Layer               │  ← Acesso a dados
│        (Spring Data JPA)                │     Abstração do DB
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│          Database (H2)                  │  ← Persistência
└─────────────────────────────────────────┘
```

### Aplicação dos Princípios SOLID

| Princípio | Aplicação no Projeto |
|-----------|---------------------|
| **S** - Single Responsibility | Cada classe tem uma única responsabilidade: Controller apenas roteia, Service contém lógica de negócio, Repository acessa dados |
| **O** - Open/Closed | Service usa interface, permitindo extensão sem modificar código existente |
| **L** - Liskov Substitution | `ProdutoServiceImpl` pode substituir `ProdutoService` sem quebrar funcionalidades |
| **I** - Interface Segregation | DTOs específicos (Request/Response), interfaces focadas |
| **D** - Dependency Inversion | Controller depende de abstração (`ProdutoService`), não de implementação concreta |

---

## 🚀 Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework principal
- **Spring Web** - APIs REST
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM (Object-Relational Mapping)
- **Bean Validation** - Validações declarativas

### Banco de Dados
- **H2 Database** - Banco em memória/arquivo (desenvolvimento e produção)

### Documentação
- **SpringDoc OpenAPI 3** - Documentação automática da API
- **Swagger UI** - Interface visual para testes

### Build e Deploy
- **Maven** - Gerenciamento de dependências
- **Azure App Service** - Hospedagem em nuvem
- **Azure Resource Group** - Organização de recursos

### Ferramentas de Desenvolvimento
- **Lombok** - Redução de boilerplate
- **JUnit 5** - Testes unitários
- **Mockito** - Mocks para testes

---

## ✨ Funcionalidades

- ✅ **Cadastro de produtos** com validação de dados
- ✅ **Listagem completa** de todos os produtos
- ✅ **Busca por ID** com tratamento de produto não encontrado
- ✅ **Atualização de produtos** existentes
- ✅ **Exclusão de produtos** com validação de existência
- ✅ **Validações automáticas** de campos obrigatórios
- ✅ **Tratamento global de exceções** com mensagens amigáveis
- ✅ **Documentação interativa** via Swagger
- ✅ **Persistência de dados** em arquivo (produção)
- ✅ **Respostas HTTP padronizadas** (201, 200, 204, 400, 404, 500)

---

## 📡 Endpoints da API

### Base URL (Produção)
```
https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net
```

### Base URL (Local)
```
http://localhost:8080
```

### Documentação Interativa
```
https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/swagger-ui/index.html
```

---

### 📝 Endpoints Disponíveis

#### 1️⃣ Criar Produto
```http
POST /api/v1/produtos
Content-Type: application/json

{
  "nome": "Notebook Dell Inspiron",
  "preco": 3500.00
}
```

**Resposta de Sucesso (201 Created):**
```json
{
  "id": 1,
  "nome": "Notebook Dell Inspiron",
  "preco": 3500.00
}
```

**Resposta de Erro (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Erro de validação",
  "timestamp": "2026-02-06T14:30:00",
  "errors": {
    "nome": "Nome é obrigatório",
    "preco": "Preço deve ser maior que zero"
  }
}
```

---

#### 2️⃣ Listar Todos os Produtos
```http
GET /api/v1/produtos
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "Notebook Dell Inspiron",
    "preco": 3500.00
  },
  {
    "id": 2,
    "nome": "Mouse Logitech MX",
    "preco": 250.00
  }
]
```

---

#### 3️⃣ Buscar Produto por ID
```http
GET /api/v1/produtos/{id}
```

**Exemplo:**
```http
GET /api/v1/produtos/1
```

**Resposta de Sucesso (200 OK):**
```json
{
  "id": 1,
  "nome": "Notebook Dell Inspiron",
  "preco": 3500.00
}
```

**Resposta de Erro (404 Not Found):**
```json
{
  "status": 404,
  "message": "Produto não encontrado com ID: 999",
  "timestamp": "2026-02-06T14:30:00"
}
```

---

#### 4️⃣ Atualizar Produto
```http
PUT /api/v1/produtos/{id}
Content-Type: application/json

{
  "nome": "Notebook Dell Inspiron 15",
  "preco": 3800.00
}
```

**Resposta de Sucesso (200 OK):**
```json
{
  "id": 1,
  "nome": "Notebook Dell Inspiron 15",
  "preco": 3800.00
}
```

---

#### 5️⃣ Deletar Produto
```http
DELETE /api/v1/produtos/{id}
```

**Exemplo:**
```http
DELETE /api/v1/produtos/1
```

**Resposta de Sucesso (204 No Content):**
```
(Sem corpo na resposta)
```

**Resposta de Erro (404 Not Found):**
```json
{
  "status": 404,
  "message": "Produto não encontrado com ID: 999",
  "timestamp": "2026-02-06T14:30:00"
}
```

---

## 🌐 Acesso à Aplicação

### 🔴 Produção (Azure)

**URL da API:**
```
https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/api/v1/produtos
```

**Swagger UI (Documentação Interativa):**
```
https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/swagger-ui/index.html
```

**OpenAPI JSON:**
```
https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/v3/api-docs
```

### 🟢 Testando a API (Produção)

**Exemplo com cURL:**
```bash
# Criar produto
curl -X POST https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/api/v1/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome": "Teclado Mecânico", "preco": 450.00}'

# Listar produtos
curl https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/api/v1/produtos

# Buscar por ID
curl https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/api/v1/produtos/1

# Atualizar produto
curl -X PUT https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/api/v1/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{"nome": "Teclado Mecânico RGB", "preco": 499.00}'

# Deletar produto
curl -X DELETE https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/api/v1/produtos/1
```

---

## 💻 Executar Localmente

### Pré-requisitos
- **Java 17** ou superior
- **Maven 3.8+** (ou use o Maven Wrapper incluído)
- **Git** para clonar o repositório
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### Passo a Passo

#### 1. Clonar o Repositório
```bash
git clone https://github.com/rcaio0598/api-produtos-azure.git
cd api-produtos-azure
```

#### 2. Compilar o Projeto
```bash
# Com Maven instalado
mvn clean install

# Ou usando Maven Wrapper
./mvnw clean install
```

#### 3. Executar a Aplicação
```bash
# Com Maven
mvn spring-boot:run

# Ou usando Maven Wrapper
./mvnw spring-boot:run

# Ou executando o JAR gerado
java -jar target/api-produtos-0.0.1-SNAPSHOT.jar
```

#### 4. Acessar a Aplicação

A aplicação estará disponível em:
- **API:** http://localhost:8080/api/v1/produtos
- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **H2 Console:** http://localhost:8080/h2-console

**Credenciais H2 Console (Desenvolvimento):**
```
URL: jdbc:h2:mem:testdb
Username: sa
Password: (deixar em branco)
```

---

## ☁️ Deploy na Azure

### Pré-requisitos Azure
- Conta Azure ativa
- Azure CLI instalado
- Acesso ao Azure Portal

### Passo a Passo do Deploy

#### 1. Criar Resource Group
```bash
az group create \
  --name rg-bootcamp-produtos \
  --location brazilsouth
```

#### 2. Criar App Service Plan
```bash
az appservice plan create \
  --name plan-bootcamp-produtos \
  --resource-group rg-bootcamp-produtos \
  --sku B1 \
  --is-linux
```

#### 3. Criar Web App
```bash
az webapp create \
  --name bootcamp-produtos-caioruan \
  --resource-group rg-bootcamp-produtos \
  --plan plan-bootcamp-produtos \
  --runtime "JAVA:17-java17"
```

#### 4. Configurar Variáveis de Ambiente
```bash
az webapp config appsettings set \
  --name bootcamp-produtos-caioruan \
  --resource-group rg-bootcamp-produtos \
  --settings \
    SPRING_PROFILES_ACTIVE=prod \
    DB_PATH=/home/data/produtosdb \
    JAVA_OPTS="-Dspring.profiles.active=prod"
```

#### 5. Deploy da Aplicação
```bash
# Gerar JAR
mvn clean package -DskipTests

# Deploy via Azure CLI
az webapp deploy \
  --name bootcamp-produtos-caioruan \
  --resource-group rg-bootcamp-produtos \
  --src-path target/api-produtos-0.0.1-SNAPSHOT.jar \
  --type jar
```

#### 6. Verificar Logs
```bash
az webapp log tail \
  --name bootcamp-produtos-caioruan \
  --resource-group rg-bootcamp-produtos
```

### Configurações Adicionais na Azure

**Portal Azure → App Service → Configuration:**

| Nome | Valor | Descrição |
|------|-------|-----------|
| `SPRING_PROFILES_ACTIVE` | `prod` | Ativa perfil de produção |
| `DB_PATH` | `/home/data/produtosdb` | Caminho do banco H2 |
| `JAVA_OPTS` | `-Dspring.profiles.active=prod` | Opções JVM |

---

## 📁 Estrutura do Projeto

```
api-produtos-azure/
│
├── src/
│   ├── main/
│   │   ├── java/com/seupackage/apiprodutos/
│   │   │   ├── controller/
│   │   │   │   └── ProdutoController.java          # Endpoints REST
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── ProdutoRequestDTO.java          # DTO de entrada
│   │   │   │   └── ProdutoResponseDTO.java         # DTO de saída
│   │   │   │
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java     # Tratamento global
│   │   │   │   ├── ProdutoNotFoundException.java   # Exceção customizada
│   │   │   │   ├── BusinessException.java          # Exceção de negócio
│   │   │   │   ├── ErrorResponse.java              # Resposta de erro
│   │   │   │   └── ValidationErrorResponse.java    # Resposta validação
│   │   │   │
│   │   │   ├── mapper/
│   │   │   │   └── ProdutoMapper.java              # Conversão Entity ↔ DTO
│   │   │   │
│   │   │   ├── model/
│   │   │   │   └── Produto.java                    # Entidade JPA
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   └── ProdutoRepository.java          # Acesso a dados
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── ProdutoService.java             # Interface do serviço
│   │   │   │   └── impl/
│   │   │   │       └── ProdutoServiceImpl.java     # Implementação
│   │   │   │
│   │   │   ├── config/
│   │   │   │   └── SwaggerConfig.java              # Configuração Swagger
│   │   │   │
│   │   │   └── ApiProdutosApplication.java         # Classe principal
│   │   │
│   │   └── resources/
│   │       ├── application.properties               # Config padrão
│   │       ├── application-dev.properties           # Config desenvolvimento
│   │       └── application-prod.properties          # Config produção
│   │
│   └── test/
│       └── java/com/seupackage/apiprodutos/
│           ├── controller/
│           │   └── ProdutoControllerTest.java
│           └── service/
│               └── ProdutoServiceImplTest.java
│
├── target/                                          # Arquivos compilados
├── .gitignore                                       # Arquivos ignorados
├── pom.xml                                          # Dependências Maven
├── README.md                                        # Este arquivo
└── HELP.md                                          # Documentação Spring
```

---

## ⚠️ Tratamento de Erros

A API implementa tratamento global de exceções com respostas padronizadas.

### Tipos de Erro

#### 1. Produto Não Encontrado (404)
```json
{
  "status": 404,
  "message": "Produto não encontrado com ID: 999",
  "timestamp": "2026-02-06T14:30:00"
}
```

#### 2. Erro de Validação (400)
```json
{
  "status": 400,
  "message": "Erro de validação",
  "timestamp": "2026-02-06T14:30:00",
  "errors": {
    "nome": "Nome deve ter entre 3 e 100 caracteres",
    "preco": "Preço deve ser maior que zero"
  }
}
```

#### 3. Erro de Negócio (400)
```json
{
  "status": 400,
  "message": "Produto com este nome já existe",
  "timestamp": "2026-02-06T14:30:00"
}
```

#### 4. Erro Interno (500)
```json
{
  "status": 500,
  "message": "Erro interno do servidor",
  "timestamp": "2026-02-06T14:30:00"
}
```

---

## ✅ Validações

### Regras de Validação - ProdutoRequestDTO

| Campo | Regras | Mensagens de Erro |
|-------|--------|-------------------|
| **nome** | • Obrigatório (`@NotBlank`)<br>• Mínimo 3 caracteres<br>• Máximo 100 caracteres | • "Nome é obrigatório"<br>• "Nome deve ter entre 3 e 100 caracteres" |
| **preco** | • Obrigatório (`@NotNull`)<br>• Maior que zero (`@Positive`)<br>• Mínimo R$ 0,01 | • "Preço é obrigatório"<br>• "Preço deve ser maior que zero"<br>• "Preço mínimo é R$ 0.01" |

### Exemplo de Request Inválido

**Request:**
```json
{
  "nome": "AB",
  "preco": -10.00
}
```

**Response (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Erro de validação",
  "timestamp": "2026-02-06T14:30:00",
  "errors": {
    "nome": "Nome deve ter entre 3 e 100 caracteres",
    "preco": "Preço deve ser maior que zero"
  }
}
```

---

## 🧪 Testes

### Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar com relatório de cobertura
mvn clean test jacoco:report

# Ver relatório de cobertura
open target/site/jacoco/index.html
```

### Cobertura de Testes

- ✅ **Testes Unitários** - Service Layer
- ✅ **Testes de Integração** - Controller + Service + Repository
- ✅ **Testes de Validação** - Bean Validation
- ✅ **Testes de Exception Handler** - Tratamento de erros

### Exemplo de Teste Unitário

```java
@Test
void deveSalvarProdutoComSucesso() {
    // Arrange
    ProdutoRequestDTO requestDTO = new ProdutoRequestDTO("Notebook", 3500.0);
    Produto produto = new Produto(null, "Notebook", 3500.0);
    Produto savedProduto = new Produto(1L, "Notebook", 3500.0);
    ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(1L, "Notebook", 3500.0);
    
    when(mapper.toEntity(requestDTO)).thenReturn(produto);
    when(repository.save(produto)).thenReturn(savedProduto);
    when(mapper.toResponseDTO(savedProduto)).thenReturn(responseDTO);
    
    // Act
    ProdutoResponseDTO result = service.salvar(requestDTO);
    
    // Assert
    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("Notebook", result.getNome());
    verify(repository, times(1)).save(produto);
}
```

---

## 📊 Configurações de Ambiente

### application.properties (Padrão)
```properties
# Perfil ativo
spring.profiles.active=${PROFILE:dev}

# Nome da aplicação
spring.application.name=api-produtos

# Porta do servidor
server.port=8080

# Datasource H2
spring.datasource.url=jdbc:h2:file:${DB_PATH:./data/produtosdb}
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui/index.html
```

### application-dev.properties (Desenvolvimento)
```properties
# H2 em memória
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Logs detalhados
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### application-prod.properties (Produção)
```properties
# H2 em arquivo
spring.datasource.url=jdbc:h2:file:/home/data/produtosdb
spring.h2.console.enabled=false

# Logs otimizados
spring.jpa.show-sql=false
logging.level.root=INFO
logging.level.com.seupackage=INFO
```

---

## 📚 Documentação Adicional

### Swagger/OpenAPI
A API está totalmente documentada com Swagger/OpenAPI 3.0.

**Acesse a documentação interativa:**
- **Produção:** https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/swagger-ui/index.html
- **Local:** http://localhost:8080/swagger-ui/index.html

**OpenAPI JSON:**
- **Produção:** https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/v3/api-docs
- **Local:** http://localhost:8080/v3/api-docs

---

## 🛡️ Segurança e Boas Práticas

✅ **Implementadas:**
- DTOs para isolamento de dados
- Validações em todas as entradas
- Tratamento global de exceções
- Logs estruturados
- Variáveis de ambiente para configurações sensíveis
- Perfis de aplicação (dev/prod)

🔜 **Próximos Passos:**
- Implementar autenticação JWT
- Adicionar rate limiting
- Configurar HTTPS obrigatório
- Implementar cache com Redis
- Adicionar métricas com Actuator + Prometheus

---

## 🔄 CI/CD Pipeline (Planejado)

### GitHub Actions Workflow

Arquivo: `.github/workflows/azure-deploy.yml`

```yaml
name: CI/CD - Deploy Azure

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    
    steps:
    - name: Checkout código
      uses: actions/checkout@v3
    
    - name: Configurar JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    
    - name: Build com Maven
      run: mvn clean package -DskipTests
    
    - name: Executar testes
      run: mvn test
    
    - name: Deploy para Azure
      uses: azure/webapps-deploy@v2
      with:
        app-name: bootcamp-produtos-caioruan-dyd4c2budxdxcrcx
        publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
        package: target/*.jar
```

---

## 📈 Melhorias Futuras

- [ ] Implementar paginação e ordenação
- [ ] Adicionar filtros de busca
- [ ] Implementar cache com Spring Cache
- [ ] Adicionar autenticação e autorização (Spring Security)
- [ ] Migrar para Azure SQL Database
- [ ] Implementar rate limiting
- [ ] Adicionar logs estruturados (ELK Stack)
- [ ] Implementar HATEOAS para REST nível 3
- [ ] Adicionar versionamento de API (v2, v3)
- [ ] Implementar soft delete
- [ ] Adicionar auditoria (created_at, updated_at)
- [ ] Implementar testes de carga (JMeter)
- [ ] Configurar monitoramento (Application Insights)

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Siga os passos:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Caio Ruan**

- GitHub: [@rcaio0598](https://github.com/rcaio0598)
- LinkedIn: [Seu LinkedIn](https://www.linkedin.com/in/caionascimentoo/)
- Email: rcaio0598@gmail.com

---

## 🙏 Agradecimentos

- Bootcamp de Java Deloitte
- Renato Santiago (Instrutor Deloitte)

---

## 📞 Suporte

Encontrou algum problema? Abra uma [issue](https://github.com/rcaio0598/api-produtos-azure/issues) no GitHub.

---

<div align="center">

**⭐ Se este projeto te ajudou, considere dar uma estrela no repositório!**

**"Código que não está em produção não resolve problema."**

</div>


