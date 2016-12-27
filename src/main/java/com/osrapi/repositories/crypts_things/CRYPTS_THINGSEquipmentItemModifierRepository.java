package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSEquipmentItemModifierEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSEquipmentItemModifierRepository
extends CrudRepository<CRYPTS_THINGSEquipmentItemModifierEntity, Long> {
	/**
	 * Retrieves a list of equipment item modifiers by their code.
	 * @param code the code
	 * @return {@link List}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>
	 */
	List<CRYPTS_THINGSEquipmentItemModifierEntity> findByCode(String code);
	/**
	 * Retrieves a list of equipment item modifiers by their percent.
	 * @param percent the percent
	 * @return {@link List}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>
	 */
	List<CRYPTS_THINGSEquipmentItemModifierEntity> findByPercent(Boolean percent);
	/**
	 * Retrieves a list of equipment item modifiers by their special.
	 * @param special the special
	 * @return {@link List}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>
	 */
	List<CRYPTS_THINGSEquipmentItemModifierEntity> findBySpecial(Long special);
	/**
	 * Retrieves a list of equipment item modifiers by their value.
	 * @param value the value
	 * @return {@link List}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>
	 */
	List<CRYPTS_THINGSEquipmentItemModifierEntity> findByValue(Float value);
}
