package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.rowmapper.ClienteRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final JdbcTemplate jdbcTemplate;

    public ClienteService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cliente> findByNomeAndCognome(String nome, String cognome) {
        String sql = "{call sp_getClientInfo(?, ?)}";
        return jdbcTemplate.query(sql, new Object[]{nome, cognome}, new ClienteRowMapper());
    }
}
