package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPArmourCoverageEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPArmourCoverageRepository
        extends CrudRepository<WFRPArmourCoverageEntity, Long> {
    /**
     * Retrieves a list of armour coverages by their flag.
     * @param flag the flag
     * @return {@link List}<{@link WFRPArmourCoverageEntity}>
     */
    List<WFRPArmourCoverageEntity> findByFlag(Long flag);
    /**
     * Retrieves a list of armour coverages by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPArmourCoverageEntity}>
     */
    List<WFRPArmourCoverageEntity> findByName(String name);
}
