package com.example.springBoot.Controller;

import com.example.springBoot.dto.ClienteRequestDTO;
import com.example.springBoot.models.ClienteModel;
import com.example.springBoot.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Classe Controller para manipulação das requisições HTTP destinadas ao banco de dados dos clientes da aplicação
 */
@RestController
@RequestMapping(value = "/clientes",  produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> registraCliente (@RequestBody @Valid ClienteRequestDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.registra(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCliente (@PathVariable(value = "id")UUID id){
        Optional<ClienteModel> cliente = clienteService.getCliente(id);
        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @GetMapping
    public ResponseEntity<?> getAllClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listaClientes());
    }




}
