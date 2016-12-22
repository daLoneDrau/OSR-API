package com.osrapi.models.ff;

import java.util.ArrayList;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
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
@Table(name = "io_npc_data", schema = "ff")
public final class FFIoNpcDataEntity {
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "io_npc_data_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
  generator = "io_npc_data_seq")
    @SequenceGenerator(
        name = "io_npc_data_seq",
        sequenceName = "ff.io_npc_data_id_seq",
        allocationSize = 1
    )
    private Long                    id;
    /** Creates a new instance of {@link FFIoNpcDataEntity}. */
    public FFIoNpcDataEntity() {
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

    /** the xpvalue. */
    @Column(name = "xpvalue")
    @JsonProperty("xpvalue")
    
    private Long                    xpvalue;
    /**
     * Gets the xpvalue.
     * @return {@link Long}
     */
    public Long getXpvalue() {
        return xpvalue;
    }
    /**
     * Sets the xpvalue.
     * @param val the new value
     */
    public void setXpvalue(final Long val) {
        xpvalue = val;
    }

    /** the title. */
    @Column(name = "title")
    @JsonProperty("title")
    @NotNull
    private String                    title;
    /**
     * Gets the title.
     * @return {@link String}
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title.
     * @param val the new value
     */
    public void setTitle(final String val) {
        title = val;
    }

    /** the npcFlags. */
    @Column(name = "npc_flags")
    @JsonProperty("npc_flags")
    
    private Long                    npcFlags;
    /**
     * Gets the npcFlags.
     * @return {@link Long}
     */
    public Long getNpcFlags() {
        return npcFlags;
    }
    /**
     * Sets the npcFlags.
     * @param val the new value
     */
    public void setNpcFlags(final Long val) {
        npcFlags = val;
    }

    /** the name. */
    @Column(name = "name")
    @JsonProperty("name")
    @NotNull
    private String                    name;
    /**
     * Gets the name.
     * @return {@link String}
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name.
     * @param val the new value
     */
    public void setName(final String val) {
        name = val;
    }

    /** the maxmana. */
    @Column(name = "maxmana")
    @JsonProperty("maxmana")
    
    private Float                    maxmana;
    /**
     * Gets the maxmana.
     * @return {@link Float}
     */
    public Float getMaxmana() {
        return maxmana;
    }
    /**
     * Sets the maxmana.
     * @param val the new value
     */
    public void setMaxmana(final Float val) {
        maxmana = val;
    }

    /** the maxlife. */
    @Column(name = "maxlife")
    @JsonProperty("maxlife")
    
    private Float                    maxlife;
    /**
     * Gets the maxlife.
     * @return {@link Float}
     */
    public Float getMaxlife() {
        return maxlife;
    }
    /**
     * Sets the maxlife.
     * @param val the new value
     */
    public void setMaxlife(final Float val) {
        maxlife = val;
    }

    /** the mana. */
    @Column(name = "mana")
    @JsonProperty("mana")
    
    private Float                    mana;
    /**
     * Gets the mana.
     * @return {@link Float}
     */
    public Float getMana() {
        return mana;
    }
    /**
     * Sets the mana.
     * @param val the new value
     */
    public void setMana(final Float val) {
        mana = val;
    }

    /** the life. */
    @Column(name = "life")
    @JsonProperty("life")
    
    private Float                    life;
    /**
     * Gets the life.
     * @return {@link Float}
     */
    public Float getLife() {
        return life;
    }
    /**
     * Sets the life.
     * @param val the new value
     */
    public void setLife(final Float val) {
        life = val;
    }

    /** the gender. */
    @ManyToOne(targetEntity = FFGenderEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "gender")
    @JsonProperty("gender")
  @NotNull
    private FFGenderEntity    gender;
    /**
     * Gets the gender.
     * @return {@link FFGenderEntity}
     */
    public FFGenderEntity getGender() {
        return gender;
    }
    /**
     * Sets the gender.
     * @param val the new value
     */
    public void setGender(final FFGenderEntity val) {
        gender = val;
    }

    /** the damages. */
    @Column(name = "damages")
    @JsonProperty("damages")
    
    private Float                    damages;
    /**
     * Gets the damages.
     * @return {@link Float}
     */
    public Float getDamages() {
        return damages;
    }
    /**
     * Sets the damages.
     * @param val the new value
     */
    public void setDamages(final Float val) {
        damages = val;
    }

    /** the cuts. */
    @Column(name = "cuts")
    @JsonProperty("cuts")
    
    private Long                    cuts;
    /**
     * Gets the cuts.
     * @return {@link Long}
     */
    public Long getCuts() {
        return cuts;
    }
    /**
     * Sets the cuts.
     * @param val the new value
     */
    public void setCuts(final Long val) {
        cuts = val;
    }

    /** the cut. */
    @Column(name = "cut")
    @JsonProperty("cut")
    
    private Boolean                    cut;
    /**
     * Gets the cut.
     * @return {@link Boolean}
     */
    public Boolean getCut() {
        return cut;
    }
    /**
     * Sets the cut.
     * @param val the new value
     */
    public void setCut(final Boolean val) {
        cut = val;
    }

    /** the critical. */
    @Column(name = "critical")
    @JsonProperty("critical")
    
    private Float                    critical;
    /**
     * Gets the critical.
     * @return {@link Float}
     */
    public Float getCritical() {
        return critical;
    }
    /**
     * Sets the critical.
     * @param val the new value
     */
    public void setCritical(final Float val) {
        critical = val;
    }

    /** the collidTime. */
    @Column(name = "collid_time")
    @JsonProperty("collid_time")
    
    private Long                    collidTime;
    /**
     * Gets the collidTime.
     * @return {@link Long}
     */
    public Long getCollidTime() {
        return collidTime;
    }
    /**
     * Sets the collidTime.
     * @param val the new value
     */
    public void setCollidTime(final Long val) {
        collidTime = val;
    }

    /** the collidState. */
    @Column(name = "collid_state")
    @JsonProperty("collid_state")
    
    private Long                    collidState;
    /**
     * Gets the collidState.
     * @return {@link Long}
     */
    public Long getCollidState() {
        return collidState;
    }
    /**
     * Sets the collidState.
     * @param val the new value
     */
    public void setCollidState(final Long val) {
        collidState = val;
    }

    /** the climbCount. */
    @Column(name = "climb_count")
    @JsonProperty("climb_count")
    
    private Float                    climbCount;
    /**
     * Gets the climbCount.
     * @return {@link Float}
     */
    public Float getClimbCount() {
        return climbCount;
    }
    /**
     * Sets the climbCount.
     * @param val the new value
     */
    public void setClimbCount(final Float val) {
        climbCount = val;
    }

    /** the behaviorParam. */
    @Column(name = "behavior_param")
    @JsonProperty("behavior_param")
    
    private Float                    behaviorParam;
    /**
     * Gets the behaviorParam.
     * @return {@link Float}
     */
    public Float getBehaviorParam() {
        return behaviorParam;
    }
    /**
     * Sets the behaviorParam.
     * @param val the new value
     */
    public void setBehaviorParam(final Float val) {
        behaviorParam = val;
    }

    /** the behavior. */
    @Column(name = "behavior")
    @JsonProperty("behavior")
    @NotNull
    private long                    behavior;
    /**
     * Gets the behavior.
     * @return {@link long}
     */
    public long getBehavior() {
        return behavior;
    }
    /**
     * Sets the behavior.
     * @param val the new value
     */
    public void setBehavior(final long val) {
        behavior = val;
    }

    /** the internalScript. */
    @Column(name = "internal_script")
    @JsonProperty("internal_script")
    
    private String                    internalScript;
    /**
     * Gets the internalScript.
     * @return {@link String}
     */
    public String getInternalScript() {
        return internalScript;
    }
    /**
     * Sets the internalScript.
     * @param val the new value
     */
    public void setInternalScript(final String val) {
        internalScript = val;
    }

    @ElementCollection
    @CollectionTable(name = "io_npc_data_scripted_events_lookup",
  schema = "ff", joinColumns = @JoinColumn(name = "io_npc_data_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @JsonProperty("scripted_events")
    private Map<String, String> scriptedEvents;
    /**
     * Gets the map of scriptedEventss.
     * @return {@link Map}<{@link String}, {@link String}>
     */
    public Map<String, String> getScriptedEvents() {
        return scriptedEvents;
    }
    /**
     * Sets the mapping for scriptedEventss.
     * @param val the new value
     */
    public void setScriptedEvents(Map<String, String> val) {
        scriptedEvents = val;
    }

    @ElementCollection
    @CollectionTable(name = "io_npc_data_attributes_lookup",
  schema = "ff", joinColumns = @JoinColumn(name = "io_npc_data_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @JsonProperty("attributes")
    private Map<String, Integer> attributes;
    /**
     * Gets the map of attributes.
     * @return {@link Map}<{@link String}, {@link Integer}>
     */
    public Map<String, Integer> getAttributes() {
        return attributes;
    }
    /**
     * Sets the mapping for attributes.
     * @param val the new value
     */
    public void setAttributes(Map<String, Integer> val) {
        attributes = val;
    }

}

