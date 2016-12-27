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

import com.osrapi.models.crypts_things.CRYPTS_THINGSEquipmentItemModifierEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSEquipmentItemModifierRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/equipment_item_modifiers")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSEquipmentItemModifierController {
    /** the static instance of {@link CRYPTS_THINGSEquipmentItemModifierController}. */
    private static CRYPTS_THINGSEquipmentItemModifierController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSEquipmentItemModifierController}
     */
    public static CRYPTS_THINGSEquipmentItemModifierController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSEquipmentItemModifierController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSEquipmentItemModifierRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSEquipmentItemModifierController}. */
    public CRYPTS_THINGSEquipmentItemModifierController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentItemModifierEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> getAll() {
        Iterator<CRYPTS_THINGSEquipmentItemModifierEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSEquipmentItemModifierEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSEquipmentItemModifierEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        resources.add(getEquipmentItemModifierResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSEquipmentItemModifierEntity}.
     * @param entity the {@link CRYPTS_THINGSEquipmentItemModifierEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>
     */
    private Resource<CRYPTS_THINGSEquipmentItemModifierEntity> getEquipmentItemModifierResource(
            final CRYPTS_THINGSEquipmentItemModifierEntity entity) {
        Resource<CRYPTS_THINGSEquipmentItemModifierEntity> resource =
                new Resource<CRYPTS_THINGSEquipmentItemModifierEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSEquipmentItemModifierEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSEquipmentItemModifierEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> save(
            @RequestBody final List<CRYPTS_THINGSEquipmentItemModifierEntity> entities) {
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        Iterator<CRYPTS_THINGSEquipmentItemModifierEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSEquipmentItemModifierEntity}.
     * @param entity the {@link CRYPTS_THINGSEquipmentItemModifierEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> save(
            @RequestBody final CRYPTS_THINGSEquipmentItemModifierEntity entity) {
    
    
        CRYPTS_THINGSEquipmentItemModifierEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSEquipmentItemModifierEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSEquipmentItemModifierEntity entity) {
        List<CRYPTS_THINGSEquipmentItemModifierEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSEquipmentItemModifierEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSEquipmentItemModifierEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSEquipmentItemModifierEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSEquipmentItemModifierEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSEquipmentItemModifierEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSEquipmentItemModifierEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> update(
            @RequestBody final List<CRYPTS_THINGSEquipmentItemModifierEntity> entities) {
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        Iterator<CRYPTS_THINGSEquipmentItemModifierEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSEquipmentItemModifierEntity}.
     * @param entity the {@link CRYPTS_THINGSEquipmentItemModifierEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> update(
            @RequestBody final CRYPTS_THINGSEquipmentItemModifierEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        CRYPTS_THINGSEquipmentItemModifierEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentItemModifierEntity}s that share a code.
     * @param code the equipment_item_modifier' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSEquipmentItemModifierEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentItemModifierEntity}s that share a percent.
     * @param percent the equipment_item_modifier' percent
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "percent/{percent}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> getByPercent(
            @PathVariable final Boolean percent) {
        Iterator<CRYPTS_THINGSEquipmentItemModifierEntity> iter = repository.findByPercent(percent)
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentItemModifierEntity}s that share a special.
     * @param special the equipment_item_modifier' special
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "special/{special}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> getBySpecial(
            @PathVariable final Long special) {
        Iterator<CRYPTS_THINGSEquipmentItemModifierEntity> iter = repository.findBySpecial(special)
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSEquipmentItemModifierEntity}s that share a value.
     * @param value the equipment_item_modifier' value
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> getByValue(
            @PathVariable final float value) {
        Iterator<CRYPTS_THINGSEquipmentItemModifierEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
