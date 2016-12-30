package com.osrapi.models.basic_dnd;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
@Table(name = "script_bundle", schema = "basic_dnd")
public final class BASIC_DNDScriptBundleEntity {
    /**
     * the primary key - an autogenerated id (unique for each user in the db).
     */
    @Id
    @Column(name = "script_bundle_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
  generator = "script_bundle_seq")
    @SequenceGenerator(
        name = "script_bundle_seq",
        sequenceName = "basic_dnd.script_bundle_id_seq",
        allocationSize = 1
    )
    private Long                    id;
    /** Creates a new instance of {@link BASIC_DNDScriptBundleEntity}. */
    public BASIC_DNDScriptBundleEntity() {
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

    /**
     * the list of {@link BASIC_DNDScriptActionEntity}s associated with this
     * {@link BASIC_DNDScriptBundleEntity}.
     */
    @OneToMany(targetEntity = BASIC_DNDScriptActionEntity.class,
      fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "script_bundle_scripts_lookup", schema = "basic_dnd",
  joinColumns = @JoinColumn(name = "script_bundle_id",
  referencedColumnName = "script_bundle_id"),
  inverseJoinColumns = @JoinColumn(name = "script_action_id",
  referencedColumnName = "script_action_id"))
    @JsonProperty("scripts")
    private List<BASIC_DNDScriptActionEntity>    scripts;
    /**
     * Gets the list of scriptss.
     * @return {@link List}<{@link BASIC_DNDScriptActionEntity}>
     */
    public List<BASIC_DNDScriptActionEntity> getScripts() {
        return scripts;
    }
    /**
     * Sets the list of scriptss.
     * @param val the new value
     */
    public void setScripts(final List<BASIC_DNDScriptActionEntity> val) {
        scripts = val;
    }

}

