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

import com.osrapi.models.lablord.LABLORDEquipmentTypeEntity;
import com.osrapi.repositories.lablord.LABLORDEquipmentTypeRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/equipment_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDEquipmentTypeController {
    /** the static instance of {@link LABLORDEquipmentTypeController}. */
    private static LABLORDEquipmentTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDEquipmentTypeController}
     */
    public static LABLORDEquipmentTypeController getInstance() {
        if (instance == null) {
            new LABLORDEquipmentTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDEquipmentTypeRepository repository;
    /** Creates a new instance of {@link LABLORDEquipmentTypeController}. */
    public LABLORDEquipmentTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDEquipmentTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentTypeEntity>> getAll() {
        Iterator<LABLORDEquipmentTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDEquipmentTypeEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDEquipmentTypeEntity}s that share a code.
     * @param code the equipment_type' code
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentTypeEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<LABLORDEquipmentTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<LABLORDEquipmentTypeEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDEquipmentTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentTypeEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDEquipmentTypeEntity entity = repository.findOne(id);
        List<Resource<LABLORDEquipmentTypeEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentTypeEntity>>();
        resources.add(getEquipmentTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDEquipmentTypeEntity}s that share a name.
     * @param name the equipment_type' name
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentTypeEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<LABLORDEquipmentTypeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<LABLORDEquipmentTypeEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDEquipmentTypeEntity}.
     * @param entity the {@link LABLORDEquipmentTypeEntity}
     * @return {@link Resource}<{@link LABLORDEquipmentTypeEntity}>
     */
    private Resource<LABLORDEquipmentTypeEntity> getEquipmentTypeResource(
            final LABLORDEquipmentTypeEntity entity) {
        Resource<LABLORDEquipmentTypeEntity> resource =
                new Resource<LABLORDEquipmentTypeEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves a single {@link LABLORDEquipmentTypeEntity}.
     * @param entity the {@link LABLORDEquipmentTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDEquipmentTypeEntity>> save(
            @RequestBody
            final LABLORDEquipmentTypeEntity entity) {

        LABLORDEquipmentTypeEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDEquipmentTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDEquipmentTypeEntity}s.
     * @param entities the list of {@link LABLORDEquipmentTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDEquipmentTypeEntity>> save(
            @RequestBody
            final List<LABLORDEquipmentTypeEntity> entities) {
        List<Resource<LABLORDEquipmentTypeEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentTypeEntity>>();
        Iterator<LABLORDEquipmentTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDEquipmentTypeEntity} instance
     */
    private void setIdFromRepository(final LABLORDEquipmentTypeEntity entity) {
        List<LABLORDEquipmentTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDEquipmentTypeEntity.class
                        .getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDEquipmentTypeEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDEquipmentTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDEquipmentTypeEntity>) method.invoke(
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
     * Updates a single {@link LABLORDEquipmentTypeEntity}.
     * @param entity the {@link LABLORDEquipmentTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDEquipmentTypeEntity>> update(
            @RequestBody
            final LABLORDEquipmentTypeEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }

        LABLORDEquipmentTypeEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDEquipmentTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Updates multiple {@link LABLORDEquipmentTypeEntity}s.
     * @param entities the list of {@link LABLORDEquipmentTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentTypeEntity}
     *         >>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDEquipmentTypeEntity>> update(
            @RequestBody
            final List<LABLORDEquipmentTypeEntity> entities) {
        List<Resource<LABLORDEquipmentTypeEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentTypeEntity>>();
        Iterator<LABLORDEquipmentTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
