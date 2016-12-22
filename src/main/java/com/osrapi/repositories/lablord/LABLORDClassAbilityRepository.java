package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDClassAbilityEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDClassAbilityRepository
        extends CrudRepository<LABLORDClassAbilityEntity, Long> {
    /**
     * Retrieves a list of class abilitys by their code.
     * @param code the code
     * @return {@link List}<{@link LABLORDClassAbilityEntity}>
     */
    List<LABLORDClassAbilityEntity> findByCode(Long code);
    /**
     * Retrieves a list of class abilitys by their description.
     * @param description the description
     * @return {@link List}<{@link LABLORDClassAbilityEntity}>
     */
    List<LABLORDClassAbilityEntity> findByDescription(String description);
    /**
     * Retrieves a list of class abilitys by their name.
     * @param name the name
     * @return {@link List}<{@link LABLORDClassAbilityEntity}>
     */
    List<LABLORDClassAbilityEntity> findByName(String name);
}
