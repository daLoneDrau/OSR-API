package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPWeaponReloadEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPWeaponReloadRepository
        extends CrudRepository<WFRPWeaponReloadEntity, Long> {
    /**
     * Retrieves a list of weapon reloads by their code.
     * @param code the code
     * @return {@link List}<{@link WFRPWeaponReloadEntity}>
     */
    List<WFRPWeaponReloadEntity> findByCode(Long code);
    /**
     * Retrieves a list of weapon reloads by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPWeaponReloadEntity}>
     */
    List<WFRPWeaponReloadEntity> findByName(String name);
}
