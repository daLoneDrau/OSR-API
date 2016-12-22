package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPWeaponQualityEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPWeaponQualityRepository
		extends CrudRepository<WFRPWeaponQualityEntity, Long> {
	/**
	 * Retrieves a list of weapon qualitys by their code.
	 * @param code the code
	 * @return {@link List}<{@link WFRPWeaponQualityEntity}>
	 */
	List<WFRPWeaponQualityEntity> findByCode(Long code);
	/**
	 * Retrieves a list of weapon qualitys by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPWeaponQualityEntity}>
	 */
	List<WFRPWeaponQualityEntity> findByName(String name);
}
