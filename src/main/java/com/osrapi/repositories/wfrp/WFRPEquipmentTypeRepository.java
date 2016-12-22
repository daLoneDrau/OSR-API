package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPEquipmentTypeEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPEquipmentTypeRepository
        extends CrudRepository<WFRPEquipmentTypeEntity, Long> {
    /**
     * Retrieves a list of equipment types by their code.
     * @param code the code
     * @return {@link List}<{@link WFRPEquipmentTypeEntity}>
     */
    List<WFRPEquipmentTypeEntity> findByCode(Long code);
    /**
     * Retrieves a list of equipment types by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPEquipmentTypeEntity}>
     */
    List<WFRPEquipmentTypeEntity> findByName(String name);
}
