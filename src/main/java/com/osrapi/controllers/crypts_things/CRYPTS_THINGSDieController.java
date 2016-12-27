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

import com.osrapi.models.crypts_things.CRYPTS_THINGSDieEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSDieRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/dies")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSDieController {
    /** the static instance of {@link CRYPTS_THINGSDieController}. */
    private static CRYPTS_THINGSDieController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSDieController}
     */
    public static CRYPTS_THINGSDieController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSDieController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSDieRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSDieController}. */
    public CRYPTS_THINGSDieController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSDieEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDieEntity>> getAll() {
        Iterator<CRYPTS_THINGSDieEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSDieEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDieEntity>>();
        while (iter.hasNext()) {
            resources.add(getDieResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSDieEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDieEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSDieEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSDieEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDieEntity>>();
        resources.add(getDieResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSDieEntity}.
     * @param entity the {@link CRYPTS_THINGSDieEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSDieEntity}>
     */
    private Resource<CRYPTS_THINGSDieEntity> getDieResource(
            final CRYPTS_THINGSDieEntity entity) {
        Resource<CRYPTS_THINGSDieEntity> resource =
                new Resource<CRYPTS_THINGSDieEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSDieEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSDieEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSDieEntity>> save(
            @RequestBody final List<CRYPTS_THINGSDieEntity> entities) {
        List<Resource<CRYPTS_THINGSDieEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDieEntity>>();
        Iterator<CRYPTS_THINGSDieEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSDieEntity}.
     * @param entity the {@link CRYPTS_THINGSDieEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSDieEntity>> save(
            @RequestBody final CRYPTS_THINGSDieEntity entity) {
    
    
        CRYPTS_THINGSDieEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSDieEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSDieEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSDieEntity entity) {
        List<CRYPTS_THINGSDieEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSDieEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSDieEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSDieEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSDieEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSDieEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSDieEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSDieEntity>> update(
            @RequestBody final List<CRYPTS_THINGSDieEntity> entities) {
        List<Resource<CRYPTS_THINGSDieEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSDieEntity>>();
        Iterator<CRYPTS_THINGSDieEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSDieEntity}.
     * @param entity the {@link CRYPTS_THINGSDieEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSDieEntity>> update(
            @RequestBody final CRYPTS_THINGSDieEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSDieEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSDieEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSDieEntity}s that share a code.
     * @param code the die' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDieEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSDieEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSDieEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDieEntity>>();
        while (iter.hasNext()) {
            resources.add(getDieResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSDieEntity}s that share a value.
     * @param value the die' value
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDieEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDieEntity>> getByValue(
            @PathVariable final Long value) {
        Iterator<CRYPTS_THINGSDieEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<CRYPTS_THINGSDieEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDieEntity>>();
        while (iter.hasNext()) {
            resources.add(getDieResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
