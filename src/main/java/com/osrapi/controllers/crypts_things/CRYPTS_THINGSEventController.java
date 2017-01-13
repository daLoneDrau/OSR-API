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

import com.osrapi.models.crypts_things.CRYPTS_THINGSEventEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSEventRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/events")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSEventController {
    /** the static instance of {@link CRYPTS_THINGSEventController}. */
    private static CRYPTS_THINGSEventController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSEventController}
     */
    public static CRYPTS_THINGSEventController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSEventController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSEventRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSEventController}. */
    public CRYPTS_THINGSEventController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSEventEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEventEntity>> getAll() {
        Iterator<CRYPTS_THINGSEventEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEventEntity>>();
        while (iter.hasNext()) {
            resources.add(getEventResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSEventEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEventEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEventEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSEventEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEventEntity>>();
        resources.add(getEventResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSEventEntity}.
     * @param entity the {@link CRYPTS_THINGSEventEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSEventEntity}>
     */
    private Resource<CRYPTS_THINGSEventEntity> getEventResource(
            final CRYPTS_THINGSEventEntity entity) {
        Resource<CRYPTS_THINGSEventEntity> resource =
                new Resource<CRYPTS_THINGSEventEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSEventEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSEventEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEventEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSEventEntity>> save(
            @RequestBody final List<CRYPTS_THINGSEventEntity> entities) {
        List<Resource<CRYPTS_THINGSEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEventEntity>>();
        Iterator<CRYPTS_THINGSEventEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSEventEntity}.
     * @param entity the {@link CRYPTS_THINGSEventEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSEventEntity>> save(
            @RequestBody final CRYPTS_THINGSEventEntity entity) {
    
    
        CRYPTS_THINGSEventEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSEventEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSEventEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSEventEntity entity) {
        List<CRYPTS_THINGSEventEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSEventEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSEventEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSEventEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSEventEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSEventEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSEventEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEventEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSEventEntity>> update(
            @RequestBody final List<CRYPTS_THINGSEventEntity> entities) {
        List<Resource<CRYPTS_THINGSEventEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSEventEntity>>();
        Iterator<CRYPTS_THINGSEventEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSEventEntity}.
     * @param entity the {@link CRYPTS_THINGSEventEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSEventEntity>> update(
            @RequestBody final CRYPTS_THINGSEventEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSEventEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSEventEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSEventEntity}s that share a code.
     * @param code the event' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEventEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEventEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSEventEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEventEntity>>();
        while (iter.hasNext()) {
            resources.add(getEventResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
