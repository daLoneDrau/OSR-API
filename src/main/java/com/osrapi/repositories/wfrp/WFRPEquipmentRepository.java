package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPEquipmentEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPEquipmentRepository
		extends CrudRepository<WFRPEquipmentEntity, Long> {
	/**
	 * Retrieves a list of equipments by their costGc.
	 * @param costGc the costGc
	 * @return {@link List}<{@link WFRPEquipmentEntity}>
	 */
	List<WFRPEquipmentEntity> findByCostGc(Long costGc);
	/**
	 * Retrieves a list of equipments by their costP.
	 * @param costP the costP
	 * @return {@link List}<{@link WFRPEquipmentEntity}>
	 */
	List<WFRPEquipmentEntity> findByCostP(Long costP);
	/**
	 * Retrieves a list of equipments by their costS.
	 * @param costS the costS
	 * @return {@link List}<{@link WFRPEquipmentEntity}>
	 */
	List<WFRPEquipmentEntity> findByCostS(Long costS);
	/**
	 * Retrieves a list of equipments by their description.
	 * @param description the description
	 * @return {@link List}<{@link WFRPEquipmentEntity}>
	 */
	List<WFRPEquipmentEntity> findByDescription(String description);
	/**
	 * Retrieves a list of equipments by their encumbrance.
	 * @param encumbrance the encumbrance
	 * @return {@link List}<{@link WFRPEquipmentEntity}>
	 */
	List<WFRPEquipmentEntity> findByEncumbrance(Long encumbrance);
	/**
	 * Retrieves a list of equipments by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPEquipmentEntity}>
	 */
	List<WFRPEquipmentEntity> findByName(String name);
}
