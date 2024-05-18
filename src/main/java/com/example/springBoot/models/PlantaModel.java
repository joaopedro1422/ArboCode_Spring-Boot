package com.example.springBoot.models;

import com.example.springBoot.dto.PlantaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "TB_PLANTAS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantaModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPlanta;
    private String nomePlanta;
    private String descriçaoPlanta;
    private String porte;
    private BigDecimal valor;

    public PlantaModel(PlantaRequestDTO data) {
        this.nomePlanta = data.nomePlanta();
        this.descriçaoPlanta = data.descriçaoPlanta();
        this.valor = data.valor();
        this.porte = data.porte();
    }

    public UUID getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(UUID idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNomePlanta() {
        return nomePlanta;
    }

    public void setNomePlanta(String nomePlanta) {
        this.nomePlanta = nomePlanta;
    }

    public String getDescriçaoPlanta() {
        return descriçaoPlanta;
    }

    public void setDescriçaoPlanta(String descriçaoPlanta) {
        this.descriçaoPlanta = descriçaoPlanta;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
