package com.rumblesoftware.mv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumblesoftware.mv.model.MovementEntity;
import com.rumblesoftware.mv.model.MovementID;

@Repository
public interface MovementsRepository extends JpaRepository<MovementEntity, MovementID>{


}
