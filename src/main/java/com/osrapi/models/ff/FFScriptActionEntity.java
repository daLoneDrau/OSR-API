package com.osrapi.models.ff;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "script_action", schema = "ff")
public final class FFScriptActionEntity {
    /** the amount. */
    @Column(name = "amount")
    @JsonProperty("amount")

    private Long amount;
    /** the attribute. */
    @Column(name = "attribute")
    @JsonProperty("attribute")

    private String attribute;
    /** the destination. */
    @Column(name = "destination")
    @JsonProperty("destination")

    private String destination;
    /** the direction. */
    @Column(name = "direction")
    @JsonProperty("direction")

    private String direction;

    /** the doorName. */
    @Column(name = "door_name")
    @JsonProperty("door_name")

    private String doorName;
    /** the error. */
    @Column(name = "error")
    @JsonProperty("error")

    private Boolean error;
    /** the failScripts. */
    @Column(name = "fail_scripts")
    @JsonProperty("fail_scripts")

    private String failScripts;

    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "script_action_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "script_action_seq")
    @SequenceGenerator(
            name = "script_action_seq",
            sequenceName = "ff.script_action_id_seq",
            allocationSize = 1)
    private Long id;
    /** the isDieRoll. */
    @Column(name = "is_die_roll")
    @JsonProperty("is_die_roll")

    private Boolean isDieRoll;
    /** the mobCode. */
    @Column(name = "mob_code")
    @JsonProperty("mob_code")

    private String mobCode;

    /**
     * the list of mobs associated with this {@link FFScriptActionEntity}.
     */
    @Column
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "script_action_mobs_lookup", schema = "ff",
            joinColumns = { @JoinColumn(name = "script_action_id") })
    @JsonProperty("mobs")
    private List<String> mobs;
    /** the name. */
    @Column(name = "name")
    @JsonProperty("name")
    @NotNull
    private String name;
    /** the numDieRolled. */
    @Column(name = "num_die_rolled")
    @JsonProperty("num_die_rolled")

    private Long numDieRolled;

    /** the origin. */
    @Column(name = "origin")
    @JsonProperty("origin")

    private String origin;
    /** the passScripts. */
    @Column(name = "pass_scripts")
    @JsonProperty("pass_scripts")

    private String passScripts;
    /** the roomCode. */
    @Column(name = "room_code")
    @JsonProperty("room_code")

    private String roomCode;

    /** the textName. */
    @Column(name = "text_name")
    @JsonProperty("text_name")

    private String textName;
    /** the type. */
    @Column(name = "type")
    @JsonProperty("type")
    @NotNull
    private String type;
    /** Creates a new instance of {@link FFScriptActionEntity}. */
    public FFScriptActionEntity() {
        super();
    }

    /**
     * Gets the amount.
     * @return {@link Long}
     */
    public Long getAmount() {
        return amount;
    }
    /**
     * Gets the attribute.
     * @return {@link String}
     */
    public String getAttribute() {
        return attribute;
    }
    /**
     * Gets the destination.
     * @return {@link String}
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Gets the direction.
     * @return {@link String}
     */
    public String getDirection() {
        return direction;
    }
    /**
     * Gets the doorName.
     * @return {@link String}
     */
    public String getDoorName() {
        return doorName;
    }
    /**
     * Gets the error.
     * @return {@link Boolean}
     */
    public Boolean getError() {
        return error;
    }

    /**
     * Gets the failScripts.
     * @return {@link String}
     */
    public String getFailScripts() {
        return failScripts;
    }
    /**
     * Gets the id.
     * @return {@link Long}
     */
    public Long getId() {
        return id;
    }
    /**
     * Gets the isDieRoll.
     * @return {@link Boolean}
     */
    public Boolean getIsDieRoll() {
        return isDieRoll;
    }

    /**
     * Gets the mobCode.
     * @return {@link String}
     */
    public String getMobCode() {
        return mobCode;
    }
    /**
     * Gets the list of mobs.
     * @return {@link List}<{@link String}>
     */
    public List<String> getMobs() {
        return mobs;
    }
    /**
     * Gets the name.
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the numDieRolled.
     * @return {@link Long}
     */
    public Long getNumDieRolled() {
        return numDieRolled;
    }
    /**
     * Gets the origin.
     * @return {@link String}
     */
    public String getOrigin() {
        return origin;
    }
    /**
     * Gets the passScripts.
     * @return {@link String}
     */
    public String getPassScripts() {
        return passScripts;
    }

    /**
     * Gets the roomCode.
     * @return {@link String}
     */
    public String getRoomCode() {
        return roomCode;
    }
    /**
     * Gets the textName.
     * @return {@link String}
     */
    public String getTextName() {
        return textName;
    }
    /**
     * Gets the type.
     * @return {@link String}
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the amount.
     * @param val the new value
     */
    public void setAmount(final Long val) {
        amount = val;
    }
    /**
     * Sets the attribute.
     * @param val the new value
     */
    public void setAttribute(final String val) {
        attribute = val;
    }
    /**
     * Sets the destination.
     * @param val the new value
     */
    public void setDestination(final String val) {
        destination = val;
    }

    /**
     * Sets the direction.
     * @param val the new value
     */
    public void setDirection(final String val) {
        direction = val;
    }
    /**
     * Sets the doorName.
     * @param val the new value
     */
    public void setDoorName(final String val) {
        doorName = val;
    }
    /**
     * Sets the error.
     * @param val the new value
     */
    public void setError(final Boolean val) {
        error = val;
    }

    /**
     * Sets the failScripts.
     * @param val the new value
     */
    public void setFailScripts(final String val) {
        failScripts = val;
    }
    /**
     * Sets the id.
     * @param val the new value
     */
    public void setId(final Long val) {
        id = val;
    }
    /**
     * Sets the isDieRoll.
     * @param val the new value
     */
    public void setIsDieRoll(final Boolean val) {
        isDieRoll = val;
    }

    /**
     * Sets the mobCode.
     * @param val the new value
     */
    public void setMobCode(final String val) {
        mobCode = val;
    }
    /**
     * Sets the list of mobs.
     * @param val the new value
     */
    public void setMobs(final List<String> val) {
        mobs = val;
    }
    /**
     * Sets the name.
     * @param val the new value
     */
    public void setName(final String val) {
        name = val;
    }

    /**
     * Sets the numDieRolled.
     * @param val the new value
     */
    public void setNumDieRolled(final Long val) {
        numDieRolled = val;
    }
    /**
     * Sets the origin.
     * @param val the new value
     */
    public void setOrigin(final String val) {
        origin = val;
    }
    /**
     * Sets the passScripts.
     * @param val the new value
     */
    public void setPassScripts(final String val) {
        passScripts = val;
    }

    /**
     * Sets the roomCode.
     * @param val the new value
     */
    public void setRoomCode(final String val) {
        roomCode = val;
    }
    /**
     * Sets the textName.
     * @param val the new value
     */
    public void setTextName(final String val) {
        textName = val;
    }
    /**
     * Sets the type.
     * @param val the new value
     */
    public void setType(final String val) {
        type = val;
    }

}
