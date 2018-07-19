package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.Position;
import com.webdev.employeesofthecompany.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Position save(Position position){
        positionRepository.save(position);
        return position;
    }

    public Position getById(long id){
        return positionRepository.findById(id).get();
    }

    public List<Position> getAll(){
        return positionRepository.findAll();
    }

    public void delete(Position position){
        positionRepository.delete(position);
    }

    public boolean exists(long positionId) {
        return positionRepository.existsById(positionId);
    }

    public void update(Position position){
        positionRepository.save(position);
    }
}
