package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSGenderEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSGenderRepository
extends CrudRepository<CRYPTS_THINGSGenderEntity, Long> {
	/**
	 * Retrieves a list of genders by their description.
	 * @param description the description
	 * @return {@link List}<{@link CRYPTS_THINGSGenderEntity}>
	 */
	List<CRYPTS_THINGSGenderEntity> findByDescription(String description);
	/**
	 * Retrieves a list of genders by their name.
	 * @param name the name
	 * @return {@link List}<{@link CRYPTS_THINGSGenderEntity}>
	 */
	List<CRYPTS_THINGSGenderEntity> findByName(String name);
}
