package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPMissileWeaponEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPMissileWeaponRepository
		extends CrudRepository<WFRPMissileWeaponEntity, Long> {
	/**
	 * Retrieves a list of missile weapons by their dmg.
	 * @param dmg the dmg
	 * @return {@link List}<{@link WFRPMissileWeaponEntity}>
	 */
	List<WFRPMissileWeaponEntity> findByDmg(Long dmg);
	/**
	 * Retrieves a list of missile weapons by their dmgIsSb.
	 * @param dmgIsSb the dmgIsSb
	 * @return {@link List}<{@link WFRPMissileWeaponEntity}>
	 */
	List<WFRPMissileWeaponEntity> findByDmgIsSb(Boolean dmgIsSb);
	/**
	 * Retrieves a list of missile weapons by their dmgModifier.
	 * @param dmgModifier the dmgModifier
	 * @return {@link List}<{@link WFRPMissileWeaponEntity}>
	 */
	List<WFRPMissileWeaponEntity> findByDmgModifier(Long dmgModifier);
	/**
	 * Retrieves a list of missile weapons by their longRange.
	 * @param longRange the longRange
	 * @return {@link List}<{@link WFRPMissileWeaponEntity}>
	 */
	List<WFRPMissileWeaponEntity> findByLongRange(Long longRange);
	/**
	 * Retrieves a list of missile weapons by their name.
	 * @param name the name
	 * @return {@link List}<{@link WFRPMissileWeaponEntity}>
	 */
	List<WFRPMissileWeaponEntity> findByName(String name);
	/**
	 * Retrieves a list of missile weapons by their shortRange.
	 * @param shortRange the shortRange
	 * @return {@link List}<{@link WFRPMissileWeaponEntity}>
	 */
	List<WFRPMissileWeaponEntity> findByShortRange(Long shortRange);
}
