package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSScriptActionTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSScriptActionTypeRepository
extends CrudRepository<CRYPTS_THINGSScriptActionTypeEntity, Long> {
	/**
	 * Retrieves a list of script action types by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSScriptActionTypeEntity}>
	 */
	List<CRYPTS_THINGSScriptActionTypeEntity> findByCode(String code);
}
