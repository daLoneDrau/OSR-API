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

import com.osrapi.models.basic_dnd.BASIC_DNDGroupEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDGroupRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/groups")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDGroupController {
    /** the static instance of {@link BASIC_DNDGroupController}. */
    private static BASIC_DNDGroupController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDGroupController}
     */
    public static BASIC_DNDGroupController getInstance() {
        if (instance == null) {
            new BASIC_DNDGroupController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDGroupRepository repository;
    /** Creates a new instance of {@link BASIC_DNDGroupController}. */
    public BASIC_DNDGroupController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDGroupEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDGroupEntity>> getAll() {
        Iterator<BASIC_DNDGroupEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDGroupEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDGroupEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGroupEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDGroupEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDGroupEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDGroupEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGroupEntity>>();
        resources.add(getGroupResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDGroupEntity}.
     * @param entity the {@link BASIC_DNDGroupEntity}
     * @return {@link Resource}<{@link BASIC_DNDGroupEntity}>
     */
    private Resource<BASIC_DNDGroupEntity> getGroupResource(
            final BASIC_DNDGroupEntity entity) {
        Resource<BASIC_DNDGroupEntity> resource =
                new Resource<BASIC_DNDGroupEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDGroupEntity}s.
     * @param entities the list of {@link BASIC_DNDGroupEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGroupEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDGroupEntity>> save(
            @RequestBody final List<BASIC_DNDGroupEntity> entities) {
        List<Resource<BASIC_DNDGroupEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGroupEntity>>();
        Iterator<BASIC_DNDGroupEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDGroupEntity}.
     * @param entity the {@link BASIC_DNDGroupEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDGroupEntity>> save(
            @RequestBody final BASIC_DNDGroupEntity entity) {
    
    
        BASIC_DNDGroupEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDGroupEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDGroupEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDGroupEntity entity) {
        List<BASIC_DNDGroupEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDGroupEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDGroupEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDGroupEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDGroupEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDGroupEntity}s.
     * @param entities the list of {@link BASIC_DNDGroupEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGroupEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDGroupEntity>> update(
            @RequestBody final List<BASIC_DNDGroupEntity> entities) {
        List<Resource<BASIC_DNDGroupEntity>> resources = new ArrayList<Resource<BASIC_DNDGroupEntity>>();
        Iterator<BASIC_DNDGroupEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDGroupEntity}.
     * @param entity the {@link BASIC_DNDGroupEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDGroupEntity>> update(
            @RequestBody final BASIC_DNDGroupEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDGroupEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDGroupEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDGroupEntity}s that share a name.
     * @param name the group' name
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGroupEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDGroupEntity>> getByName(
            @PathVariable final String name) {
        Iterator<BASIC_DNDGroupEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<BASIC_DNDGroupEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
