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

import com.osrapi.models.crypts_things.CRYPTS_THINGSDiceEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSDieEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSDiceRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/dices")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSDiceController {
    /** the static instance of {@link CRYPTS_THINGSDiceController}. */
    private static CRYPTS_THINGSDiceController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSDiceController}
     */
    public static CRYPTS_THINGSDiceController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSDiceController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSDiceRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSDiceController}. */
    public CRYPTS_THINGSDiceController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSDiceEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDiceEntity>> getAll() {
        Iterator<CRYPTS_THINGSDiceEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSDiceEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDiceEntity>>();
        while (iter.hasNext()) {
            resources.add(getDiceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSDiceEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDiceEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSDiceEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSDiceEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDiceEntity>>();
        resources.add(getDiceResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSDiceEntity}.
     * @param entity the {@link CRYPTS_THINGSDiceEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSDiceEntity}>
     */
    private Resource<CRYPTS_THINGSDiceEntity> getDiceResource(
            final CRYPTS_THINGSDiceEntity entity) {
        Resource<CRYPTS_THINGSDiceEntity> resource =
                new Resource<CRYPTS_THINGSDiceEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSDiceEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSDiceEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSDiceEntity>> save(
            @RequestBody final List<CRYPTS_THINGSDiceEntity> entities) {
        List<Resource<CRYPTS_THINGSDiceEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDiceEntity>>();
        Iterator<CRYPTS_THINGSDiceEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSDiceEntity}.
     * @param entity the {@link CRYPTS_THINGSDiceEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSDiceEntity>> save(
            @RequestBody final CRYPTS_THINGSDiceEntity entity) {
            if (entity.getDie() != null
        && entity.getDie().getId() == null) {
      setDieIdFromRepository(entity);
        }


    
        CRYPTS_THINGSDiceEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSDiceEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSDiceEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSDiceEntity entity) {
        List<CRYPTS_THINGSDiceEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSDiceEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSDiceEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSDiceEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSDiceEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSDiceEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSDiceEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSDiceEntity>> update(
            @RequestBody final List<CRYPTS_THINGSDiceEntity> entities) {
        List<Resource<CRYPTS_THINGSDiceEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSDiceEntity>>();
        Iterator<CRYPTS_THINGSDiceEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSDiceEntity}.
     * @param entity the {@link CRYPTS_THINGSDiceEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSDiceEntity>> update(
            @RequestBody final CRYPTS_THINGSDiceEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
            if (entity.getDie() != null
        && entity.getDie().getId() == null) {
      setDieIdFromRepository(entity);
        }


    
        CRYPTS_THINGSDiceEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSDiceEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setDieIdFromRepository(
      final CRYPTS_THINGSDiceEntity entity) {
    CRYPTS_THINGSDieEntity memberEntity = null;
    List<Resource<CRYPTS_THINGSDieEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = CRYPTS_THINGSDieController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = CRYPTS_THINGSDieEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getDie()) != null) {
          list = (List<Resource<CRYPTS_THINGSDieEntity>>) method
              .invoke(
                  CRYPTS_THINGSDieController.getInstance(),
                  (String) field
                      .get(entity.getDie()));
        }
      }
      if (list == null) {
        try {
          method = CRYPTS_THINGSDieController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = CRYPTS_THINGSDieEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getDie()) != null) {
            list = (List<Resource<CRYPTS_THINGSDieEntity>>)
                method.invoke(CRYPTS_THINGSDieController
                    .getInstance(),(String) field.get(
                        entity.getDie()));
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
      memberEntity = (CRYPTS_THINGSDieEntity)
          ((Resource) CRYPTS_THINGSDieController.getInstance().save(
              entity.getDie()).get(0)).getContent();
    }
    entity.setDie(memberEntity);
    list = null;
    }


    /**
     * Gets a list of {@link CRYPTS_THINGSDiceEntity}s that share a code.
     * @param code the dice' code
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDiceEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<CRYPTS_THINGSDiceEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<CRYPTS_THINGSDiceEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDiceEntity>>();
        while (iter.hasNext()) {
            resources.add(getDiceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSDiceEntity}s that share a number.
     * @param number the dice' number
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(path = "number/{number}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDiceEntity>> getByNumber(
            @PathVariable final Long number) {
        Iterator<CRYPTS_THINGSDiceEntity> iter = repository.findByNumber(number)
                .iterator();
        List<Resource<CRYPTS_THINGSDiceEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDiceEntity>>();
        while (iter.hasNext()) {
            resources.add(getDiceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSDiceEntity}s that share a plus.
     * @param plus the dice' plus
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSDiceEntity}>>
     */
    @RequestMapping(path = "plus/{plus}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSDiceEntity>> getByPlus(
            @PathVariable final Long plus) {
        Iterator<CRYPTS_THINGSDiceEntity> iter = repository.findByPlus(plus)
                .iterator();
        List<Resource<CRYPTS_THINGSDiceEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSDiceEntity>>();
        while (iter.hasNext()) {
            resources.add(getDiceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
