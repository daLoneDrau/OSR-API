package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSEventEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSEventRepository
extends CrudRepository<CRYPTS_THINGSEventEntity, Long> {
	/**
	 * Retrieves a list of events by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSEventEntity}>
	 */
	List<CRYPTS_THINGSEventEntity> findByCode(String code);
}
