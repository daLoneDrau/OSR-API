package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPGenderEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPGenderRepository
        extends CrudRepository<WFRPGenderEntity, Long> {
    /**
     * Retrieves a list of genders by their description.
     * @param description the description
     * @return {@link List}<{@link WFRPGenderEntity}>
     */
    List<WFRPGenderEntity> findByDescription(String description);
    /**
     * Retrieves a list of genders by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPGenderEntity}>
     */
    List<WFRPGenderEntity> findByName(String name);
}
