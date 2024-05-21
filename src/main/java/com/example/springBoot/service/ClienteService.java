package com.example.springBoot.service;

import com.example.springBoot.dto.cliente.ClienteRequestDTO;
import com.example.springBoot.dto.cliente.ClienteResponseDTO;
import com.example.springBoot.exceptions.SenhaAtualIncorretaException;
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

    public ClienteModel registra(ClienteRequestDTO data, String senhaHash){
        ClienteModel cliente = new ClienteModel(data, senhaHash);
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> getCliente(UUID id){
        return clienteRepository.findById(id);
    }

    public List<ClienteModel> listaClientes(){
        return clienteRepository.findAll();
    }

    public ClienteResponseDTO saveCliente(ClienteModel cliente){
        clienteRepository.save(cliente);
        return converteModelEmDTO(cliente);
    }

    public void verificaSenha(ClienteModel cliente, String senha) throws SenhaAtualIncorretaException {
        if(!cliente.getSenha().equals(senha)){
            throw new SenhaAtualIncorretaException();
        }
    }

    /**
     * Método que transforma uma entidade do tipo Model para um DTOResponse, com os dados adequados a serem exibidos
     * @param model
     * @return - Uma entidade ClienteDTO contendo: nome, cpf, telefone, endereço,dataDenascimento e usuário do cliente.
     */
    public ClienteResponseDTO converteModelEmDTO(ClienteModel model){
        ClienteResponseDTO response = new ClienteResponseDTO(model.getNomeCliente(),model.getCpfCliente(),
                model.getTelefoneCliente(),
                model.getEndereçoCliente(),
                model.getDataDeNascimento(),
                model.getEmail());
        return  response;
    }
}
