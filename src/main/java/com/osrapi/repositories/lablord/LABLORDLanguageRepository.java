package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDLanguageEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDLanguageRepository
		extends CrudRepository<LABLORDLanguageEntity, Long> {
	/**
	 * Retrieves a list of languages by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDLanguageEntity}>
	 */
	List<LABLORDLanguageEntity> findByCode(Long code);
	/**
	 * Retrieves a list of languages by their description.
	 * @param description the description
	 * @return {@link List}<{@link LABLORDLanguageEntity}>
	 */
	List<LABLORDLanguageEntity> findByDescription(String description);
	/**
	 * Retrieves a list of languages by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDLanguageEntity}>
	 */
	List<LABLORDLanguageEntity> findByName(String name);
}
