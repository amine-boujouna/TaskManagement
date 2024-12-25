package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Categorie;
import com.example.taskmanagement.entity.Tache;
import com.example.taskmanagement.serivce.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("*")
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

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Tache>> getTachesByCategorie(@PathVariable Categorie categorie) {
        List<Tache> taches = tacheService.findTachesByCategorie(categorie);
        return ResponseEntity.ok(taches);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Tache>> findTachesByDatedebut(@RequestParam("datedebut") @DateTimeFormat(pattern = "yyyy-MM-dd")Date datedebut) {
        List<Tache> taches = tacheService.findTachesByDatedebut(datedebut);
        return ResponseEntity.ok(taches);
    }

    @GetMapping("/date/before")
    public ResponseEntity<List<Tache>> getTachesBeforeDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Tache> taches = tacheService.findTachesBeforeDate(date);
        return ResponseEntity.ok(taches);
    }
    @GetMapping("/date/after")
    public ResponseEntity<List<Tache>> getTachesAfterDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Tache> taches = tacheService.findByDatedebutAfter(date);
        return ResponseEntity.ok(taches);
    }

    @GetMapping("/date/between")
    public ResponseEntity<List<Tache>> getTachesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Tache> taches = tacheService.findTachesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(taches);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Tache>> getTachesSortedByDate(
            @RequestParam(value = "order", defaultValue = "asc") String order) {
        List<Tache> taches;
        if ("desc".equalsIgnoreCase(order)) {
            taches = tacheService.findAllSortedByDateDebutDescending();
        } else {
            taches = tacheService.findAllSortedByDateDebut();
        }
        return ResponseEntity.ok(taches);
    }
}
