package com.osrapi.models.lablord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author drau
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "ability", schema = "lablord")
public final class LABLORDAbilityEntity {
	/** the code. */
	@Column(name = "code")
	@JsonProperty("code")
	@NotNull
	private String	code;
	/** the description. */
	@Column(name = "description")
	@JsonProperty("description")
	@NotNull
	private String	description;
	/**
	 * the primary key - an autogenerated id (unique for each user in the db).
	 */
	@Id
	@Column(name = "ability_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ability_seq")
	@SequenceGenerator(name = "ability_seq", sequenceName = "lablord.ability_id_seq", allocationSize = 1)
	private Long	id;
	/** the name. */
	@Column(name = "name")
	@JsonProperty("name")
	@NotNull
	private String	name;

	/** Creates a new instance of {@link LABLORDAbilityEntity}. */
	public LABLORDAbilityEntity() {
		super();
	}
	/**
	 * Gets the code.
	 * @return {@link String}
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Gets the description.
	 * @return {@link String}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the id.
	 * @return {@link Long}
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Gets the name.
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the code.
	 * @param val the new value
	 */
	public void setCode(final String val) {
		code = val;
	}

	/**
	 * Sets the description.
	 * @param val the new value
	 */
	public void setDescription(final String val) {
		description = val;
	}
	/**
	 * Sets the id.
	 * @param val the new value
	 */
	public void setId(final Long val) {
		id = val;
	}
	/**
	 * Sets the name.
	 * @param val the new value
	 */
	public void setName(final String val) {
		name = val;
	}

}
