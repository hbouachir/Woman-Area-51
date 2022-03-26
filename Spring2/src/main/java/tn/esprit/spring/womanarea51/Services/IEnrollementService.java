package tn.esprit.spring.womanarea51.Services;

import tn.esprit.spring.womanarea51.Entities.Enrollement;

import java.util.List;

public interface IEnrollementService {
    Enrollement addEnrollement(Enrollement e);
    Enrollement ShowEnrollement(long id);
    Enrollement UpdateEnrollement(Enrollement u);
    Enrollement seachUserValidEnrollement(long id);
    void DeleteEnrollement(long id);
    List<Enrollement> ShowAllEnrollement();
}
