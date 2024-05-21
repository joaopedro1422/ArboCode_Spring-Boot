package com.example.springBoot.Controller;

import com.example.springBoot.dto.cliente.ClienteRequestDTO;
import com.example.springBoot.dto.login.LoginRequestDTO;
import com.example.springBoot.dto.login.LoginResponseDTO;
import com.example.springBoot.infra.security.TokenService;
import com.example.springBoot.models.ClienteModel;
import com.example.springBoot.repository.ClienteRepository;
import com.example.springBoot.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Classe controller para a manipulação dos endpoints dotados de autenticação pelo Spring security (Login e Registro)
 * dos clientes no sistema.
 */
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    @Autowired
    ClienteService clienteService;

    /**
     * Endpoint para a autenticação do login do cliente.
     * @param body - recebe um requestDTO contendo o email e a senha do cliente para verificação.
     * @return - Caso os dados estejam corretos, retorna um token de acesso JWT e o Id do cliente logado no sistema.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body){
        ClienteModel cliente= clienteRepository.findByEmail(body.email()).orElseThrow(()-> new RuntimeException("Cliente Not found."));
        if(passwordEncoder.matches(body.senha(),cliente.getSenha())){
            String token = this.tokenService.generateToken(cliente);
            return ResponseEntity.ok(new LoginResponseDTO(cliente.getIdCliente(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Endpoint para o registro de um novo cliente no sistema.
     * @param data - Recebe um RequestDTO contendo os dados do ClienteModel e verifica se o cliente pode ser registrado
     * @return - Caso seu usuário ainda não exista, o cliente é registrado com sucesso e é retornado seu ID e seu Token JWT de acesso
     * - Caso o usuário escolhido já esteja em uso, retorna NULL.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClienteRequestDTO data){
        Optional<ClienteModel> clienteOptional= clienteRepository.findByEmail(data.email());
        if(clienteOptional.isEmpty()){
            ClienteModel cliente = new ClienteModel();
            cliente = clienteService.registra(data,passwordEncoder.encode(data.senha()));
            String token = this.tokenService.generateToken(cliente);
            return ResponseEntity.ok(new LoginResponseDTO(cliente.getIdCliente(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
