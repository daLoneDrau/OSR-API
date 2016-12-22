package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPSourcebookEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPSourcebookRepository
        extends CrudRepository<WFRPSourcebookEntity, Long> {
    /**
     * Retrieves a list of sourcebooks by their code.
     * @param code the code
     * @return {@link List}<{@link WFRPSourcebookEntity}>
     */
    List<WFRPSourcebookEntity> findByCode(String code);
    /**
     * Retrieves a list of sourcebooks by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPSourcebookEntity}>
     */
    List<WFRPSourcebookEntity> findByName(String name);
    /**
     * Retrieves a list of sourcebooks by their owned.
     * @param owned the owned
     * @return {@link List}<{@link WFRPSourcebookEntity}>
     */
    List<WFRPSourcebookEntity> findByOwned(Boolean owned);
}
