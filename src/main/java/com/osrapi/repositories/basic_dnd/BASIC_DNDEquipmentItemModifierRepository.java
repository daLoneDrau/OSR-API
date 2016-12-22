package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDEquipmentItemModifierEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDEquipmentItemModifierRepository
extends CrudRepository<BASIC_DNDEquipmentItemModifierEntity, Long> {
	/**
	 * Retrieves a list of equipment item modifiers by their code.
	 * @param code the code
	 * @return {@link List}<{@link BASIC_DNDEquipmentItemModifierEntity}>
	 */
	List<BASIC_DNDEquipmentItemModifierEntity> findByCode(String code);
	/**
	 * Retrieves a list of equipment item modifiers by their percent.
	 * @param percent the percent
	 * @return {@link List}<{@link BASIC_DNDEquipmentItemModifierEntity}>
	 */
	List<BASIC_DNDEquipmentItemModifierEntity> findByPercent(Boolean percent);
	/**
	 * Retrieves a list of equipment item modifiers by their special.
	 * @param special the special
	 * @return {@link List}<{@link BASIC_DNDEquipmentItemModifierEntity}>
	 */
	List<BASIC_DNDEquipmentItemModifierEntity> findBySpecial(Long special);
	/**
	 * Retrieves a list of equipment item modifiers by their value.
	 * @param value the value
	 * @return {@link List}<{@link BASIC_DNDEquipmentItemModifierEntity}>
	 */
	List<BASIC_DNDEquipmentItemModifierEntity> findByValue(Float value);
}
