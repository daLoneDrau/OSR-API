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

import com.osrapi.models.basic_dnd.BASIC_DNDScriptBundleEntity;
import com.osrapi.models.basic_dnd.BASIC_DNDScriptActionEntity;

import com.osrapi.repositories.basic_dnd.BASIC_DNDScriptBundleRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/basic_dnd/script_bundles")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BASIC_DNDScriptBundleController {
    /** the static instance of {@link BASIC_DNDScriptBundleController}. */
    private static BASIC_DNDScriptBundleController instance;
    /**
     * Gets the static instance.
     * @return {@link BASIC_DNDScriptBundleController}
     */
    public static BASIC_DNDScriptBundleController getInstance() {
        if (instance == null) {
            new BASIC_DNDScriptBundleController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private BASIC_DNDScriptBundleRepository repository;
    /** Creates a new instance of {@link BASIC_DNDScriptBundleController}. */
    public BASIC_DNDScriptBundleController() {
        instance = this;
    }
    /**
     * Gets a list of {@link BASIC_DNDScriptBundleEntity}s.
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptBundleEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptBundleEntity>> getAll() {
        Iterator<BASIC_DNDScriptBundleEntity> iter = repository.findAll()
                .iterator();
        List<Resource<BASIC_DNDScriptBundleEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptBundleEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptBundleResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link BASIC_DNDScriptBundleEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptBundleEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptBundleEntity>> getById(
            @PathVariable final Long id) {
        BASIC_DNDScriptBundleEntity entity = repository.findOne(id);
        List<Resource<BASIC_DNDScriptBundleEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptBundleEntity>>();
        resources.add(getScriptBundleResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link BASIC_DNDScriptBundleEntity}.
     * @param entity the {@link BASIC_DNDScriptBundleEntity}
     * @return {@link Resource}<{@link BASIC_DNDScriptBundleEntity}>
     */
    private Resource<BASIC_DNDScriptBundleEntity> getScriptBundleResource(
            final BASIC_DNDScriptBundleEntity entity) {
        Resource<BASIC_DNDScriptBundleEntity> resource =
                new Resource<BASIC_DNDScriptBundleEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link BASIC_DNDScriptBundleEntity}s.
     * @param entities the list of {@link BASIC_DNDScriptBundleEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptBundleEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<BASIC_DNDScriptBundleEntity>> save(
            @RequestBody final List<BASIC_DNDScriptBundleEntity> entities) {
        List<Resource<BASIC_DNDScriptBundleEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptBundleEntity>>();
        Iterator<BASIC_DNDScriptBundleEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link BASIC_DNDScriptBundleEntity}.
     * @param entity the {@link BASIC_DNDScriptBundleEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptBundleEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<BASIC_DNDScriptBundleEntity>> save(
            @RequestBody final BASIC_DNDScriptBundleEntity entity) {
        if (entity.getScripts() != null
                && !entity.getScripts().isEmpty()) {
            for (int i = entity.getScripts().size() - 1; i >= 0; i--) {
                BASIC_DNDScriptActionEntity scripts = null;
                List<Resource<BASIC_DNDScriptActionEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = BASIC_DNDScriptActionController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = BASIC_DNDScriptActionEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getScripts().get(i)) != null) {
                            list = (List<Resource<BASIC_DNDScriptActionEntity>>) method
                                    .invoke(
                                            BASIC_DNDScriptActionController.getInstance(),
                                            (String) field.get(entity.getScripts().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = BASIC_DNDScriptActionController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = BASIC_DNDScriptActionEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getScripts().get(i)) != null) {
                                list = (List<Resource<BASIC_DNDScriptActionEntity>>) method
                                        .invoke(
                                                BASIC_DNDScriptActionController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getScripts().get(i)));
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
                    scripts = list.get(0).getContent();
                }
                if (scripts == null) {
                    scripts = (BASIC_DNDScriptActionEntity) ((Resource) BASIC_DNDScriptActionController
                            .getInstance()
                            .save(entity.getScripts().get(i)).get(0)).getContent();
                }
                entity.getScripts().set(i, scripts);
                list = null;
            }
        }


    
        BASIC_DNDScriptBundleEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDScriptBundleEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link BASIC_DNDScriptBundleEntity} instance
     */
    private void setIdFromRepository(final BASIC_DNDScriptBundleEntity entity) {
        List<BASIC_DNDScriptBundleEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = BASIC_DNDScriptBundleEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<BASIC_DNDScriptBundleEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = BASIC_DNDScriptBundleEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<BASIC_DNDScriptBundleEntity>) method.invoke(
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
     * Updates multiple {@link BASIC_DNDScriptBundleEntity}s.
     * @param entities the list of {@link BASIC_DNDScriptBundleEntity} instances
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptBundleEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDScriptBundleEntity>> update(
            @RequestBody final List<BASIC_DNDScriptBundleEntity> entities) {
        List<Resource<BASIC_DNDScriptBundleEntity>> resources = new ArrayList<Resource<BASIC_DNDScriptBundleEntity>>();
        Iterator<BASIC_DNDScriptBundleEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link BASIC_DNDScriptBundleEntity}.
     * @param entity the {@link BASIC_DNDScriptBundleEntity} instance
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptBundleEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<BASIC_DNDScriptBundleEntity>> update(
            @RequestBody final BASIC_DNDScriptBundleEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getScripts() != null
                && !entity.getScripts().isEmpty()) {
            for (int i = entity.getScripts().size() - 1; i >= 0; i--) {
                BASIC_DNDScriptActionEntity scripts = null;
                List<Resource<BASIC_DNDScriptActionEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = BASIC_DNDScriptActionController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = BASIC_DNDScriptActionEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getScripts().get(i)) != null) {
                            list = (List<Resource<BASIC_DNDScriptActionEntity>>) method
                                    .invoke(
                                            BASIC_DNDScriptActionController.getInstance(),
                                            (String) field.get(entity.getScripts().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = BASIC_DNDScriptActionController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = BASIC_DNDScriptActionEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getScripts().get(i)) != null) {
                                list = (List<Resource<BASIC_DNDScriptActionEntity>>) method
                                        .invoke(
                                                BASIC_DNDScriptActionController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getScripts().get(i)));
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
                    scripts = list.get(0).getContent();
                }
                if (scripts == null) {
                    scripts = (BASIC_DNDScriptActionEntity) ((Resource) BASIC_DNDScriptActionController
                            .getInstance()
                            .save(entity.getScripts().get(i)).get(0)).getContent();
                }
                entity.getScripts().set(i, scripts);
                list = null;
            }
        }


    
        BASIC_DNDScriptBundleEntity savedEntity = repository.save(entity);
        List<Resource<BASIC_DNDScriptBundleEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link BASIC_DNDScriptBundleEntity}s that share a name.
     * @param name the script_bundle' name
     * @return {@link List}<{@link Resource}<{@link BASIC_DNDScriptBundleEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<BASIC_DNDScriptBundleEntity>> getByName(
            @PathVariable final String name) {
        Iterator<BASIC_DNDScriptBundleEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<BASIC_DNDScriptBundleEntity>> resources =
                new ArrayList<Resource<BASIC_DNDScriptBundleEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptBundleResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
