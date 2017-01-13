package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDDiceEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDDiceRepository
extends CrudRepository<BASIC_DNDDiceEntity, Long> {
	/**
	 * Retrieves a list of dices by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDDiceEntity}>
	 */
	List<BASIC_DNDDiceEntity> findByCode(String code);
	/**
	 * Retrieves a list of dices by their number.
	 * @param number the number
	 * @return {@link List}<{@link BASIC_DNDDiceEntity}>
	 */
	List<BASIC_DNDDiceEntity> findByNumber(Long number);
	/**
	 * Retrieves a list of dices by their plus.
	 * @param plus the plus
	 * @return {@link List}<{@link BASIC_DNDDiceEntity}>
	 */
	List<BASIC_DNDDiceEntity> findByPlus(Long plus);
}
