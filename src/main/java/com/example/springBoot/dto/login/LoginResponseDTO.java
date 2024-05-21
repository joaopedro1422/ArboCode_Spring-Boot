package com.example.springBoot.dto.login;

import java.util.UUID;

public record LoginResponseDTO(UUID id, String token) {
}
