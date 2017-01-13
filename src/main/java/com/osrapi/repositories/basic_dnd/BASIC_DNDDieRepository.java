package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDDieEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDDieRepository
extends CrudRepository<BASIC_DNDDieEntity, Long> {
	/**
	 * Retrieves a list of dies by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDDieEntity}>
	 */
	List<BASIC_DNDDieEntity> findByCode(String code);
	/**
	 * Retrieves a list of dies by their value.
	 * @param value the value
	 * @return {@link List}<{@link BASIC_DNDDieEntity}>
	 */
	List<BASIC_DNDDieEntity> findByValue(Long value);
}
