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

import com.osrapi.models.crypts_things.CRYPTS_THINGSEquipmentElementTypeEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSEquipmentElementTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/equipment_element_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSEquipmentElementTypeController {
    /** the static instance of {@link CRYPTS_THINGSEquipmentElementTypeController}. */
    private static CRYPTS_THINGSEquipmentElementTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSEquipmentElementTypeController}
     */
    public static CRYPTS_THINGSEquipmentElementTypeController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSEquipmentElementTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSEquipmentElementTypeRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSEquipmentElementTypeController}. */
    public CRYPTS_THINGSEquipmentElementTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentElementTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> getAll() {
        Iterator<CRYPTS_THINGSEquipmentElementTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSEquipmentElementTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSEquipmentElementTypeEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>>();
        resources.add(getEquipmentElementTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSEquipmentElementTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSEquipmentElementTypeEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>
     */
    private Resource<CRYPTS_THINGSEquipmentElementTypeEntity> getEquipmentElementTypeResource(
            final CRYPTS_THINGSEquipmentElementTypeEntity entity) {
        Resource<CRYPTS_THINGSEquipmentElementTypeEntity> resource =
                new Resource<CRYPTS_THINGSEquipmentElementTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSEquipmentElementTypeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSEquipmentElementTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> save(
            @RequestBody final List<CRYPTS_THINGSEquipmentElementTypeEntity> entities) {
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>>();
        Iterator<CRYPTS_THINGSEquipmentElementTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSEquipmentElementTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSEquipmentElementTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> save(
            @RequestBody final CRYPTS_THINGSEquipmentElementTypeEntity entity) {
    
    
        CRYPTS_THINGSEquipmentElementTypeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSEquipmentElementTypeEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSEquipmentElementTypeEntity entity) {
        List<CRYPTS_THINGSEquipmentElementTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSEquipmentElementTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSEquipmentElementTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSEquipmentElementTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSEquipmentElementTypeEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSEquipmentElementTypeEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSEquipmentElementTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> update(
            @RequestBody final List<CRYPTS_THINGSEquipmentElementTypeEntity> entities) {
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>>();
        Iterator<CRYPTS_THINGSEquipmentElementTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSEquipmentElementTypeEntity}.
     * @param entity the {@link CRYPTS_THINGSEquipmentElementTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> update(
            @RequestBody final CRYPTS_THINGSEquipmentElementTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSEquipmentElementTypeEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentElementTypeEntity}s that share a code.
     * @param code the equipment_element_type' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSEquipmentElementTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentElementTypeEntity}s that share a value.
     * @param value the equipment_element_type' value
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> getByValue(
            @PathVariable final Long value) {
        Iterator<CRYPTS_THINGSEquipmentElementTypeEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
