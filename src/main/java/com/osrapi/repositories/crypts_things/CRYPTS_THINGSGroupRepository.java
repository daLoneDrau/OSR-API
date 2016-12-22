package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSGroupEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSGroupRepository
extends CrudRepository<CRYPTS_THINGSGroupEntity, Long> {
	/**
	 * Retrieves a list of groups by their name.
	 * @param name the name
	 * @return {@link List}<{@link CRYPTS_THINGSGroupEntity}>
	 */
	List<CRYPTS_THINGSGroupEntity> findByName(String name);
}
