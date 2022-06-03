package tn.esprit.spring.womanarea51.services;

import tn.esprit.spring.womanarea51.entities.PublicComplaint;

import java.util.List;

public interface PublicComplaintService {
    void addPublicComplaint(PublicComplaint publicComplaint);
    List<PublicComplaint> getPublicComplaint();
}
