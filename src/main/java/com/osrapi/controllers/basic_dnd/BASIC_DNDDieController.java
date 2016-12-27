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

import com.osrapi.models.basic_dnd.BASIC_DNDDieEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDDieRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/dies")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDDieController {
    /** the static instance of {@link BASIC_DNDDieController}. */
    private static BASIC_DNDDieController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDDieController}
     */
    public static BASIC_DNDDieController getInstance() {
        if (instance == null) {
            new BASIC_DNDDieController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDDieRepository repository;
    /** Creates a new instance of {@link BASIC_DNDDieController}. */
    public BASIC_DNDDieController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDDieEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDieEntity>> getAll() {
        Iterator<BASIC_DNDDieEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDDieEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDieEntity>>();
        while (iter.hasNext()) {
            resources.add(getDieResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDDieEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDieEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDDieEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDDieEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDieEntity>>();
        resources.add(getDieResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDDieEntity}.
     * @param entity the {@link BASIC_DNDDieEntity}
     * @return {@link Resource}<{@link BASIC_DNDDieEntity}>
     */
    private Resource<BASIC_DNDDieEntity> getDieResource(
            final BASIC_DNDDieEntity entity) {
        Resource<BASIC_DNDDieEntity> resource =
                new Resource<BASIC_DNDDieEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDDieEntity}s.
     * @param entities the list of {@link BASIC_DNDDieEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDDieEntity>> save(
            @RequestBody final List<BASIC_DNDDieEntity> entities) {
        List<Resource<BASIC_DNDDieEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDieEntity>>();
        Iterator<BASIC_DNDDieEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDDieEntity}.
     * @param entity the {@link BASIC_DNDDieEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDDieEntity>> save(
            @RequestBody final BASIC_DNDDieEntity entity) {
    
    
        BASIC_DNDDieEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDDieEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDDieEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDDieEntity entity) {
        List<BASIC_DNDDieEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDDieEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDDieEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDDieEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDDieEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDDieEntity}s.
     * @param entities the list of {@link BASIC_DNDDieEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDDieEntity>> update(
            @RequestBody final List<BASIC_DNDDieEntity> entities) {
        List<Resource<BASIC_DNDDieEntity>> resources = new ArrayList<Resource<BASIC_DNDDieEntity>>();
        Iterator<BASIC_DNDDieEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDDieEntity}.
     * @param entity the {@link BASIC_DNDDieEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDDieEntity>> update(
            @RequestBody final BASIC_DNDDieEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDDieEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDDieEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDDieEntity}s that share a code.
     * @param code the die' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDieEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDDieEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDDieEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDieEntity>>();
        while (iter.hasNext()) {
            resources.add(getDieResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDDieEntity}s that share a value.
     * @param value the die' value
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDieEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDieEntity>> getByValue(
            @PathVariable final Long value) {
        Iterator<BASIC_DNDDieEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<BASIC_DNDDieEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDieEntity>>();
        while (iter.hasNext()) {
            resources.add(getDieResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
