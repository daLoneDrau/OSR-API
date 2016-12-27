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

import com.osrapi.models.basic_dnd.BASIC_DNDIoPcDataEntity;
import com.osrapi.models.basic_dnd.BASIC_DNDGenderEntity;
import com.osrapi.models.basic_dnd.BASIC_DNDGroupEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDIoPcDataRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/io_pc_data")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDIoPcDataController {
    /** the static instance of {@link BASIC_DNDIoPcDataController}. */
    private static BASIC_DNDIoPcDataController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDIoPcDataController}
     */
    public static BASIC_DNDIoPcDataController getInstance() {
        if (instance == null) {
            new BASIC_DNDIoPcDataController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDIoPcDataRepository repository;
    /** Creates a new instance of {@link BASIC_DNDIoPcDataController}. */
    public BASIC_DNDIoPcDataController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getAll() {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDIoPcDataEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDIoPcDataEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        resources.add(getIoPcDataResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDIoPcDataEntity}.
     * @param entity the {@link BASIC_DNDIoPcDataEntity}
     * @return {@link Resource}<{@link BASIC_DNDIoPcDataEntity}>
     */
    private Resource<BASIC_DNDIoPcDataEntity> getIoPcDataResource(
            final BASIC_DNDIoPcDataEntity entity) {
        Resource<BASIC_DNDIoPcDataEntity> resource =
                new Resource<BASIC_DNDIoPcDataEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDIoPcDataEntity}s.
     * @param entities the list of {@link BASIC_DNDIoPcDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDIoPcDataEntity>> save(
            @RequestBody final List<BASIC_DNDIoPcDataEntity> entities) {
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        Iterator<BASIC_DNDIoPcDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDIoPcDataEntity}.
     * @param entity the {@link BASIC_DNDIoPcDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDIoPcDataEntity>> save(
            @RequestBody final BASIC_DNDIoPcDataEntity entity) {
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

        if (entity.getGender() != null
        && entity.getGender().getId() == null) {
      setGenderIdFromRepository(entity);
        }


    
        BASIC_DNDIoPcDataEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDIoPcDataEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDIoPcDataEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDIoPcDataEntity entity) {
        List<BASIC_DNDIoPcDataEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDIoPcDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDIoPcDataEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDIoPcDataEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDIoPcDataEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDIoPcDataEntity}s.
     * @param entities the list of {@link BASIC_DNDIoPcDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDIoPcDataEntity>> update(
            @RequestBody final List<BASIC_DNDIoPcDataEntity> entities) {
        List<Resource<BASIC_DNDIoPcDataEntity>> resources = new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        Iterator<BASIC_DNDIoPcDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDIoPcDataEntity}.
     * @param entity the {@link BASIC_DNDIoPcDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDIoPcDataEntity>> update(
            @RequestBody final BASIC_DNDIoPcDataEntity entity) {        
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

        if (entity.getGender() != null
        && entity.getGender().getId() == null) {
      setGenderIdFromRepository(entity);
        }


    
        BASIC_DNDIoPcDataEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDIoPcDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setGenderIdFromRepository(
      final BASIC_DNDIoPcDataEntity entity) {
    BASIC_DNDGenderEntity memberEntity = null;
    List<Resource<BASIC_DNDGenderEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = BASIC_DNDGenderController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = BASIC_DNDGenderEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getGender()) != null) {
          list = (List<Resource<BASIC_DNDGenderEntity>>) method
              .invoke(
                  BASIC_DNDGenderController.getInstance(),
                  (String) field
                      .get(entity.getGender()));
        }
      }
      if (list == null) {
        try {
          method = BASIC_DNDGenderController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = BASIC_DNDGenderEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getGender()) != null) {
            list = (List<Resource<BASIC_DNDGenderEntity>>)
                method.invoke(BASIC_DNDGenderController
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
      memberEntity = (BASIC_DNDGenderEntity)
          ((Resource) BASIC_DNDGenderController.getInstance().save(
              entity.getGender()).get(0)).getContent();
    }
    entity.setGender(memberEntity);
    list = null;
    }


    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s that share a bags.
     * @param bags the io_pc_data' bags
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "bags/{bags}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getByBags(
            @PathVariable final Long bags) {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findByBags(bags)
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s that share a flags.
     * @param flags the io_pc_data' flags
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "flags/{flags}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getByFlags(
            @PathVariable final Long flags) {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findByFlags(flags)
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s that share a gold.
     * @param gold the io_pc_data' gold
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "gold/{gold}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getByGold(
            @PathVariable final float gold) {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findByGold(gold)
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s that share a interfaceFlags.
     * @param interfaceFlags the io_pc_data' interfaceFlags
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "interface_flags/{interfaceFlags}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getByInterfaceFlags(
            @PathVariable final Long interfaceFlags) {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findByInterfaceFlags(interfaceFlags)
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s that share a internalScript.
     * @param internalScript the io_pc_data' internalScript
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "internal_script/{internalScript}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getByInternalScript(
            @PathVariable final String internalScript) {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findByInternalScript(internalScript)
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s that share a level.
     * @param level the io_pc_data' level
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "level/{level}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getByLevel(
            @PathVariable final Long level) {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findByLevel(level)
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDIoPcDataEntity}s that share a xp.
     * @param xp the io_pc_data' xp
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDIoPcDataEntity}>>
     */
    @RequestMapping(path = "xp/{xp}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDIoPcDataEntity>> getByXp(
            @PathVariable final Long xp) {
        Iterator<BASIC_DNDIoPcDataEntity> iter = repository.findByXp(xp)
                .iterator();
        List<Resource<BASIC_DNDIoPcDataEntity>> resources =
                new ArrayList<Resource<BASIC_DNDIoPcDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getIoPcDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
