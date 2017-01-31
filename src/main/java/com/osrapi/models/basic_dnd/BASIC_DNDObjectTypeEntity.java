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
@Table(name = "object_type", schema = "basic_dnd")
public final class BASIC_DNDObjectTypeEntity {
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "object_type_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
  generator = "object_type_seq")
    @SequenceGenerator(
        name = "object_type_seq",
        sequenceName = "basic_dnd.object_type_id_seq",
        allocationSize = 1
    )
    private Long                    id;
    /** Creates a new instance of {@link BASIC_DNDObjectTypeEntity}. */
    public BASIC_DNDObjectTypeEntity() {
        super();
    }
    /**
     * Gets the id.
     * @return {@link Long}
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the id.
     * @param val the new value
     */
    public void setId(final Long val) {
        id = val;
    }

    /** the flag. */
    @Column(name = "flag")
    @JsonProperty("flag")
    @NotNull
    private long                    flag;
    /**
     * Gets the flag.
     * @return {@link long}
     */
    public long getFlag() {
        return flag;
    }
    /**
     * Sets the flag.
     * @param val the new value
     */
    public void setFlag(final long val) {
        flag = val;
    }

    /** the code. */
    @Column(name = "code")
    @JsonProperty("code")
    @NotNull
    private String                    code;
    /**
     * Gets the code.
     * @return {@link String}
     */
    public String getCode() {
        return code;
    }
    /**
     * Sets the code.
     * @param val the new value
     */
    public void setCode(final String val) {
        code = val;
    }

}

