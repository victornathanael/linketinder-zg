# 🧑‍💻 Linketinder

Bem-vindo ao Linketinder! Esse projeto está sendo desenvolvido em Groovy e Typescript e combina as características do LinkedIn e do Tinder para criar uma plataforma inovadora. Ele visa simplificar e otimizar a busca por empregos, conectando candidatos a oportunidades de forma eficiente. O destaque é um sistema de "match" que permite empresas e candidatos a expressar interesse mútuo, aumentando as chances de encontrar as combinações ideais. 

***OBS: Até o presente momento o projeto ainda não tem uma comunicação entre o frontend e o backend, mas você pode testar as duas partes de forma separada.*** 

📊 O Frontend possui um total de 4 páginas e 1 menu de navegação. As páginas são divididas da seguinte forma: 
- Cadastro do Candidato,
- Cadastro da Empresa,
- Perfil do Candidato
- Perfil da Empresa.

Além disso existe um gráfico no perfil da empresa que indica quantas pessoas possuem determinada competência.

⚙️ Para rodar o frontend você vai precisar do [Typescript](https://www.typescriptlang.org/download) e do [LiveServer](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) instalado na sua máquina. Navegue até a pasta ```Frontend``` e com o LiveServer rodando basta digitar no terminal:
```
tsc
```
![image](https://github.com/victornathanael/linketinder-zg/assets/99601659/3f1624da-7b29-4d68-b891-88685d41979a)

### ***Abaixo você encontra de forma detalhada como rodar o backend.***

![MER](https://github.com/victornathanael/linketinder-zg/assets/99601659/8997ce13-0cb1-4592-b370-0ee9f8ee99c1)



## ▶️ Primeiros Passos

### 🔧 Pré-requisitos 

É necessário ter o Java Development Kit (JDK) instalado em sua máquina para compilar e executar o código Java.

Também é necessário possuir o [groovy](https://groovy.apache.org/download.html) instalado na sua máquina.

### 🎯 Instalação 

1. Clone o repositório para o seu computador local:

```
git clone https://github.com/victornathanael/linketinder-zg.git
```

2. Navegue até o diretório do projeto:

```
cd linketinder-zg
```

## 🚀 Como Executar o Projeto

Antes de executar o projeto altere os atributos da classe PostgreSQLConnection com a url, user e password com as informações do seu banco de dados Postgre.

O projeto pode ser executado utilizando o [Tomcat](https://tomcat.apache.org/tomcat-8.5-doc/index.html) para subir o servidor e o [Postman](https://www.postman.com/) para consumir os endpoints


## 💻 Uso  

A api pode ser acessada por padrão em localhost:8080 e pode ser alterada nas configurações do Tomcat

### ☝️🤓 Candidatos

#### Listar Candidatos (GET)
  
```http
GET /candidates
```

#### Cadastrar Candidato (POST)
  
```http
POST /candidates
```

Body da Requisição POST:

```json
{
    "name": "Lindsay RR",
    "email": "lin@gmail.com",
    "cpf": "7544242",
    "age": 21,
    "state": "ES",
    "cep": "168757",
    "personalDescription": "Dev SR",
    "skills":["java", "react", "css", "html"]
}
```

#### Atualizar Candidato (PUT)
  
```http
PUT /candidates?id=
```

*O ID do candidato a ser atualizado deve ser passado por parâmetro na URL*

Body da Requisição PUT:

```json
{
    "name": "Matheus Muniz",
    "email": "matheus@gmail.com",
    "cpf": "929262612",
    "age": 21,
    "state": "CE",
    "cep": "62624848",
    "personalDescription": "bla bla bla"
}

```

#### Deletar Candidato (DELETE)

```http
DELETE /candidates?id=
```

*O ID do candidato a ser deletado deve ser passado por parâmetro na URL*

### 👨‍💼 Empresas

#### Listar Empresas (GET)

```http
GET /companies
```

#### Cadastrar Empresa (POST)

```http
POST /companies
```

Body da Requisição: 

```json
{
    "name": "CornoTechnology",
    "corporateEmail": "contato@corno.com",
    "cnpj": "75.124.112/0006-12",
    "country": "EUA",
    "state": "CA",
    "cep": "8657424212",
    "companyDescription": "Presta serviços para corretora",
    "skills": [
        "JS",
        "react",
        "html",
        "css"
    ]
}
```

#### Atualizar Empresa (PUT)

```http
PUT /companies?id=
```

*O ID da empresa a ser atualizado deve ser passado por parâmetro na URL*

Body da Requisição:

```json
{
    "name": "CornoTechnology",
    "corporateEmail": "contato@tech.com",
    "cnpj": "75.124.112/0006-12",
    "country": "EUA",
    "state": "CA",
    "cep": "8657424212",
    "companyDescription": "Presta serviços para corretora",
    "skills": [
        "JS",
        "react",
        "html",
        "css"
    ]
}
```

#### Deletar Empresa (DELETE)

```http
DELETE /companies?id=
```

*O ID da empresa a ser deletado deve ser passado por parâmetro na URL*

### 💼 Vagas de Emprego

#### Listar Vagas (GET)

```http
GET /jobs
```

#### Cadastrar Vaga (POST)

```http
POST /jobs
````

Body da Requisição:

```json
{
    "name": "Desenvolvedor Junior React Native",
    "description": "bla bla bla ",
    "idCompany": 40
}
```

#### Atualizar Vaga (PUT)

```http
PUT /jobs?id=
````

*O ID da vaga a ser atualizado deve ser passado por parâmetro na URL*

Body da Requisição: 

```json
{
    "name": "Desenvolvedor SR Go",
    "description": "bla bla bla ",
    "idCompany": 2
}
```
#### Deletar Vaga (DELETE)

```http
DELETE /jobs?id=
```

*O ID da vaga a ser deletada deve ser passado por parâmetro na URL*

### 🛠️ Habilidades

#### Listar Habilidades (GET)

```http
GET /skills
```

#### Cadastrar Habilidade (POST)

```http
POST /skills
```

Body da Requisição

```json
{
    "name": "clean code"
}
```

#### Atualizar Habilidade (PUT)

```http
PUT /skills?id=
```

*O ID da competência a ser atualizado deve ser passado por parâmetro na URL*

Body da Requisição

```json
{
    "name": "xml"
}
```

#### Deletar Habilidade (DELETE)

```http
DELETE /skills?id=
```

*O ID da competência a ser deletada deve ser passado por parâmetro na URL*


## 🌹 Agradecimento

Obrigado por explorar este projeto! Espero que seja útil para você.

Desenvolvido por [Victor Nathanael](https://www.linkedin.com/in/victornathanael/)

