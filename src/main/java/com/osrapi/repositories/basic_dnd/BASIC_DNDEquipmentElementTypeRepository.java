package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDEquipmentElementTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDEquipmentElementTypeRepository
extends CrudRepository<BASIC_DNDEquipmentElementTypeEntity, Long> {
	/**
	 * Retrieves a list of equipment element types by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDEquipmentElementTypeEntity}>
	 */
	List<BASIC_DNDEquipmentElementTypeEntity> findByCode(String code);
	/**
	 * Retrieves a list of equipment element types by their value.
	 * @param value the value
	 * @return {@link List}<{@link BASIC_DNDEquipmentElementTypeEntity}>
	 */
	List<BASIC_DNDEquipmentElementTypeEntity> findByValue(Long value);
}
