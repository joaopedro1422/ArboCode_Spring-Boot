package com.example.springBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entidade de resposta, contendo os dados mais relevantes para a vizualização das solicitações pelos administradores
 * do sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitaçaoResponseAdminDTO {
    private String nomePlanta;
    private String nomeCliente;
    private String usuarioCliente;
    private String endereço;
    private String telefone;
    private Date data;
    private String status;
}
