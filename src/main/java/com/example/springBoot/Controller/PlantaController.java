package com.example.springBoot.Controller;

import com.example.springBoot.dto.PlantaRequestDTO;
import com.example.springBoot.models.PlantaModel;
import com.example.springBoot.service.PlantaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe Controller para manipulação das requisições HTTP destinadas ao banco de dados das plantas
 */
@RestController
@RequestMapping(value = "/plantas",  produces = MediaType.APPLICATION_JSON_VALUE)
public class PlantaController {

    @Autowired
    PlantaService plantaService;

    // CRUD de plantas:
    @PostMapping
    public ResponseEntity<?> registraPlanta(@RequestBody @Valid PlantaRequestDTO data){

        return ResponseEntity.status(HttpStatus.CREATED).body(plantaService.registraNovaPlanta(data));
    }

    @GetMapping
    public ResponseEntity<?> listarPlantasCatalogo(){
        return ResponseEntity.status(HttpStatus.OK).body(plantaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanta(@PathVariable(value = "id")UUID id){
        Optional<PlantaModel> planta = plantaService.getPlanta(id);
        if(planta.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(planta.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlanta(@PathVariable(value = "id") UUID id,
                                          @RequestBody @Valid PlantaRequestDTO plantaRequestDTO){
        Optional<PlantaModel> planta = plantaService.getPlanta(id);
        if(planta.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não encontrada.");
        }
        PlantaModel plantaModel = planta.get();
        BeanUtils.copyProperties(plantaRequestDTO, plantaModel);
        return ResponseEntity.status(HttpStatus.OK).body(plantaService.updatePlanta(plantaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaPlanta(@PathVariable(value = "id") UUID id){
        Optional<PlantaModel> planta = plantaService.getPlanta(id);
        if(planta.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não encontrada.");
        }
        plantaService.deletePlanta(planta.get());
        return ResponseEntity.status((HttpStatus.OK)).body("Planta deletada com sucesso.");
    }

    /**
     * Método que filtra e retorna apenas as plantas que forem de grande porte
     * @return - Lista contendo as plantas grandes
     */
    @GetMapping("/plantas/grandes")
    public ResponseEntity<?> getPlantasGrandePorte(){
        return ResponseEntity.status((HttpStatus.OK)).body(plantaService.getPlantasGrandePorte());
    }

    /**
     * Método que filtra e retorna apenas as plantas que forem de medio porte
     * @return - Lista contendo as plantas medias
     */
    @GetMapping("/plantas/medias")
    public ResponseEntity<?> getPlantasMedioPorte(){
        return ResponseEntity.status(HttpStatus.OK).body(plantaService.getPlantasMedioPorte());
    }

    /**
     * Método que filtra e retorna apenas as plantas que forem de pequeno porte
     * @return - Lista contendo as plantas pequenas
     */
    @GetMapping("/plantas/pequenas")
    public ResponseEntity<?> getPlantasPequenoPorte(){
        return ResponseEntity.status(HttpStatus.OK).body(plantaService.getPlantasPequenoPorte());
    }

    /**
     * Método que filtra as plantas que possuirem valor de mercado menor do que o valor passado na requisição
     * @param valor - valor a ser comparado
     * @return - Lista contendo as plantas filtradas
     */
    @GetMapping("/plantas/filtra/{valor}")
    public ResponseEntity<?> filtraPlantasPValor (@PathVariable(value= "valor")BigDecimal valor){
        return ResponseEntity.status(HttpStatus.OK).body(plantaService.getPlantasValorMenorQue(valor));
    }

}
