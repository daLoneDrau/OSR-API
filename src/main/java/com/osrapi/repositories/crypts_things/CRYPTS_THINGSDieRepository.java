package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSDieEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSDieRepository
extends CrudRepository<CRYPTS_THINGSDieEntity, Long> {
	/**
	 * Retrieves a list of dies by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSDieEntity}>
	 */
	List<CRYPTS_THINGSDieEntity> findByCode(String code);
	/**
	 * Retrieves a list of dies by their value.
	 * @param value the value
	 * @return {@link List}<{@link CRYPTS_THINGSDieEntity}>
	 */
	List<CRYPTS_THINGSDieEntity> findByValue(Long value);
}
