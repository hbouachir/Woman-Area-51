package tn.esprit.spring.womanarea51.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.womanarea51.entities.ImageModel;

public interface ImageModelRepository extends JpaRepository<ImageModel, Long>{
	Optional<ImageModel> findByName(String name);

}
