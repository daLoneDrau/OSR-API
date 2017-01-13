package com.osrapi.controllers.crypts_things;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osrapi.models.crypts_things.CRYPTS_THINGSSkillEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSSkillRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/skills")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSSkillController {
    /** the static instance of {@link CRYPTS_THINGSSkillController}. */
    private static CRYPTS_THINGSSkillController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSSkillController}
     */
    public static CRYPTS_THINGSSkillController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSSkillController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSSkillRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSSkillController}. */
    public CRYPTS_THINGSSkillController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSSkillEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSSkillEntity>> getAll() {
        Iterator<CRYPTS_THINGSSkillEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSSkillEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSSkillEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSSkillEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSSkillEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSSkillEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSSkillEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSSkillEntity>>();
        resources.add(getSkillResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSSkillEntity}.
     * @param entity the {@link CRYPTS_THINGSSkillEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSSkillEntity}>
     */
    private Resource<CRYPTS_THINGSSkillEntity> getSkillResource(
            final CRYPTS_THINGSSkillEntity entity) {
        Resource<CRYPTS_THINGSSkillEntity> resource =
                new Resource<CRYPTS_THINGSSkillEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSSkillEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSSkillEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSSkillEntity>> save(
            @RequestBody final List<CRYPTS_THINGSSkillEntity> entities) {
        List<Resource<CRYPTS_THINGSSkillEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSSkillEntity>>();
        Iterator<CRYPTS_THINGSSkillEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSSkillEntity}.
     * @param entity the {@link CRYPTS_THINGSSkillEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSSkillEntity>> save(
            @RequestBody final CRYPTS_THINGSSkillEntity entity) {
    
    
        CRYPTS_THINGSSkillEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSSkillEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSSkillEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSSkillEntity entity) {
        List<CRYPTS_THINGSSkillEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSSkillEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSSkillEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSSkillEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSSkillEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSSkillEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSSkillEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSSkillEntity>> update(
            @RequestBody final List<CRYPTS_THINGSSkillEntity> entities) {
        List<Resource<CRYPTS_THINGSSkillEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSSkillEntity>>();
        Iterator<CRYPTS_THINGSSkillEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSSkillEntity}.
     * @param entity the {@link CRYPTS_THINGSSkillEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSSkillEntity>> update(
            @RequestBody final CRYPTS_THINGSSkillEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSSkillEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSSkillEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSSkillEntity}s that share a description.
     * @param description the skill' description
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSSkillEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CRYPTS_THINGSSkillEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CRYPTS_THINGSSkillEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSSkillEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSSkillEntity}s that share a name.
     * @param name the skill' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSSkillEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSSkillEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSSkillEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSSkillEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSSkillEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
