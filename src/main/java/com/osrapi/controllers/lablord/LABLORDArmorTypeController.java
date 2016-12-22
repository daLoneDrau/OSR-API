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

import com.osrapi.models.lablord.LABLORDArmorTypeEntity;
import com.osrapi.repositories.lablord.LABLORDArmorTypeRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/armor_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDArmorTypeController {
    /** the static instance of {@link LABLORDArmorTypeController}. */
    private static LABLORDArmorTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDArmorTypeController}
     */
    public static LABLORDArmorTypeController getInstance() {
        if (instance == null) {
            new LABLORDArmorTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDArmorTypeRepository repository;
    /** Creates a new instance of {@link LABLORDArmorTypeController}. */
    public LABLORDArmorTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDArmorTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDArmorTypeEntity>> getAll() {
        Iterator<LABLORDArmorTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDArmorTypeEntity>> resources =
                new ArrayList<Resource<LABLORDArmorTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmorTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDArmorTypeEntity}.
     * @param entity the {@link LABLORDArmorTypeEntity}
     * @return {@link Resource}<{@link LABLORDArmorTypeEntity}>
     */
    private Resource<LABLORDArmorTypeEntity> getArmorTypeResource(
            final LABLORDArmorTypeEntity entity) {
        Resource<LABLORDArmorTypeEntity> resource =
                new Resource<LABLORDArmorTypeEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Gets a list of {@link LABLORDArmorTypeEntity}s that share a code.
     * @param code the armor_type' code
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<LABLORDArmorTypeEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<LABLORDArmorTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<LABLORDArmorTypeEntity>> resources =
                new ArrayList<Resource<LABLORDArmorTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmorTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDArmorTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDArmorTypeEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDArmorTypeEntity entity = repository.findOne(id);
        List<Resource<LABLORDArmorTypeEntity>> resources =
                new ArrayList<Resource<LABLORDArmorTypeEntity>>();
        resources.add(getArmorTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDArmorTypeEntity}s that share a name.
     * @param name the armor_type' name
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<LABLORDArmorTypeEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<LABLORDArmorTypeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<LABLORDArmorTypeEntity>> resources =
                new ArrayList<Resource<LABLORDArmorTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmorTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link LABLORDArmorTypeEntity}.
     * @param entity the {@link LABLORDArmorTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDArmorTypeEntity>> save(
            @RequestBody
            final LABLORDArmorTypeEntity entity) {

        LABLORDArmorTypeEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDArmorTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDArmorTypeEntity}s.
     * @param entities the list of {@link LABLORDArmorTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDArmorTypeEntity>> save(
            @RequestBody
            final List<LABLORDArmorTypeEntity> entities) {
        List<Resource<LABLORDArmorTypeEntity>> resources =
                new ArrayList<Resource<LABLORDArmorTypeEntity>>();
        Iterator<LABLORDArmorTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDArmorTypeEntity} instance
     */
    private void setIdFromRepository(final LABLORDArmorTypeEntity entity) {
        List<LABLORDArmorTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDArmorTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDArmorTypeEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDArmorTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDArmorTypeEntity>) method.invoke(
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
     * Updates a single {@link LABLORDArmorTypeEntity}.
     * @param entity the {@link LABLORDArmorTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDArmorTypeEntity>> update(
            @RequestBody
            final LABLORDArmorTypeEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }

        LABLORDArmorTypeEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDArmorTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Updates multiple {@link LABLORDArmorTypeEntity}s.
     * @param entities the list of {@link LABLORDArmorTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDArmorTypeEntity>> update(
            @RequestBody
            final List<LABLORDArmorTypeEntity> entities) {
        List<Resource<LABLORDArmorTypeEntity>> resources =
                new ArrayList<Resource<LABLORDArmorTypeEntity>>();
        Iterator<LABLORDArmorTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
