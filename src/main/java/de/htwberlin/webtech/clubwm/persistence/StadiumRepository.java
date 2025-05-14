package de.htwberlin.webtech.clubwm.persistence;

import de.htwberlin.webtech.clubwm.web.api.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}