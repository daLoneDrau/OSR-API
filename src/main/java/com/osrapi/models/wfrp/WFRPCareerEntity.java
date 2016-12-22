package com.osrapi.models.wfrp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author drau
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "career", schema = "wfrp")
public final class WFRPCareerEntity {
    /** the advanceA. */
    @Column(name = "advance_a")
    @JsonProperty("advance_a")

    private Long advanceA;
    /** the advanceAg. */
    @Column(name = "advance_ag")
    @JsonProperty("advance_ag")

    private Long advanceAg;
    /** the advanceBs. */
    @Column(name = "advance_bs")
    @JsonProperty("advance_bs")

    private Long advanceBs;
    /** the advanceFel. */
    @Column(name = "advance_fel")
    @JsonProperty("advance_fel")

    private Long advanceFel;

    /** the advanceFp. */
    @Column(name = "advance_fp")
    @JsonProperty("advance_fp")

    private Long advanceFp;
    /** the advanceInt. */
    @Column(name = "advance_int")
    @JsonProperty("advance_int")

    private Long advanceInt;
    /** the advanceIp. */
    @Column(name = "advance_ip")
    @JsonProperty("advance_ip")

    private Long advanceIp;

    /** the advanceM. */
    @Column(name = "advance_m")
    @JsonProperty("advance_m")

    private Long advanceM;
    /** the advanceMag. */
    @Column(name = "advance_mag")
    @JsonProperty("advance_mag")

    private Long advanceMag;
    /** the advanceS. */
    @Column(name = "advance_s")
    @JsonProperty("advance_s")

    private Long advanceS;

    /** the advanceSb. */
    @Column(name = "advance_sb")
    @JsonProperty("advance_sb")

    private Long advanceSb;
    /** the advanceT. */
    @Column(name = "advance_t")
    @JsonProperty("advance_t")

    private Long advanceT;
    /** the advanceTb. */
    @Column(name = "advance_tb")
    @JsonProperty("advance_tb")

    private Long advanceTb;

    /** the advanceW. */
    @Column(name = "advance_w")
    @JsonProperty("advance_w")

    private Long advanceW;
    /** the advanceWp. */
    @Column(name = "advance_wp")
    @JsonProperty("advance_wp")

    private Long advanceWp;
    /** the advanceWs. */
    @Column(name = "advance_ws")
    @JsonProperty("advance_ws")

    private Long advanceWs;

    /** the allowedGender. */
    @Cascade({ CascadeType.ALL })
    @JsonProperty("allowed_gender")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)

    private WFRPGenderEntity allowedGender;
    /**
     * the list of {@link WFRPCareerEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPCareerEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_career_entries_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "career2_id",
                    referencedColumnName = "career_id"))
    @JsonProperty("career_entries")
    private List<WFRPCareerEntity> careerEntries;
    /**
     * the list of {@link WFRPCareerEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPCareerEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_career_exits_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "career2_id",
                    referencedColumnName = "career_id"))
    @JsonProperty("career_exits")
    private List<WFRPCareerEntity> careerExits;

    /** the description. */
    @Column(name = "description")
    @JsonProperty("description")
    @NotNull
    private String description;
    /**
     * the list of {@link WFRPRaceEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPRaceEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_disallowed_races_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "race_id",
                    referencedColumnName = "race_id"))
    @JsonProperty("disallowed_races")
    private List<WFRPRaceEntity> disallowedRaces;
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "career_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "career_seq")
    @SequenceGenerator(name = "career_seq", sequenceName = "wfrp.career_id_seq",
            allocationSize = 1)
    private Long id;

    /** the saying. */
    @Column(name = "saying")
    @JsonProperty("saying")
    @NotNull
    private String saying;
    /**
     * the list of {@link WFRPSkillChoiceEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPSkillChoiceEntity.class,
            fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_skill_choices_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_choice_id",
                    referencedColumnName = "skill_choice_id"))
    @JsonProperty("skill_choices")
    private List<WFRPSkillChoiceEntity> skillChoices;
    /**
     * the list of {@link WFRPSkillEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPSkillEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_skills_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id",
                    referencedColumnName = "skill_id"))
    @JsonProperty("skills")
    private List<WFRPSkillEntity> skills;

    /** the sourcebook. */
    @Cascade({ CascadeType.ALL })
    @JsonProperty("sourcebook")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private WFRPSourcebookEntity sourcebook;
    /**
     * the list of {@link WFRPTalentChoiceEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPTalentChoiceEntity.class,
            fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_talent_choices_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "talent_choice_id",
                    referencedColumnName = "talent_choice_id"))
    @JsonProperty("talent_choices")
    private List<WFRPTalentChoiceEntity> talentChoices;
    /**
     * the list of {@link WFRPTalentEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPTalentEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_talents_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "talent_id",
                    referencedColumnName = "talent_id"))
    @JsonProperty("talents")
    private List<WFRPTalentEntity> talents;

    /** the title. */
    @Column(name = "title")
    @JsonProperty("title")
    @NotNull
    private String title;
    /** the titleFemale. */
    @Column(name = "title_female")
    @JsonProperty("title_female")

    private String titleFemale;
    /**
     * the list of {@link WFRPEquipmentEntity}s associated with this
     * {@link WFRPCareerEntity}.
     */
    @OneToMany(targetEntity = WFRPEquipmentEntity.class,
            fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "career_trappings_lookup", schema = "wfrp",
            joinColumns = @JoinColumn(name = "career_id",
                    referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id",
                    referencedColumnName = "equipment_id"))
    @JsonProperty("trappings")
    private List<WFRPEquipmentEntity> trappings;

    /** the type. */
    @Cascade({ CascadeType.ALL })
    @JsonProperty("type")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private WFRPCareerTypeEntity type;
    /** Creates a new instance of {@link WFRPCareerEntity}. */
    public WFRPCareerEntity() {
        super();
    }
    /**
     * Gets the advanceA.
     * @return {@link Long}
     */
    public Long getAdvanceA() {
        return advanceA;
    }

    /**
     * Gets the advanceAg.
     * @return {@link Long}
     */
    public Long getAdvanceAg() {
        return advanceAg;
    }
    /**
     * Gets the advanceBs.
     * @return {@link Long}
     */
    public Long getAdvanceBs() {
        return advanceBs;
    }
    /**
     * Gets the advanceFel.
     * @return {@link Long}
     */
    public Long getAdvanceFel() {
        return advanceFel;
    }

    /**
     * Gets the advanceFp.
     * @return {@link Long}
     */
    public Long getAdvanceFp() {
        return advanceFp;
    }
    /**
     * Gets the advanceInt.
     * @return {@link Long}
     */
    public Long getAdvanceInt() {
        return advanceInt;
    }
    /**
     * Gets the advanceIp.
     * @return {@link Long}
     */
    public Long getAdvanceIp() {
        return advanceIp;
    }

    /**
     * Gets the advanceM.
     * @return {@link Long}
     */
    public Long getAdvanceM() {
        return advanceM;
    }
    /**
     * Gets the advanceMag.
     * @return {@link Long}
     */
    public Long getAdvanceMag() {
        return advanceMag;
    }
    /**
     * Gets the advanceS.
     * @return {@link Long}
     */
    public Long getAdvanceS() {
        return advanceS;
    }

    /**
     * Gets the advanceSb.
     * @return {@link Long}
     */
    public Long getAdvanceSb() {
        return advanceSb;
    }
    /**
     * Gets the advanceT.
     * @return {@link Long}
     */
    public Long getAdvanceT() {
        return advanceT;
    }
    /**
     * Gets the advanceTb.
     * @return {@link Long}
     */
    public Long getAdvanceTb() {
        return advanceTb;
    }

    /**
     * Gets the advanceW.
     * @return {@link Long}
     */
    public Long getAdvanceW() {
        return advanceW;
    }
    /**
     * Gets the advanceWp.
     * @return {@link Long}
     */
    public Long getAdvanceWp() {
        return advanceWp;
    }
    /**
     * Gets the advanceWs.
     * @return {@link Long}
     */
    public Long getAdvanceWs() {
        return advanceWs;
    }

    /**
     * Gets the allowedGender.
     * @return {@link WFRPGenderEntity}
     */
    public WFRPGenderEntity getAllowedGender() {
        return allowedGender;
    }
    /**
     * Gets the list of careerEntrieses.
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    public List<WFRPCareerEntity> getCareerEntries() {
        return careerEntries;
    }
    /**
     * Gets the list of careerExitses.
     * @return {@link List}<{@link WFRPCareerEntity}>
     */
    public List<WFRPCareerEntity> getCareerExits() {
        return careerExits;
    }

    /**
     * Gets the description.
     * @return {@link String}
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the list of disallowedRaceses.
     * @return {@link List}<{@link WFRPRaceEntity}>
     */
    public List<WFRPRaceEntity> getDisallowedRaces() {
        return disallowedRaces;
    }
    /**
     * Gets the id.
     * @return {@link Long}
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the saying.
     * @return {@link String}
     */
    public String getSaying() {
        return saying;
    }
    /**
     * Gets the list of skillChoiceses.
     * @return {@link List}<{@link WFRPSkillChoiceEntity}>
     */
    public List<WFRPSkillChoiceEntity> getSkillChoices() {
        return skillChoices;
    }
    /**
     * Gets the list of skillses.
     * @return {@link List}<{@link WFRPSkillEntity}>
     */
    public List<WFRPSkillEntity> getSkills() {
        return skills;
    }

    /**
     * Gets the sourcebook.
     * @return {@link WFRPSourcebookEntity}
     */
    public WFRPSourcebookEntity getSourcebook() {
        return sourcebook;
    }
    /**
     * Gets the list of talentChoiceses.
     * @return {@link List}<{@link WFRPTalentChoiceEntity}>
     */
    public List<WFRPTalentChoiceEntity> getTalentChoices() {
        return talentChoices;
    }
    /**
     * Gets the list of talentses.
     * @return {@link List}<{@link WFRPTalentEntity}>
     */
    public List<WFRPTalentEntity> getTalents() {
        return talents;
    }

    /**
     * Gets the title.
     * @return {@link String}
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the titleFemale.
     * @return {@link String}
     */
    public String getTitleFemale() {
        return titleFemale;
    }
    /**
     * Gets the list of trappingses.
     * @return {@link List}<{@link WFRPEquipmentEntity}>
     */
    public List<WFRPEquipmentEntity> getTrappings() {
        return trappings;
    }

    /**
     * Gets the type.
     * @return {@link WFRPCareerTypeEntity}
     */
    public WFRPCareerTypeEntity getType() {
        return type;
    }
    /**
     * Sets the advanceA.
     * @param val the new value
     */
    public void setAdvanceA(final Long val) {
        advanceA = val;
    }
    /**
     * Sets the advanceAg.
     * @param val the new value
     */
    public void setAdvanceAg(final Long val) {
        advanceAg = val;
    }

    /**
     * Sets the advanceBs.
     * @param val the new value
     */
    public void setAdvanceBs(final Long val) {
        advanceBs = val;
    }
    /**
     * Sets the advanceFel.
     * @param val the new value
     */
    public void setAdvanceFel(final Long val) {
        advanceFel = val;
    }
    /**
     * Sets the advanceFp.
     * @param val the new value
     */
    public void setAdvanceFp(final Long val) {
        advanceFp = val;
    }

    /**
     * Sets the advanceInt.
     * @param val the new value
     */
    public void setAdvanceInt(final Long val) {
        advanceInt = val;
    }
    /**
     * Sets the advanceIp.
     * @param val the new value
     */
    public void setAdvanceIp(final Long val) {
        advanceIp = val;
    }
    /**
     * Sets the advanceM.
     * @param val the new value
     */
    public void setAdvanceM(final Long val) {
        advanceM = val;
    }

    /**
     * Sets the advanceMag.
     * @param val the new value
     */
    public void setAdvanceMag(final Long val) {
        advanceMag = val;
    }
    /**
     * Sets the advanceS.
     * @param val the new value
     */
    public void setAdvanceS(final Long val) {
        advanceS = val;
    }
    /**
     * Sets the advanceSb.
     * @param val the new value
     */
    public void setAdvanceSb(final Long val) {
        advanceSb = val;
    }

    /**
     * Sets the advanceT.
     * @param val the new value
     */
    public void setAdvanceT(final Long val) {
        advanceT = val;
    }
    /**
     * Sets the advanceTb.
     * @param val the new value
     */
    public void setAdvanceTb(final Long val) {
        advanceTb = val;
    }
    /**
     * Sets the advanceW.
     * @param val the new value
     */
    public void setAdvanceW(final Long val) {
        advanceW = val;
    }

    /**
     * Sets the advanceWp.
     * @param val the new value
     */
    public void setAdvanceWp(final Long val) {
        advanceWp = val;
    }
    /**
     * Sets the advanceWs.
     * @param val the new value
     */
    public void setAdvanceWs(final Long val) {
        advanceWs = val;
    }
    /**
     * Sets the allowedGender.
     * @param val the new value
     */
    public void setAllowedGender(final WFRPGenderEntity val) {
        allowedGender = val;
    }

    /**
     * Sets the list of careerEntrieses.
     * @param val the new value
     */
    public void setCareerEntries(final List<WFRPCareerEntity> val) {
        careerEntries = val;
    }
    /**
     * Sets the list of careerExitses.
     * @param val the new value
     */
    public void setCareerExits(final List<WFRPCareerEntity> val) {
        careerExits = val;
    }
    /**
     * Sets the description.
     * @param val the new value
     */
    public void setDescription(final String val) {
        description = val;
    }

    /**
     * Sets the list of disallowedRaceses.
     * @param val the new value
     */
    public void setDisallowedRaces(final List<WFRPRaceEntity> val) {
        disallowedRaces = val;
    }
    /**
     * Sets the id.
     * @param val the new value
     */
    public void setId(final Long val) {
        id = val;
    }
    /**
     * Sets the saying.
     * @param val the new value
     */
    public void setSaying(final String val) {
        saying = val;
    }

    /**
     * Sets the list of skillChoiceses.
     * @param val the new value
     */
    public void setSkillChoices(final List<WFRPSkillChoiceEntity> val) {
        skillChoices = val;
    }
    /**
     * Sets the list of skillses.
     * @param val the new value
     */
    public void setSkills(final List<WFRPSkillEntity> val) {
        skills = val;
    }
    /**
     * Sets the sourcebook.
     * @param val the new value
     */
    public void setSourcebook(final WFRPSourcebookEntity val) {
        sourcebook = val;
    }

    /**
     * Sets the list of talentChoiceses.
     * @param val the new value
     */
    public void setTalentChoices(final List<WFRPTalentChoiceEntity> val) {
        talentChoices = val;
    }
    /**
     * Sets the list of talentses.
     * @param val the new value
     */
    public void setTalents(final List<WFRPTalentEntity> val) {
        talents = val;
    }
    /**
     * Sets the title.
     * @param val the new value
     */
    public void setTitle(final String val) {
        title = val;
    }

    /**
     * Sets the titleFemale.
     * @param val the new value
     */
    public void setTitleFemale(final String val) {
        titleFemale = val;
    }
    /**
     * Sets the list of trappingses.
     * @param val the new value
     */
    public void setTrappings(final List<WFRPEquipmentEntity> val) {
        trappings = val;
    }
    /**
     * Sets the type.
     * @param val the new value
     */
    public void setType(final WFRPCareerTypeEntity val) {
        type = val;
    }

}
