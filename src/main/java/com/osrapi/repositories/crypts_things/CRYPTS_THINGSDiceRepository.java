package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSDiceEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSDiceRepository
extends CrudRepository<CRYPTS_THINGSDiceEntity, Long> {
	/**
	 * Retrieves a list of dices by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSDiceEntity}>
	 */
	List<CRYPTS_THINGSDiceEntity> findByCode(String code);
	/**
	 * Retrieves a list of dices by their number.
	 * @param number the number
	 * @return {@link List}<{@link CRYPTS_THINGSDiceEntity}>
	 */
	List<CRYPTS_THINGSDiceEntity> findByNumber(Long number);
}
