package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDGenderEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDGenderRepository
extends CrudRepository<BASIC_DNDGenderEntity, Long> {
	/**
	 * Retrieves a list of genders by their description.
	 * @param description the description
	 * @return {@link List}<{@link BASIC_DNDGenderEntity}>
	 */
	List<BASIC_DNDGenderEntity> findByDescription(String description);
	/**
	 * Retrieves a list of genders by their name.
	 * @param name the name
	 * @return {@link List}<{@link BASIC_DNDGenderEntity}>
	 */
	List<BASIC_DNDGenderEntity> findByName(String name);
}
