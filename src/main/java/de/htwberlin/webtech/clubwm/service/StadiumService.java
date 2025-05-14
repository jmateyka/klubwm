package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.StadiumRepository;
import de.htwberlin.webtech.clubwm.web.api.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }

    public Stadium saveStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }
}