package com.osrapi.repositories.ff;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.ff.FFIoEquipItemEntity;

/**
 * @author drau
 */
@Repository
public interface FFIoEquipItemRepository
        extends CrudRepository<FFIoEquipItemEntity, Long> {}
