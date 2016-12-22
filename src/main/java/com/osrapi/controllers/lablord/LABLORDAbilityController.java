package com.osrapi.controllers.lablord;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osrapi.models.lablord.LABLORDAbilityEntity;
import com.osrapi.repositories.lablord.LABLORDAbilityRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/abilities")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDAbilityController {
    /** the static instance of {@link LABLORDAbilityController}. */
    private static LABLORDAbilityController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDAbilityController}
     */
    public static LABLORDAbilityController getInstance() {
        if (instance == null) {
            new LABLORDAbilityController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDAbilityRepository repository;
    /** Creates a new instance of {@link LABLORDAbilityController}. */
    public LABLORDAbilityController() {
        instance = this;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDAbilityEntity}.
     * @param entity the {@link LABLORDAbilityEntity}
     * @return {@link Resource}<{@link LABLORDAbilityEntity}>
     */
    private Resource<LABLORDAbilityEntity> getAbilityResource(
            final LABLORDAbilityEntity entity) {
        Resource<LABLORDAbilityEntity> resource =
                new Resource<LABLORDAbilityEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Gets a list of {@link LABLORDAbilityEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityEntity>> getAll() {
        Iterator<LABLORDAbilityEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDAbilityEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityEntity>>();
        while (iter.hasNext()) {
            resources.add(getAbilityResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDAbilityEntity}s that share a code.
     * @param code the ability' code
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityEntity>> getByCode(
            @PathVariable
            final String code) {
        Iterator<LABLORDAbilityEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<LABLORDAbilityEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityEntity>>();
        while (iter.hasNext()) {
            resources.add(getAbilityResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDAbilityEntity}s that share a description.
     * @param description the ability' description
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityEntity>> getByDescription(
            @PathVariable
            final String description) {
        Iterator<LABLORDAbilityEntity> iter = repository
                .findByDescription(description)
                .iterator();
        List<Resource<LABLORDAbilityEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityEntity>>();
        while (iter.hasNext()) {
            resources.add(getAbilityResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDAbilityEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDAbilityEntity entity = repository.findOne(id);
        List<Resource<LABLORDAbilityEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityEntity>>();
        resources.add(getAbilityResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDAbilityEntity}s that share a name.
     * @param name the ability' name
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<LABLORDAbilityEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<LABLORDAbilityEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityEntity>>();
        while (iter.hasNext()) {
            resources.add(getAbilityResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link LABLORDAbilityEntity}.
     * @param entity the {@link LABLORDAbilityEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDAbilityEntity>> save(
            @RequestBody
            final LABLORDAbilityEntity entity) {

        LABLORDAbilityEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDAbilityEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDAbilityEntity}s.
     * @param entities the list of {@link LABLORDAbilityEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDAbilityEntity>> save(
            @RequestBody
            final List<LABLORDAbilityEntity> entities) {
        List<Resource<LABLORDAbilityEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityEntity>>();
        Iterator<LABLORDAbilityEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }

    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDAbilityEntity} instance
     */
    private void setIdFromRepository(final LABLORDAbilityEntity entity) {
        List<LABLORDAbilityEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDAbilityEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDAbilityEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDAbilityEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDAbilityEntity>) method.invoke(
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
     * Updates a single {@link LABLORDAbilityEntity}.
     * @param entity the {@link LABLORDAbilityEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDAbilityEntity>> update(
            @RequestBody
            final LABLORDAbilityEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }

        LABLORDAbilityEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDAbilityEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Updates multiple {@link LABLORDAbilityEntity}s.
     * @param entities the list of {@link LABLORDAbilityEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDAbilityEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDAbilityEntity>> update(
            @RequestBody
            final List<LABLORDAbilityEntity> entities) {
        List<Resource<LABLORDAbilityEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityEntity>>();
        Iterator<LABLORDAbilityEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
