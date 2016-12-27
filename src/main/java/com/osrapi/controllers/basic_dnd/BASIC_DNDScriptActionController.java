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

import com.osrapi.models.basic_dnd.BASIC_DNDScriptActionEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDScriptActionRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/script_actions")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDScriptActionController {
    /** the static instance of {@link BASIC_DNDScriptActionController}. */
    private static BASIC_DNDScriptActionController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDScriptActionController}
     */
    public static BASIC_DNDScriptActionController getInstance() {
        if (instance == null) {
            new BASIC_DNDScriptActionController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDScriptActionRepository repository;
    /** Creates a new instance of {@link BASIC_DNDScriptActionController}. */
    public BASIC_DNDScriptActionController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDScriptActionEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptActionEntity>> getAll() {
        Iterator<BASIC_DNDScriptActionEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDScriptActionEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDScriptActionEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptActionEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDScriptActionEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDScriptActionEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionEntity>>();
        resources.add(getScriptActionResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDScriptActionEntity}.
     * @param entity the {@link BASIC_DNDScriptActionEntity}
     * @return {@link Resource}<{@link BASIC_DNDScriptActionEntity}>
     */
    private Resource<BASIC_DNDScriptActionEntity> getScriptActionResource(
            final BASIC_DNDScriptActionEntity entity) {
        Resource<BASIC_DNDScriptActionEntity> resource =
                new Resource<BASIC_DNDScriptActionEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDScriptActionEntity}s.
     * @param entities the list of {@link BASIC_DNDScriptActionEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDScriptActionEntity>> save(
            @RequestBody final List<BASIC_DNDScriptActionEntity> entities) {
        List<Resource<BASIC_DNDScriptActionEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionEntity>>();
        Iterator<BASIC_DNDScriptActionEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDScriptActionEntity}.
     * @param entity the {@link BASIC_DNDScriptActionEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDScriptActionEntity>> save(
            @RequestBody final BASIC_DNDScriptActionEntity entity) {
    
    
        BASIC_DNDScriptActionEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDScriptActionEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDScriptActionEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDScriptActionEntity entity) {
        List<BASIC_DNDScriptActionEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDScriptActionEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDScriptActionEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDScriptActionEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDScriptActionEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDScriptActionEntity}s.
     * @param entities the list of {@link BASIC_DNDScriptActionEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDScriptActionEntity>> update(
            @RequestBody final List<BASIC_DNDScriptActionEntity> entities) {
        List<Resource<BASIC_DNDScriptActionEntity>> resources = new ArrayList<Resource<BASIC_DNDScriptActionEntity>>();
        Iterator<BASIC_DNDScriptActionEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDScriptActionEntity}.
     * @param entity the {@link BASIC_DNDScriptActionEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDScriptActionEntity>> update(
            @RequestBody final BASIC_DNDScriptActionEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDScriptActionEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDScriptActionEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDScriptActionEntity}s that share a name.
     * @param name the script_action' name
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptActionEntity>> getByName(
            @PathVariable final String name) {
        Iterator<BASIC_DNDScriptActionEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<BASIC_DNDScriptActionEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDScriptActionEntity}s that share a type.
     * @param type the script_action' type
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionEntity}>>
     */
    @RequestMapping(path = "type/{type}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptActionEntity>> getByType(
            @PathVariable final String type) {
        Iterator<BASIC_DNDScriptActionEntity> iter = repository.findByType(type)
                .iterator();
        List<Resource<BASIC_DNDScriptActionEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
