package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSEquipmentElementTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSEquipmentElementTypeRepository
extends CrudRepository<CRYPTS_THINGSEquipmentElementTypeEntity, Long> {
	/**
	 * Retrieves a list of equipment element types by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>
	 */
	List<CRYPTS_THINGSEquipmentElementTypeEntity> findByCode(String code);
	/**
	 * Retrieves a list of equipment element types by their value.
	 * @param value the value
	 * @return {@link List}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>
	 */
	List<CRYPTS_THINGSEquipmentElementTypeEntity> findByValue(Long value);
}
