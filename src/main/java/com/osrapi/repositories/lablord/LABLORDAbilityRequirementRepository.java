package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDAbilityRequirementEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDAbilityRequirementRepository
		extends CrudRepository<LABLORDAbilityRequirementEntity, Long> {
	/**
	 * Retrieves a list of ability requirements by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDAbilityRequirementEntity}>
	 */
	List<LABLORDAbilityRequirementEntity> findByCode(String code);
	/**
	 * Retrieves a list of ability requirements by their requirement.
	 * @param requirement the requirement
	 * @return {@link List}<{@link LABLORDAbilityRequirementEntity}>
	 */
	List<LABLORDAbilityRequirementEntity> findByRequirement(Long requirement);
}
