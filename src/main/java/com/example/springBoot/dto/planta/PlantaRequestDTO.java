package com.example.springBoot.dto.planta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PlantaRequestDTO(@NotBlank String nomePlanta,
         @NotBlank String descriçaoPlanta,
          @NotBlank String porte,
          @NotBlank String imagem,
          @NotNull BigDecimal valor) {
}
