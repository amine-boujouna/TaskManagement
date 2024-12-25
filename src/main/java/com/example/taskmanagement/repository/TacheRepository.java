package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Categorie;
import com.example.taskmanagement.entity.Tache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findTachesByCategorie(Categorie categorie);
    List<Tache> findTachesByDatedebut(Date datedebut);
    List<Tache> findByDatedebutBefore(Date date);

    List<Tache> findByDatedebutAfter(Date date);

    List<Tache> findByDatedebutBetween(Date startDate, Date endDate);

    List<Tache> findAll(Sort sort);


}
