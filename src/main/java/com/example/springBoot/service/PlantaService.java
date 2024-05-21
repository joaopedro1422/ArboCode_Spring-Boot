package com.example.springBoot.service;

import com.example.springBoot.dto.planta.PlantaRequestDTO;
import com.example.springBoot.models.PlantaModel;
import com.example.springBoot.repository.PlantaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlantaService {
    @Autowired
    PlantaRepository plantaRepository;

    public PlantaModel registraNovaPlanta(PlantaRequestDTO data){
        PlantaModel plantaModel = new PlantaModel(data);
        BeanUtils.copyProperties(data, plantaModel);
        return plantaRepository.save(plantaModel);
    }
    public List<PlantaModel> getAll(){
        return plantaRepository.findAll();
    }

    public Optional<PlantaModel> getPlanta(UUID id){
        Optional<PlantaModel> plantaRetorno  = plantaRepository.findById(id);
        return plantaRetorno;
    }

    public void deletePlanta(PlantaModel planta){
        plantaRepository.delete(planta);
    }

    public PlantaModel updatePlanta(PlantaModel planta){
        return plantaRepository.save(planta);
    }

    /**
     * Conjunto de métodos para filtragem das plantas a partir do seu porte.
     * @return
     */
    public List<PlantaModel> getPlantasGrandePorte(){
        return plantaRepository.findByPorte("grande");
    }
    public List<PlantaModel> getPlantasMedioPorte(){
        return plantaRepository.findByPorte("medio");
    }
    public List<PlantaModel> getPlantasPequenoPorte(){
        return plantaRepository.findByPorte("pequeno");
    }

    /**
     * Filtragem das plantas que possuem valor menor do que o valor passado como parâmetro.
     * @param valor
     * @return
     */
    public List<PlantaModel> getPlantasValorMenorQue(BigDecimal valor){
        return  plantaRepository.findByValorLessThan(valor);
    }
}
