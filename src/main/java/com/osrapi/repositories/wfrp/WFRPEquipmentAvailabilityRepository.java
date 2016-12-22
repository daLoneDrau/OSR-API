package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPEquipmentAvailabilityEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPEquipmentAvailabilityRepository
		extends CrudRepository<WFRPEquipmentAvailabilityEntity, Long> {
	/**
	 * Retrieves a list of equipment availabilitys by their code.
	 * @param code the code
	 * @return {@link List}<{@link WFRPEquipmentAvailabilityEntity}>
	 */
	List<WFRPEquipmentAvailabilityEntity> findByCode(Long code);
	/**
	 * Retrieves a list of equipment availabilitys by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPEquipmentAvailabilityEntity}>
	 */
	List<WFRPEquipmentAvailabilityEntity> findByName(String name);
}
