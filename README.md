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

Você pode executar o projeto através de uma IDE diretamente no arquivo App. 

Você pode usar uma IDE como o [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/) ou o [Eclipse](https://www.eclipse.org/downloads/) para executar o projeto mais facilmente.

## 💻 Uso 

Ao executar o aplicativo, você será apresentado a um menu com as seguintes opções:

1.  **Listar os Candidatos:** Exibe a lista com os candidatos cadastrados, apresentando algumas informações relevantes de cada candidato.

2. **Listar as empresas:**  Exibe a lista com as empresas cadastrados, apresentando algumas informações relevantes de cada empresa.

3. **Cadastrar um novo candidato:** É possivel cadastrar um novo candidato com as seguintes informações:
   - Nome
   - Email
   - CPF
   - Idade
   - Estado
   - CEP
   - Descrição
   - Competências (É possivel passar mais de uma competência usando virgula por ex: Java, Groovy, Html, Css, Angular)

5. **Cadastrar uma nova empresa:**  É possivel cadastrar uma nova empresa com as seguintes informações:
   - Nome
   - Email
   - CNPJ
   - País
   - Estado
   - CEP
   - Descrição
   - Competências (É possivel passar mais de uma competência usando virgula por ex: Java, Groovy, Html, Css, Angular)



## 🌹 Agradecimento

Obrigado por explorar este projeto! Espero que seja útil para você.

Desenvolvido por [Victor Nathanael](https://www.linkedin.com/in/victornathanael/)

