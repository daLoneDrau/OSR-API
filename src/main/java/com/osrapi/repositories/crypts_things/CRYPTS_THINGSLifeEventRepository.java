package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSLifeEventEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSLifeEventRepository
extends CrudRepository<CRYPTS_THINGSLifeEventEntity, Long> {
	/**
	 * Retrieves a list of life events by their description.
	 * @param description the description
	 * @return {@link List}<{@link CRYPTS_THINGSLifeEventEntity}>
	 */
	List<CRYPTS_THINGSLifeEventEntity> findByDescription(String description);
	/**
	 * Retrieves a list of life events by their name.
	 * @param name the name
	 * @return {@link List}<{@link CRYPTS_THINGSLifeEventEntity}>
	 */
	List<CRYPTS_THINGSLifeEventEntity> findByName(String name);
}
