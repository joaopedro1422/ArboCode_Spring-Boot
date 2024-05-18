package com.example.springBoot.Controller;

import com.example.springBoot.dto.SolicitaçaoRequestDTO;
import com.example.springBoot.service.SolicitaçaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/solicitacoes",  produces = MediaType.APPLICATION_JSON_VALUE)
public class SolicitaçaoController {

    @Autowired
    SolicitaçaoService solicitaçaoService;

    /**
     * Endpoint para o registro de uma solicitação de plantio realizada por um cliente.
     * @param request - DTO contendo o id do cliente solicitante, id da planta solicitada , a data
     *                 e o status(pendente) da solicitação.
     * @return
     */
    @PostMapping
    public ResponseEntity<?> registraSolicitaçao(@RequestBody @Valid SolicitaçaoRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitaçaoService.registraSolicitaçao(request));
    }

    /**
     * Endpoint para obter a lista de todas as solicitaçoes realizadas no sistema, com os atributos necessários para
     * o administrador da organização.
     * @return Lista de entidades contendo : Nome do cliente, Usuário de acesso, tipo de planta, endereço , telefone
     *          e data da solicitação.
     */
    @GetMapping("/adminView")
    public ResponseEntity<?> getAllSolicitaçoesForAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(solicitaçaoService.listaSolicitaçoesAdmin());
    }

    /**
     * Endpoint para obter as solicitações realizadas pelo Cliente.
     * @param id - Id do cliente
     * @return - Lista de entidades contendo: Nome da planta, descrição, valor do plantio, endereço, data da
     * solicitaçao e o status (pendente, aprovada ou rejeitada).
     */
    @GetMapping("/clienteView/{id}")
    public ResponseEntity<?> getSolicitaçoesCliente(@PathVariable(value= "id")UUID id){
        return ResponseEntity.status((HttpStatus.OK)).body(solicitaçaoService.listaSolicitaçoesCliente(id));
    }



}
