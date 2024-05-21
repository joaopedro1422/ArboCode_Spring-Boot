package com.example.springBoot.dto.cliente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteResponseDTO {
    private String nomeCliente;
    private String cpfCliente;
    private String telefoneCliente;
    private String endereçoCliente;
    private Date dataDeNascimento;
    private String email;

}
