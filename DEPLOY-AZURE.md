# Deploy na Azure

Documentação do deploy desta API no Azure App Service.

---

## Pré-requisitos

- [ ] Conta **Microsoft Azure** (estudante pode ter créditos gratuitos).
- [ ] **Git** instalado e repositório no **GitHub** com o código da API.
- [ ] **Azure CLI** instalado (opcional; dá para fazer tudo pelo portal).
- [ ] Projeto rodando localmente: `.\mvnw.cmd spring-boot:run` (PowerShell) e teste em `http://localhost:8080/produtos`.

---

## PASSO 1 — Criar Resource Group

1. Acesse [portal.azure.com](https://portal.azure.com) e faça login.
2. No campo de busca no topo, digite: **Resource groups**.
3. Clique em **+ Create**.
4. Preencha:
   - **Subscription:** sua assinatura.
   - **Resource group:** ex: `rg-bootcamp-produtos`.
   - **Region:** ex: Brazil South.
5. Clique em **Review + create** → **Create**.
6. Anote o nome do resource group (ex: `rg-bootcamp-produtos`).

---

## PASSO 2 — Criar App Service (a “caixa” onde a API vai rodar)

1. No portal, busque **App Service** (ou **Web App**).
2. Clique em **+ Create**.
3. **Basics:**
   - **Subscription** e **Resource group:** use o grupo criado no Passo 1.
   - **Name:** ex: `bootcamp-produtos-seunome` (vai virar a URL: `https://bootcamp-produtos-seunome.azurewebsites.net`).
   - **Publish:** Code.
   - **Runtime stack:** **Java 8** (ou Java 11, conforme seu projeto).
   - **Operating System:** Linux (recomendado).
   - **Region:** mesma do resource group (ex: Brazil South).
   - **Pricing:** Free F1 ou Basic B1 (Free pode dormir e demorar a acordar).
4. Clique em **Review + create** → **Create**.
5. Anote a URL do app: `https://<nome-do-app>.azurewebsites.net`.

---

## PASSO 3 — Configurar variáveis de ambiente (obrigatório do desafio)

1. No portal, abra o **App Service** que você criou.
2. No menu lateral, em **Settings**, clique em **Configuration**.
3. Aba **Application settings** → **+ New application setting**.
4. Adicione estas configurações (uma por uma):

   | Name | Value |
   |------|--------|
   | `SPRING_PROFILES_ACTIVE` | `prod` |
   | `SPRING_H2_CONSOLE_ENABLED` | `false` |

   (Opcional, se quiser sobrescrever a URL do banco:  
   `SPRING_DATASOURCE_URL` = `jdbc:h2:file:./data/produtosdb`)

5. Clique em **Save** (no topo da tela).
6. As variáveis devem aparecer na lista em Configuration.

---

## PASSO 4 — Fazer o deploy da aplicação

**Não uso GitHub Desktop — repositório está no GitHub (web):**  
Tudo certo. O deploy pela Azure usa só o **navegador** e o **GitHub na web**. Você vai conectar o App Service ao seu repositório no site do GitHub (autorizando a Azure uma vez). Não precisa do GitHub Desktop.

- Se o **código já está no GitHub** (você já deu push de outra forma): vá direto para a **Opção A** abaixo e conecte o App Service ao repositório.
- Se o **código está só no seu PC** e ainda não está no GitHub: use o **Git no PowerShell** (veja a caixa “Enviar código do PC para o GitHub” no final deste passo) para dar push; depois use a Opção A.

Escolha **uma** das opções abaixo.

### Opção A — Deploy pelo GitHub (recomendado; tudo pelo navegador)

1. No **App Service**, menu lateral: **Deployment Center**.
2. **Source:** GitHub → autorize se pedir.
3. **Organization / Repository / Branch:** selecione seu repositório e branch (ex: `main`).
4. **Build Provider:** GitHub Actions (ou App Service build service).
5. Clique em **Save**. O Azure pode criar um workflow de deploy automaticamente.
6. Aguarde o workflow rodar no GitHub (Actions). Depois de “sucesso”, a API estará no ar.
7. Teste em `https://<seu-app>.azurewebsites.net/produtos`.

### Opção B — Deploy manual (JAR pelo portal)

1. No seu PC, na pasta do projeto, gere o JAR:
   - `mvnw.cmd clean package -DskipTests`
2. O JAR estará em `target\api-produtos-0.0.1-SNAPSHOT.jar`.
3. No **App Service** → **Deployment Center** → **FTPS credentials** ou use **Advanced Tools** (Kudu) para enviar o JAR e configurar o startup.
4. Ou: **App Service** → **Settings** → **General settings** → **Startup Command:**  
   `java -jar /home/site/wwwroot/app.jar` (ajuste o caminho se o JAR tiver outro nome/local).
5. Envie o JAR via FTP ou **Advanced Tools** (Kudu) → **Debug console** → arrastar o JAR para `/home/site/wwwroot/`.
6. Reinicie o App Service e teste a URL.

### Opção C — Pipeline no GitHub Actions (desafio CI/CD)

Há um workflow de exemplo em `.github/workflows/deploy-azure.yml`. Para usar:

1. No **App Service** → **Deployment Center**, configure a origem como GitHub e anote o **publish profile** (baixe o arquivo).
2. No GitHub: **Settings** → **Secrets and variables** → **Actions** → **New repository secret**:
   - Nome: `AZURE_WEBAPP_PUBLISH_PROFILE`.
   - Valor: conteúdo do arquivo do publish profile.
3. Faça push na branch configurada; o workflow vai buildar e fazer deploy.
4. Verifique em **Actions** se o job passou e teste a URL.

---

**Enviar código do PC para o GitHub (sem GitHub Desktop)**  
Se o projeto está só na sua máquina e você quer subir para o GitHub pelo PowerShell:

1. No GitHub.com, crie um repositório novo (vazio, sem README).
2. Na pasta do projeto no PowerShell (ex: `api-produtos`):
   ```powershell
   git init
   git add .
   git commit -m "API Produtos - bootcamp"
   git branch -M main
   git remote add origin https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
   git push -u origin main
   ```
   (Troque `SEU_USUARIO` e `SEU_REPOSITORIO` pela URL do repositório que você criou.)
3. Se pedir login, use seu usuário do GitHub e um **Personal Access Token** como senha (em GitHub.com → Settings → Developer settings → Personal access tokens).

Depois disso, o código estará no GitHub e você pode usar a Opção A no Deployment Center.

---

## PASSO 5 — Testar a URL pública

1. Abra no navegador: `https://<seu-app>.azurewebsites.net/produtos`  
   - Deve retornar `[]` ou lista de produtos (GET).
2. Documentação da API (Swagger): `https://<seu-app>.azurewebsites.net/swagger-ui.html`
3. Anote a URL para colocar no README e na entrega.

---

## Resumo

| O quê | Onde |
|-------|------|
| Resource Group | Portal Azure → Resource groups → Create |
| App Service | Portal Azure → App Service → Create (Java 8/11, Linux) |
| Variáveis de ambiente | App Service → Configuration → Application settings |
| Deploy | Deployment Center (GitHub) ou JAR manual ou GitHub Actions |
| URL da API | `https://<nome-do-app>.azurewebsites.net/produtos` |
| Swagger | `https://<nome-do-app>.azurewebsites.net/swagger-ui.html` |
