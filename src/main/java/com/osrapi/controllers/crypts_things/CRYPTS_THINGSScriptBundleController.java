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

import com.osrapi.models.crypts_things.CRYPTS_THINGSScriptBundleEntity;
import com.osrapi.models.crypts_things.CRYPTS_THINGSScriptActionEntity;

import com.osrapi.repositories.crypts_things.CRYPTS_THINGSScriptBundleRepository;

/**
 * @author drau
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/crypts_things/script_bundles")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CRYPTS_THINGSScriptBundleController {
    /** the static instance of {@link CRYPTS_THINGSScriptBundleController}. */
    private static CRYPTS_THINGSScriptBundleController instance;
    /**
     * Gets the static instance.
     * @return {@link CRYPTS_THINGSScriptBundleController}
     */
    public static CRYPTS_THINGSScriptBundleController getInstance() {
        if (instance == null) {
            new CRYPTS_THINGSScriptBundleController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private CRYPTS_THINGSScriptBundleRepository repository;
    /** Creates a new instance of {@link CRYPTS_THINGSScriptBundleController}. */
    public CRYPTS_THINGSScriptBundleController() {
        instance = this;
    }
    /**
     * Gets a list of {@link CRYPTS_THINGSScriptBundleEntity}s.
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptBundleEntity>> getAll() {
        Iterator<CRYPTS_THINGSScriptBundleEntity> iter = repository.findAll()
                .iterator();
        List<Resource<CRYPTS_THINGSScriptBundleEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptBundleEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptBundleResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link CRYPTS_THINGSScriptBundleEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptBundleEntity>> getById(
            @PathVariable final Long id) {
        CRYPTS_THINGSScriptBundleEntity entity = repository.findOne(id);
        List<Resource<CRYPTS_THINGSScriptBundleEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptBundleEntity>>();
        resources.add(getScriptBundleResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link CRYPTS_THINGSScriptBundleEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptBundleEntity}
     * @return {@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>
     */
    private Resource<CRYPTS_THINGSScriptBundleEntity> getScriptBundleResource(
            final CRYPTS_THINGSScriptBundleEntity entity) {
        Resource<CRYPTS_THINGSScriptBundleEntity> resource =
                new Resource<CRYPTS_THINGSScriptBundleEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link CRYPTS_THINGSScriptBundleEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSScriptBundleEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSScriptBundleEntity>> save(
            @RequestBody final List<CRYPTS_THINGSScriptBundleEntity> entities) {
        List<Resource<CRYPTS_THINGSScriptBundleEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptBundleEntity>>();
        Iterator<CRYPTS_THINGSScriptBundleEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link CRYPTS_THINGSScriptBundleEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptBundleEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<CRYPTS_THINGSScriptBundleEntity>> save(
            @RequestBody final CRYPTS_THINGSScriptBundleEntity entity) {
        if (entity.getScripts() != null
                && !entity.getScripts().isEmpty()) {
            for (int i = entity.getScripts().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSScriptActionEntity scripts = null;
                List<Resource<CRYPTS_THINGSScriptActionEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSScriptActionController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSScriptActionEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getScripts().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSScriptActionEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSScriptActionController.getInstance(),
                                            (String) field.get(entity.getScripts().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSScriptActionController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSScriptActionEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getScripts().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSScriptActionEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSScriptActionController
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
                    scripts = (CRYPTS_THINGSScriptActionEntity) ((Resource) CRYPTS_THINGSScriptActionController
                            .getInstance()
                            .save(entity.getScripts().get(i)).get(0)).getContent();
                }
                entity.getScripts().set(i, scripts);
                list = null;
            }
        }


    
        CRYPTS_THINGSScriptBundleEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSScriptBundleEntity>> list =
                getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link CRYPTS_THINGSScriptBundleEntity} instance
     */
    private void setIdFromRepository(final CRYPTS_THINGSScriptBundleEntity entity) {
        List<CRYPTS_THINGSScriptBundleEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = CRYPTS_THINGSScriptBundleEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<CRYPTS_THINGSScriptBundleEntity>) method.invoke(
              repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || (old != null
                    && old.size() > 1)) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = CRYPTS_THINGSScriptBundleEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<CRYPTS_THINGSScriptBundleEntity>) method.invoke(
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
     * Updates multiple {@link CRYPTS_THINGSScriptBundleEntity}s.
     * @param entities the list of {@link CRYPTS_THINGSScriptBundleEntity} instances
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSScriptBundleEntity>> update(
            @RequestBody final List<CRYPTS_THINGSScriptBundleEntity> entities) {
        List<Resource<CRYPTS_THINGSScriptBundleEntity>> resources = new ArrayList<Resource<CRYPTS_THINGSScriptBundleEntity>>();
        Iterator<CRYPTS_THINGSScriptBundleEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link CRYPTS_THINGSScriptBundleEntity}.
     * @param entity the {@link CRYPTS_THINGSScriptBundleEntity} instance
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<CRYPTS_THINGSScriptBundleEntity>> update(
            @RequestBody final CRYPTS_THINGSScriptBundleEntity entity) {        
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getScripts() != null
                && !entity.getScripts().isEmpty()) {
            for (int i = entity.getScripts().size() - 1; i >= 0; i--) {
                CRYPTS_THINGSScriptActionEntity scripts = null;
                List<Resource<CRYPTS_THINGSScriptActionEntity>> list = null;
                try {
                    Method method = null;
          try {
            method = CRYPTS_THINGSScriptActionController.class.getDeclaredMethod(
                "getByName", new Class[] { String.class });
          } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
          try {
            field = CRYPTS_THINGSScriptActionEntity.class
                .getDeclaredField("name");
          } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getScripts().get(i)) != null) {
                            list = (List<Resource<CRYPTS_THINGSScriptActionEntity>>) method
                                    .invoke(
                                            CRYPTS_THINGSScriptActionController.getInstance(),
                                            (String) field.get(entity.getScripts().get(i)));
                        }
                    }
                    if (list == null) {
            try {
              method = CRYPTS_THINGSScriptActionController.class.getDeclaredMethod(
                  "getByCode", new Class[] { String.class });
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            try {
              field = CRYPTS_THINGSScriptActionEntity.class.getDeclaredField(
                  "code");
            } catch (NoSuchFieldException e) {
              e.printStackTrace();
            }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getScripts().get(i)) != null) {
                                list = (List<Resource<CRYPTS_THINGSScriptActionEntity>>) method
                                        .invoke(
                                                CRYPTS_THINGSScriptActionController
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
                    scripts = (CRYPTS_THINGSScriptActionEntity) ((Resource) CRYPTS_THINGSScriptActionController
                            .getInstance()
                            .save(entity.getScripts().get(i)).get(0)).getContent();
                }
                entity.getScripts().set(i, scripts);
                list = null;
            }
        }


    
        CRYPTS_THINGSScriptBundleEntity savedEntity = repository.save(entity);
        List<Resource<CRYPTS_THINGSScriptBundleEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Gets a list of {@link CRYPTS_THINGSScriptBundleEntity}s that share a name.
     * @param name the script_bundle' name
     * @return {@link List}<{@link Resource}<{@link CRYPTS_THINGSScriptBundleEntity}>>
     */
    @RequestMapping(path = "name/{name}",
            method = RequestMethod.GET)
    public List<Resource<CRYPTS_THINGSScriptBundleEntity>> getByName(
            @PathVariable final String name) {
        Iterator<CRYPTS_THINGSScriptBundleEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<CRYPTS_THINGSScriptBundleEntity>> resources =
                new ArrayList<Resource<CRYPTS_THINGSScriptBundleEntity>>();
        while (iter.hasNext()) {
            resources.add(getScriptBundleResource(iter.next()));
        }
        iter = null;
        return resources;
    }
}
