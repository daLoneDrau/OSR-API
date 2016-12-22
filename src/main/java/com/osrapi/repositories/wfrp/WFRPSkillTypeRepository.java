package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPSkillTypeEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPSkillTypeRepository
		extends CrudRepository<WFRPSkillTypeEntity, Long> {
	/**
	 * Retrieves a list of skill types by their code.
	 * @param code the code
	 * @return {@link List}<{@link WFRPSkillTypeEntity}>
	 */
	List<WFRPSkillTypeEntity> findByCode(Long code);
	/**
	 * Retrieves a list of skill types by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPSkillTypeEntity}>
	 */
	List<WFRPSkillTypeEntity> findByName(String name);
}
