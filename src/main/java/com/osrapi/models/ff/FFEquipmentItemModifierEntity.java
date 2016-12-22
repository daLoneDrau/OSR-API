package com.osrapi.models.ff;

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
@Table(name = "equipment_item_modifier", schema = "ff")
public final class FFEquipmentItemModifierEntity {
    /** the code. */
    @Column(name = "code")
    @JsonProperty("code")
    @NotNull
    private String code;
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "equipment_item_modifier_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "equipment_item_modifier_seq")
    @SequenceGenerator(
            name = "equipment_item_modifier_seq",
            sequenceName = "ff.equipment_item_modifier_id_seq",
            allocationSize = 1)
    private Long id;
    /** the percent. */
    @Column(name = "percent")
    @JsonProperty("percent")
    @NotNull
    private Boolean percent;
    /** the special. */
    @Column(name = "special")
    @JsonProperty("special")
    @NotNull
    private long special;

    /** the value. */
    @Column(name = "value")
    @JsonProperty("value")
    @NotNull
    private float value;
    /** Creates a new instance of {@link FFEquipmentItemModifierEntity}. */
    public FFEquipmentItemModifierEntity() {
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
     * Gets the id.
     * @return {@link Long}
     */
    public Long getId() {
        return id;
    }
    /**
     * Gets the percent.
     * @return {@link Boolean}
     */
    public Boolean getPercent() {
        return percent;
    }
    /**
     * Gets the special.
     * @return {@link long}
     */
    public long getSpecial() {
        return special;
    }

    /**
     * Gets the value.
     * @return {@link float}
     */
    public float getValue() {
        return value;
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
     * Sets the percent.
     * @param val the new value
     */
    public void setPercent(final Boolean val) {
        percent = val;
    }
    /**
     * Sets the special.
     * @param val the new value
     */
    public void setSpecial(final long val) {
        special = val;
    }
    /**
     * Sets the value.
     * @param val the new value
     */
    public void setValue(final float val) {
        value = val;
    }

}
