package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDAttributeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDAttributeRepository
extends CrudRepository<BASIC_DNDAttributeEntity, Long> {
	/**
	 * Retrieves a list of attributes by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDAttributeEntity}>
	 */
	List<BASIC_DNDAttributeEntity> findByCode(String code);
	/**
	 * Retrieves a list of attributes by their description.
	 * @param description the description
	 * @return {@link List}<{@link BASIC_DNDAttributeEntity}>
	 */
	List<BASIC_DNDAttributeEntity> findByDescription(String description);
	/**
	 * Retrieves a list of attributes by their name.
	 * @param name the name
	 * @return {@link List}<{@link BASIC_DNDAttributeEntity}>
	 */
	List<BASIC_DNDAttributeEntity> findByName(String name);
}
