package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDHitDiceEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDHitDiceRepository
		extends CrudRepository<LABLORDHitDiceEntity, Long> {
	/**
	 * Retrieves a list of hit dices by their number.
	 * @param number the number
	 * @return {@link List}<{@link LABLORDHitDiceEntity}>
	 */
	List<LABLORDHitDiceEntity> findByNumber(Long number);
}
