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

import com.osrapi.models.basic_dnd.BASIC_DNDAttributeEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDAttributeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/attributes")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDAttributeController {
    /** the static instance of {@link BASIC_DNDAttributeController}. */
    private static BASIC_DNDAttributeController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDAttributeController}
     */
    public static BASIC_DNDAttributeController getInstance() {
        if (instance == null) {
            new BASIC_DNDAttributeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDAttributeRepository repository;
    /** Creates a new instance of {@link BASIC_DNDAttributeController}. */
    public BASIC_DNDAttributeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDAttributeEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDAttributeEntity>> getAll() {
        Iterator<BASIC_DNDAttributeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDAttributeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDAttributeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDAttributeEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDAttributeEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDAttributeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDAttributeEntity>>();
        resources.add(getAttributeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDAttributeEntity}.
     * @param entity the {@link BASIC_DNDAttributeEntity}
     * @return {@link Resource}<{@link BASIC_DNDAttributeEntity}>
     */
    private Resource<BASIC_DNDAttributeEntity> getAttributeResource(
            final BASIC_DNDAttributeEntity entity) {
        Resource<BASIC_DNDAttributeEntity> resource =
                new Resource<BASIC_DNDAttributeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDAttributeEntity}s.
     * @param entities the list of {@link BASIC_DNDAttributeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDAttributeEntity>> save(
            @RequestBody final List<BASIC_DNDAttributeEntity> entities) {
        List<Resource<BASIC_DNDAttributeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDAttributeEntity>>();
        Iterator<BASIC_DNDAttributeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDAttributeEntity}.
     * @param entity the {@link BASIC_DNDAttributeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDAttributeEntity>> save(
            @RequestBody final BASIC_DNDAttributeEntity entity) {
    
    
        BASIC_DNDAttributeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDAttributeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDAttributeEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDAttributeEntity entity) {
        List<BASIC_DNDAttributeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDAttributeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDAttributeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDAttributeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDAttributeEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDAttributeEntity}s.
     * @param entities the list of {@link BASIC_DNDAttributeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDAttributeEntity>> update(
            @RequestBody final List<BASIC_DNDAttributeEntity> entities) {
        List<Resource<BASIC_DNDAttributeEntity>> resources = new ArrayList<Resource<BASIC_DNDAttributeEntity>>();
        Iterator<BASIC_DNDAttributeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDAttributeEntity}.
     * @param entity the {@link BASIC_DNDAttributeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDAttributeEntity>> update(
            @RequestBody final BASIC_DNDAttributeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDAttributeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDAttributeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDAttributeEntity}s that share a code.
     * @param code the attribute' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDAttributeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDAttributeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDAttributeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDAttributeEntity}s that share a description.
     * @param description the attribute' description
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDAttributeEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<BASIC_DNDAttributeEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<BASIC_DNDAttributeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDAttributeEntity}s that share a name.
     * @param name the attribute' name
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDAttributeEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDAttributeEntity>> getByName(
            @PathVariable final String name) {
        Iterator<BASIC_DNDAttributeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<BASIC_DNDAttributeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDAttributeEntity>>();
        while (iter.hasNext()) {
            resources.add(getAttributeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
