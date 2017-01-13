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

import com.osrapi.models.crypts_things.CRYPTS_THINGSIoItemDataEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSDiceEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSGroupEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSObjectTypeEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSIoItemDataRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/io_item_data")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSIoItemDataController {
    /** the static instance of {@link CRYPTS_THINGSIoItemDataController}. */
    private static CRYPTS_THINGSIoItemDataController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSIoItemDataController}
     */
    public static CRYPTS_THINGSIoItemDataController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSIoItemDataController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSIoItemDataRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSIoItemDataController}. */
    public CRYPTS_THINGSIoItemDataController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getAll() {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSIoItemDataEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSIoItemDataEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        resources.add(getIoItemDataResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSIoItemDataEntity}.
     * @param entity the {@link CRYPTS_THINGSIoItemDataEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>
     */
    private Resource<CRYPTS_THINGSIoItemDataEntity> getIoItemDataResource(
            final CRYPTS_THINGSIoItemDataEntity entity) {
        Resource<CRYPTS_THINGSIoItemDataEntity> resource =
                new Resource<CRYPTS_THINGSIoItemDataEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSIoItemDataEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSIoItemDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> save(
            @RequestBody final List<CRYPTS_THINGSIoItemDataEntity> entities) {
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSIoItemDataEntity}.
     * @param entity the {@link CRYPTS_THINGSIoItemDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> save(
            @RequestBody final CRYPTS_THINGSIoItemDataEntity entity) {
        if (entity.getGroups() != null
                && !entity.getGroups().isEmpty()) {
            for (int i = entity.getGroups().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSGroupEntity groups = null;
                List<Resource<CRYPTS_THINGSGroupEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSGroupController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSGroupEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getGroups().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSGroupEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSGroupController.getInstance(),
                                            (String) field.get(entity.getGroups().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSGroupController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSGroupEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getGroups().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSGroupEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSGroupController
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
                    groups = (CRYPTS_THINGSGroupEntity) ((Resource) CRYPTS_THINGSGroupController
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
                CRYPTS_THINGSObjectTypeEntity types = null;
                List<Resource<CRYPTS_THINGSObjectTypeEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSObjectTypeController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSObjectTypeEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getTypes().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSObjectTypeEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSObjectTypeController.getInstance(),
                                            (String) field.get(entity.getTypes().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSObjectTypeController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSObjectTypeEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getTypes().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSObjectTypeEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSObjectTypeController
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
                    types = (CRYPTS_THINGSObjectTypeEntity) ((Resource) CRYPTS_THINGSObjectTypeController
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


    
        CRYPTS_THINGSIoItemDataEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSIoItemDataEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSIoItemDataEntity entity) {
        List<CRYPTS_THINGSIoItemDataEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSIoItemDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSIoItemDataEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSIoItemDataEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSIoItemDataEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSIoItemDataEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSIoItemDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> update(
            @RequestBody final List<CRYPTS_THINGSIoItemDataEntity> entities) {
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSIoItemDataEntity}.
     * @param entity the {@link CRYPTS_THINGSIoItemDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> update(
            @RequestBody final CRYPTS_THINGSIoItemDataEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getGroups() != null
                && !entity.getGroups().isEmpty()) {
            for (int i = entity.getGroups().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSGroupEntity groups = null;
                List<Resource<CRYPTS_THINGSGroupEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSGroupController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSGroupEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getGroups().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSGroupEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSGroupController.getInstance(),
                                            (String) field.get(entity.getGroups().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSGroupController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSGroupEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getGroups().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSGroupEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSGroupController
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
                    groups = (CRYPTS_THINGSGroupEntity) ((Resource) CRYPTS_THINGSGroupController
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
                CRYPTS_THINGSObjectTypeEntity types = null;
                List<Resource<CRYPTS_THINGSObjectTypeEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSObjectTypeController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSObjectTypeEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getTypes().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSObjectTypeEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSObjectTypeController.getInstance(),
                                            (String) field.get(entity.getTypes().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSObjectTypeController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSObjectTypeEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getTypes().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSObjectTypeEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSObjectTypeController
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
                    types = (CRYPTS_THINGSObjectTypeEntity) ((Resource) CRYPTS_THINGSObjectTypeController
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


    
        CRYPTS_THINGSIoItemDataEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setDamagesIdFromRepository(
      final CRYPTS_THINGSIoItemDataEntity entity) {
    CRYPTS_THINGSDiceEntity memberEntity = null;
    List<Resource<CRYPTS_THINGSDiceEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = CRYPTS_THINGSDiceController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = CRYPTS_THINGSDiceEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getDamages()) != null) {
          list = (List<Resource<CRYPTS_THINGSDiceEntity>>) method
              .invoke(
                  CRYPTS_THINGSDiceController.getInstance(),
                  (String) field
                      .get(entity.getDamages()));
        }
      }
      if (list == null) {
        try {
          method = CRYPTS_THINGSDiceController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = CRYPTS_THINGSDiceEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getDamages()) != null) {
            list = (List<Resource<CRYPTS_THINGSDiceEntity>>)
                method.invoke(CRYPTS_THINGSDiceController
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
      memberEntity = (CRYPTS_THINGSDiceEntity)
          ((Resource) CRYPTS_THINGSDiceController.getInstance().save(
              entity.getDamages()).get(0)).getContent();
    }
    entity.setDamages(memberEntity);
    list = null;
    }


    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a count.
     * @param count the io_item_data' count
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "count/{count}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByCount(
            @PathVariable final Long count) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByCount(count)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a description.
     * @param description the io_item_data' description
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByDescription(
            @PathVariable final String description) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByDescription(description)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a foodValue.
     * @param foodValue the io_item_data' foodValue
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "food_value/{foodValue}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByFoodValue(
            @PathVariable final Long foodValue) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByFoodValue(foodValue)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a internalScript.
     * @param internalScript the io_item_data' internalScript
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "internal_script/{internalScript}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByInternalScript(
            @PathVariable final String internalScript) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByInternalScript(internalScript)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a name.
     * @param name the io_item_data' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a leftRing.
     * @param leftRing the io_item_data' leftRing
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "left_ring/{leftRing}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByLeftRing(
            @PathVariable final Boolean leftRing) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByLeftRing(leftRing)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a lightValue.
     * @param lightValue the io_item_data' lightValue
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "light_value/{lightValue}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByLightValue(
            @PathVariable final Long lightValue) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByLightValue(lightValue)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a maxOwned.
     * @param maxOwned the io_item_data' maxOwned
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "max_owned/{maxOwned}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByMaxOwned(
            @PathVariable final Long maxOwned) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByMaxOwned(maxOwned)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a price.
     * @param price the io_item_data' price
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "price/{price}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByPrice(
            @PathVariable final float price) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByPrice(price)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a ringType.
     * @param ringType the io_item_data' ringType
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "ring_type/{ringType}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByRingType(
            @PathVariable final Long ringType) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByRingType(ringType)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a stackSize.
     * @param stackSize the io_item_data' stackSize
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "stack_size/{stackSize}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByStackSize(
            @PathVariable final Long stackSize) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByStackSize(stackSize)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a stealValue.
     * @param stealValue the io_item_data' stealValue
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "steal_value/{stealValue}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByStealValue(
            @PathVariable final Long stealValue) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByStealValue(stealValue)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoItemDataEntity}s that share a weight.
     * @param weight the io_item_data' weight
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoItemDataEntity}>>
     */
    @RequestMapping(path = "weight/{weight}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoItemDataEntity>> getByWeight(
            @PathVariable final float weight) {
        Iterator<CRYPTS_THINGSIoItemDataEntity> iter = repository.findByWeight(weight)
                .iterator();
        List<Resource<CRYPTS_THINGSIoItemDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoItemDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoItemDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
