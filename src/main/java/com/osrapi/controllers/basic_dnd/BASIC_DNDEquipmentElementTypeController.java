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

import com.osrapi.models.basic_dnd.BASIC_DNDEquipmentElementTypeEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDEquipmentElementTypeRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/equipment_element_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDEquipmentElementTypeController {
    /** the static instance of {@link BASIC_DNDEquipmentElementTypeController}. */
    private static BASIC_DNDEquipmentElementTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDEquipmentElementTypeController}
     */
    public static BASIC_DNDEquipmentElementTypeController getInstance() {
        if (instance == null) {
            new BASIC_DNDEquipmentElementTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDEquipmentElementTypeRepository repository;
    /** Creates a new instance of {@link BASIC_DNDEquipmentElementTypeController}. */
    public BASIC_DNDEquipmentElementTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDEquipmentElementTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> getAll() {
        Iterator<BASIC_DNDEquipmentElementTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDEquipmentElementTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDEquipmentElementTypeEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentElementTypeEntity>>();
        resources.add(getEquipmentElementTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDEquipmentElementTypeEntity}.
     * @param entity the {@link BASIC_DNDEquipmentElementTypeEntity}
     * @return {@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>
     */
    private Resource<BASIC_DNDEquipmentElementTypeEntity> getEquipmentElementTypeResource(
            final BASIC_DNDEquipmentElementTypeEntity entity) {
        Resource<BASIC_DNDEquipmentElementTypeEntity> resource =
                new Resource<BASIC_DNDEquipmentElementTypeEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDEquipmentElementTypeEntity}s.
     * @param entities the list of {@link BASIC_DNDEquipmentElementTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> save(
            @RequestBody final List<BASIC_DNDEquipmentElementTypeEntity> entities) {
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentElementTypeEntity>>();
        Iterator<BASIC_DNDEquipmentElementTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDEquipmentElementTypeEntity}.
     * @param entity the {@link BASIC_DNDEquipmentElementTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> save(
            @RequestBody final BASIC_DNDEquipmentElementTypeEntity entity) {
    
    
        BASIC_DNDEquipmentElementTypeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDEquipmentElementTypeEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDEquipmentElementTypeEntity entity) {
        List<BASIC_DNDEquipmentElementTypeEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDEquipmentElementTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDEquipmentElementTypeEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDEquipmentElementTypeEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDEquipmentElementTypeEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDEquipmentElementTypeEntity}s.
     * @param entities the list of {@link BASIC_DNDEquipmentElementTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> update(
            @RequestBody final List<BASIC_DNDEquipmentElementTypeEntity> entities) {
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> resources = new ArrayList<Resource<BASIC_DNDEquipmentElementTypeEntity>>();
        Iterator<BASIC_DNDEquipmentElementTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDEquipmentElementTypeEntity}.
     * @param entity the {@link BASIC_DNDEquipmentElementTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> update(
            @RequestBody final BASIC_DNDEquipmentElementTypeEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDEquipmentElementTypeEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDEquipmentElementTypeEntity}s that share a code.
     * @param code the equipment_element_type' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDEquipmentElementTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDEquipmentElementTypeEntity}s that share a value.
     * @param value the equipment_element_type' value
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentElementTypeEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentElementTypeEntity>> getByValue(
            @PathVariable final Long value) {
        Iterator<BASIC_DNDEquipmentElementTypeEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<BASIC_DNDEquipmentElementTypeEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentElementTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentElementTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
