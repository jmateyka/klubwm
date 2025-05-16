package de.htwberlin.webtech.clubwm.service;

import de.htwberlin.webtech.clubwm.persistence.StadiumEntity;
import de.htwberlin.webtech.clubwm.persistence.StadiumRepository;
import de.htwberlin.webtech.clubwm.web.api.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll().stream()
                .map(this::convertEntityToApi)
                .collect(Collectors.toList());
    }

    private Stadium convertEntityToApi(StadiumEntity entity) {
        return new Stadium(entity.getId(), entity.getName(), entity.getLocation(), entity.getCapacity());
    }
}