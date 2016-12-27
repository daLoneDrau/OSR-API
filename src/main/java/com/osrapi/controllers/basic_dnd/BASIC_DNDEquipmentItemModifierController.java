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

import com.osrapi.models.basic_dnd.BASIC_DNDEquipmentItemModifierEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDEquipmentItemModifierRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/equipment_item_modifiers")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDEquipmentItemModifierController {
    /** the static instance of {@link BASIC_DNDEquipmentItemModifierController}. */
    private static BASIC_DNDEquipmentItemModifierController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDEquipmentItemModifierController}
     */
    public static BASIC_DNDEquipmentItemModifierController getInstance() {
        if (instance == null) {
            new BASIC_DNDEquipmentItemModifierController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDEquipmentItemModifierRepository repository;
    /** Creates a new instance of {@link BASIC_DNDEquipmentItemModifierController}. */
    public BASIC_DNDEquipmentItemModifierController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDEquipmentItemModifierEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> getAll() {
        Iterator<BASIC_DNDEquipmentItemModifierEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDEquipmentItemModifierEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDEquipmentItemModifierEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        resources.add(getEquipmentItemModifierResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDEquipmentItemModifierEntity}.
     * @param entity the {@link BASIC_DNDEquipmentItemModifierEntity}
     * @return {@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>
     */
    private Resource<BASIC_DNDEquipmentItemModifierEntity> getEquipmentItemModifierResource(
            final BASIC_DNDEquipmentItemModifierEntity entity) {
        Resource<BASIC_DNDEquipmentItemModifierEntity> resource =
                new Resource<BASIC_DNDEquipmentItemModifierEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDEquipmentItemModifierEntity}s.
     * @param entities the list of {@link BASIC_DNDEquipmentItemModifierEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> save(
            @RequestBody final List<BASIC_DNDEquipmentItemModifierEntity> entities) {
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        Iterator<BASIC_DNDEquipmentItemModifierEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDEquipmentItemModifierEntity}.
     * @param entity the {@link BASIC_DNDEquipmentItemModifierEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> save(
            @RequestBody final BASIC_DNDEquipmentItemModifierEntity entity) {
    
    
        BASIC_DNDEquipmentItemModifierEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDEquipmentItemModifierEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDEquipmentItemModifierEntity entity) {
        List<BASIC_DNDEquipmentItemModifierEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDEquipmentItemModifierEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDEquipmentItemModifierEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDEquipmentItemModifierEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDEquipmentItemModifierEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDEquipmentItemModifierEntity}s.
     * @param entities the list of {@link BASIC_DNDEquipmentItemModifierEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> update(
            @RequestBody final List<BASIC_DNDEquipmentItemModifierEntity> entities) {
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources = new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        Iterator<BASIC_DNDEquipmentItemModifierEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDEquipmentItemModifierEntity}.
     * @param entity the {@link BASIC_DNDEquipmentItemModifierEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> update(
            @RequestBody final BASIC_DNDEquipmentItemModifierEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
    
    
        BASIC_DNDEquipmentItemModifierEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDEquipmentItemModifierEntity}s that share a code.
     * @param code the equipment_item_modifier' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDEquipmentItemModifierEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDEquipmentItemModifierEntity}s that share a percent.
     * @param percent the equipment_item_modifier' percent
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "percent/{percent}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> getByPercent(
            @PathVariable final Boolean percent) {
        Iterator<BASIC_DNDEquipmentItemModifierEntity> iter = repository.findByPercent(percent)
                .iterator();
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDEquipmentItemModifierEntity}s that share a special.
     * @param special the equipment_item_modifier' special
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "special/{special}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> getBySpecial(
            @PathVariable final Long special) {
        Iterator<BASIC_DNDEquipmentItemModifierEntity> iter = repository.findBySpecial(special)
                .iterator();
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDEquipmentItemModifierEntity}s that share a value.
     * @param value the equipment_item_modifier' value
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDEquipmentItemModifierEntity}>>
     */
    @RequestMapping(path = "value/{value}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDEquipmentItemModifierEntity>> getByValue(
            @PathVariable final float value) {
        Iterator<BASIC_DNDEquipmentItemModifierEntity> iter = repository.findByValue(value)
                .iterator();
        List<Resource<BASIC_DNDEquipmentItemModifierEntity>> resources =
                new ArrayList<Resource<BASIC_DNDEquipmentItemModifierEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentItemModifierResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
