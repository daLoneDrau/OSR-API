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

import com.osrapi.models.crypts_things.CRYPTS_THINGSLifeEventEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSEquipmentItemModifierEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSLifeEventRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/life_events")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSLifeEventController {
    /** the static instance of {@link CRYPTS_THINGSLifeEventController}. */
    private static CRYPTS_THINGSLifeEventController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSLifeEventController}
     */
    public static CRYPTS_THINGSLifeEventController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSLifeEventController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSLifeEventRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSLifeEventController}. */
    public CRYPTS_THINGSLifeEventController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSLifeEventEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> getAll() {
        Iterator<CRYPTS_THINGSLifeEventEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSLifeEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSLifeEventEntity>>();
        while (iter.hasNext()) {
            resources.add(getLifeEventResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSLifeEventEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSLifeEventEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSLifeEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSLifeEventEntity>>();
        resources.add(getLifeEventResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSLifeEventEntity}.
     * @param entity the {@link CRYPTS_THINGSLifeEventEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>
     */
    private Resource<CRYPTS_THINGSLifeEventEntity> getLifeEventResource(
            final CRYPTS_THINGSLifeEventEntity entity) {
        Resource<CRYPTS_THINGSLifeEventEntity> resource =
                new Resource<CRYPTS_THINGSLifeEventEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSLifeEventEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSLifeEventEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> save(
            @RequestBody final List<CRYPTS_THINGSLifeEventEntity> entities) {
        List<Resource<CRYPTS_THINGSLifeEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSLifeEventEntity>>();
        Iterator<CRYPTS_THINGSLifeEventEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSLifeEventEntity}.
     * @param entity the {@link CRYPTS_THINGSLifeEventEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> save(
            @RequestBody final CRYPTS_THINGSLifeEventEntity entity) {
        if (entity.getModifiers() != null
                && !entity.getModifiers().isEmpty()) {
            for (int i = entity.getModifiers().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSEquipmentItemModifierEntity modifiers = null;
                List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSEquipmentItemModifierController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSEquipmentItemModifierEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getModifiers().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSEquipmentItemModifierController.getInstance(),
                                            (String) field.get(entity.getModifiers().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSEquipmentItemModifierController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSEquipmentItemModifierEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getModifiers().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSEquipmentItemModifierController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getModifiers().get(i)));
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
                if (list != null
                        && !list.isEmpty()) {
                    modifiers = list.get(0).getContent();
                }
                if (modifiers == null) {
                    modifiers = (CRYPTS_THINGSEquipmentItemModifierEntity) ((Resource) CRYPTS_THINGSEquipmentItemModifierController
                            .getInstance()
                            .save(entity.getModifiers().get(i)).get(0)).getContent();
                }
                entity.getModifiers().set(i, modifiers);
                list = null;
            }
        }


    
        CRYPTS_THINGSLifeEventEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSLifeEventEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSLifeEventEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSLifeEventEntity entity) {
        List<CRYPTS_THINGSLifeEventEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSLifeEventEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSLifeEventEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSLifeEventEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSLifeEventEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSLifeEventEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSLifeEventEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> update(
            @RequestBody final List<CRYPTS_THINGSLifeEventEntity> entities) {
        List<Resource<CRYPTS_THINGSLifeEventEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSLifeEventEntity>>();
        Iterator<CRYPTS_THINGSLifeEventEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSLifeEventEntity}.
     * @param entity the {@link CRYPTS_THINGSLifeEventEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> update(
            @RequestBody final CRYPTS_THINGSLifeEventEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getModifiers() != null
                && !entity.getModifiers().isEmpty()) {
            for (int i = entity.getModifiers().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSEquipmentItemModifierEntity modifiers = null;
                List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSEquipmentItemModifierController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSEquipmentItemModifierEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getModifiers().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSEquipmentItemModifierController.getInstance(),
                                            (String) field.get(entity.getModifiers().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSEquipmentItemModifierController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSEquipmentItemModifierEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getModifiers().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSEquipmentItemModifierEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSEquipmentItemModifierController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getModifiers().get(i)));
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
                if (list != null
                        && !list.isEmpty()) {
                    modifiers = list.get(0).getContent();
                }
                if (modifiers == null) {
                    modifiers = (CRYPTS_THINGSEquipmentItemModifierEntity) ((Resource) CRYPTS_THINGSEquipmentItemModifierController
                            .getInstance()
                            .save(entity.getModifiers().get(i)).get(0)).getContent();
                }
                entity.getModifiers().set(i, modifiers);
                list = null;
            }
        }


    
        CRYPTS_THINGSLifeEventEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSLifeEventEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSLifeEventEntity}s that share a description.
     * @param description the life_event' description
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CRYPTS_THINGSLifeEventEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CRYPTS_THINGSLifeEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSLifeEventEntity>>();
        while (iter.hasNext()) {
            resources.add(getLifeEventResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSLifeEventEntity}s that share a name.
     * @param name the life_event' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSLifeEventEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSLifeEventEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSLifeEventEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSLifeEventEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSLifeEventEntity>>();
        while (iter.hasNext()) {
            resources.add(getLifeEventResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
