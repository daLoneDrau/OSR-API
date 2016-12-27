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

import com.osrapi.models.basic_dnd.BASIC_DNDGenderEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDGenderRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/genders")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDGenderController {
    /** the static instance of {@link BASIC_DNDGenderController}. */
    private static BASIC_DNDGenderController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDGenderController}
     */
    public static BASIC_DNDGenderController getInstance() {
        if (instance == null) {
            new BASIC_DNDGenderController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDGenderRepository repository;
    /** Creates a new instance of {@link BASIC_DNDGenderController}. */
    public BASIC_DNDGenderController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDGenderEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDGenderEntity>> getAll() {
        Iterator<BASIC_DNDGenderEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDGenderEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDGenderEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDGenderEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDGenderEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDGenderEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGenderEntity>>();
        resources.add(getGenderResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDGenderEntity}.
     * @param entity the {@link BASIC_DNDGenderEntity}
     * @return {@link Resource}<{@link BASIC_DNDGenderEntity}>
     */
    private Resource<BASIC_DNDGenderEntity> getGenderResource(
            final BASIC_DNDGenderEntity entity) {
        Resource<BASIC_DNDGenderEntity> resource =
                new Resource<BASIC_DNDGenderEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDGenderEntity}s.
     * @param entities the list of {@link BASIC_DNDGenderEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDGenderEntity>> save(
            @RequestBody final List<BASIC_DNDGenderEntity> entities) {
        List<Resource<BASIC_DNDGenderEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGenderEntity>>();
        Iterator<BASIC_DNDGenderEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDGenderEntity}.
     * @param entity the {@link BASIC_DNDGenderEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDGenderEntity>> save(
            @RequestBody final BASIC_DNDGenderEntity entity) {
    
    
        BASIC_DNDGenderEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDGenderEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDGenderEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDGenderEntity entity) {
        List<BASIC_DNDGenderEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDGenderEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDGenderEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDGenderEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDGenderEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDGenderEntity}s.
     * @param entities the list of {@link BASIC_DNDGenderEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDGenderEntity>> update(
            @RequestBody final List<BASIC_DNDGenderEntity> entities) {
        List<Resource<BASIC_DNDGenderEntity>> resources = new ArrayList<Resource<BASIC_DNDGenderEntity>>();
        Iterator<BASIC_DNDGenderEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDGenderEntity}.
     * @param entity the {@link BASIC_DNDGenderEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDGenderEntity>> update(
            @RequestBody final BASIC_DNDGenderEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDGenderEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDGenderEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDGenderEntity}s that share a description.
     * @param description the gender' description
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDGenderEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<BASIC_DNDGenderEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<BASIC_DNDGenderEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDGenderEntity}s that share a name.
     * @param name the gender' name
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDGenderEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDGenderEntity>> getByName(
            @PathVariable final String name) {
        Iterator<BASIC_DNDGenderEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<BASIC_DNDGenderEntity>> resources =
                new ArrayList<Resource<BASIC_DNDGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
