package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDScriptActionTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDScriptActionTypeRepository
extends CrudRepository<BASIC_DNDScriptActionTypeEntity, Long> {
	/**
	 * Retrieves a list of script action types by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDScriptActionTypeEntity}>
	 */
	List<BASIC_DNDScriptActionTypeEntity> findByCode(String code);
}
