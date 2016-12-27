package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSScriptBundleEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSScriptBundleRepository
extends CrudRepository<CRYPTS_THINGSScriptBundleEntity, Long> {
	/**
	 * Retrieves a list of script bundles by their name.
	 * @param name the name
	 * @return {@link List}<{@link CRYPTS_THINGSScriptBundleEntity}>
	 */
	List<CRYPTS_THINGSScriptBundleEntity> findByName(String name);
}
