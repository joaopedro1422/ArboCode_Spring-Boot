package com.example.springBoot.models;

import com.example.springBoot.dto.cliente.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

import static java.time.LocalDate.parse;

@Entity
@Table(name = "TB_CLIENTES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private String telefoneCliente;
    private String endereçoCliente;
    private Date dataDeNascimento;
    private String email;
    private String senha;
    private String role;

    public ClienteModel(ClienteRequestDTO data, String senhaHash){
        this.cpfCliente= data.cpfCliente();
        this.nomeCliente= data.nomeCliente();
        this.telefoneCliente= data.telefoneCliente();
        this.endereçoCliente = data.endereçoCliente();
        this.dataDeNascimento = data.dataNascimento();
        this.email = data.email();
        this.senha = senhaHash;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getEndereçoCliente() {
        return endereçoCliente;
    }

    public void setEndereçoCliente(String endereçoCliente) {
        this.endereçoCliente = endereçoCliente;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
