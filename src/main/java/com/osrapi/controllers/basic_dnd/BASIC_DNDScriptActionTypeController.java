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

import com.osrapi.models.basic_dnd.BASIC_DNDScriptActionTypeEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDScriptActionTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/script_action_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDScriptActionTypeController {
    /** the static instance of {@link BASIC_DNDScriptActionTypeController}. */
    private static BASIC_DNDScriptActionTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDScriptActionTypeController}
     */
    public static BASIC_DNDScriptActionTypeController getInstance() {
        if (instance == null) {
            new BASIC_DNDScriptActionTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDScriptActionTypeRepository repository;
    /** Creates a new instance of {@link BASIC_DNDScriptActionTypeController}. */
    public BASIC_DNDScriptActionTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDScriptActionTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptActionTypeEntity>> getAll() {
        Iterator<BASIC_DNDScriptActionTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDScriptActionTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDScriptActionTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptActionTypeEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDScriptActionTypeEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDScriptActionTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionTypeEntity>>();
        resources.add(getScriptActionTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDScriptActionTypeEntity}.
     * @param entity the {@link BASIC_DNDScriptActionTypeEntity}
     * @return {@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>
     */
    private Resource<BASIC_DNDScriptActionTypeEntity> getScriptActionTypeResource(
            final BASIC_DNDScriptActionTypeEntity entity) {
        Resource<BASIC_DNDScriptActionTypeEntity> resource =
                new Resource<BASIC_DNDScriptActionTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDScriptActionTypeEntity}s.
     * @param entities the list of {@link BASIC_DNDScriptActionTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDScriptActionTypeEntity>> save(
            @RequestBody final List<BASIC_DNDScriptActionTypeEntity> entities) {
        List<Resource<BASIC_DNDScriptActionTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionTypeEntity>>();
        Iterator<BASIC_DNDScriptActionTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDScriptActionTypeEntity}.
     * @param entity the {@link BASIC_DNDScriptActionTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDScriptActionTypeEntity>> save(
            @RequestBody final BASIC_DNDScriptActionTypeEntity entity) {
    
    
        BASIC_DNDScriptActionTypeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDScriptActionTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDScriptActionTypeEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDScriptActionTypeEntity entity) {
        List<BASIC_DNDScriptActionTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDScriptActionTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDScriptActionTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDScriptActionTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDScriptActionTypeEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDScriptActionTypeEntity}s.
     * @param entities the list of {@link BASIC_DNDScriptActionTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDScriptActionTypeEntity>> update(
            @RequestBody final List<BASIC_DNDScriptActionTypeEntity> entities) {
        List<Resource<BASIC_DNDScriptActionTypeEntity>> resources = new ArrayList<Resource<BASIC_DNDScriptActionTypeEntity>>();
        Iterator<BASIC_DNDScriptActionTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDScriptActionTypeEntity}.
     * @param entity the {@link BASIC_DNDScriptActionTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDScriptActionTypeEntity>> update(
            @RequestBody final BASIC_DNDScriptActionTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDScriptActionTypeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDScriptActionTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDScriptActionTypeEntity}s that share a code.
     * @param code the script_action_type' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptActionTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDScriptActionTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDScriptActionTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptActionTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
