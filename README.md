# Spring Boot Demo – API con Stored Procedure SQL Server

Questo progetto è una semplice applicazione **Spring Boot** che espone una **API REST POST** per recuperare informazioni di un cliente tramite chiamata a una **stored procedure SQL Server**.

Il progetto è utile come esempio di:

- creazione di API Spring Boot,
- utilizzo di `JdbcTemplate`,
- mapping di risultati tramite `RowMapper`,
- configurazione di datasource per SQL Server,
- gestione dei model e service.

## Struttura progetto

src/main/java/com/example/demo
├── DemoApplication.java
├── config
│   └── JdbcConfig.java
├── controller
│   └── ClienteController.java
├── model
│   └── Cliente.java
├── rowmapper
│   └── ClienteRowMapper.java
└── service
    └── ClienteService.java

## Avvio dell’applicazione

### Requisiti

- Java 17+
- Maven
- SQL Server

### Importazione

Clona il repository:

```bash
git clone https://github.com/<tuo-utente>/<tuo-repo>.git
cd tuo-repo
```

Compila:

```bash
mvn clean package
```

E avvia:

```bash
mvn spring-boot:run
```

## Configurazione database

Modifica `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:sqlserver://<HOST>:<PORT>;databaseName=<NOME_DATABASE>
spring.datasource.username=<TUO_USERNAME>
spring.datasource.password=<TUA_PASSWORD>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### Stored Procedure richiesta

```sql
CREATE PROCEDURE sp_getClientInfo
  @nome NVARCHAR(50),
  @cognome NVARCHAR(50)
AS
BEGIN
    SELECT nome, cognome, luogo_nascita, anno_nascita, indirizzo
    FROM clienti
    WHERE nome = @nome AND cognome = @cognome
END
```

## Test con Postman

### Metodo

POST

### URL

```
http://localhost:8080/restapi/getquery
```

### Body (JSON)

```json
{
    "nome": "Mario",
    "cognome": "Rossi"
}
```

## Licenza

MIT – libero per studio e utilizzo.


