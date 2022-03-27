package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.Enrollement;

import java.util.List;

public interface IEnrollementService {
    Enrollement addEnrollement(Enrollement e);
    Enrollement ShowEnrollement(long id);
    Enrollement UpdateEnrollement(Enrollement u);
    Enrollement seachUserValidEnrollement(long id);
    void DeleteEnrollement(long id);
    List<Enrollement> ShowAllEnrollement();
}
