package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDAbilityEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDAbilityRepository
		extends CrudRepository<LABLORDAbilityEntity, Long> {
	/**
	 * Retrieves a list of abilitys by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDAbilityEntity}>
	 */
	List<LABLORDAbilityEntity> findByCode(String code);
	/**
	 * Retrieves a list of abilitys by their description.
	 * @param description the description
	 * @return {@link List}<{@link LABLORDAbilityEntity}>
	 */
	List<LABLORDAbilityEntity> findByDescription(String description);
	/**
	 * Retrieves a list of abilitys by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDAbilityEntity}>
	 */
	List<LABLORDAbilityEntity> findByName(String name);
}
