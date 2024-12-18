package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Tache;
import com.example.taskmanagement.serivce.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TacheController {
    private final TacheService tacheService;

    @Autowired
    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @PostMapping()
    public ResponseEntity<Tache> ajouterTache(@RequestBody Tache tache) {
        Tache nouvelleTache = tacheService.ajouterTache(tache);
        return new ResponseEntity<>(nouvelleTache, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tache> modifierTache(@RequestBody Tache tache, @PathVariable("id") Long id) {
        try {
            Tache tache1 = tacheService.modifierTache(id, tache);
            return ResponseEntity.ok(tache1);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
