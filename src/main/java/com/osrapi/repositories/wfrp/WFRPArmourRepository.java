package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPArmourEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPArmourRepository
		extends CrudRepository<WFRPArmourEntity, Long> {
	/**
	 * Retrieves a list of armours by their ap.
	 * @param ap the ap
	 * @return {@link List}<{@link WFRPArmourEntity}>
	 */
	List<WFRPArmourEntity> findByAp(Long ap);
	/**
	 * Retrieves a list of armours by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPArmourEntity}>
	 */
	List<WFRPArmourEntity> findByName(String name);
}
