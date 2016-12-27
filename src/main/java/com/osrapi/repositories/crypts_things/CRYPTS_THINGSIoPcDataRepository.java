package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSIoPcDataEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSIoPcDataRepository
extends CrudRepository<CRYPTS_THINGSIoPcDataEntity, Long> {
	/**
	 * Retrieves a list of io pc datas by their bags.
	 * @param bags the bags
	 * @return {@link List}<{@link CRYPTS_THINGSIoPcDataEntity}>
	 */
	List<CRYPTS_THINGSIoPcDataEntity> findByBags(Long bags);
	/**
	 * Retrieves a list of io pc datas by their flags.
	 * @param flags the flags
	 * @return {@link List}<{@link CRYPTS_THINGSIoPcDataEntity}>
	 */
	List<CRYPTS_THINGSIoPcDataEntity> findByFlags(Long flags);
	/**
	 * Retrieves a list of io pc datas by their gold.
	 * @param gold the gold
	 * @return {@link List}<{@link CRYPTS_THINGSIoPcDataEntity}>
	 */
	List<CRYPTS_THINGSIoPcDataEntity> findByGold(Float gold);
	/**
	 * Retrieves a list of io pc datas by their interfaceFlags.
	 * @param interfaceFlags the interfaceFlags
	 * @return {@link List}<{@link CRYPTS_THINGSIoPcDataEntity}>
	 */
	List<CRYPTS_THINGSIoPcDataEntity> findByInterfaceFlags(Long interfaceFlags);
	/**
	 * Retrieves a list of io pc datas by their internalScript.
	 * @param internalScript the internalScript
	 * @return {@link List}<{@link CRYPTS_THINGSIoPcDataEntity}>
	 */
	List<CRYPTS_THINGSIoPcDataEntity> findByInternalScript(String internalScript);
	/**
	 * Retrieves a list of io pc datas by their level.
	 * @param level the level
	 * @return {@link List}<{@link CRYPTS_THINGSIoPcDataEntity}>
	 */
	List<CRYPTS_THINGSIoPcDataEntity> findByLevel(Long level);
	/**
	 * Retrieves a list of io pc datas by their xp.
	 * @param xp the xp
	 * @return {@link List}<{@link CRYPTS_THINGSIoPcDataEntity}>
	 */
	List<CRYPTS_THINGSIoPcDataEntity> findByXp(Long xp);
}
