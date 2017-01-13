package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDObjectTypeEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDObjectTypeRepository
extends CrudRepository<BASIC_DNDObjectTypeEntity, Long> {
	/**
	 * Retrieves a list of object types by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDObjectTypeEntity}>
	 */
	List<BASIC_DNDObjectTypeEntity> findByCode(String code);
	/**
	 * Retrieves a list of object types by their flag.
	 * @param flag the flag
	 * @return {@link List}<{@link BASIC_DNDObjectTypeEntity}>
	 */
	List<BASIC_DNDObjectTypeEntity> findByFlag(Long flag);
}
