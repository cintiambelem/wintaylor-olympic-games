# Wintaylor Olympic Games 🏅

Este projeto consiste em uma API Spring Boot desenvolvida para responder ao teste de Java para a Wintaylor.
O projeto utiliza os dados dos Jogos Olímpicos de 2012, que são consumidos diretamente de um JSON remoto e manipulados em memória via `HashMap`.

---
## Pré-requisitos

- Java: **8 até 11.0.19**
- Maven
- Spring Boot
- Porta da aplicação: **10001**

---

## Fonte de Dados

A API utiliza como base de dados o JSON disponível em:

```
https://cdn.jsdelivr.net/gh/highcharts/highcharts@24912efc85/samples/data/olympic2012.json
```

Os dados são carregados em memória em um `HashMap<String, Athlete>` no momento da inicialização, onde a **chave é o índice da linha no JSON**.

---

## Rodar a aplicação:
Executar o comando abaixo na raiz do projeto:

```
java -jar wintaylor-olympic-games-0.0.1-SNAPSHOT.jar
```
---

## Exercícios implementados

### Exercício 1
**Endpoint:** `GET /api/athletes`  

Criar um endpoint que leia toda a informação do JSON e guarde num `HashMap<String,POJO>`, a chave de cada objecto será o número da linha correspondente ao seu posicionamento no Array.
Esse método deverá ser executado inicialmente e servirá de base para os exercícios seguintes

---

### Exercício 2
**Endpoint:** `GET /api/countries/{countryCode}/sports`

Criar um endpoint que receba como parâmetro o código do país e retorne um objeto que liste todos os esportes que esse país pratica.

---

### Exercício 3
**Endpoint:** `GET /api/countries/{countryCode}/sports/{sport}/stats`
**Parâmetros:** `countryCode`, `sport`

Criar um endpoint que receba como parâmetro o código do país e um esporte, e retorne um objeto com a quantidade de homens e mulheres, e as médias de idade.

---

### Exercício 4
**Endpoint:** `GET /api/continents/stats`  
**Parâmetros:** `sport`, `age`  

Criar um endpoint que receba como parâmetro o esporte e uma idade, e retorne um objeto com a quantidade de pessoas acima dessa idade por continente.

Exemplo: `GET /api/continents/stats?sport=football&age=25`

Response:
```json
{
    "sport": "football",
    "minAge": 25.0,
    "countsByContinent": {
        "South America": 17,
        "Asia": 16,
        "Europe": 68,
        "Africa": 3,
        "North America": 51,
        "Oceania": 24
    }
}
```

---

### Exercício 5
**Endpoint:** `GET /api/countries/max-bmi`  
**Parâmetros:** `continent`, `gender`
`Continents: AFRICA, ASIA, EUROPE, NORTH_AMERICA, OCEANIA, SOUTH_AMERICA`

Criar um endpoint que receba como parâmetro o continente e o gênero, e retorne um objeto com o país que tenha a maior média de índice de massa corporal (BMI) do continente, juntamente com o valor da média.

Exemplo: `GET /api/countries/max-bmi?continent=SOUTH_AMERICA&gender=female`

Response:
```json
{
    "country": "GUY",
    "averageBmi": 23.87
}
```
    
---

### Exercício 6
**Endpoint:** `POST /api/team/add`  
**Body:** `AthleteRequest`  

Criar um endpoint que insira um atleta na seleção de futebol olímpica masculina do Brasil.

Exemplo de body:
```json
{
  "height": 1.75,
  "weight": 65,
  "age": 18,
  "bmi": 21.2,
  "continent": "South America",
  "country": "BRA",
  "gender": "male",
  "sport": "football"
}
```
Obs: Esse endpoint possui regra de validação para que atenda as condições estabelecidas no enunciado do exercício. 
Contudo, para que o serviço seja mais flexível e reutilizável, foi criado o método abaixo, que permite a inserção de atletas de qualquer país e modalidade esportiva.
`TeamService.addAthleteToTeam(AthleteRequest)`
---

### Exercício 7
**Endpoint:** `GET /api/team/filter`  
**Parâmetros:** `AthleteFilterRequest`

Criar um endpoint que liste todos os atletas da seleção de futebol olímpica masculina do Brasil.

Exemplo: `GET api/team/filter?country=BRA&sport=football&gender=male`

Obs: Para maior flexibilidade, esse endpoint aceita filtros de todos os países, gêneros e esportes.

---

### Exercício 8
**Endpoint:** `PATCH /api/athletes/{id}`
**Parâmetros:** `id`

Criar um endpoint que edite a informação de um determinado atleta.

Campos editáveis:
```
"height": Double

"weight": Double

"age": Double

"bmi": Double

"continent": string

"country": string

"sport": string

"gender": string
```

---

### Exercício 9
**Endpoint:** `GET /api/team/paris/preselection`  

Criar um endpoint que:

• faça uma pré seleção para os jogos olímpicos de Paris, apenas deverão ser consideradas as atletas femininas de todas as modalidades excepto o futebol e com menos de 20 anos.

• liste os resultados
Esse resultado deverá ser guardado numa estrutura igual à estrutura presente no exercício 1.

---

### Exercício 10
**Endpoint:** `POST api/team/paris/preselection/add-and-list`  
**Body:** `AthleteRequest` 

Criar um endpoint que:

• insira uma atleta na seleção de atletismo olímpica feminina do Brasil com 18 anos de idade.

• execute a pré selecção para os jogos olímpicos de Paris

• liste os resultados

Obs: Esse endpoint possui regra de validação para que atenda as condições estabelecidas no enunciado do exercício.
Contudo, para que o serviço seja mais flexível e reutilizável, foi criado o método abaixo, que permite a inserção de atletas de qualquer país e modalidade esportiva.
`TeamService.addAthleteToTeam(AthleteRequest)`

---

Para acessar o Swagger da API, rode aplicação localmente e acesse:  
```
http://localhost:10001/swagger-ui/index.html
```

<div align="center">

### 👩‍💻 Desenvolvido por 
**Cíntia Maria Belém**  
[GitHub](https://github.com/cintiambelem)
| [LinkedIn](https://www.linkedin.com/in/cintiambelem/)
[| Repositório do projeto](https://github.com/cintiambelem/wintaylor-olympic-games)

</div>

