package com.osrapi.repositories.basic_dnd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.basic_dnd.BASIC_DNDIoItemDataEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface BASIC_DNDIoItemDataRepository
extends CrudRepository<BASIC_DNDIoItemDataEntity, Long> {
	/**
	 * Retrieves a list of io item datas by their count.
	 * @param count the count
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByCount(Long count);
	/**
	 * Retrieves a list of io item datas by their description.
	 * @param description the description
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByDescription(String description);
	/**
	 * Retrieves a list of io item datas by their foodValue.
	 * @param foodValue the foodValue
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByFoodValue(Long foodValue);
	/**
	 * Retrieves a list of io item datas by their internalScript.
	 * @param internalScript the internalScript
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByInternalScript(String internalScript);
	/**
	 * Retrieves a list of io item datas by their name.
	 * @param name the name
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByName(String name);
	/**
	 * Retrieves a list of io item datas by their leftRing.
	 * @param leftRing the leftRing
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByLeftRing(Boolean leftRing);
	/**
	 * Retrieves a list of io item datas by their lightValue.
	 * @param lightValue the lightValue
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByLightValue(Long lightValue);
	/**
	 * Retrieves a list of io item datas by their maxOwned.
	 * @param maxOwned the maxOwned
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByMaxOwned(Long maxOwned);
	/**
	 * Retrieves a list of io item datas by their price.
	 * @param price the price
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByPrice(Float price);
	/**
	 * Retrieves a list of io item datas by their ringType.
	 * @param ringType the ringType
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByRingType(Long ringType);
	/**
	 * Retrieves a list of io item datas by their stackSize.
	 * @param stackSize the stackSize
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByStackSize(Long stackSize);
	/**
	 * Retrieves a list of io item datas by their stealValue.
	 * @param stealValue the stealValue
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByStealValue(Long stealValue);
	/**
	 * Retrieves a list of io item datas by their weight.
	 * @param weight the weight
	 * @return {@link List}<{@link BASIC_DNDIoItemDataEntity}>
	 */
	List<BASIC_DNDIoItemDataEntity> findByWeight(Float weight);
}
