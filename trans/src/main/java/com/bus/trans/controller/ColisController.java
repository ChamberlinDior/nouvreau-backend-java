// ColisController.java
package com.bus.trans.controller;


import com.bus.trans.dto.ColisDTO;
import com.bus.trans.service.ColisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colis")
public class ColisController {
    private final ColisService colisService;

    public ColisController(ColisService colisService) {
        this.colisService = colisService;
    }

    @PostMapping
    public ColisDTO createColis(@RequestBody ColisDTO colisDTO) {
        return colisService.createColis(colisDTO);
    }

    @PutMapping("/{id}")
    public ColisDTO updateColis(@PathVariable Long id, @RequestBody ColisDTO colisDTO) {
        return colisService.updateColis(id, colisDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteColis(@PathVariable Long id) {
        colisService.deleteColis(id);
    }

    @GetMapping("/{id}")
    public ColisDTO getColisById(@PathVariable Long id) {
        return colisService.getColisById(id);
    }

    @GetMapping
    public List<ColisDTO> getAllColis() {
        return colisService.getAllColis();
    }
}
