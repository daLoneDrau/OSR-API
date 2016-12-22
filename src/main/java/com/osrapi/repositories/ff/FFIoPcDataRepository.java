package com.osrapi.repositories.ff;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.ff.FFIoPcDataEntity;

/**
 * @author drau
 */
@Repository
public interface FFIoPcDataRepository
        extends CrudRepository<FFIoPcDataEntity, Long> {
    /**
     * Retrieves a list of io pc datas by their flags.
     * @param flags the flags
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByFlags(Long flags);
    /**
     * Retrieves a list of io pc datas by their gold.
     * @param gold the gold
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByGold(Float gold);
    /**
     * Retrieves a list of io pc datas by their interfaceFlags.
     * @param interfaceFlags the interfaceFlags
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByInterfaceFlags(Long interfaceFlags);
    /**
     * Retrieves a list of io pc datas by their internalScript.
     * @param internalScript the internalScript
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByInternalScript(String internalScript);
    /**
     * Retrieves a list of io pc datas by their level.
     * @param level the level
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByLevel(Long level);
    /**
     * Retrieves a list of io pc datas by their name.
     * @param name the name
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByName(String name);
    /**
     * Retrieves a list of io pc datas by their profession.
     * @param profession the profession
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByProfession(Long profession);
    /**
     * Retrieves a list of io pc datas by their race.
     * @param race the race
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByRace(Long race);
    /**
     * Retrieves a list of io pc datas by their xp.
     * @param xp the xp
     * @return {@link List}<{@link FFIoPcDataEntity}>
     */
    List<FFIoPcDataEntity> findByXp(Long xp);
}
