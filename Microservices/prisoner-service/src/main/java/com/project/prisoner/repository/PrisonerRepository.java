package com.project.prisoner.repository;

import com.project.prisoner.model.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrisonerRepository extends JpaRepository<Prisoner, Long> {

}
