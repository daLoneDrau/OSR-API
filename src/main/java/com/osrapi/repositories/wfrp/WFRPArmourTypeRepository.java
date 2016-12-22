package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPArmourTypeEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPArmourTypeRepository
        extends CrudRepository<WFRPArmourTypeEntity, Long> {
    /**
     * Retrieves a list of armour types by their code.
     * @param code the code
     * @return {@link List}<{@link WFRPArmourTypeEntity}>
     */
    List<WFRPArmourTypeEntity> findByCode(Long code);
    /**
     * Retrieves a list of armour types by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPArmourTypeEntity}>
     */
    List<WFRPArmourTypeEntity> findByName(String name);
}
