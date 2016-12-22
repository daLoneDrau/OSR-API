package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPMeleeWeaponEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPMeleeWeaponRepository
        extends CrudRepository<WFRPMeleeWeaponEntity, Long> {
    /**
     * Retrieves a list of melee weapons by their dmgModifier.
     * @param dmgModifier the dmgModifier
     * @return {@link List}<{@link WFRPMeleeWeaponEntity}>
     */
    List<WFRPMeleeWeaponEntity> findByDmgModifier(Long dmgModifier);
    /**
     * Retrieves a list of melee weapons by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPMeleeWeaponEntity}>
     */
    List<WFRPMeleeWeaponEntity> findByName(String name);
}
