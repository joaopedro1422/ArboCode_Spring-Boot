package com.example.springBoot.models;

import com.example.springBoot.dto.SolicitaçaoRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_solicitaçoes")
public class SolicitaçaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idSolicitaçao;
    private UUID idCliente;
    private UUID idPlanta;
    private String status;
    private Date data;

    public SolicitaçaoModel(SolicitaçaoRequestDTO request){
        this.idCliente=request.idCliente();
        this.idPlanta= request.idPlanta();
        this.status = request.status();
        this.data = request.data();
    }

}
