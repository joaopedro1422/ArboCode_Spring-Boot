package com.example.springBoot.service;

import com.example.springBoot.dto.ClienteRequestDTO;
import com.example.springBoot.models.ClienteModel;
import com.example.springBoot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteModel registra(ClienteRequestDTO data){
        ClienteModel cliente = new ClienteModel(data);
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> getCliente(UUID id){
        return clienteRepository.findById(id);
    }

    public List<ClienteModel> listaClientes(){
        return clienteRepository.findAll();
    }
}
