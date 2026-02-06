# 🛒 API de Gerenciamento de Produtos
### Desafio Final - Bootcamp JAVA Deloitte

---

## ⚡ Avaliação rápida (2 minutos)

| Passo | Ação |
| ----- | ---- |
| 1 | [Abrir API no Swagger](https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net/swagger-ui.html) |
| 2 | Clicar em **POST /produtos** → Try it out → Execute |
| 3 | Clicar em **GET /produtos** → Execute (ver o produto criado) |
| 4 | Código-fonte: pasta `src` deste repositório |

[![Java](https://img.shields.io/badge/Java-8%2F11-orange?style=for-the-badge&logo=java)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
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

**Desafio do Bootcamp:**  
Integrar toda a aplicação desenvolvida com a Cloud Microsoft Azure, realizando deploy de uma API funcional e acessível publicamente.

---

## 🏗️ Arquitetura e SOLID

A aplicação foi projetada seguindo os **princípios SOLID** e **Clean Architecture**, garantindo um código mais fácil de manter e testar.

### Camadas da Aplicação

Controller Layer -> Requisições HTTP / DTOs / ResponseEntity  
Service Layer -> Regras de negócio / Validações  
Repository Layer -> Acesso a dados (Spring Data JPA)  
Database (H2) -> Persistência  

### Aplicação dos Princípios SOLID

| Princípio | Aplicação no Projeto |
| --------- | -------------------- |
| **S** | Cada classe tem uma única responsabilidade |
| **O** | Validações extensíveis via ProdutoValidation |
| **L** | Implementações substituíveis |
| **I** | DTOs e interfaces específicas |
| **D** | Controller depende de abstrações |

---

## 🚀 Tecnologias Utilizadas

| Área | Tecnologia |
| ---- | ---------- |
| Backend | Java 8/11, Spring Boot 2.7 |
| Banco | H2 |
| Validação | Bean Validation |
| Documentação | Swagger / OpenAPI |
| Build | Maven |
| Deploy | Azure App Service |
| Testes | JUnit 5, Mockito |

---

## ✨ Funcionalidades

- Cadastro de produtos  
- Listagem de produtos  
- Busca por ID  
- Atualização de produtos  
- Exclusão de produtos  
- Validações automáticas  
- Tratamento global de exceções  
- Documentação via Swagger  

---

## 📡 Endpoints da API

**Produção:**  
https://bootcamp-produtos-caioruan-dyd4c2budxdxcrcx.brazilsouth-01.azurewebsites.net

**Local:**  
http://localhost:8080

| Método | Endpoint |
|------|---------|
| POST | `/produtos` |
| GET | `/produtos` |
| GET | `/produtos/{id}` |
| PUT | `/produtos/{id}` |
| DELETE | `/produtos/{id}` |

---

## 💻 Executar Localmente

```bash
git clone https://github.com/rcaio0598/api-produtos-azure.git
cd api-produtos-azure
./mvnw spring-boot:run
☁️ Deploy na Azure
Azure App Service (Linux, Java 11)

Região: Brazil South

Deploy automático via GitHub Actions

📁 Estrutura do Projeto
controller
service
repository
dto
exception
validation
config
model
⚠️ Tratamento de Erros
{
  "mensagem": "Descrição do erro",
  "status": 400
}
🧪 Testes
./mvnw test
👨‍💻 Autor
Caio Ruan
GitHub: https://github.com/rcaio0598
LinkedIn: https://www.linkedin.com/in/caionascimentoo/
Email: rcaio0598@gmail.com

<div align="center"> "Código que não está em produção não resolve problema." </div>
