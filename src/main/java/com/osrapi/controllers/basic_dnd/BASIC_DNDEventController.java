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

import com.osrapi.models.basic_dnd.BASIC_DNDEventEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDEventRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/events")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDEventController {
    /** the static instance of {@link BASIC_DNDEventController}. */
    private static BASIC_DNDEventController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDEventController}
     */
    public static BASIC_DNDEventController getInstance() {
        if (instance == null) {
            new BASIC_DNDEventController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDEventRepository repository;
    /** Creates a new instance of {@link BASIC_DNDEventController}. */
    public BASIC_DNDEventController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDEventEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEventEntity>> getAll() {
        Iterator<BASIC_DNDEventEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDEventEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEventEntity>>();
        while (iter.hasNext()) {
            resources.add(getEventResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDEventEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEventEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEventEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDEventEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDEventEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEventEntity>>();
        resources.add(getEventResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDEventEntity}.
     * @param entity the {@link BASIC_DNDEventEntity}
     * @return {@link Resource}<{@link BASIC_DNDEventEntity}>
     */
    private Resource<BASIC_DNDEventEntity> getEventResource(
            final BASIC_DNDEventEntity entity) {
        Resource<BASIC_DNDEventEntity> resource =
                new Resource<BASIC_DNDEventEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDEventEntity}s.
     * @param entities the list of {@link BASIC_DNDEventEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEventEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDEventEntity>> save(
            @RequestBody final List<BASIC_DNDEventEntity> entities) {
        List<Resource<BASIC_DNDEventEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEventEntity>>();
        Iterator<BASIC_DNDEventEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDEventEntity}.
     * @param entity the {@link BASIC_DNDEventEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDEventEntity>> save(
            @RequestBody final BASIC_DNDEventEntity entity) {
    
    
        BASIC_DNDEventEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDEventEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDEventEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDEventEntity entity) {
        List<BASIC_DNDEventEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDEventEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDEventEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDEventEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDEventEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDEventEntity}s.
     * @param entities the list of {@link BASIC_DNDEventEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEventEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDEventEntity>> update(
            @RequestBody final List<BASIC_DNDEventEntity> entities) {
        List<Resource<BASIC_DNDEventEntity>> resources = new ArrayList<Resource<BASIC_DNDEventEntity>>();
        Iterator<BASIC_DNDEventEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDEventEntity}.
     * @param entity the {@link BASIC_DNDEventEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDEventEntity>> update(
            @RequestBody final BASIC_DNDEventEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDEventEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDEventEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDEventEntity}s that share a code.
     * @param code the event' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEventEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEventEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDEventEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDEventEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEventEntity>>();
        while (iter.hasNext()) {
            resources.add(getEventResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
