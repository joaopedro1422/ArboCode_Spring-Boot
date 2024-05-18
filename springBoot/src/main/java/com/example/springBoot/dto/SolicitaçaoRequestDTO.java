package com.example.springBoot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record SolicitaçaoRequestDTO(@NotNull UUID idCliente, @NotNull UUID idPlanta, @NotNull String status, Date data) {
}
