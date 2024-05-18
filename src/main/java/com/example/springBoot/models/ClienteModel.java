package com.example.springBoot.models;

import com.example.springBoot.dto.ClienteRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private String usuario;
    private String senha;

    public ClienteModel(ClienteRequestDTO data){
        this.cpfCliente= data.cpfCliente();
        this.nomeCliente= data.nomeCliente();
        this.telefoneCliente= data.telefoneCliente();
        this.endereçoCliente = data.endereçoCliente();
        this.dataDeNascimento = data.dataNascimento();
        this.usuario = data.usuario();
        this.senha = data.senha();
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
