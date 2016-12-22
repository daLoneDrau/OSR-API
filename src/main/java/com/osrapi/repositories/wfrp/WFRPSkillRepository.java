package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPSkillEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPSkillRepository
		extends CrudRepository<WFRPSkillEntity, Long> {
	/**
	 * Retrieves a list of skills by their description.
	 * @param description the description
	 * @return {@link List}<{@link WFRPSkillEntity}>
	 */
	List<WFRPSkillEntity> findByDescription(String description);
	/**
	 * Retrieves a list of skills by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPSkillEntity}>
	 */
	List<WFRPSkillEntity> findByName(String name);
}
