package com.osrapi.repositories.avalon;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.avalon.AVALONIoItemDataEntity;

/**
 *
 * @author drau
 *
 */
@Repository
public interface AVALONIoItemDataRepository
extends CrudRepository<AVALONIoItemDataEntity, Long> {
	/**
	 * Retrieves a list of io item datas by their attackSpeed.
	 * @param attackSpeed the attackSpeed
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByAttackSpeed(Long attackSpeed);
	/**
	 * Retrieves a list of io item datas by their count.
	 * @param count the count
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByCount(Long count);
	/**
	 * Retrieves a list of io item datas by their description.
	 * @param description the description
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByDescription(String description);
	/**
	 * Retrieves a list of io item datas by their foodValue.
	 * @param foodValue the foodValue
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByFoodValue(Long foodValue);
	/**
	 * Retrieves a list of io item datas by their internalScript.
	 * @param internalScript the internalScript
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByInternalScript(String internalScript);
	/**
	 * Retrieves a list of io item datas by their leftRing.
	 * @param leftRing the leftRing
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByLeftRing(Boolean leftRing);
	/**
	 * Retrieves a list of io item datas by their length.
	 * @param length the length
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByLength(Long length);
	/**
	 * Retrieves a list of io item datas by their lightValue.
	 * @param lightValue the lightValue
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByLightValue(Long lightValue);
	/**
	 * Retrieves a list of io item datas by their maxOwned.
	 * @param maxOwned the maxOwned
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByMaxOwned(Long maxOwned);
	/**
	 * Retrieves a list of io item datas by their name.
	 * @param name the name
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByName(String name);
	/**
	 * Retrieves a list of io item datas by their price.
	 * @param price the price
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByPrice(Float price);
	/**
	 * Retrieves a list of io item datas by their ringType.
	 * @param ringType the ringType
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByRingType(Long ringType);
	/**
	 * Retrieves a list of io item datas by their sharpness.
	 * @param sharpness the sharpness
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findBySharpness(Long sharpness);
	/**
	 * Retrieves a list of io item datas by their stackSize.
	 * @param stackSize the stackSize
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByStackSize(Long stackSize);
	/**
	 * Retrieves a list of io item datas by their stealValue.
	 * @param stealValue the stealValue
	 * @return {@link List}<{@link AVALONIoItemDataEntity}>
	 */
	List<AVALONIoItemDataEntity> findByStealValue(Long stealValue);
}
