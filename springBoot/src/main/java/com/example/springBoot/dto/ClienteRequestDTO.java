package com.example.springBoot.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record ClienteRequestDTO(@NotBlank String nomeCliente, @NotBlank String cpfCliente, @NotBlank String telefoneCliente
    , @NotBlank String endereçoCliente,Date dataNascimento, @NotBlank String usuario, @NotBlank String senha) {
}
