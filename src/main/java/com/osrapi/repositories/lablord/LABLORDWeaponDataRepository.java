package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDWeaponDataEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDWeaponDataRepository
        extends CrudRepository<LABLORDWeaponDataEntity, Long> {
    /**
     * Retrieves a list of weapon datas by their code.
     * @param code the code
     * @return {@link List}<{@link LABLORDWeaponDataEntity}>
     */
    List<LABLORDWeaponDataEntity> findByCode(String code);
}
