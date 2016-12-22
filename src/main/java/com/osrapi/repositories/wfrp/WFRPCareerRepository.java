package com.osrapi.repositories.wfrp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.wfrp.WFRPCareerEntity;

/**
 * @author drau
 */
@Repository
public interface WFRPCareerRepository
        extends CrudRepository<WFRPCareerEntity, Long> {
    /**
     * Retrieves a list of careers by their advanceA.
     * @param advanceA the advanceA
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceA(Long advanceA);
    /**
     * Retrieves a list of careers by their advanceAg.
     * @param advanceAg the advanceAg
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceAg(Long advanceAg);
    /**
     * Retrieves a list of careers by their advanceBs.
     * @param advanceBs the advanceBs
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceBs(Long advanceBs);
    /**
     * Retrieves a list of careers by their advanceFel.
     * @param advanceFel the advanceFel
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceFel(Long advanceFel);
    /**
     * Retrieves a list of careers by their advanceFp.
     * @param advanceFp the advanceFp
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceFp(Long advanceFp);
    /**
     * Retrieves a list of careers by their advanceInt.
     * @param advanceInt the advanceInt
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceInt(Long advanceInt);
    /**
     * Retrieves a list of careers by their advanceIp.
     * @param advanceIp the advanceIp
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceIp(Long advanceIp);
    /**
     * Retrieves a list of careers by their advanceM.
     * @param advanceM the advanceM
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceM(Long advanceM);
    /**
     * Retrieves a list of careers by their advanceMag.
     * @param advanceMag the advanceMag
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceMag(Long advanceMag);
    /**
     * Retrieves a list of careers by their advanceS.
     * @param advanceS the advanceS
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceS(Long advanceS);
    /**
     * Retrieves a list of careers by their advanceSb.
     * @param advanceSb the advanceSb
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceSb(Long advanceSb);
    /**
     * Retrieves a list of careers by their advanceT.
     * @param advanceT the advanceT
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceT(Long advanceT);
    /**
     * Retrieves a list of careers by their advanceTb.
     * @param advanceTb the advanceTb
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceTb(Long advanceTb);
    /**
     * Retrieves a list of careers by their advanceW.
     * @param advanceW the advanceW
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceW(Long advanceW);
    /**
     * Retrieves a list of careers by their advanceWp.
     * @param advanceWp the advanceWp
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceWp(Long advanceWp);
    /**
     * Retrieves a list of careers by their advanceWs.
     * @param advanceWs the advanceWs
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByAdvanceWs(Long advanceWs);
    /**
     * Retrieves a list of careers by their description.
     * @param description the description
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByDescription(String description);
    /**
     * Retrieves a list of careers by their saying.
     * @param saying the saying
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findBySaying(String saying);
    /**
     * Retrieves a list of careers by their title.
     * @param title the title
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByTitle(String title);
    /**
     * Retrieves a list of careers by their titleFemale.
     * @param titleFemale the titleFemale
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    List<WFRPCareerEntity> findByTitleFemale(String titleFemale);
}
