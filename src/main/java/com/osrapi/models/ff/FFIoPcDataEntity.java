package com.osrapi.models.ff;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author drau
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "io_pc_data", schema = "ff")
public final class FFIoPcDataEntity {
    @ElementCollection
    @CollectionTable(name = "io_pc_data_attributes_lookup",
            schema = "ff", joinColumns = @JoinColumn(name = "io_pc_data_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @JsonProperty("attributes")
    private Map<String, Integer> attributes;
    /**
     * the list of equippedItems associated with this {@link FFIoPcDataEntity}.
     */
    @Column
    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "io_pc_data_equipped_items_lookup", schema = "ff",
            joinColumns = { @JoinColumn(name = "io_pc_data_id") })
    @JsonProperty("equipped_items")
    private List<Integer> equippedItems;
    /** the flags. */
    @Column(name = "flags")
    @JsonProperty("flags")

    private Long flags;
    /** the gender. */
    @ManyToOne(targetEntity = FFGenderEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "gender")
    @JsonProperty("gender")
    @NotNull
    private FFGenderEntity gender;

    /** the gold. */
    @Column(name = "gold")
    @JsonProperty("gold")

    private Float gold;
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "io_pc_data_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "io_pc_data_seq")
    @SequenceGenerator(
            name = "io_pc_data_seq",
            sequenceName = "ff.io_pc_data_id_seq",
            allocationSize = 1)
    private Long id;
    /** the interfaceFlags. */
    @Column(name = "interface_flags")
    @JsonProperty("interface_flags")

    private Long interfaceFlags;

    /** the internalScript. */
    @Column(name = "internal_script")
    @JsonProperty("internal_script")

    private String internalScript;
    /**
     * the list of keyring associated with this {@link FFIoPcDataEntity}.
     */
    @Column
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "io_pc_data_keyring_lookup", schema = "ff",
            joinColumns = { @JoinColumn(name = "io_pc_data_id") })
    @JsonProperty("keyring")
    private List<String> keyring;
    /** the level. */
    @Column(name = "level")
    @JsonProperty("level")
    @NotNull
    private long level;

    /** the name. */
    @Column(name = "name")
    @JsonProperty("name")
    @NotNull
    private String name;
    /** the profession. */
    @Column(name = "profession")
    @JsonProperty("profession")
    @NotNull
    private long profession;
    /** the race. */
    @Column(name = "race")
    @JsonProperty("race")
    @NotNull
    private long race;

    @ElementCollection
    @CollectionTable(name = "io_pc_data_scripted_events_lookup",
            schema = "ff", joinColumns = @JoinColumn(name = "io_pc_data_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @JsonProperty("scripted_events")
    private Map<String, String> scriptedEvents;
    /** the xp. */
    @Column(name = "xp")
    @JsonProperty("xp")
    @NotNull
    private long xp;
    /** Creates a new instance of {@link FFIoPcDataEntity}. */
    public FFIoPcDataEntity() {
        super();
    }

    /**
     * Gets the map of attributes.
     * @return {@link Map}<{@link String}, {@link Integer}>
     */
    public Map<String, Integer> getAttributes() {
        return attributes;
    }
    /**
     * Gets the list of equippedItems.
     * @return {@link List}<{@link Integer}>
     */
    public List<Integer> getEquippedItems() {
        return equippedItems;
    }
    /**
     * Gets the flags.
     * @return {@link Long}
     */
    public Long getFlags() {
        return flags;
    }

    /**
     * Gets the gender.
     * @return {@link FFGenderEntity}
     */
    public FFGenderEntity getGender() {
        return gender;
    }
    /**
     * Gets the gold.
     * @return {@link Float}
     */
    public Float getGold() {
        return gold;
    }
    /**
     * Gets the id.
     * @return {@link Long}
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the interfaceFlags.
     * @return {@link Long}
     */
    public Long getInterfaceFlags() {
        return interfaceFlags;
    }
    /**
     * Gets the internalScript.
     * @return {@link String}
     */
    public String getInternalScript() {
        return internalScript;
    }
    /**
     * Gets the list of keyring.
     * @return {@link List}<{@link String}>
     */
    public List<String> getKeyring() {
        return keyring;
    }

    /**
     * Gets the level.
     * @return {@link long}
     */
    public long getLevel() {
        return level;
    }
    /**
     * Gets the name.
     * @return {@link String}
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the profession.
     * @return {@link long}
     */
    public long getProfession() {
        return profession;
    }

    /**
     * Gets the race.
     * @return {@link long}
     */
    public long getRace() {
        return race;
    }
    /**
     * Gets the map of scriptedEventss.
     * @return {@link Map}<{@link String}, {@link String}>
     */
    public Map<String, String> getScriptedEvents() {
        return scriptedEvents;
    }
    /**
     * Gets the xp.
     * @return {@link long}
     */
    public long getXp() {
        return xp;
    }

    /**
     * Sets the mapping for attributes.
     * @param val the new value
     */
    public void setAttributes(Map<String, Integer> val) {
        attributes = val;
    }
    /**
     * Sets the list of equippedItems.
     * @param val the new value
     */
    public void setEquippedItems(final List<Integer> val) {
        equippedItems = val;
    }
    /**
     * Sets the flags.
     * @param val the new value
     */
    public void setFlags(final Long val) {
        flags = val;
    }

    /**
     * Sets the gender.
     * @param val the new value
     */
    public void setGender(final FFGenderEntity val) {
        gender = val;
    }
    /**
     * Sets the gold.
     * @param val the new value
     */
    public void setGold(final Float val) {
        gold = val;
    }
    /**
     * Sets the id.
     * @param val the new value
     */
    public void setId(final Long val) {
        id = val;
    }

    /**
     * Sets the interfaceFlags.
     * @param val the new value
     */
    public void setInterfaceFlags(final Long val) {
        interfaceFlags = val;
    }
    /**
     * Sets the internalScript.
     * @param val the new value
     */
    public void setInternalScript(final String val) {
        internalScript = val;
    }
    /**
     * Sets the list of keyring.
     * @param val the new value
     */
    public void setKeyring(final List<String> val) {
        keyring = val;
    }

    /**
     * Sets the level.
     * @param val the new value
     */
    public void setLevel(final long val) {
        level = val;
    }
    /**
     * Sets the name.
     * @param val the new value
     */
    public void setName(final String val) {
        name = val;
    }
    /**
     * Sets the profession.
     * @param val the new value
     */
    public void setProfession(final long val) {
        profession = val;
    }

    /**
     * Sets the race.
     * @param val the new value
     */
    public void setRace(final long val) {
        race = val;
    }
    /**
     * Sets the mapping for scriptedEventss.
     * @param val the new value
     */
    public void setScriptedEvents(Map<String, String> val) {
        scriptedEvents = val;
    }
    /**
     * Sets the xp.
     * @param val the new value
     */
    public void setXp(final long val) {
        xp = val;
    }

}
