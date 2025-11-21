===========================================================
CONFIGURAZIONE SPRING BOOT PER MYSQL
===========================================================

# URL di connessione al database MySQL
spring.datasource.url=jdbc:mysql://HOST:3306/NOME_DB?useSSL=false&serverTimezone=UTC

# Username e password per l'accesso al database
spring.datasource.username=TUO_USERNAME
spring.datasource.password=TUA_PASSWORD

# Driver JDBC di MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# (Opzionale) impostazioni Hibernate se usi JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

===========================================================
DIPENDENZE GRADLE
===========================================================

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-jdbc'
    implementation 'mysql:mysql-connector-java:8.1.0'
}

===========================================================
DIPENDENZE MAVEN (pom.xml)
===========================================================

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
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.1.0</version>
    </dependency>
</dependencies>

===========================================================
NOTE IMPORTANTI
===========================================================

1) Porta predefinita MySQL: 3306
2) Assicurati che la stored procedure esista e sia compatibile con MySQL
3) Controlla i tipi dei campi nel RowMapper per corrispondere ai tipi MySQL
4) Aggiungi '?useSSL=false&serverTimezone=UTC' all'URL per evitare warning
5) Se usi JPA, imposta correttamente il Dialect

