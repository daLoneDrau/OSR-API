package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPSkillChoiceEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPSkillChoiceRepository
        extends CrudRepository<WFRPSkillChoiceEntity, Long> {
    /**
     * Retrieves a list of skill choices by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPSkillChoiceEntity}>
     */
    List<WFRPSkillChoiceEntity> findByName(String name);
    /**
     * Retrieves a list of skill choices by their numToChoose.
     * @param numToChoose the numToChoose
     * @return {@link List}<{@link WFRPSkillChoiceEntity}>
     */
    List<WFRPSkillChoiceEntity> findByNumToChoose(Long numToChoose);
}
