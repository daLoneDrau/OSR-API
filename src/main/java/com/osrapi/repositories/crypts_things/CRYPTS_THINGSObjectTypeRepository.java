package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSObjectTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSObjectTypeRepository
extends CrudRepository<CRYPTS_THINGSObjectTypeEntity, Long> {
	/**
	 * Retrieves a list of object types by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSObjectTypeEntity}>
	 */
	List<CRYPTS_THINGSObjectTypeEntity> findByCode(String code);
	/**
	 * Retrieves a list of object types by their flag.
	 * @param flag the flag
	 * @return {@link List}<{@link CRYPTS_THINGSObjectTypeEntity}>
	 */
	List<CRYPTS_THINGSObjectTypeEntity> findByFlag(Long flag);
}
