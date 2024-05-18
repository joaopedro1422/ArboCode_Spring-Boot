package com.example.springBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidade de resposta, contendo os dados mais relevantes das solicitações realizadas por determinado cliente.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitaçaoResponseClienteDTO {
    private String nomePlanta;
    private String descriçaoPlanta;
    private BigDecimal valor;
    private String endereço;
    private Date data;
    private String status;
}
