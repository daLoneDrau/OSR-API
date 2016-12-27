package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDGroupEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDGroupRepository
extends CrudRepository<BASIC_DNDGroupEntity, Long> {
	/**
	 * Retrieves a list of groups by their name.
	 * @param name the name
	 * @return {@link List}<{@link BASIC_DNDGroupEntity}>
	 */
	List<BASIC_DNDGroupEntity> findByName(String name);
}
