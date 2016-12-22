package com.osrapi.controllers.lablord;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osrapi.models.lablord.LABLORDCharacterEntity;
import com.osrapi.repositories.lablord.LABLORDCharacterRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/characters")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDCharacterController {
    /** the static instance of {@link LABLORDCharacterController}. */
    private static LABLORDCharacterController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDCharacterController}
     */
    public static LABLORDCharacterController getInstance() {
        if (instance == null) {
            new LABLORDCharacterController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDCharacterRepository repository;
    /** Creates a new instance of {@link LABLORDCharacterController}. */
    public LABLORDCharacterController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getAll() {
        Iterator<LABLORDCharacterEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a armorClass.
     * @param armorClass the character' armorClass
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "armor_class/{armorClass}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByArmorClass(
            @PathVariable
            final Long armorClass) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByArmorClass(armorClass)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a charisma.
     * @param charisma the character' charisma
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "charisma/{charisma}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByCharisma(
            @PathVariable
            final Long charisma) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByCharisma(charisma)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a constitution.
     * @param constitution the character' constitution
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "constitution/{constitution}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByConstitution(
            @PathVariable
            final Long constitution) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByConstitution(constitution)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a dexterity.
     * @param dexterity the character' dexterity
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "dexterity/{dexterity}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByDexterity(
            @PathVariable
            final Long dexterity) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByDexterity(dexterity)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a experience.
     * @param experience the character' experience
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "experience/{experience}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByExperience(
            @PathVariable
            final Long experience) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByExperience(experience)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a hitPoints.
     * @param hitPoints the character' hitPoints
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "hit_points/{hitPoints}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByHitPoints(
            @PathVariable
            final Long hitPoints) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByHitPoints(hitPoints)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDCharacterEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDCharacterEntity entity = repository.findOne(id);
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        resources.add(getCharacterResource(entity));
        entity = null;
        return resources;
    }

    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a intelligence.
     * @param intelligence the character' intelligence
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "intelligence/{intelligence}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByIntelligence(
            @PathVariable
            final Long intelligence) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByIntelligence(intelligence)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a level.
     * @param level the character' level
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "level/{level}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByLevel(
            @PathVariable
            final Long level) {
        Iterator<LABLORDCharacterEntity> iter = repository.findByLevel(level)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a maxHitPoints.
     * @param maxHitPoints the character' maxHitPoints
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "max_hit_points/{maxHitPoints}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByMaxHitPoints(
            @PathVariable
            final Long maxHitPoints) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByMaxHitPoints(maxHitPoints)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a name.
     * @param name the character' name
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<LABLORDCharacterEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a strength.
     * @param strength the character' strength
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "strength/{strength}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByStrength(
            @PathVariable
            final Long strength) {
        Iterator<LABLORDCharacterEntity> iter = repository
                .findByStrength(strength)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDCharacterEntity}s that share a wisdom.
     * @param wisdom the character' wisdom
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "wisdom/{wisdom}", method = RequestMethod.GET)
    public List<Resource<LABLORDCharacterEntity>> getByWisdom(
            @PathVariable
            final Long wisdom) {
        Iterator<LABLORDCharacterEntity> iter = repository.findByWisdom(wisdom)
                .iterator();
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDCharacterEntity}.
     * @param entity the {@link LABLORDCharacterEntity}
     * @return {@link Resource}<{@link LABLORDCharacterEntity}>
     */
    private Resource<LABLORDCharacterEntity> getCharacterResource(
            final LABLORDCharacterEntity entity) {
        Resource<LABLORDCharacterEntity> resource =
                new Resource<LABLORDCharacterEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves a single {@link LABLORDCharacterEntity}.
     * @param entity the {@link LABLORDCharacterEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDCharacterEntity>> save(
            @RequestBody
            final LABLORDCharacterEntity entity) {

        LABLORDCharacterEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDCharacterEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDCharacterEntity}s.
     * @param entities the list of {@link LABLORDCharacterEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDCharacterEntity>> save(
            @RequestBody
            final List<LABLORDCharacterEntity> entities) {
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        Iterator<LABLORDCharacterEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDCharacterEntity} instance
     */
    private void setIdFromRepository(final LABLORDCharacterEntity entity) {
        List<LABLORDCharacterEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDCharacterEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDCharacterEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDCharacterEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDCharacterEntity>) method.invoke(
                                repository, (String) field.get(entity));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (old != null
                && old.size() == 1) {
            entity.setId(old.get(0).getId());
        }
        old = null;
    }
    /**
     * Updates a single {@link LABLORDCharacterEntity}.
     * @param entity the {@link LABLORDCharacterEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDCharacterEntity>> update(
            @RequestBody
            final LABLORDCharacterEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }

        LABLORDCharacterEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDCharacterEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Updates multiple {@link LABLORDCharacterEntity}s.
     * @param entities the list of {@link LABLORDCharacterEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDCharacterEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDCharacterEntity>> update(
            @RequestBody
            final List<LABLORDCharacterEntity> entities) {
        List<Resource<LABLORDCharacterEntity>> resources =
                new ArrayList<Resource<LABLORDCharacterEntity>>();
        Iterator<LABLORDCharacterEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
