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

import com.osrapi.models.crypts_things.CRYPTS_THINGSGroupEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSGroupRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/groups")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSGroupController {
    /** the static instance of {@link CRYPTS_THINGSGroupController}. */
    private static CRYPTS_THINGSGroupController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSGroupController}
     */
    public static CRYPTS_THINGSGroupController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSGroupController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSGroupRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSGroupController}. */
    public CRYPTS_THINGSGroupController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSGroupEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSGroupEntity>> getAll() {
        Iterator<CRYPTS_THINGSGroupEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSGroupEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSGroupEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGroupEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSGroupEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSGroupEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSGroupEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGroupEntity>>();
        resources.add(getGroupResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSGroupEntity}.
     * @param entity the {@link CRYPTS_THINGSGroupEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSGroupEntity}>
     */
    private Resource<CRYPTS_THINGSGroupEntity> getGroupResource(
            final CRYPTS_THINGSGroupEntity entity) {
        Resource<CRYPTS_THINGSGroupEntity> resource =
                new Resource<CRYPTS_THINGSGroupEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSGroupEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSGroupEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGroupEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSGroupEntity>> save(
            @RequestBody final List<CRYPTS_THINGSGroupEntity> entities) {
        List<Resource<CRYPTS_THINGSGroupEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGroupEntity>>();
        Iterator<CRYPTS_THINGSGroupEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSGroupEntity}.
     * @param entity the {@link CRYPTS_THINGSGroupEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSGroupEntity>> save(
            @RequestBody final CRYPTS_THINGSGroupEntity entity) {
    
    
        CRYPTS_THINGSGroupEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSGroupEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSGroupEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSGroupEntity entity) {
        List<CRYPTS_THINGSGroupEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSGroupEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSGroupEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSGroupEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSGroupEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSGroupEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSGroupEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGroupEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSGroupEntity>> update(
            @RequestBody final List<CRYPTS_THINGSGroupEntity> entities) {
        List<Resource<CRYPTS_THINGSGroupEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSGroupEntity>>();
        Iterator<CRYPTS_THINGSGroupEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSGroupEntity}.
     * @param entity the {@link CRYPTS_THINGSGroupEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSGroupEntity>> update(
            @RequestBody final CRYPTS_THINGSGroupEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSGroupEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSGroupEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSGroupEntity}s that share a name.
     * @param name the group' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGroupEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSGroupEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSGroupEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSGroupEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
