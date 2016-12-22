package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDEquipmentTypeEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDEquipmentTypeRepository
		extends CrudRepository<LABLORDEquipmentTypeEntity, Long> {
	/**
	 * Retrieves a list of equipment types by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDEquipmentTypeEntity}>
	 */
	List<LABLORDEquipmentTypeEntity> findByCode(Long code);
	/**
	 * Retrieves a list of equipment types by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDEquipmentTypeEntity}>
	 */
	List<LABLORDEquipmentTypeEntity> findByName(String name);
}
