package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restapi")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/getquery")
    public List<Cliente> getClientByNomeAndCognome(@RequestBody Cliente cliente) {
        return clienteService.findByNomeAndCognome(cliente.getNome(), cliente.getCognome());
    }
}
