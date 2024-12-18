package com.example.taskmanagement.serivce;

import com.example.taskmanagement.entity.Tache;
import com.example.taskmanagement.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacheService {
    private final TacheRepository tacheRepository;

    @Autowired
    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }
    public Tache ajouterTache(Tache tache) {
        return tacheRepository.save(tache);
    }
}
