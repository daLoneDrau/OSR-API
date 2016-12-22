package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDCharacterEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDCharacterRepository
		extends CrudRepository<LABLORDCharacterEntity, Long> {
	/**
	 * Retrieves a list of characters by their armorClass.
	 * @param armorClass the armorClass
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByArmorClass(Long armorClass);
	/**
	 * Retrieves a list of characters by their charisma.
	 * @param charisma the charisma
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByCharisma(Long charisma);
	/**
	 * Retrieves a list of characters by their constitution.
	 * @param constitution the constitution
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByConstitution(Long constitution);
	/**
	 * Retrieves a list of characters by their dexterity.
	 * @param dexterity the dexterity
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByDexterity(Long dexterity);
	/**
	 * Retrieves a list of characters by their experience.
	 * @param experience the experience
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByExperience(Long experience);
	/**
	 * Retrieves a list of characters by their hitPoints.
	 * @param hitPoints the hitPoints
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByHitPoints(Long hitPoints);
	/**
	 * Retrieves a list of characters by their intelligence.
	 * @param intelligence the intelligence
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByIntelligence(Long intelligence);
	/**
	 * Retrieves a list of characters by their level.
	 * @param level the level
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByLevel(Long level);
	/**
	 * Retrieves a list of characters by their maxHitPoints.
	 * @param maxHitPoints the maxHitPoints
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByMaxHitPoints(Long maxHitPoints);
	/**
	 * Retrieves a list of characters by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByName(String name);
	/**
	 * Retrieves a list of characters by their strength.
	 * @param strength the strength
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByStrength(Long strength);
	/**
	 * Retrieves a list of characters by their wisdom.
	 * @param wisdom the wisdom
	 * @return {@link List}<{@link LABLORDCharacterEntity}>
	 */
	List<LABLORDCharacterEntity> findByWisdom(Long wisdom);
}
