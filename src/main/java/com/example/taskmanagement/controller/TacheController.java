package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Tache;
import com.example.taskmanagement.serivce.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTaskById(@PathVariable Long id) {
        try {
            Tache task = tacheService.findTacheById(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Tache>> getAllTasks() {
        List<Tache> tasks = tacheService.findAlltache();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        try {
            tacheService.deleteTaskById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
