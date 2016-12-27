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

import com.osrapi.models.crypts_things.CRYPTS_THINGSScriptActionEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSScriptActionRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/script_actions")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSScriptActionController {
    /** the static instance of {@link CRYPTS_THINGSScriptActionController}. */
    private static CRYPTS_THINGSScriptActionController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSScriptActionController}
     */
    public static CRYPTS_THINGSScriptActionController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSScriptActionController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSScriptActionRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSScriptActionController}. */
    public CRYPTS_THINGSScriptActionController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSScriptActionEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> getAll() {
        Iterator<CRYPTS_THINGSScriptActionEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSScriptActionEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSScriptActionEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSScriptActionEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSScriptActionEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionEntity>>();
        resources.add(getScriptActionResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSScriptActionEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptActionEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>
     */
    private Resource<CRYPTS_THINGSScriptActionEntity> getScriptActionResource(
            final CRYPTS_THINGSScriptActionEntity entity) {
        Resource<CRYPTS_THINGSScriptActionEntity> resource =
                new Resource<CRYPTS_THINGSScriptActionEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSScriptActionEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSScriptActionEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> save(
            @RequestBody final List<CRYPTS_THINGSScriptActionEntity> entities) {
        List<Resource<CRYPTS_THINGSScriptActionEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionEntity>>();
        Iterator<CRYPTS_THINGSScriptActionEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSScriptActionEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptActionEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> save(
            @RequestBody final CRYPTS_THINGSScriptActionEntity entity) {
    
    
        CRYPTS_THINGSScriptActionEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSScriptActionEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSScriptActionEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSScriptActionEntity entity) {
        List<CRYPTS_THINGSScriptActionEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSScriptActionEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSScriptActionEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSScriptActionEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSScriptActionEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSScriptActionEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSScriptActionEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> update(
            @RequestBody final List<CRYPTS_THINGSScriptActionEntity> entities) {
        List<Resource<CRYPTS_THINGSScriptActionEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSScriptActionEntity>>();
        Iterator<CRYPTS_THINGSScriptActionEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSScriptActionEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptActionEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> update(
            @RequestBody final CRYPTS_THINGSScriptActionEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSScriptActionEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSScriptActionEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSScriptActionEntity}s that share a name.
     * @param name the script_action' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSScriptActionEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSScriptActionEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSScriptActionEntity}s that share a type.
     * @param type the script_action' type
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionEntity}>>
     */
    @RequestMapping(path = "type/{type}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptActionEntity>> getByType(
            @PathVariable final String type) {
        Iterator<CRYPTS_THINGSScriptActionEntity> iter = repository.findByType(type)
                .iterator();
        List<Resource<CRYPTS_THINGSScriptActionEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
