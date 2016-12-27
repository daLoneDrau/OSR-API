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

import com.osrapi.models.basic_dnd.BASIC_DNDDiceEntity;
import com.osrapi.models.basic_dnd.BASIC_DNDDieEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDDiceRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/dices")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDDiceController {
    /** the static instance of {@link BASIC_DNDDiceController}. */
    private static BASIC_DNDDiceController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDDiceController}
     */
    public static BASIC_DNDDiceController getInstance() {
        if (instance == null) {
            new BASIC_DNDDiceController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDDiceRepository repository;
    /** Creates a new instance of {@link BASIC_DNDDiceController}. */
    public BASIC_DNDDiceController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDDiceEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDiceEntity>> getAll() {
        Iterator<BASIC_DNDDiceEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDDiceEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDiceEntity>>();
        while (iter.hasNext()) {
            resources.add(getDiceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDDiceEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDiceEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDDiceEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDDiceEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDiceEntity>>();
        resources.add(getDiceResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDDiceEntity}.
     * @param entity the {@link BASIC_DNDDiceEntity}
     * @return {@link Resource}<{@link BASIC_DNDDiceEntity}>
     */
    private Resource<BASIC_DNDDiceEntity> getDiceResource(
            final BASIC_DNDDiceEntity entity) {
        Resource<BASIC_DNDDiceEntity> resource =
                new Resource<BASIC_DNDDiceEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDDiceEntity}s.
     * @param entities the list of {@link BASIC_DNDDiceEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDDiceEntity>> save(
            @RequestBody final List<BASIC_DNDDiceEntity> entities) {
        List<Resource<BASIC_DNDDiceEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDiceEntity>>();
        Iterator<BASIC_DNDDiceEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDDiceEntity}.
     * @param entity the {@link BASIC_DNDDiceEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDDiceEntity>> save(
            @RequestBody final BASIC_DNDDiceEntity entity) {
            if (entity.getDie() != null
        && entity.getDie().getId() == null) {
      setDieIdFromRepository(entity);
        }


    
        BASIC_DNDDiceEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDDiceEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDDiceEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDDiceEntity entity) {
        List<BASIC_DNDDiceEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDDiceEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDDiceEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDDiceEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDDiceEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDDiceEntity}s.
     * @param entities the list of {@link BASIC_DNDDiceEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDDiceEntity>> update(
            @RequestBody final List<BASIC_DNDDiceEntity> entities) {
        List<Resource<BASIC_DNDDiceEntity>> resources = new ArrayList<Resource<BASIC_DNDDiceEntity>>();
        Iterator<BASIC_DNDDiceEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDDiceEntity}.
     * @param entity the {@link BASIC_DNDDiceEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDDiceEntity>> update(
            @RequestBody final BASIC_DNDDiceEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
            if (entity.getDie() != null
        && entity.getDie().getId() == null) {
      setDieIdFromRepository(entity);
        }


    
        BASIC_DNDDiceEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDDiceEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

  private void setDieIdFromRepository(
      final BASIC_DNDDiceEntity entity) {
    BASIC_DNDDieEntity memberEntity = null;
    List<Resource<BASIC_DNDDieEntity>> list = null;
    try {
      Method method = null;
      Field field = null;
      try {
        method = BASIC_DNDDieController.class.getDeclaredMethod(
            "getByName", new Class[] { String.class });
        field = BASIC_DNDDieEntity.class.getDeclaredField("name");
      } catch (NoSuchMethodException | NoSuchFieldException e) {
      }
      if (method != null
          && field != null) {
        field.setAccessible(true);
        if (field.get(entity.getDie()) != null) {
          list = (List<Resource<BASIC_DNDDieEntity>>) method
              .invoke(
                  BASIC_DNDDieController.getInstance(),
                  (String) field
                      .get(entity.getDie()));
        }
      }
      if (list == null) {
        try {
          method = BASIC_DNDDieController.class.getDeclaredMethod(
              "getByCode", new Class[] { String.class });
          field = BASIC_DNDDieEntity.class
              .getDeclaredField("code");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
        }
        if (method != null
            && field != null) {
          field.setAccessible(true);
          if (field.get(entity.getDie()) != null) {
            list = (List<Resource<BASIC_DNDDieEntity>>)
                method.invoke(BASIC_DNDDieController
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
      memberEntity = (BASIC_DNDDieEntity)
          ((Resource) BASIC_DNDDieController.getInstance().save(
              entity.getDie()).get(0)).getContent();
    }
    entity.setDie(memberEntity);
    list = null;
    }


    /**
     * Gets a list of {@link BASIC_DNDDiceEntity}s that share a code.
     * @param code the dice' code
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(path = "code/{code}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDiceEntity>> getByCode(
            @PathVariable final String code) {
        Iterator<BASIC_DNDDiceEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<BASIC_DNDDiceEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDiceEntity>>();
        while (iter.hasNext()) {
            resources.add(getDiceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link BASIC_DNDDiceEntity}s that share a number.
     * @param number the dice' number
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDDiceEntity}>>
     */
    @RequestMapping(path = "number/{number}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDDiceEntity>> getByNumber(
            @PathVariable final Long number) {
        Iterator<BASIC_DNDDiceEntity> iter = repository.findByNumber(number)
                .iterator();
        List<Resource<BASIC_DNDDiceEntity>> resources =
                new ArrayList<Resource<BASIC_DNDDiceEntity>>();
        while (iter.hasNext()) {
            resources.add(getDiceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
