package com.osrapi.models.lablord;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
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
@Table(name = "currency", schema = "lablord")
public final class LABLORDCurrencyEntity {
	/** the code. */
	@Column(name = "code")
	@JsonProperty("code")
	@NotNull
	private String				code;
	@ElementCollection
	@CollectionTable(name = "currency_exchange_rates_lookup", schema = "lablord", joinColumns = @JoinColumn(name = "currency_id") )
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	@JsonProperty("exchange_rates")
	private Map<String, Float>	exchangeRates;
	/**
	 * the primary key - an autogenerated id (unique for each user in the db).
	 */
	@Id
	@Column(name = "currency_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_seq")
	@SequenceGenerator(name = "currency_seq", sequenceName = "lablord.currency_id_seq", allocationSize = 1)
	private Long				id;
	/** the name. */
	@Column(name = "name")
	@JsonProperty("name")
	@NotNull
	private String				name;

	/** the sortOrder. */
	@Column(name = "sort_order")
	@JsonProperty("sort_order")
	@NotNull
	private long				sortOrder;
	/** Creates a new instance of {@link LABLORDCurrencyEntity}. */
	public LABLORDCurrencyEntity() {
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
	 * Gets the map of exchangeRates.
	 * @return {@link Map}<{@link String}, {@link Float}>
	 */
	public Map<String, Float> getExchangeRates() {
		return exchangeRates;
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
	 * Gets the sortOrder.
	 * @return {@link long}
	 */
	public long getSortOrder() {
		return sortOrder;
	}
	/**
	 * Sets the code.
	 * @param val the new value
	 */
	public void setCode(final String val) {
		code = val;
	}
	/**
	 * Sets the mapping for exchangeRates.
	 * @param val the new value
	 */
	public void setExchangeRates(final Map<String, Float> val) {
		exchangeRates = val;
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
	/**
	 * Sets the sortOrder.
	 * @param val the new value
	 */
	public void setSortOrder(final long val) {
		sortOrder = val;
	}

}
