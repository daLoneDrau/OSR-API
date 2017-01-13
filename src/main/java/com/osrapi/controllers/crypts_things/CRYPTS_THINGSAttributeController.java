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

import com.osrapi.models.crypts_things.CRYPTS_THINGSAttributeEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSAttributeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/attributes")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSAttributeController {
    /** the static instance of {@link CRYPTS_THINGSAttributeController}. */
    private static CRYPTS_THINGSAttributeController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSAttributeController}
     */
    public static CRYPTS_THINGSAttributeController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSAttributeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSAttributeRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSAttributeController}. */
    public CRYPTS_THINGSAttributeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSAttributeEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> getAll() {
        Iterator<CRYPTS_THINGSAttributeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSAttributeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSAttributeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSAttributeEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSAttributeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSAttributeEntity>>();
        resources.add(getAttributeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSAttributeEntity}.
     * @param entity the {@link CRYPTS_THINGSAttributeEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>
     */
    private Resource<CRYPTS_THINGSAttributeEntity> getAttributeResource(
            final CRYPTS_THINGSAttributeEntity entity) {
        Resource<CRYPTS_THINGSAttributeEntity> resource =
                new Resource<CRYPTS_THINGSAttributeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSAttributeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSAttributeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> save(
            @RequestBody final List<CRYPTS_THINGSAttributeEntity> entities) {
        List<Resource<CRYPTS_THINGSAttributeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSAttributeEntity>>();
        Iterator<CRYPTS_THINGSAttributeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSAttributeEntity}.
     * @param entity the {@link CRYPTS_THINGSAttributeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> save(
            @RequestBody final CRYPTS_THINGSAttributeEntity entity) {
    
    
        CRYPTS_THINGSAttributeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSAttributeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSAttributeEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSAttributeEntity entity) {
        List<CRYPTS_THINGSAttributeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSAttributeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSAttributeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSAttributeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSAttributeEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSAttributeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSAttributeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> update(
            @RequestBody final List<CRYPTS_THINGSAttributeEntity> entities) {
        List<Resource<CRYPTS_THINGSAttributeEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSAttributeEntity>>();
        Iterator<CRYPTS_THINGSAttributeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSAttributeEntity}.
     * @param entity the {@link CRYPTS_THINGSAttributeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> update(
            @RequestBody final CRYPTS_THINGSAttributeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSAttributeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSAttributeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSAttributeEntity}s that share a code.
     * @param code the attribute' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSAttributeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSAttributeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSAttributeEntity}s that share a description.
     * @param description the attribute' description
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CRYPTS_THINGSAttributeEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CRYPTS_THINGSAttributeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSAttributeEntity}s that share a name.
     * @param name the attribute' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSAttributeEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSAttributeEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSAttributeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSAttributeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
