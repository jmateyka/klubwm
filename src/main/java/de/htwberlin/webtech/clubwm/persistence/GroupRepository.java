package de.htwberlin.webtech.clubwm.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    // Abfrage zum Abrufen einer Gruppe mit ihren zugehörigen Teams
    @Query("SELECT g FROM Groups g LEFT JOIN FETCH g.teams WHERE g.id = :id")
    GroupEntity findGroupWithTeamsById(@Param("id") Long id);

    // Abfrage zum Abrufen aller Gruppen mit ihren zugehörigen Teams
    @Query("SELECT DISTINCT g FROM Groups g LEFT JOIN FETCH g.teams")
    List<GroupEntity> findAllGroupsWithTeams();
}