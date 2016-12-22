package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPWeaponGroupEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPWeaponGroupRepository
        extends CrudRepository<WFRPWeaponGroupEntity, Long> {
    /**
     * Retrieves a list of weapon groups by their code.
     * @param code the code
     * @return {@link List}<{@link WFRPWeaponGroupEntity}>
     */
    List<WFRPWeaponGroupEntity> findByCode(Long code);
    /**
     * Retrieves a list of weapon groups by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPWeaponGroupEntity}>
     */
    List<WFRPWeaponGroupEntity> findByName(String name);
}
