package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPRaceEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPRaceRepository
		extends CrudRepository<WFRPRaceEntity, Long> {
	/**
	 * Retrieves a list of races by their background.
	 * @param background the background
	 * @return {@link List}<{@link WFRPRaceEntity}>
	 */
	List<WFRPRaceEntity> findByBackground(String background);
	/**
	 * Retrieves a list of races by their code.
	 * @param code the code
	 * @return {@link List}<{@link WFRPRaceEntity}>
	 */
	List<WFRPRaceEntity> findByCode(Long code);
	/**
	 * Retrieves a list of races by their description.
	 * @param description the description
	 * @return {@link List}<{@link WFRPRaceEntity}>
	 */
	List<WFRPRaceEntity> findByDescription(String description);
	/**
	 * Retrieves a list of races by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPRaceEntity}>
	 */
	List<WFRPRaceEntity> findByName(String name);
}
