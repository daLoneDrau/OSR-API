package com.osrapi.models.wfrp;

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
@Table(name = "career_type", schema = "wfrp")
public final class WFRPCareerTypeEntity {
    /** the code. */
    @Column(name = "code")
    @JsonProperty("code")
    @NotNull
    private long code;
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "career_type_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "career_type_seq")
    @SequenceGenerator(name = "career_type_seq",
            sequenceName = "wfrp.career_type_id_seq", allocationSize = 1)
    private Long id;
    /** the name. */
    @Column(name = "name")
    @JsonProperty("name")
    @NotNull
    private String name;
    /** Creates a new instance of {@link WFRPCareerTypeEntity}. */
    public WFRPCareerTypeEntity() {
        super();
    }

    /**
     * Gets the code.
     * @return {@link long}
     */
    public long getCode() {
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
    public void setCode(final long val) {
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
     * Sets the name.
     * @param val the new value
     */
    public void setName(final String val) {
        name = val;
    }

}
