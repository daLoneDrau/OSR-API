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

import com.osrapi.models.crypts_things.CRYPTS_THINGSObjectTypeEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSObjectTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/object_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSObjectTypeController {
    /** the static instance of {@link CRYPTS_THINGSObjectTypeController}. */
    private static CRYPTS_THINGSObjectTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSObjectTypeController}
     */
    public static CRYPTS_THINGSObjectTypeController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSObjectTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSObjectTypeRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSObjectTypeController}. */
    public CRYPTS_THINGSObjectTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSObjectTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> getAll() {
        Iterator<CRYPTS_THINGSObjectTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSObjectTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSObjectTypeEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSObjectTypeEntity>>();
        resources.add(getObjectTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSObjectTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSObjectTypeEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>
     */
    private Resource<CRYPTS_THINGSObjectTypeEntity> getObjectTypeResource(
            final CRYPTS_THINGSObjectTypeEntity entity) {
        Resource<CRYPTS_THINGSObjectTypeEntity> resource =
                new Resource<CRYPTS_THINGSObjectTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSObjectTypeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSObjectTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> save(
            @RequestBody final List<CRYPTS_THINGSObjectTypeEntity> entities) {
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSObjectTypeEntity>>();
        Iterator<CRYPTS_THINGSObjectTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSObjectTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSObjectTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> save(
            @RequestBody final CRYPTS_THINGSObjectTypeEntity entity) {
    
    
        CRYPTS_THINGSObjectTypeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSObjectTypeEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSObjectTypeEntity entity) {
        List<CRYPTS_THINGSObjectTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSObjectTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSObjectTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSObjectTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSObjectTypeEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSObjectTypeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSObjectTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> update(
            @RequestBody final List<CRYPTS_THINGSObjectTypeEntity> entities) {
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSObjectTypeEntity>>();
        Iterator<CRYPTS_THINGSObjectTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSObjectTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSObjectTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> update(
            @RequestBody final CRYPTS_THINGSObjectTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSObjectTypeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSObjectTypeEntity}s that share a code.
     * @param code the object_type' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSObjectTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSObjectTypeEntity}s that share a flag.
     * @param flag the object_type' flag
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSObjectTypeEntity}>>
     */
    @RequestMapping(path = "flag/{flag}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSObjectTypeEntity>> getByFlag(
            @PathVariable final Long flag) {
        Iterator<CRYPTS_THINGSObjectTypeEntity> iter = repository.findByFlag(flag)
                .iterator();
        List<Resource<CRYPTS_THINGSObjectTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
