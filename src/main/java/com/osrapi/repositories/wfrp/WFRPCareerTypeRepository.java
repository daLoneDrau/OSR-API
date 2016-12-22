package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPCareerTypeEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPCareerTypeRepository
        extends CrudRepository<WFRPCareerTypeEntity, Long> {
    /**
     * Retrieves a list of career types by their code.
     * @param code the code
     * @return {@link List}<{@link WFRPCareerTypeEntity}>
     */
    List<WFRPCareerTypeEntity> findByCode(Long code);
    /**
     * Retrieves a list of career types by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPCareerTypeEntity}>
     */
    List<WFRPCareerTypeEntity> findByName(String name);
}
