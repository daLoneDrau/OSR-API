package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPTalentChoiceEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPTalentChoiceRepository
		extends CrudRepository<WFRPTalentChoiceEntity, Long> {
	/**
	 * Retrieves a list of talent choices by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPTalentChoiceEntity}>
	 */
	List<WFRPTalentChoiceEntity> findByName(String name);
	/**
	 * Retrieves a list of talent choices by their numToChoose.
	 * @param numToChoose the numToChoose
	 * @return {@link List}<{@link WFRPTalentChoiceEntity}>
	 */
	List<WFRPTalentChoiceEntity> findByNumToChoose(Long numToChoose);
}
