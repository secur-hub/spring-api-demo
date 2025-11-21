GUIDA COMPLETA – SPRING BOOT CON GRADLE E MAVEN

====================================================
1. INTRODUZIONE
====================================================
Spring Boot è un framework Java che permette di creare
API e applicazioni web rapidamente.

Per usarlo serve:
- Java JDK 17 o superiore
- Un sistema di build:
    - Gradle
    - oppure Maven

Questa guida spiega entrambe le soluzioni.


====================================================
2. INSTALLAZIONE DI JAVA
====================================================
Verifica se Java è installato:

    java -version

Se non lo è, installa JDK 17+ dal sito Oracle o OpenJDK.


====================================================
3. CREAZIONE DI UN PROGETTO SPRING BOOT
====================================================
Il modo più semplice è usare Spring Initializr:

    https://start.spring.io

Parametri consigliati:
- Language: Java
- Packaging: Jar
- Java: 17+
- Dipendenze:
    - Spring Web
    - Spring JDBC
    - SQL Server driver (o quello che ti serve)
    - Lombok (opzionale)

Poi scegli:
- Gradle     -> se vuoi usare Gradle
- Maven      -> se vuoi usare Maven

Scarica lo ZIP, estrailo e aprilo nel tuo IDE (IntelliJ, VSCode, Eclipse).


====================================================
4. STRUTTURA DEL PROGETTO
====================================================
La struttura base sarà:

src/
 └─ main/
    ├─ java/
    │  └─ com.example.demo/
    │      ├─ DemoApplication.java
    │      ├─ controller/
    │      ├─ service/
    │      ├─ model/
    │      └─ rowmapper/
    └─ resources/
       └─ application.properties


====================================================
5. CONFIGURAZIONE DEL DATABASE
====================================================
Apri:

    src/main/resources/application.properties

E inserisci:

spring.datasource.url=jdbc:sqlserver://HOST:PORT;databaseName=NOME_DB
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver


====================================================
6. AVVIARE UN PROGETTO SPRING BOOT CON GRADLE
====================================================

----------------------------------------------------
6.1 Verifica Gradle
----------------------------------------------------
Se Gradle non è installato, puoi:

A) Scaricarlo da:
    https://gradle.org/install

B) Oppure installarlo con SDKMAN:

    sdk install gradle

Verifica:

    gradle -v


----------------------------------------------------
6.2 File build.gradle
----------------------------------------------------
Spring Initializr crea già il file:

    build.gradle

Esempio:

plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.0'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-jdbc'
    implementation 'com.microsoft.sqlserver:mssql-jdbc'
}


----------------------------------------------------
6.3 Avviare Spring Boot con Gradle
----------------------------------------------------
Da terminale:

    gradle bootRun

L’app parte su:

    http://localhost:8080


----------------------------------------------------
6.4 Creare il JAR
----------------------------------------------------
    gradle build

Il JAR sarà in:

    build/libs/

Avvio del jar:

    java -jar build/libs/app.jar


====================================================
7. AVVIARE UN PROGETTO SPRING BOOT CON MAVEN
====================================================

----------------------------------------------------
7.1 Verifica Maven
----------------------------------------------------
Verifica:

    mvn -v

Se non c’è, installalo da:

    https://maven.apache.org


----------------------------------------------------
7.2 File pom.xml
----------------------------------------------------
Se scegli Maven da Spring Initializr avrai:

<pom.xml>

Esempio:

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <dependency>
        <groupId>com.microsoft.sqlserver</groupId>
        <artifactId>mssql-jdbc</artifactId>
    </dependency>
</dependencies>


----------------------------------------------------
7.3 Avviare Spring Boot con Maven
----------------------------------------------------
Da terminale:

    mvn spring-boot:run

L’app parte su:

    http://localhost:8080


----------------------------------------------------
7.4 Creare il JAR
----------------------------------------------------
    mvn clean package

Lo trovi in:

    target/

Avvio:

    java -jar target/app.jar


====================================================
8. CREARE UNA SEMPLICE API
====================================================
Esempio controller:

@RestController
public class TestController {

    @GetMapping("/ciao")
    public String ciao() {
        return "Ciao dal server Spring Boot!";
    }
}

Apri:

    http://localhost:8080/ciao


====================================================
9. TEST DELL'API CON POSTMAN
====================================================
Metodo: POST

URL:

    http://localhost:8080/restapi/getquery

Body -> JSON:

{
  "nome": "Mario",
  "cognome": "Rossi"
}


====================================================
10. DIFFERENZA TRA MAVEN E GRADLE
====================================================

| Sistema  | Tipo             | Velocità  | Diffusione |
|----------|------------------|-----------|------------|
| Maven    | XML              | Media     | Molto alta |
| Gradle   | Groovy/Kotlin    | Molto alta| Alta       |

Entrambi sono ufficialmente supportati da Spring.


====================================================
11. CONCLUSIONI
====================================================
Spring Boot può essere usato:

- con Gradle (rapidissimo e moderno)
- con Maven (più tradizionale e diffuso)

In entrambi i casi puoi:
- creare API velocemente
- usare database e JDBC
- generare un file JAR eseguibile

Fine.

