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

import com.osrapi.models.lablord.LABLORDWeaponTypeEntity;
import com.osrapi.repositories.lablord.LABLORDWeaponTypeRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/weapon_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDWeaponTypeController {
    /** the static instance of {@link LABLORDWeaponTypeController}. */
    private static LABLORDWeaponTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDWeaponTypeController}
     */
    public static LABLORDWeaponTypeController getInstance() {
        if (instance == null) {
            new LABLORDWeaponTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDWeaponTypeRepository repository;
    /** Creates a new instance of {@link LABLORDWeaponTypeController}. */
    public LABLORDWeaponTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDWeaponTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDWeaponTypeEntity>> getAll() {
        Iterator<LABLORDWeaponTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDWeaponTypeEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDWeaponTypeEntity}s that share a code.
     * @param code the weapon_type' code
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<LABLORDWeaponTypeEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<LABLORDWeaponTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<LABLORDWeaponTypeEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDWeaponTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDWeaponTypeEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDWeaponTypeEntity entity = repository.findOne(id);
        List<Resource<LABLORDWeaponTypeEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponTypeEntity>>();
        resources.add(getWeaponTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDWeaponTypeEntity}s that share a name.
     * @param name the weapon_type' name
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<LABLORDWeaponTypeEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<LABLORDWeaponTypeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<LABLORDWeaponTypeEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDWeaponTypeEntity}.
     * @param entity the {@link LABLORDWeaponTypeEntity}
     * @return {@link Resource}<{@link LABLORDWeaponTypeEntity}>
     */
    private Resource<LABLORDWeaponTypeEntity> getWeaponTypeResource(
            final LABLORDWeaponTypeEntity entity) {
        Resource<LABLORDWeaponTypeEntity> resource =
                new Resource<LABLORDWeaponTypeEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves a single {@link LABLORDWeaponTypeEntity}.
     * @param entity the {@link LABLORDWeaponTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDWeaponTypeEntity>> save(
            @RequestBody
            final LABLORDWeaponTypeEntity entity) {

        LABLORDWeaponTypeEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDWeaponTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDWeaponTypeEntity}s.
     * @param entities the list of {@link LABLORDWeaponTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDWeaponTypeEntity>> save(
            @RequestBody
            final List<LABLORDWeaponTypeEntity> entities) {
        List<Resource<LABLORDWeaponTypeEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponTypeEntity>>();
        Iterator<LABLORDWeaponTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDWeaponTypeEntity} instance
     */
    private void setIdFromRepository(final LABLORDWeaponTypeEntity entity) {
        List<LABLORDWeaponTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDWeaponTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDWeaponTypeEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDWeaponTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDWeaponTypeEntity>) method.invoke(
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
     * Updates a single {@link LABLORDWeaponTypeEntity}.
     * @param entity the {@link LABLORDWeaponTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDWeaponTypeEntity>> update(
            @RequestBody
            final LABLORDWeaponTypeEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }

        LABLORDWeaponTypeEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDWeaponTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Updates multiple {@link LABLORDWeaponTypeEntity}s.
     * @param entities the list of {@link LABLORDWeaponTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDWeaponTypeEntity>> update(
            @RequestBody
            final List<LABLORDWeaponTypeEntity> entities) {
        List<Resource<LABLORDWeaponTypeEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponTypeEntity>>();
        Iterator<LABLORDWeaponTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
