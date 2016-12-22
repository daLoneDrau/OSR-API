package com.osrapi.models.lablord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author drau
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "ability_requirement", schema = "lablord")
public final class LABLORDAbilityRequirementEntity {
	/** the ability. */
	@Cascade({ CascadeType.ALL })
	@JsonProperty("ability")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull
	private LABLORDAbilityEntity	ability;
	/** the code. */
	@Column(name = "code")
	@JsonProperty("code")
	@NotNull
	private String					code;
	/**
	 * the primary key - an autogenerated id (unique for each user in the db).
	 */
	@Id
	@Column(name = "ability_requirement_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ability_requirement_seq")
	@SequenceGenerator(name = "ability_requirement_seq", sequenceName = "lablord.ability_requirement_id_seq", allocationSize = 1)
	private Long					id;
	/** the requirement. */
	@Column(name = "requirement")
	@JsonProperty("requirement")
	@NotNull
	private long					requirement;

	/** Creates a new instance of {@link LABLORDAbilityRequirementEntity}. */
	public LABLORDAbilityRequirementEntity() {
		super();
	}
	/**
	 * Gets the ability.
	 * @return {@link LABLORDAbilityEntity}
	 */
	public LABLORDAbilityEntity getAbility() {
		return ability;
	}
	/**
	 * Gets the code.
	 * @return {@link String}
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the id.
	 * @return {@link Long}
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Gets the requirement.
	 * @return {@link long}
	 */
	public long getRequirement() {
		return requirement;
	}
	/**
	 * Sets the ability.
	 * @param val the new value
	 */
	public void setAbility(final LABLORDAbilityEntity val) {
		ability = val;
	}

	/**
	 * Sets the code.
	 * @param val the new value
	 */
	public void setCode(final String val) {
		code = val;
	}
	/**
	 * Sets the id.
	 * @param val the new value
	 */
	public void setId(final Long val) {
		id = val;
	}
	/**
	 * Sets the requirement.
	 * @param val the new value
	 */
	public void setRequirement(final long val) {
		requirement = val;
	}

}
