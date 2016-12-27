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

import com.osrapi.models.crypts_things.CRYPTS_THINGSGenderEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSGenderRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/genders")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSGenderController {
    /** the static instance of {@link CRYPTS_THINGSGenderController}. */
    private static CRYPTS_THINGSGenderController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSGenderController}
     */
    public static CRYPTS_THINGSGenderController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSGenderController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSGenderRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSGenderController}. */
    public CRYPTS_THINGSGenderController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSGenderEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSGenderEntity>> getAll() {
        Iterator<CRYPTS_THINGSGenderEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSGenderEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSGenderEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSGenderEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSGenderEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSGenderEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGenderEntity>>();
        resources.add(getGenderResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSGenderEntity}.
     * @param entity the {@link CRYPTS_THINGSGenderEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSGenderEntity}>
     */
    private Resource<CRYPTS_THINGSGenderEntity> getGenderResource(
            final CRYPTS_THINGSGenderEntity entity) {
        Resource<CRYPTS_THINGSGenderEntity> resource =
                new Resource<CRYPTS_THINGSGenderEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSGenderEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSGenderEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSGenderEntity>> save(
            @RequestBody final List<CRYPTS_THINGSGenderEntity> entities) {
        List<Resource<CRYPTS_THINGSGenderEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGenderEntity>>();
        Iterator<CRYPTS_THINGSGenderEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSGenderEntity}.
     * @param entity the {@link CRYPTS_THINGSGenderEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSGenderEntity>> save(
            @RequestBody final CRYPTS_THINGSGenderEntity entity) {
    
    
        CRYPTS_THINGSGenderEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSGenderEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSGenderEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSGenderEntity entity) {
        List<CRYPTS_THINGSGenderEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSGenderEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSGenderEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSGenderEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSGenderEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSGenderEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSGenderEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSGenderEntity>> update(
            @RequestBody final List<CRYPTS_THINGSGenderEntity> entities) {
        List<Resource<CRYPTS_THINGSGenderEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSGenderEntity>>();
        Iterator<CRYPTS_THINGSGenderEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSGenderEntity}.
     * @param entity the {@link CRYPTS_THINGSGenderEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSGenderEntity>> update(
            @RequestBody final CRYPTS_THINGSGenderEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSGenderEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSGenderEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSGenderEntity}s that share a description.
     * @param description the gender' description
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSGenderEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CRYPTS_THINGSGenderEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CRYPTS_THINGSGenderEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSGenderEntity}s that share a name.
     * @param name the gender' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSGenderEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSGenderEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSGenderEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSGenderEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
