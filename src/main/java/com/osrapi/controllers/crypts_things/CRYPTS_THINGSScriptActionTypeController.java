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

import com.osrapi.models.crypts_things.CRYPTS_THINGSScriptActionTypeEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSScriptActionTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/script_action_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSScriptActionTypeController {
    /** the static instance of {@link CRYPTS_THINGSScriptActionTypeController}. */
    private static CRYPTS_THINGSScriptActionTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSScriptActionTypeController}
     */
    public static CRYPTS_THINGSScriptActionTypeController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSScriptActionTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSScriptActionTypeRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSScriptActionTypeController}. */
    public CRYPTS_THINGSScriptActionTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSScriptActionTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> getAll() {
        Iterator<CRYPTS_THINGSScriptActionTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSScriptActionTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSScriptActionTypeEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionTypeEntity>>();
        resources.add(getScriptActionTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSScriptActionTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptActionTypeEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>
     */
    private Resource<CRYPTS_THINGSScriptActionTypeEntity> getScriptActionTypeResource(
            final CRYPTS_THINGSScriptActionTypeEntity entity) {
        Resource<CRYPTS_THINGSScriptActionTypeEntity> resource =
                new Resource<CRYPTS_THINGSScriptActionTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSScriptActionTypeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSScriptActionTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> save(
            @RequestBody final List<CRYPTS_THINGSScriptActionTypeEntity> entities) {
        List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionTypeEntity>>();
        Iterator<CRYPTS_THINGSScriptActionTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSScriptActionTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptActionTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> save(
            @RequestBody final CRYPTS_THINGSScriptActionTypeEntity entity) {
    
    
        CRYPTS_THINGSScriptActionTypeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSScriptActionTypeEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSScriptActionTypeEntity entity) {
        List<CRYPTS_THINGSScriptActionTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSScriptActionTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSScriptActionTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSScriptActionTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSScriptActionTypeEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSScriptActionTypeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSScriptActionTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> update(
            @RequestBody final List<CRYPTS_THINGSScriptActionTypeEntity> entities) {
        List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSScriptActionTypeEntity>>();
        Iterator<CRYPTS_THINGSScriptActionTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSScriptActionTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptActionTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> update(
            @RequestBody final CRYPTS_THINGSScriptActionTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSScriptActionTypeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSScriptActionTypeEntity}s that share a code.
     * @param code the script_action_type' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptActionTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSScriptActionTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSScriptActionTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptActionTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptActionTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
