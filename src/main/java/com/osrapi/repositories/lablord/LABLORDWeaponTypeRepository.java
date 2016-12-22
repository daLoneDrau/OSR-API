package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDWeaponTypeEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDWeaponTypeRepository
        extends CrudRepository<LABLORDWeaponTypeEntity, Long> {
    /**
     * Retrieves a list of weapon types by their code.
     * @param code the code
     * @return {@link List}<{@link LABLORDWeaponTypeEntity}>
     */
    List<LABLORDWeaponTypeEntity> findByCode(Long code);
    /**
     * Retrieves a list of weapon types by their name.
     * @param name the name
     * @return {@link List}<{@link LABLORDWeaponTypeEntity}>
     */
    List<LABLORDWeaponTypeEntity> findByName(String name);
}
