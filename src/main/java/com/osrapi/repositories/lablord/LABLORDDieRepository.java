package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDDieEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDDieRepository
        extends CrudRepository<LABLORDDieEntity, Long> {
    /**
     * Retrieves a list of dies by their code.
     * @param code the code
     * @return {@link List}<{@link LABLORDDieEntity}>
     */
    List<LABLORDDieEntity> findByCode(String code);
    /**
     * Retrieves a list of dies by their value.
     * @param value the value
     * @return {@link List}<{@link LABLORDDieEntity}>
     */
    List<LABLORDDieEntity> findByValue(Long value);
}
