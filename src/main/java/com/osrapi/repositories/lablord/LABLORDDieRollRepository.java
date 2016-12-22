package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDDieRollEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDDieRollRepository
		extends CrudRepository<LABLORDDieRollEntity, Long> {
	/**
	 * Retrieves a list of die rolls by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDDieRollEntity}>
	 */
	List<LABLORDDieRollEntity> findByCode(String code);
	/**
	 * Retrieves a list of die rolls by their number.
	 * @param number the number
	 * @return {@link List}<{@link LABLORDDieRollEntity}>
	 */
	List<LABLORDDieRollEntity> findByNumber(Long number);
}
