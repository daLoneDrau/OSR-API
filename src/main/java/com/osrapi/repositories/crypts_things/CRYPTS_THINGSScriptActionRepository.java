package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSScriptActionEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSScriptActionRepository
extends CrudRepository<CRYPTS_THINGSScriptActionEntity, Long> {
	/**
	 * Retrieves a list of script actions by their name.
	 * @param name the name
	 * @return {@link List}<{@link CRYPTS_THINGSScriptActionEntity}>
	 */
	List<CRYPTS_THINGSScriptActionEntity> findByName(String name);
	/**
	 * Retrieves a list of script actions by their type.
	 * @param type the type
	 * @return {@link List}<{@link CRYPTS_THINGSScriptActionEntity}>
	 */
	List<CRYPTS_THINGSScriptActionEntity> findByType(String type);
}
