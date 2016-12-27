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

import com.osrapi.models.crypts_things.CRYPTS_THINGSIoPcDataEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSGenderEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSGroupEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSLifeEventEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSIoPcDataRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/io_pc_data")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSIoPcDataController {
    /** the static instance of {@link CRYPTS_THINGSIoPcDataController}. */
    private static CRYPTS_THINGSIoPcDataController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSIoPcDataController}
     */
    public static CRYPTS_THINGSIoPcDataController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSIoPcDataController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSIoPcDataRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSIoPcDataController}. */
    public CRYPTS_THINGSIoPcDataController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getAll() {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSIoPcDataEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSIoPcDataEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        resources.add(getIoPcDataResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSIoPcDataEntity}.
     * @param entity the {@link CRYPTS_THINGSIoPcDataEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>
     */
    private Resource<CRYPTS_THINGSIoPcDataEntity> getIoPcDataResource(
            final CRYPTS_THINGSIoPcDataEntity entity) {
        Resource<CRYPTS_THINGSIoPcDataEntity> resource =
                new Resource<CRYPTS_THINGSIoPcDataEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSIoPcDataEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSIoPcDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> save(
            @RequestBody final List<CRYPTS_THINGSIoPcDataEntity> entities) {
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSIoPcDataEntity}.
     * @param entity the {@link CRYPTS_THINGSIoPcDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> save(
            @RequestBody final CRYPTS_THINGSIoPcDataEntity entity) {
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

    if (entity.getLifeEvents() != null
                && !entity.getLifeEvents().isEmpty()) {
            for (int i = entity.getLifeEvents().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSLifeEventEntity lifeEvents = null;
                List<Resource<CRYPTS_THINGSLifeEventEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSLifeEventController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSLifeEventEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getLifeEvents().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSLifeEventEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSLifeEventController.getInstance(),
                                            (String) field.get(entity.getLifeEvents().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSLifeEventController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSLifeEventEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getLifeEvents().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSLifeEventEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSLifeEventController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getLifeEvents().get(i)));
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
                    lifeEvents = list.get(0).getContent();
                }
                if (lifeEvents == null) {
                    lifeEvents = (CRYPTS_THINGSLifeEventEntity) ((Resource) CRYPTS_THINGSLifeEventController
                            .getInstance()
                            .save(entity.getLifeEvents().get(i)).get(0)).getContent();
                }
                entity.getLifeEvents().set(i, lifeEvents);
                list = null;
            }
        }

        if (entity.getGender() != null
        && entity.getGender().getId() == null) {
      setGenderIdFromRepository(entity);
        }


    
        CRYPTS_THINGSIoPcDataEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSIoPcDataEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSIoPcDataEntity entity) {
        List<CRYPTS_THINGSIoPcDataEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSIoPcDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSIoPcDataEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSIoPcDataEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSIoPcDataEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSIoPcDataEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSIoPcDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> update(
            @RequestBody final List<CRYPTS_THINGSIoPcDataEntity> entities) {
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSIoPcDataEntity}.
     * @param entity the {@link CRYPTS_THINGSIoPcDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> update(
            @RequestBody final CRYPTS_THINGSIoPcDataEntity entity) {        
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

    if (entity.getLifeEvents() != null
                && !entity.getLifeEvents().isEmpty()) {
            for (int i = entity.getLifeEvents().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSLifeEventEntity lifeEvents = null;
                List<Resource<CRYPTS_THINGSLifeEventEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSLifeEventController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSLifeEventEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getLifeEvents().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSLifeEventEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSLifeEventController.getInstance(),
                                            (String) field.get(entity.getLifeEvents().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSLifeEventController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSLifeEventEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getLifeEvents().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSLifeEventEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSLifeEventController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getLifeEvents().get(i)));
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
                    lifeEvents = list.get(0).getContent();
                }
                if (lifeEvents == null) {
                    lifeEvents = (CRYPTS_THINGSLifeEventEntity) ((Resource) CRYPTS_THINGSLifeEventController
                            .getInstance()
                            .save(entity.getLifeEvents().get(i)).get(0)).getContent();
                }
                entity.getLifeEvents().set(i, lifeEvents);
                list = null;
            }
        }

        if (entity.getGender() != null
        && entity.getGender().getId() == null) {
      setGenderIdFromRepository(entity);
        }


    
        CRYPTS_THINGSIoPcDataEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setGenderIdFromRepository(
      final CRYPTS_THINGSIoPcDataEntity entity) {
    CRYPTS_THINGSGenderEntity memberEntity = null;
    List<Resource<CRYPTS_THINGSGenderEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = CRYPTS_THINGSGenderController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = CRYPTS_THINGSGenderEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getGender()) != null) {
          list = (List<Resource<CRYPTS_THINGSGenderEntity>>) method
              .invoke(
                  CRYPTS_THINGSGenderController.getInstance(),
                  (String) field
                      .get(entity.getGender()));
        }
      }
      if (list == null) {
        try {
          method = CRYPTS_THINGSGenderController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = CRYPTS_THINGSGenderEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getGender()) != null) {
            list = (List<Resource<CRYPTS_THINGSGenderEntity>>)
                method.invoke(CRYPTS_THINGSGenderController
                    .getInstance(),(String) field.get(
                        entity.getGender()));
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
      memberEntity = (CRYPTS_THINGSGenderEntity)
          ((Resource) CRYPTS_THINGSGenderController.getInstance().save(
              entity.getGender()).get(0)).getContent();
    }
    entity.setGender(memberEntity);
    list = null;
    }


    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s that share a bags.
     * @param bags the io_pc_data' bags
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "bags/{bags}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getByBags(
            @PathVariable final Long bags) {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findByBags(bags)
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s that share a flags.
     * @param flags the io_pc_data' flags
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "flags/{flags}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getByFlags(
            @PathVariable final Long flags) {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findByFlags(flags)
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s that share a gold.
     * @param gold the io_pc_data' gold
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "gold/{gold}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getByGold(
            @PathVariable final float gold) {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findByGold(gold)
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s that share a interfaceFlags.
     * @param interfaceFlags the io_pc_data' interfaceFlags
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "interface_flags/{interfaceFlags}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getByInterfaceFlags(
            @PathVariable final Long interfaceFlags) {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findByInterfaceFlags(interfaceFlags)
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s that share a internalScript.
     * @param internalScript the io_pc_data' internalScript
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "internal_script/{internalScript}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getByInternalScript(
            @PathVariable final String internalScript) {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findByInternalScript(internalScript)
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s that share a level.
     * @param level the io_pc_data' level
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "level/{level}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getByLevel(
            @PathVariable final Long level) {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findByLevel(level)
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSIoPcDataEntity}s that share a xp.
     * @param xp the io_pc_data' xp
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSIoPcDataEntity}>>
     */
    @RequestMapping(path = "xp/{xp}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSIoPcDataEntity>> getByXp(
            @PathVariable final Long xp) {
        Iterator<CRYPTS_THINGSIoPcDataEntity> iter = repository.findByXp(xp)
                .iterator();
        List<Resource<CRYPTS_THINGSIoPcDataEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
