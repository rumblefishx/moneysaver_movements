package com.rumblesoftware.mv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rumblesoftware.mv.model.RecurrentMovEntity;
import com.rumblesoftware.mv.model.RecurrentMovID;

public interface RecurrentMovementRepository extends JpaRepository<RecurrentMovEntity, RecurrentMovID>{
	
	@Query(value = "select m from TRecurrent_Mov m where m.categoryId = :catId and m.customerId = :custId and m.movementId = :movId and m.activityStatus = 1")
	public Optional<RecurrentMovEntity> findByLogicalIds(
			@Param(value = "catId") Long catId,
			@Param(value = "custId") Long custId,
			@Param(value = "movId") Long movId);
	
	@Query(value = "select m from TRecurrent_Mov m where m.customerId = :custId and m.movementId = :movId and m.activityStatus = 1")
	public Optional<RecurrentMovEntity> findByCustAndMovIds(
			@Param(value = "custId") Long custId,
			@Param(value = "movId") Long movId);
}
