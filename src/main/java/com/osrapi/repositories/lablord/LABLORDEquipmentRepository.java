package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDEquipmentEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDEquipmentRepository
		extends CrudRepository<LABLORDEquipmentEntity, Long> {
	/**
	 * Retrieves a list of equipments by their description.
	 * @param description the description
	 * @return {@link List}<{@link LABLORDEquipmentEntity}>
	 */
	List<LABLORDEquipmentEntity> findByDescription(String description);
	/**
	 * Retrieves a list of equipments by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDEquipmentEntity}>
	 */
	List<LABLORDEquipmentEntity> findByName(String name);
	/**
	 * Retrieves a list of equipments by their weight.
	 * @param weight the weight
	 * @return {@link List}<{@link LABLORDEquipmentEntity}>
	 */
	List<LABLORDEquipmentEntity> findByWeight(Float weight);
}
