package com.osrapi.models.basic_dnd;

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
@Table(name = "equipment_element_type", schema = "basic_dnd")
public final class BASIC_DNDEquipmentElementTypeEntity {
    /** the code. */
    @Column(name = "code")
    @JsonProperty("code")
    @NotNull
    private String code;
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "equipment_element_type_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "equipment_element_type_seq")
    @SequenceGenerator(
            name = "equipment_element_type_seq",
            sequenceName = "basic_dnd.equipment_element_type_id_seq",
            allocationSize = 1)
    private Long id;
    /** the value. */
    @Column(name = "value")
    @JsonProperty("value")
    @NotNull
    private long value;
    /**
     * Creates a new instance of {@link BASIC_DNDEquipmentElementTypeEntity}.
     */
    public BASIC_DNDEquipmentElementTypeEntity() {
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
     * Gets the value.
     * @return {@link long}
     */
    public long getValue() {
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
     * Sets the value.
     * @param val the new value
     */
    public void setValue(final long val) {
        value = val;
    }

}
