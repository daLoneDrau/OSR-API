package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDScriptActionEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDScriptActionRepository
extends CrudRepository<BASIC_DNDScriptActionEntity, Long> {
	/**
	 * Retrieves a list of script actions by their name.
	 * @param name the name
	 * @return {@link List}<{@link BASIC_DNDScriptActionEntity}>
	 */
	List<BASIC_DNDScriptActionEntity> findByName(String name);
	/**
	 * Retrieves a list of script actions by their type.
	 * @param type the type
	 * @return {@link List}<{@link BASIC_DNDScriptActionEntity}>
	 */
	List<BASIC_DNDScriptActionEntity> findByType(String type);
}
