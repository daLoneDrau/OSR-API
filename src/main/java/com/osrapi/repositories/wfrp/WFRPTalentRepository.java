package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPTalentEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPTalentRepository
        extends CrudRepository<WFRPTalentEntity, Long> {
    /**
     * Retrieves a list of talents by their description.
     * @param description the description
     * @return {@link List}<{@link WFRPTalentEntity}>
     */
    List<WFRPTalentEntity> findByDescription(String description);
    /**
     * Retrieves a list of talents by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPTalentEntity}>
     */
    List<WFRPTalentEntity> findByName(String name);
}
