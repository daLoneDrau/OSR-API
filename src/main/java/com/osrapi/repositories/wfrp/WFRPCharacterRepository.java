package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPCharacterEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPCharacterRepository
        extends CrudRepository<WFRPCharacterEntity, Long> {
    /**
     * Retrieves a list of characters by their agility.
     * @param agility the agility
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByAgility(Long agility);
    /**
     * Retrieves a list of characters by their attacks.
     * @param attacks the attacks
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByAttacks(Long attacks);
    /**
     * Retrieves a list of characters by their ballisticSkill.
     * @param ballisticSkill the ballisticSkill
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByBallisticSkill(Long ballisticSkill);
    /**
     * Retrieves a list of characters by their fatePoints.
     * @param fatePoints the fatePoints
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByFatePoints(Long fatePoints);
    /**
     * Retrieves a list of characters by their fellowship.
     * @param fellowship the fellowship
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByFellowship(Long fellowship);
    /**
     * Retrieves a list of characters by their insanityPoints.
     * @param insanityPoints the insanityPoints
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByInsanityPoints(Long insanityPoints);
    /**
     * Retrieves a list of characters by their intelligence.
     * @param intelligence the intelligence
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByIntelligence(Long intelligence);
    /**
     * Retrieves a list of characters by their magic.
     * @param magic the magic
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByMagic(Long magic);
    /**
     * Retrieves a list of characters by their movement.
     * @param movement the movement
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByMovement(Long movement);
    /**
     * Retrieves a list of characters by their name.
     * @param name the name
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByName(String name);
    /**
     * Retrieves a list of characters by their strength.
     * @param strength the strength
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByStrength(Long strength);
    /**
     * Retrieves a list of characters by their strengthBonus.
     * @param strengthBonus the strengthBonus
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByStrengthBonus(Long strengthBonus);
    /**
     * Retrieves a list of characters by their toughness.
     * @param toughness the toughness
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByToughness(Long toughness);
    /**
     * Retrieves a list of characters by their toughnessBonus.
     * @param toughnessBonus the toughnessBonus
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByToughnessBonus(Long toughnessBonus);
    /**
     * Retrieves a list of characters by their weaponSkill.
     * @param weaponSkill the weaponSkill
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByWeaponSkill(Long weaponSkill);
    /**
     * Retrieves a list of characters by their willPower.
     * @param willPower the willPower
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByWillPower(Long willPower);
    /**
     * Retrieves a list of characters by their wounds.
     * @param wounds the wounds
     * @return {@link List}<{@link WFRPCharacterEntity}>
     */
    List<WFRPCharacterEntity> findByWounds(Long wounds);
}
