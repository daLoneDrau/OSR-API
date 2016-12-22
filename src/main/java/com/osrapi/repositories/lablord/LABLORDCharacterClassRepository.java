package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDCharacterClassEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDCharacterClassRepository
		extends CrudRepository<LABLORDCharacterClassEntity, Long> {
	/**
	 * Retrieves a list of character classs by their description.
	 * @param description the description
	 * @return {@link List}<{@link LABLORDCharacterClassEntity}>
	 */
	List<LABLORDCharacterClassEntity> findByDescription(String description);
	/**
	 * Retrieves a list of character classs by their maxLevel.
	 * @param maxLevel the maxLevel
	 * @return {@link List}<{@link LABLORDCharacterClassEntity}>
	 */
	List<LABLORDCharacterClassEntity> findByMaxLevel(Long maxLevel);
	/**
	 * Retrieves a list of character classs by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDCharacterClassEntity}>
	 */
	List<LABLORDCharacterClassEntity> findByName(String name);
}
