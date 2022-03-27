package tn.esprit.spring.womanarea51.services;

import java.util.List;

import tn.esprit.spring.womanarea51.entities.Cv;

public interface ICvService {
       Cv addCv (Cv c);
       Cv UpdateCv (Cv c);
       Cv ShowCv (Long cvId);
       List<Cv> ShowAllCv();
       void deleteCv (Long cvId);
       
}
