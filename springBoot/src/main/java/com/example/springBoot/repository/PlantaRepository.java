package com.example.springBoot.repository;

import com.example.springBoot.models.PlantaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface PlantaRepository extends JpaRepository<PlantaModel, UUID> {
    List<PlantaModel> findByPorte(String porte);
    List<PlantaModel> findByValorLessThan(BigDecimal value);
}
