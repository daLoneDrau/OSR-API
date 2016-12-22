package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDDamageEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDDamageRepository
		extends CrudRepository<LABLORDDamageEntity, Long> {
	/**
	 * Retrieves a list of damages by their number.
	 * @param number the number
	 * @return {@link List}<{@link LABLORDDamageEntity}>
	 */
	List<LABLORDDamageEntity> findByNumber(Long number);
}
