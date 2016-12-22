package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPWeaponEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPWeaponRepository
        extends CrudRepository<WFRPWeaponEntity, Long> {
    /**
     * Retrieves a list of weapons by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPWeaponEntity}>
     */
    List<WFRPWeaponEntity> findByName(String name);
    /**
     * Retrieves a list of weapons by their twoHanded.
     * @param twoHanded the twoHanded
     * @return {@link List}<{@link WFRPWeaponEntity}>
     */
    List<WFRPWeaponEntity> findByTwoHanded(Boolean twoHanded);
}
