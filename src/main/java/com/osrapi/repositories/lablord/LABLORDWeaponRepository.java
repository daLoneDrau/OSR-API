package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDWeaponEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDWeaponRepository
		extends CrudRepository<LABLORDWeaponEntity, Long> {
	/**
	 * Retrieves a list of weapons by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDWeaponEntity}>
	 */
	List<LABLORDWeaponEntity> findByCode(String code);
}
