package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSSkillEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSSkillRepository
extends CrudRepository<CRYPTS_THINGSSkillEntity, Long> {
	/**
	 * Retrieves a list of skills by their description.
	 * @param description the description
	 * @return {@link List}<{@link CRYPTS_THINGSSkillEntity}>
	 */
	List<CRYPTS_THINGSSkillEntity> findByDescription(String description);
	/**
	 * Retrieves a list of skills by their name.
	 * @param name the name
	 * @return {@link List}<{@link CRYPTS_THINGSSkillEntity}>
	 */
	List<CRYPTS_THINGSSkillEntity> findByName(String name);
}
