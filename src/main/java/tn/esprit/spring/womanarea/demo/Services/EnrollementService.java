package tn.esprit.spring.womanarea.demo.Services;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.womanarea.demo.Entities.Enrollement;
import tn.esprit.spring.womanarea.demo.Repositories.EnrollementRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class EnrollementService implements IEnrollementService{

    @Autowired
    EnrollementRepository enrollementRepository;
    @Override
    public Enrollement addEnrollement(Enrollement e) {
        return enrollementRepository.save(e);
    }

    @Override
    public Enrollement ShowEnrollement(long id) {
        return enrollementRepository.findById(id).orElse(null);
    }

    @Override
    public Enrollement UpdateEnrollement(Enrollement u) {
        return enrollementRepository.save(u);
    }

    @Override
    public void DeleteEnrollement(long id) {
        enrollementRepository.deleteById(id);

    }

    @Override
    public List<Enrollement> ShowAllEnrollement() {
        return enrollementRepository.findAll();
    }
    @Override
    public Enrollement seachUserValidEnrollement(long id){

        return enrollementRepository.getEnrollementValidByUser(id);
    }
}
