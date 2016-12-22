package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDArmorEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDArmorRepository
		extends CrudRepository<LABLORDArmorEntity, Long> {
	/**
	 * Retrieves a list of armors by their ac.
	 * @param ac the ac
	 * @return {@link List}<{@link LABLORDArmorEntity}>
	 */
	List<LABLORDArmorEntity> findByAc(Long ac);
}
