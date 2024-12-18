package com.example.taskmanagement.serivce;

import com.example.taskmanagement.entity.Tache;
import com.example.taskmanagement.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Tache modifierTache(long id,Tache tache){
       Optional<Tache> optionalTache = tacheRepository.findById(id);
       if(optionalTache.isPresent()){
           Tache existtache = optionalTache.get();
           existtache.setDatedebut(tache.getDatedebut());
           existtache.setDatefin(tache.getDatefin());
           existtache.setDescription(tache.getDescription());
           existtache.setEtat(tache.getEtat());
           existtache.setPriorite(tache.getPriorite());
           existtache.setTitre(tache.getTitre());
           return tacheRepository.save(existtache);
       }else{
           throw new RuntimeException("tache non trouveé avec l'id"+id);
       }
    }

    public List<Tache> findAlltache(){
        return tacheRepository.findAll();
    }
    public Tache findTacheById(Long id){
        Optional<Tache> task = tacheRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tache non trouvé avec id :  " + id));
    }


    public void deleteTaskById(Long id) {
        if (tacheRepository.existsById(id)) {
            tacheRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tache non trouvé avec id : " + id);
        }
    }
}
