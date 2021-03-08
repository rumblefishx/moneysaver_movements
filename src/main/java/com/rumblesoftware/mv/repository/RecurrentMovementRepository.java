package com.rumblesoftware.mv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumblesoftware.mv.model.RecurrentMovEntity;
import com.rumblesoftware.mv.model.RecurrentMovID;

public interface RecurrentMovementRepository extends JpaRepository<RecurrentMovEntity, RecurrentMovID>{

}
