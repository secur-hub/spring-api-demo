package com.example.demo.rowmapper;

import com.example.demo.model.Cliente;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {

    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(rs.getString("nome"));
        cliente.setCognome(rs.getString("cognome"));
        cliente.setLuogoNascita(rs.getString("luogo_nascita"));
        cliente.setAnnoNascita(rs.getInt("anno_nascita"));
        cliente.setIndirizzo(rs.getString("indirizzo"));
        return cliente;
    }
}
