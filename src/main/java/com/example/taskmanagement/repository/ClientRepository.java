package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE " +
            "(LOWER(c.nom) LIKE LOWER(CONCAT('%', :param, '%'))) OR " +
            "(LOWER(c.prenom) LIKE LOWER(CONCAT('%', :param, '%'))) OR " +
            "(LOWER(c.email) LIKE LOWER(CONCAT('%', :param, '%'))) OR " +
            "(c.telephone = :param) OR " +
            "(LOWER(c.adress) LIKE LOWER(CONCAT('%', :param, '%'))) OR " +
            "(LOWER(c.city) LIKE LOWER(CONCAT('%', :param, '%'))) OR " +
            "(FUNCTION('DATE_FORMAT', c.datenaissance, '%Y-%m-%d') = :param)")
    List<Client> findByAnyParameter(@Param("param") String param);

    Page<Client> findAll(Pageable pageable);


}
