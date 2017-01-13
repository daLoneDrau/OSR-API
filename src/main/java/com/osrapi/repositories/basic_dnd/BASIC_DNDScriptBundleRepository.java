package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDScriptBundleEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDScriptBundleRepository
extends CrudRepository<BASIC_DNDScriptBundleEntity, Long> {
	/**
	 * Retrieves a list of script bundles by their name.
	 * @param name the name
	 * @return {@link List}<{@link BASIC_DNDScriptBundleEntity}>
	 */
	List<BASIC_DNDScriptBundleEntity> findByName(String name);
}
