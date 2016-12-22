package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPCharacteristicEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPCharacteristicRepository
        extends CrudRepository<WFRPCharacteristicEntity, Long> {
    /**
     * Retrieves a list of characteristics by their code.
     * @param code the code
     * @return {@link List}<{@link WFRPCharacteristicEntity}>
     */
    List<WFRPCharacteristicEntity> findByCode(String code);
    /**
     * Retrieves a list of characteristics by their description.
     * @param description the description
     * @return {@link List}<{@link WFRPCharacteristicEntity}>
     */
    List<WFRPCharacteristicEntity> findByDescription(String description);
    /**
     * Retrieves a list of characteristics by their isMain.
     * @param isMain the isMain
     * @return {@link List}<{@link WFRPCharacteristicEntity}>
     */
    List<WFRPCharacteristicEntity> findByIsMain(Boolean isMain);
    /**
     * Retrieves a list of characteristics by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPCharacteristicEntity}>
     */
    List<WFRPCharacteristicEntity> findByName(String name);
}
