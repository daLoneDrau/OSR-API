package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSAttributeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSAttributeRepository
extends CrudRepository<CRYPTS_THINGSAttributeEntity, Long> {
	/**
	 * Retrieves a list of attributes by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSAttributeEntity}>
	 */
	List<CRYPTS_THINGSAttributeEntity> findByCode(String code);
	/**
	 * Retrieves a list of attributes by their description.
	 * @param description the description
	 * @return {@link List}<{@link CRYPTS_THINGSAttributeEntity}>
	 */
	List<CRYPTS_THINGSAttributeEntity> findByDescription(String description);
	/**
	 * Retrieves a list of attributes by their name.
	 * @param name the name
	 * @return {@link List}<{@link CRYPTS_THINGSAttributeEntity}>
	 */
	List<CRYPTS_THINGSAttributeEntity> findByName(String name);
}
