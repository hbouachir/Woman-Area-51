package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Attachment;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment,Long> {

}
