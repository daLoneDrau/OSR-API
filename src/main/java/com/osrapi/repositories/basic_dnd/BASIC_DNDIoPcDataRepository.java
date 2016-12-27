package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDIoPcDataEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDIoPcDataRepository
extends CrudRepository<BASIC_DNDIoPcDataEntity, Long> {
	/**
	 * Retrieves a list of io pc datas by their bags.
	 * @param bags the bags
	 * @return {@link List}<{@link BASIC_DNDIoPcDataEntity}>
	 */
	List<BASIC_DNDIoPcDataEntity> findByBags(Long bags);
	/**
	 * Retrieves a list of io pc datas by their flags.
	 * @param flags the flags
	 * @return {@link List}<{@link BASIC_DNDIoPcDataEntity}>
	 */
	List<BASIC_DNDIoPcDataEntity> findByFlags(Long flags);
	/**
	 * Retrieves a list of io pc datas by their gold.
	 * @param gold the gold
	 * @return {@link List}<{@link BASIC_DNDIoPcDataEntity}>
	 */
	List<BASIC_DNDIoPcDataEntity> findByGold(Float gold);
	/**
	 * Retrieves a list of io pc datas by their interfaceFlags.
	 * @param interfaceFlags the interfaceFlags
	 * @return {@link List}<{@link BASIC_DNDIoPcDataEntity}>
	 */
	List<BASIC_DNDIoPcDataEntity> findByInterfaceFlags(Long interfaceFlags);
	/**
	 * Retrieves a list of io pc datas by their internalScript.
	 * @param internalScript the internalScript
	 * @return {@link List}<{@link BASIC_DNDIoPcDataEntity}>
	 */
	List<BASIC_DNDIoPcDataEntity> findByInternalScript(String internalScript);
	/**
	 * Retrieves a list of io pc datas by their level.
	 * @param level the level
	 * @return {@link List}<{@link BASIC_DNDIoPcDataEntity}>
	 */
	List<BASIC_DNDIoPcDataEntity> findByLevel(Long level);
	/**
	 * Retrieves a list of io pc datas by their xp.
	 * @param xp the xp
	 * @return {@link List}<{@link BASIC_DNDIoPcDataEntity}>
	 */
	List<BASIC_DNDIoPcDataEntity> findByXp(Long xp);
}
