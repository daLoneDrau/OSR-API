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
@Table(name = "character", schema = "wfrp")
public final class WFRPCharacterEntity {
	/** the agility. */
	@Column(name = "agility")
	@JsonProperty("agility")
	@NotNull
	private long					agility;
	/** the attacks. */
	@Column(name = "attacks")
	@JsonProperty("attacks")
	@NotNull
	private long					attacks;
	/** the ballisticSkill. */
	@Column(name = "ballistic_skill")
	@JsonProperty("ballistic_skill")
	@NotNull
	private long					ballisticSkill;
	/** the fatePoints. */
	@Column(name = "fate_points")
	@JsonProperty("fate_points")
	@NotNull
	private long					fatePoints;

	/** the fellowship. */
	@Column(name = "fellowship")
	@JsonProperty("fellowship")
	@NotNull
	private long					fellowship;
	/** the gender. */
	@Cascade({ CascadeType.ALL })
	@JsonProperty("gender")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull
	private WFRPGenderEntity		gender;
	/**
	 * the primary key - an autogenerated id (unique for each user in the db).
	 */
	@Id
	@Column(name = "character_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_seq")
	@SequenceGenerator(name = "character_seq", sequenceName = "wfrp.character_id_seq", allocationSize = 1)
	private Long					id;

	/** the insanityPoints. */
	@Column(name = "insanity_points")
	@JsonProperty("insanity_points")
	@NotNull
	private long					insanityPoints;
	/** the intelligence. */
	@Column(name = "intelligence")
	@JsonProperty("intelligence")
	@NotNull
	private long					intelligence;
	/** the magic. */
	@Column(name = "magic")
	@JsonProperty("magic")
	@NotNull
	private long					magic;

	/** the movement. */
	@Column(name = "movement")
	@JsonProperty("movement")
	@NotNull
	private long					movement;
	/** the name. */
	@Column(name = "name")
	@JsonProperty("name")
	@NotNull
	private String					name;
	/** the race. */
	@Cascade({ CascadeType.ALL })
	@JsonProperty("race")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull
	private WFRPRaceEntity			race;

	/**
	 * the list of {@link WFRPSkillEntity}s associated with this
	 * {@link WFRPCharacterEntity}.
	 */
	@OneToMany(targetEntity = WFRPSkillEntity.class, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "character_skills_lookup", schema = "wfrp", joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "character_id") , inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id") )
	@JsonProperty("skills")
	private List<WFRPSkillEntity>	skills;
	/** the strength. */
	@Column(name = "strength")
	@JsonProperty("strength")
	@NotNull
	private long					strength;
	/** the strengthBonus. */
	@Column(name = "strength_bonus")
	@JsonProperty("strength_bonus")
	@NotNull
	private long					strengthBonus;

	/**
	 * the list of {@link WFRPTalentEntity}s associated with this
	 * {@link WFRPCharacterEntity}.
	 */
	@OneToMany(targetEntity = WFRPTalentEntity.class, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "character_talents_lookup", schema = "wfrp", joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "character_id") , inverseJoinColumns = @JoinColumn(name = "talent_id", referencedColumnName = "talent_id") )
	@JsonProperty("talents")
	private List<WFRPTalentEntity>	talents;
	/** the toughness. */
	@Column(name = "toughness")
	@JsonProperty("toughness")
	@NotNull
	private long					toughness;
	/** the toughnessBonus. */
	@Column(name = "toughness_bonus")
	@JsonProperty("toughness_bonus")
	@NotNull
	private long					toughnessBonus;

	/** the weaponSkill. */
	@Column(name = "weapon_skill")
	@JsonProperty("weapon_skill")
	@NotNull
	private long					weaponSkill;
	/** the willPower. */
	@Column(name = "will_power")
	@JsonProperty("will_power")
	@NotNull
	private long					willPower;
	/** the wounds. */
	@Column(name = "wounds")
	@JsonProperty("wounds")
	@NotNull
	private long					wounds;

	/** Creates a new instance of {@link WFRPCharacterEntity}. */
	public WFRPCharacterEntity() {
		super();
	}
	/**
	 * Gets the agility.
	 * @return {@link long}
	 */
	public long getAgility() {
		return agility;
	}
	/**
	 * Gets the attacks.
	 * @return {@link long}
	 */
	public long getAttacks() {
		return attacks;
	}

	/**
	 * Gets the ballisticSkill.
	 * @return {@link long}
	 */
	public long getBallisticSkill() {
		return ballisticSkill;
	}
	/**
	 * Gets the fatePoints.
	 * @return {@link long}
	 */
	public long getFatePoints() {
		return fatePoints;
	}
	/**
	 * Gets the fellowship.
	 * @return {@link long}
	 */
	public long getFellowship() {
		return fellowship;
	}

	/**
	 * Gets the gender.
	 * @return {@link WFRPGenderEntity}
	 */
	public WFRPGenderEntity getGender() {
		return gender;
	}
	/**
	 * Gets the id.
	 * @return {@link Long}
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Gets the insanityPoints.
	 * @return {@link long}
	 */
	public long getInsanityPoints() {
		return insanityPoints;
	}

	/**
	 * Gets the intelligence.
	 * @return {@link long}
	 */
	public long getIntelligence() {
		return intelligence;
	}
	/**
	 * Gets the magic.
	 * @return {@link long}
	 */
	public long getMagic() {
		return magic;
	}
	/**
	 * Gets the movement.
	 * @return {@link long}
	 */
	public long getMovement() {
		return movement;
	}

	/**
	 * Gets the name.
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the race.
	 * @return {@link WFRPRaceEntity}
	 */
	public WFRPRaceEntity getRace() {
		return race;
	}
	/**
	 * Gets the list of skillses.
	 * @return {@link List}<{@link WFRPSkillEntity}>
	 */
	public List<WFRPSkillEntity> getSkills() {
		return skills;
	}

	/**
	 * Gets the strength.
	 * @return {@link long}
	 */
	public long getStrength() {
		return strength;
	}
	/**
	 * Gets the strengthBonus.
	 * @return {@link long}
	 */
	public long getStrengthBonus() {
		return strengthBonus;
	}
	/**
	 * Gets the list of talentses.
	 * @return {@link List}<{@link WFRPTalentEntity}>
	 */
	public List<WFRPTalentEntity> getTalents() {
		return talents;
	}

	/**
	 * Gets the toughness.
	 * @return {@link long}
	 */
	public long getToughness() {
		return toughness;
	}
	/**
	 * Gets the toughnessBonus.
	 * @return {@link long}
	 */
	public long getToughnessBonus() {
		return toughnessBonus;
	}
	/**
	 * Gets the weaponSkill.
	 * @return {@link long}
	 */
	public long getWeaponSkill() {
		return weaponSkill;
	}

	/**
	 * Gets the willPower.
	 * @return {@link long}
	 */
	public long getWillPower() {
		return willPower;
	}
	/**
	 * Gets the wounds.
	 * @return {@link long}
	 */
	public long getWounds() {
		return wounds;
	}
	/**
	 * Sets the agility.
	 * @param val the new value
	 */
	public void setAgility(final long val) {
		agility = val;
	}

	/**
	 * Sets the attacks.
	 * @param val the new value
	 */
	public void setAttacks(final long val) {
		attacks = val;
	}
	/**
	 * Sets the ballisticSkill.
	 * @param val the new value
	 */
	public void setBallisticSkill(final long val) {
		ballisticSkill = val;
	}
	/**
	 * Sets the fatePoints.
	 * @param val the new value
	 */
	public void setFatePoints(final long val) {
		fatePoints = val;
	}

	/**
	 * Sets the fellowship.
	 * @param val the new value
	 */
	public void setFellowship(final long val) {
		fellowship = val;
	}
	/**
	 * Sets the gender.
	 * @param val the new value
	 */
	public void setGender(final WFRPGenderEntity val) {
		gender = val;
	}
	/**
	 * Sets the id.
	 * @param val the new value
	 */
	public void setId(final Long val) {
		id = val;
	}

	/**
	 * Sets the insanityPoints.
	 * @param val the new value
	 */
	public void setInsanityPoints(final long val) {
		insanityPoints = val;
	}
	/**
	 * Sets the intelligence.
	 * @param val the new value
	 */
	public void setIntelligence(final long val) {
		intelligence = val;
	}
	/**
	 * Sets the magic.
	 * @param val the new value
	 */
	public void setMagic(final long val) {
		magic = val;
	}

	/**
	 * Sets the movement.
	 * @param val the new value
	 */
	public void setMovement(final long val) {
		movement = val;
	}
	/**
	 * Sets the name.
	 * @param val the new value
	 */
	public void setName(final String val) {
		name = val;
	}
	/**
	 * Sets the race.
	 * @param val the new value
	 */
	public void setRace(final WFRPRaceEntity val) {
		race = val;
	}

	/**
	 * Sets the list of skillses.
	 * @param val the new value
	 */
	public void setSkills(final List<WFRPSkillEntity> val) {
		skills = val;
	}
	/**
	 * Sets the strength.
	 * @param val the new value
	 */
	public void setStrength(final long val) {
		strength = val;
	}
	/**
	 * Sets the strengthBonus.
	 * @param val the new value
	 */
	public void setStrengthBonus(final long val) {
		strengthBonus = val;
	}

	/**
	 * Sets the list of talentses.
	 * @param val the new value
	 */
	public void setTalents(final List<WFRPTalentEntity> val) {
		talents = val;
	}
	/**
	 * Sets the toughness.
	 * @param val the new value
	 */
	public void setToughness(final long val) {
		toughness = val;
	}
	/**
	 * Sets the toughnessBonus.
	 * @param val the new value
	 */
	public void setToughnessBonus(final long val) {
		toughnessBonus = val;
	}

	/**
	 * Sets the weaponSkill.
	 * @param val the new value
	 */
	public void setWeaponSkill(final long val) {
		weaponSkill = val;
	}
	/**
	 * Sets the willPower.
	 * @param val the new value
	 */
	public void setWillPower(final long val) {
		willPower = val;
	}
	/**
	 * Sets the wounds.
	 * @param val the new value
	 */
	public void setWounds(final long val) {
		wounds = val;
	}

}
