package com.example.springBoot.Controller;

import com.example.springBoot.dto.PlantaResponseDTO;
import com.example.springBoot.dto.SolicitaçaoRequestDTO;
import com.example.springBoot.enums.Status;
import com.example.springBoot.models.SolicitaçaoModel;
import com.example.springBoot.service.SolicitaçaoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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
     * Endpoint para obter a lista de todas as solicitaçoes realizadas no sistema, com os atributos mais importantes
     * para uma view do administrador da organização.
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

    /**
     * Endpoint para filtrar a lista de solicitações a partir de um status específico.
     * @param status - Estado das solicitações a serem retornadas
     * @return
     */
    @GetMapping("/status")
    public ResponseEntity<?> getSolicitaçoesPorStatus(@RequestParam("status") String status){
        return ResponseEntity.status(HttpStatus.OK).body(solicitaçaoService.filtraSolicitaçoesPorStatus(status));
    }

    /**
     * Endpoint responsável por alterar o status da solicitação para APROVADA. Deve ser chamado após o funcionário
     * finalizar o plantio da árvore na casa do cliente.
     * @param id - id da solicitação
     * @return - Retorna o corpo da solicitação atualizado.
     */
    @PutMapping("/aprovaSolicitaçao/{id}")
    public ResponseEntity<?> aprovaSolicitaçaoDePlantio(@PathVariable(value = "id") UUID id){
        Optional<SolicitaçaoModel> solicitaçaoModelOptional = solicitaçaoService.getSolicitaçao(id);
        if(solicitaçaoModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitação não encontrada.");
        }
        SolicitaçaoModel solicitaçao = solicitaçaoModelOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(solicitaçaoService.aprovaSolicitaçao(solicitaçao));
    }

    /**
     * Endpoint responsável por alterar o status da solicitação para REJEITADA. Deve ser chamado após o funcionário
     * finalizar o plantio da árvore na casa do cliente.
     * @param id - id da solicitação
     * @return - Retorna o corpo da solicitação atualizado.
     */
    @PutMapping("/rejeitaSolicitaçao/{id}")
    public ResponseEntity<?> rejeitaSolicitaçaoDePlantio(@PathVariable(value = "id") UUID id){
        Optional<SolicitaçaoModel> solicitaçaoModelOptional = solicitaçaoService.getSolicitaçao(id);
        if(solicitaçaoModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitação não encontrada.");
        }
        SolicitaçaoModel solicitaçao = solicitaçaoModelOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(solicitaçaoService.rejeitaSolicitação(solicitaçao));
    }

    /**
     * Endpoint para obter as informações da planta mais solicitada para plantio pelos clientes.
     * @return - Entidade DTO contendo o nome, descriçao, porte e valor da planta.
     */
    @GetMapping("/plantaMaisSolicitada")
    public ResponseEntity<PlantaResponseDTO> getPlantaMaisSolicitada(){
        return ResponseEntity.status(HttpStatus.OK).body(solicitaçaoService.getPlantaMaisSolicitada());
    }

    @GetMapping("/receitaBruta")
    public ResponseEntity<?> getReceitaBruta(){
        return ResponseEntity.status(HttpStatus.OK).body(solicitaçaoService.getReceitaBruta());
    }



}
