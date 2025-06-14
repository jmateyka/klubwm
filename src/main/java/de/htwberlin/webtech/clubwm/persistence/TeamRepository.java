package de.htwberlin.webtech.clubwm.persistence;


import de.htwberlin.webtech.clubwm.web.api.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}

