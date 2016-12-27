package com.osrapi.controllers.basic_dnd;

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

import com.osrapi.models.basic_dnd.BASIC_DNDObjectTypeEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDObjectTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/object_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDObjectTypeController {
    /** the static instance of {@link BASIC_DNDObjectTypeController}. */
    private static BASIC_DNDObjectTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDObjectTypeController}
     */
    public static BASIC_DNDObjectTypeController getInstance() {
        if (instance == null) {
            new BASIC_DNDObjectTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDObjectTypeRepository repository;
    /** Creates a new instance of {@link BASIC_DNDObjectTypeController}. */
    public BASIC_DNDObjectTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDObjectTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDObjectTypeEntity>> getAll() {
        Iterator<BASIC_DNDObjectTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDObjectTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDObjectTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDObjectTypeEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDObjectTypeEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDObjectTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDObjectTypeEntity>>();
        resources.add(getObjectTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDObjectTypeEntity}.
     * @param entity the {@link BASIC_DNDObjectTypeEntity}
     * @return {@link Resource}<{@link BASIC_DNDObjectTypeEntity}>
     */
    private Resource<BASIC_DNDObjectTypeEntity> getObjectTypeResource(
            final BASIC_DNDObjectTypeEntity entity) {
        Resource<BASIC_DNDObjectTypeEntity> resource =
                new Resource<BASIC_DNDObjectTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDObjectTypeEntity}s.
     * @param entities the list of {@link BASIC_DNDObjectTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDObjectTypeEntity>> save(
            @RequestBody final List<BASIC_DNDObjectTypeEntity> entities) {
        List<Resource<BASIC_DNDObjectTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDObjectTypeEntity>>();
        Iterator<BASIC_DNDObjectTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDObjectTypeEntity}.
     * @param entity the {@link BASIC_DNDObjectTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDObjectTypeEntity>> save(
            @RequestBody final BASIC_DNDObjectTypeEntity entity) {
    
    
        BASIC_DNDObjectTypeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDObjectTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDObjectTypeEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDObjectTypeEntity entity) {
        List<BASIC_DNDObjectTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDObjectTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDObjectTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDObjectTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDObjectTypeEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDObjectTypeEntity}s.
     * @param entities the list of {@link BASIC_DNDObjectTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDObjectTypeEntity>> update(
            @RequestBody final List<BASIC_DNDObjectTypeEntity> entities) {
        List<Resource<BASIC_DNDObjectTypeEntity>> resources = new ArrayList<Resource<BASIC_DNDObjectTypeEntity>>();
        Iterator<BASIC_DNDObjectTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDObjectTypeEntity}.
     * @param entity the {@link BASIC_DNDObjectTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDObjectTypeEntity>> update(
            @RequestBody final BASIC_DNDObjectTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDObjectTypeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDObjectTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDObjectTypeEntity}s that share a code.
     * @param code the object_type' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDObjectTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDObjectTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDObjectTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDObjectTypeEntity}s that share a flag.
     * @param flag the object_type' flag
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDObjectTypeEntity}>>
     */
    @RequestMapping(path = "flag/{flag}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDObjectTypeEntity>> getByFlag(
            @PathVariable final Long flag) {
        Iterator<BASIC_DNDObjectTypeEntity> iter = repository.findByFlag(flag)
                .iterator();
        List<Resource<BASIC_DNDObjectTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDObjectTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getObjectTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
