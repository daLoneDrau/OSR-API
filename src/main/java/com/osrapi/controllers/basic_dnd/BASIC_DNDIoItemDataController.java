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

import com.osrapi.models.basic_dnd.BASIC_DNDIoItemDataEntity;
import com.osrapi.models.basic_dnd.BASIC_DNDDiceEntity;
import com.osrapi.models.basic_dnd.BASIC_DNDGroupEntity;
import com.osrapi.models.basic_dnd.BASIC_DNDObjectTypeEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDIoItemDataRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/io_item_data")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDIoItemDataController {
    /** the static instance of {@link BASIC_DNDIoItemDataController}. */
    private static BASIC_DNDIoItemDataController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDIoItemDataController}
     */
    public static BASIC_DNDIoItemDataController getInstance() {
        if (instance == null) {
            new BASIC_DNDIoItemDataController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDIoItemDataRepository repository;
    /** Creates a new instance of {@link BASIC_DNDIoItemDataController}. */
    public BASIC_DNDIoItemDataController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getAll() {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDIoItemDataEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDIoItemDataEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        resources.add(getIoItemDataResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDIoItemDataEntity}.
     * @param entity the {@link BASIC_DNDIoItemDataEntity}
     * @return {@link Resource}<{@link BASIC_DNDIoItemDataEntity}>
     */
    private Resource<BASIC_DNDIoItemDataEntity> getIoItemDataResource(
            final BASIC_DNDIoItemDataEntity entity) {
        Resource<BASIC_DNDIoItemDataEntity> resource =
                new Resource<BASIC_DNDIoItemDataEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDIoItemDataEntity}s.
     * @param entities the list of {@link BASIC_DNDIoItemDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDIoItemDataEntity>> save(
            @RequestBody final List<BASIC_DNDIoItemDataEntity> entities) {
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        Iterator<BASIC_DNDIoItemDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDIoItemDataEntity}.
     * @param entity the {@link BASIC_DNDIoItemDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDIoItemDataEntity>> save(
            @RequestBody final BASIC_DNDIoItemDataEntity entity) {
        if (entity.getGroups() != null
                && !entity.getGroups().isEmpty()) {
            for (int i = entity.getGroups().size() - 1; i >= 0; i--) {
                BASIC_DNDGroupEntity groups = null;
                List<Resource<BASIC_DNDGroupEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = BASIC_DNDGroupController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = BASIC_DNDGroupEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getGroups().get(i)) != null) {
                            list = (List<Resource<BASIC_DNDGroupEntity>>) method
                                    .invoke(
                                            BASIC_DNDGroupController.getInstance(),
                                            (String) field.get(entity.getGroups().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = BASIC_DNDGroupController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = BASIC_DNDGroupEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getGroups().get(i)) != null) {
                                list = (List<Resource<BASIC_DNDGroupEntity>>) method
                                        .invoke(
                                                BASIC_DNDGroupController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getGroups().get(i)));
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
                    groups = list.get(0).getContent();
                }
                if (groups == null) {
                    groups = (BASIC_DNDGroupEntity) ((Resource) BASIC_DNDGroupController
                            .getInstance()
                            .save(entity.getGroups().get(i)).get(0)).getContent();
                }
                entity.getGroups().set(i, groups);
                list = null;
            }
        }

    if (entity.getTypes() != null
                && !entity.getTypes().isEmpty()) {
            for (int i = entity.getTypes().size() - 1; i >= 0; i--) {
                BASIC_DNDObjectTypeEntity types = null;
                List<Resource<BASIC_DNDObjectTypeEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = BASIC_DNDObjectTypeController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = BASIC_DNDObjectTypeEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getTypes().get(i)) != null) {
                            list = (List<Resource<BASIC_DNDObjectTypeEntity>>) method
                                    .invoke(
                                            BASIC_DNDObjectTypeController.getInstance(),
                                            (String) field.get(entity.getTypes().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = BASIC_DNDObjectTypeController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = BASIC_DNDObjectTypeEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getTypes().get(i)) != null) {
                                list = (List<Resource<BASIC_DNDObjectTypeEntity>>) method
                                        .invoke(
                                                BASIC_DNDObjectTypeController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getTypes().get(i)));
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
                    types = list.get(0).getContent();
                }
                if (types == null) {
                    types = (BASIC_DNDObjectTypeEntity) ((Resource) BASIC_DNDObjectTypeController
                            .getInstance()
                            .save(entity.getTypes().get(i)).get(0)).getContent();
                }
                entity.getTypes().set(i, types);
                list = null;
            }
        }

        if (entity.getDamages() != null
        && entity.getDamages().getId() == null) {
      setDamagesIdFromRepository(entity);
        }


    
        BASIC_DNDIoItemDataEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDIoItemDataEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDIoItemDataEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDIoItemDataEntity entity) {
        List<BASIC_DNDIoItemDataEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDIoItemDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDIoItemDataEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDIoItemDataEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDIoItemDataEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDIoItemDataEntity}s.
     * @param entities the list of {@link BASIC_DNDIoItemDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDIoItemDataEntity>> update(
            @RequestBody final List<BASIC_DNDIoItemDataEntity> entities) {
        List<Resource<BASIC_DNDIoItemDataEntity>> resources = new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        Iterator<BASIC_DNDIoItemDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDIoItemDataEntity}.
     * @param entity the {@link BASIC_DNDIoItemDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDIoItemDataEntity>> update(
            @RequestBody final BASIC_DNDIoItemDataEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getGroups() != null
                && !entity.getGroups().isEmpty()) {
            for (int i = entity.getGroups().size() - 1; i >= 0; i--) {
                BASIC_DNDGroupEntity groups = null;
                List<Resource<BASIC_DNDGroupEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = BASIC_DNDGroupController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = BASIC_DNDGroupEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getGroups().get(i)) != null) {
                            list = (List<Resource<BASIC_DNDGroupEntity>>) method
                                    .invoke(
                                            BASIC_DNDGroupController.getInstance(),
                                            (String) field.get(entity.getGroups().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = BASIC_DNDGroupController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = BASIC_DNDGroupEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getGroups().get(i)) != null) {
                                list = (List<Resource<BASIC_DNDGroupEntity>>) method
                                        .invoke(
                                                BASIC_DNDGroupController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getGroups().get(i)));
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
                    groups = list.get(0).getContent();
                }
                if (groups == null) {
                    groups = (BASIC_DNDGroupEntity) ((Resource) BASIC_DNDGroupController
                            .getInstance()
                            .save(entity.getGroups().get(i)).get(0)).getContent();
                }
                entity.getGroups().set(i, groups);
                list = null;
            }
        }

    if (entity.getTypes() != null
                && !entity.getTypes().isEmpty()) {
            for (int i = entity.getTypes().size() - 1; i >= 0; i--) {
                BASIC_DNDObjectTypeEntity types = null;
                List<Resource<BASIC_DNDObjectTypeEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = BASIC_DNDObjectTypeController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = BASIC_DNDObjectTypeEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getTypes().get(i)) != null) {
                            list = (List<Resource<BASIC_DNDObjectTypeEntity>>) method
                                    .invoke(
                                            BASIC_DNDObjectTypeController.getInstance(),
                                            (String) field.get(entity.getTypes().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = BASIC_DNDObjectTypeController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = BASIC_DNDObjectTypeEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getTypes().get(i)) != null) {
                                list = (List<Resource<BASIC_DNDObjectTypeEntity>>) method
                                        .invoke(
                                                BASIC_DNDObjectTypeController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getTypes().get(i)));
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
                    types = list.get(0).getContent();
                }
                if (types == null) {
                    types = (BASIC_DNDObjectTypeEntity) ((Resource) BASIC_DNDObjectTypeController
                            .getInstance()
                            .save(entity.getTypes().get(i)).get(0)).getContent();
                }
                entity.getTypes().set(i, types);
                list = null;
            }
        }

        if (entity.getDamages() != null
        && entity.getDamages().getId() == null) {
      setDamagesIdFromRepository(entity);
        }


    
        BASIC_DNDIoItemDataEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDIoItemDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setDamagesIdFromRepository(
      final BASIC_DNDIoItemDataEntity entity) {
    BASIC_DNDDiceEntity memberEntity = null;
    List<Resource<BASIC_DNDDiceEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = BASIC_DNDDiceController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = BASIC_DNDDiceEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getDamages()) != null) {
          list = (List<Resource<BASIC_DNDDiceEntity>>) method
              .invoke(
                  BASIC_DNDDiceController.getInstance(),
                  (String) field
                      .get(entity.getDamages()));
        }
      }
      if (list == null) {
        try {
          method = BASIC_DNDDiceController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = BASIC_DNDDiceEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getDamages()) != null) {
            list = (List<Resource<BASIC_DNDDiceEntity>>)
                method.invoke(BASIC_DNDDiceController
                    .getInstance(),(String) field.get(
                        entity.getDamages()));
          }
        }
      }
      method = null;
      field = null;
    } catch (SecurityException | IllegalArgumentException
        | IllegalAccessException
        | InvocationTargetException e) {
    }
    if (list != null
        && !list.isEmpty()) {
      memberEntity = list.get(0).getContent();
    }
    if (memberEntity == null) {
      memberEntity = (BASIC_DNDDiceEntity)
          ((Resource) BASIC_DNDDiceController.getInstance().save(
              entity.getDamages()).get(0)).getContent();
    }
    entity.setDamages(memberEntity);
    list = null;
    }


    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a count.
     * @param count the io_item_data' count
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "count/{count}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByCount(
            @PathVariable final Long count) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByCount(count)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a description.
     * @param description the io_item_data' description
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a foodValue.
     * @param foodValue the io_item_data' foodValue
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "food_value/{foodValue}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByFoodValue(
            @PathVariable final Long foodValue) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByFoodValue(foodValue)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a internalScript.
     * @param internalScript the io_item_data' internalScript
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "internal_script/{internalScript}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByInternalScript(
            @PathVariable final String internalScript) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByInternalScript(internalScript)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a itemName.
     * @param itemName the io_item_data' itemName
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "item_name/{itemName}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByItemName(
            @PathVariable final String itemName) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByItemName(itemName)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a leftRing.
     * @param leftRing the io_item_data' leftRing
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "left_ring/{leftRing}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByLeftRing(
            @PathVariable final Boolean leftRing) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByLeftRing(leftRing)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a lightValue.
     * @param lightValue the io_item_data' lightValue
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "light_value/{lightValue}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByLightValue(
            @PathVariable final Long lightValue) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByLightValue(lightValue)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a maxOwned.
     * @param maxOwned the io_item_data' maxOwned
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "max_owned/{maxOwned}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByMaxOwned(
            @PathVariable final Long maxOwned) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByMaxOwned(maxOwned)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a price.
     * @param price the io_item_data' price
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "price/{price}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByPrice(
            @PathVariable final float price) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByPrice(price)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a ringType.
     * @param ringType the io_item_data' ringType
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "ring_type/{ringType}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByRingType(
            @PathVariable final Long ringType) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByRingType(ringType)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a stackSize.
     * @param stackSize the io_item_data' stackSize
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "stack_size/{stackSize}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByStackSize(
            @PathVariable final Long stackSize) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByStackSize(stackSize)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a stealValue.
     * @param stealValue the io_item_data' stealValue
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "steal_value/{stealValue}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByStealValue(
            @PathVariable final Long stealValue) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByStealValue(stealValue)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoItemDataEntity}s that share a weight.
     * @param weight the io_item_data' weight
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoItemDataEntity}>>
     */
    @RequestMapping(path = "weight/{weight}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoItemDataEntity>> getByWeight(
            @PathVariable final float weight) {
        Iterator<BASIC_DNDIoItemDataEntity> iter = repository.findByWeight(weight)
                .iterator();
        List<Resource<BASIC_DNDIoItemDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
