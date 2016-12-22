package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDArmorTypeEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDArmorTypeRepository
		extends CrudRepository<LABLORDArmorTypeEntity, Long> {
	/**
	 * Retrieves a list of armor types by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDArmorTypeEntity}>
	 */
	List<LABLORDArmorTypeEntity> findByCode(Long code);
	/**
	 * Retrieves a list of armor types by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDArmorTypeEntity}>
	 */
	List<LABLORDArmorTypeEntity> findByName(String name);
}
