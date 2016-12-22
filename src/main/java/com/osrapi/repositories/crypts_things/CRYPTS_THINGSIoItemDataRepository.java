package com.osrapi.repositories.crypts_things;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.crypts_things.CRYPTS_THINGSIoItemDataEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface CRYPTS_THINGSIoItemDataRepository
extends CrudRepository<CRYPTS_THINGSIoItemDataEntity, Long> {
	/**
	 * Retrieves a list of io item datas by their count.
	 * @param count the count
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByCount(Long count);
	/**
	 * Retrieves a list of io item datas by their description.
	 * @param description the description
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByDescription(String description);
	/**
	 * Retrieves a list of io item datas by their foodValue.
	 * @param foodValue the foodValue
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByFoodValue(Long foodValue);
	/**
	 * Retrieves a list of io item datas by their internalScript.
	 * @param internalScript the internalScript
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByInternalScript(String internalScript);
	/**
	 * Retrieves a list of io item datas by their itemName.
	 * @param itemName the itemName
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByItemName(String itemName);
	/**
	 * Retrieves a list of io item datas by their leftRing.
	 * @param leftRing the leftRing
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByLeftRing(Boolean leftRing);
	/**
	 * Retrieves a list of io item datas by their lightValue.
	 * @param lightValue the lightValue
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByLightValue(Long lightValue);
	/**
	 * Retrieves a list of io item datas by their maxOwned.
	 * @param maxOwned the maxOwned
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByMaxOwned(Long maxOwned);
	/**
	 * Retrieves a list of io item datas by their price.
	 * @param price the price
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByPrice(Float price);
	/**
	 * Retrieves a list of io item datas by their ringType.
	 * @param ringType the ringType
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByRingType(Long ringType);
	/**
	 * Retrieves a list of io item datas by their stackSize.
	 * @param stackSize the stackSize
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByStackSize(Long stackSize);
	/**
	 * Retrieves a list of io item datas by their stealValue.
	 * @param stealValue the stealValue
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByStealValue(Long stealValue);
	/**
	 * Retrieves a list of io item datas by their weight.
	 * @param weight the weight
	 * @return {@link List}<{@link CRYPTS_THINGSIoItemDataEntity}>
	 */
	List<CRYPTS_THINGSIoItemDataEntity> findByWeight(Float weight);
}
