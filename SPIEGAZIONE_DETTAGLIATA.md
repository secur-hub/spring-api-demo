===========================================================
FILE: DemoApplication.java
===========================================================

package com.example.demo;
> Indica che questa classe si trova nel package com.example.demo.
> I package servono a organizzare il codice in cartelle logiche.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
> Import delle classi Spring necessarie ad avviare l'applicazione.

@SpringBootApplication
> Annotazione fondamentale che attiva:
> - @Configuration          -> la classe contiene configurazione
> - @EnableAutoConfiguration -> Spring configura automaticamente database, server, ecc.
> - @ComponentScan          -> Spring cerca componenti nel package com.example.demo

public class DemoApplication {
> Definizione della classe principale dell'applicazione.

    public static void main(String[] args) {
    > Punto di ingresso del programma Java.

        SpringApplication.run(DemoApplication.class, args);
        > Avvia Spring Boot:
        > - crea il contesto Spring
        > - inizializza i componenti
        > - accende il server embedded (Tomcat per default)
    }
}


===========================================================
FILE: Cliente.java
===========================================================

package com.example.demo.model;
> La classe è nel package dedicato ai modelli dati.

public class Cliente {
> Classe che rappresenta un record cliente.

    private String nome;
    > Campo per il nome del cliente.

    private String cognome;
    > Campo per il cognome.

    private String luogoNascita;
    > Luogo di nascita.

    private int annoNascita;
    > Anno di nascita.

    private String indirizzo;
    > Indirizzo del cliente.

    public Cliente() {}
    > Costruttore vuoto richiesto da Spring per creare oggetti POJO.

    // Seguono getter e setter, necessari per accedere e modificare i dati.
}


===========================================================
FILE: ClienteController.java
===========================================================

package com.example.demo.controller;
> Package per i controller REST.

import ...
> Import delle classi Spring e del modello.

@RestController
> Rende la classe un controller REST che restituisce JSON.

@RequestMapping("/restapi")
> Prefisso comune per tutte le rotte del controller.

public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    > Spring inietta automaticamente il service.

    @PostMapping("/getquery")
    > Espone un endpoint POST raggiungibile a:
    > http://localhost:8080/restapi/getquery

    public List<Cliente> getClientByNomeAndCognome(@RequestBody Cliente cliente) {
        > @RequestBody dice a Spring di convertire il JSON in oggetto Cliente.
        return clienteService.findByNomeAndCognome(
            cliente.getNome(),
            cliente.getCognome()
        );
        > Chiama il service per eseguire la stored procedure.
    }
}


===========================================================
FILE: ClienteService.java
===========================================================

package com.example.demo.service;
> Package dedicato alla logica applicativa.

@Service
> Indica che è un componente Service gestito da Spring.

public class ClienteService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    > JdbcTemplate è la classe Spring che gestisce chiamate SQL verso il database.

    public List<Cliente> findByNomeAndCognome(String nome, String cognome) {

        String sql = "{call sp_getClientInfo(?, ?)}";
        > Sintassi per chiamare una stored procedure SQL Server con due parametri.

        return jdbcTemplate.query(
            sql,
            new Object[]{nome, cognome},
            new ClienteRowMapper()
        );
        > Esegue la query e usa il RowMapper per convertire le righe SQL in oggetti Cliente.
    }
}


===========================================================
FILE: ClienteRowMapper.java
===========================================================

package com.example.demo.rowmapper;
> Package per i mapper JDBC.

public class ClienteRowMapper implements RowMapper<Cliente> {
> Implementa l'interfaccia che dice come convertire una riga SQL in un oggetto Cliente.

    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {

        Cliente c = new Cliente();
        > Crea un nuovo oggetto Cliente.

        c.setNome(rs.getString("nome"));
        > Legge il valore della colonna 'nome' e lo assegna.

        c.setCognome(rs.getString("cognome"));
        c.setLuogoNascita(rs.getString("luogoNascita"));
        c.setAnnoNascita(rs.getInt("annoNascita"));
        c.setIndirizzo(rs.getString("indirizzo"));
        > Ogni campo del risultato SQL viene mappato nel POJO.

        return c;
        > Ritorna l'oggetto popolato.
    }
}


===========================================================
FILE: application.properties
===========================================================

spring.datasource.url=jdbc:sqlserver://HOST:PORT;databaseName=NOME_DB
> URL JDBC di connessione al database SQL Server.

spring.datasource.username=USERNAME
> Username del database.

spring.datasource.password=PASSWORD
> Password del database.

spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
> Driver JDBC necessario per SQL Server.


===========================================================
FLUSSO DI ESECUZIONE COMPLETO
===========================================================

1) PARTE L'APPLICAZIONE
   - DemoApplication.java esegue SpringApplication.run()
   - Spring avvia il server e configura il contesto.

2) ARRIVA UNA RICHIESTA POST
   -> POST /restapi/getquery
   -> JSON nel body con nome + cognome.

3) CONTROLLER
   - Il JSON viene trasformato automaticamente in un oggetto Cliente.
   - ClienteController chiama ClienteService.

4) SERVICE
   - Viene eseguita la stored procedure:
     { call sp_getClientInfo(?, ?) }

5) ROWMAPPER
   - Ogni riga del risultato SQL viene convertita in oggetto Cliente.

6) RISPOSTA
   - Una lista JSON viene inviata indietro al client.


======================================================
