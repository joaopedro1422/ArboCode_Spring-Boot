package com.example.springBoot.service;

import com.example.springBoot.dto.planta.PlantaResponseDTO;
import com.example.springBoot.dto.solicitaçao.SolicitaçaoRequestDTO;
import com.example.springBoot.dto.solicitaçao.SolicitaçaoResponseAdminDTO;
import com.example.springBoot.dto.solicitaçao.SolicitaçaoResponseClienteDTO;
import com.example.springBoot.models.ClienteModel;
import com.example.springBoot.models.PlantaModel;
import com.example.springBoot.models.SolicitaçaoModel;
import com.example.springBoot.repository.ClienteRepository;
import com.example.springBoot.repository.PlantaRepository;
import com.example.springBoot.repository.SolicitaçaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitaçaoService {

    @Autowired
    SolicitaçaoRepository solicitaçaoRepository;
    @Autowired
    PlantaRepository plantaRepository;
    @Autowired
    ClienteRepository clienteRepository;


    public SolicitaçaoModel registraSolicitaçao(SolicitaçaoRequestDTO request){
        SolicitaçaoModel solicitaçaoModel = new SolicitaçaoModel(request);
        return solicitaçaoRepository.save(solicitaçaoModel);
    }

    public Optional<SolicitaçaoModel> getSolicitaçao(UUID id){
        return solicitaçaoRepository.findById(id);
    }

    /**
     * Método que lista todas as solicitaçoes realizadas, pelo ponto de vista do administrador
     * @return
     */
    public List<SolicitaçaoResponseAdminDTO> listaSolicitaçoesAdmin(){
        List<SolicitaçaoModel> models = new ArrayList<>();
        List<SolicitaçaoResponseAdminDTO> listaRetorno = new ArrayList<>();
        models=solicitaçaoRepository.findAll();
        for (SolicitaçaoModel s: models) {
            listaRetorno.add(converteModelEmDTOAdmin(s));
        }
        return listaRetorno;
    }

    /**
     * Método que lista as solicitaçoes realizadas pelo cliente requisitante.
     * @return
     */
    public List<SolicitaçaoResponseClienteDTO> listaSolicitaçoesCliente(UUID id){
        List<SolicitaçaoModel> models = new ArrayList<>();
        List<SolicitaçaoResponseClienteDTO> listaRetorno = new ArrayList<>();
        models = solicitaçaoRepository.findByIdCliente(id);
        for(SolicitaçaoModel s : models){
            listaRetorno.add(converteModelEmDTOCliente(s));
        }
        return listaRetorno;
    }

    public List<SolicitaçaoResponseAdminDTO> filtraSolicitaçoesPorStatus(String status){
        List<SolicitaçaoModel> models = solicitaçaoRepository.findAll();
        List<SolicitaçaoResponseAdminDTO> listaRetorno = new ArrayList<>();
        for(SolicitaçaoModel solicitaçao: models){
            if(solicitaçao.getStatus().equals(status)){
                listaRetorno.add(converteModelEmDTOAdmin(solicitaçao));
            }
        }
        return listaRetorno;
    }
    public SolicitaçaoResponseAdminDTO aprovaSolicitaçao(SolicitaçaoModel solicitaçao) {
        solicitaçao.setStatus("APROVADA");
        solicitaçaoRepository.save(solicitaçao);
        return converteModelEmDTOAdmin(solicitaçao);
    }

    public SolicitaçaoResponseAdminDTO rejeitaSolicitação(SolicitaçaoModel solicitaçao){
        solicitaçao.setStatus("REJEITADA");
        solicitaçaoRepository.save(solicitaçao);
        return converteModelEmDTOAdmin(solicitaçao);
    }

    public PlantaResponseDTO getPlantaMaisSolicitada(){
        UUID idPlanta = solicitaçaoRepository.findByMostRequestedTree();
        PlantaModel planta = plantaRepository.findById(idPlanta).get();
        PlantaResponseDTO plantaResponse = new PlantaResponseDTO(planta.getNomePlanta(),planta.getDescriçaoPlanta(),
                planta.getPorte(),
                planta.getValor(), planta.getImagem());
        return plantaResponse;
    }

    public double getReceitaBruta(){
        List<SolicitaçaoModel> solicitaçoes = solicitaçaoRepository.findAll();
        double total = 0;
        for(SolicitaçaoModel solicitaçao: solicitaçoes){
            if(solicitaçao.getStatus().equals("APROVADA")){
                PlantaModel planta = plantaRepository.findById(solicitaçao.getIdPlanta()).get();
                BigDecimal valorPlanta = planta.getValor();
                double valorDouble = valorPlanta.doubleValue();
                total+= valorDouble;
            }
        }
        return total;
    }

    /**
     * Metodo auxiliar responsavel por receber uma SolicitaçaoModel como parâmetro e produzir uma entidade
     * SolicitaçaoResponseClienteDTO como retorno.
     * @param model
     * @return
     */
    private SolicitaçaoResponseClienteDTO converteModelEmDTOCliente(SolicitaçaoModel model){
        ClienteModel cliente = clienteRepository.findById(model.getIdCliente()).get();
        PlantaModel planta = plantaRepository.findById(model.getIdPlanta()).get();
        SolicitaçaoResponseClienteDTO response = new SolicitaçaoResponseClienteDTO(planta.getNomePlanta()
                                                                                , planta.getDescriçaoPlanta()
                                                                                , planta.getValor()
                                                                                ,cliente.getEndereçoCliente()
                                                                                ,model.getData()
                                                                                ,model.getStatus());
        return response;
    }

    /**
     * Método auxiliar responsável por receber um tipo SolicitaçaoModel e transforma-lo no tipo ResponseDTO,
     * com as informações necessárias para o administrador.
     * @param model
     * @return
     */
    private SolicitaçaoResponseAdminDTO converteModelEmDTOAdmin(SolicitaçaoModel model){
        ClienteModel cliente = clienteRepository.findById(model.getIdCliente()).get();
        PlantaModel planta = plantaRepository.findById(model.getIdPlanta()).get();
        SolicitaçaoResponseAdminDTO response = new SolicitaçaoResponseAdminDTO(planta.getNomePlanta(), cliente.getNomeCliente(), cliente.getEmail(),cliente.getEndereçoCliente(),cliente.getTelefoneCliente(),model.getData(), "PENDENTE");
        return response;
    }



}
