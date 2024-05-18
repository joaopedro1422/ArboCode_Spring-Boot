package com.example.springBoot.repository;

import com.example.springBoot.models.SolicitaçaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SolicitaçaoRepository extends JpaRepository<SolicitaçaoModel, UUID> {
    List<SolicitaçaoModel> findByIdCliente(UUID idCliente);
}
