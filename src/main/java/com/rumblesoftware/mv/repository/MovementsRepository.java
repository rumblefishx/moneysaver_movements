package com.rumblesoftware.mv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumblesoftware.mv.model.MovementEntity;
import com.rumblesoftware.mv.model.MovementID;

@Repository
public interface MovementsRepository extends JpaRepository<MovementEntity, MovementID>{

	@Query(value = "select m from TMovements m where m.categoryId = :catId and m.customerId = :custId and m.movementId = :movId")
	public Optional<MovementEntity> findEntityByLogicalIds(
			@Param(value = "catId") Long catId,
			@Param(value = "custId") Long custId,
			@Param(value = "movId") Long movId);
	
	@Query(value = "select m from TMovements m where m.customerId = :custId and m.movementId = :movId")
	public Optional<MovementEntity> findEntityByCustAndMovId(
			@Param(value = "custId") Long custId,
			@Param(value = "movId") Long recurrentMovId);
}
