package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDEventEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDEventRepository
extends CrudRepository<BASIC_DNDEventEntity, Long> {
	/**
	 * Retrieves a list of events by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDEventEntity}>
	 */
	List<BASIC_DNDEventEntity> findByCode(String code);
}
