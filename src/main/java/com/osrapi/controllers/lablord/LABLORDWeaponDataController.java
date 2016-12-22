package com.osrapi.controllers.lablord;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osrapi.models.lablord.LABLORDDieRollEntity;
import com.osrapi.models.lablord.LABLORDWeaponDataEntity;
import com.osrapi.repositories.lablord.LABLORDWeaponDataRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/weapon_data")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDWeaponDataController {
    /** the static instance of {@link LABLORDWeaponDataController}. */
    private static LABLORDWeaponDataController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDWeaponDataController}
     */
    public static LABLORDWeaponDataController getInstance() {
        if (instance == null) {
            new LABLORDWeaponDataController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDWeaponDataRepository repository;
    /** Creates a new instance of {@link LABLORDWeaponDataController}. */
    public LABLORDWeaponDataController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDWeaponDataEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDWeaponDataEntity>> getAll() {
        Iterator<LABLORDWeaponDataEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDWeaponDataEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDWeaponDataEntity}s that share a code.
     * @param code the weapon_data' code
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponDataEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<LABLORDWeaponDataEntity>> getByCode(
            @PathVariable
            final String code) {
        Iterator<LABLORDWeaponDataEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<LABLORDWeaponDataEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponDataEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponDataResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDWeaponDataEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponDataEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDWeaponDataEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDWeaponDataEntity entity = repository.findOne(id);
        List<Resource<LABLORDWeaponDataEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponDataEntity>>();
        resources.add(getWeaponDataResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDWeaponDataEntity}.
     * @param entity the {@link LABLORDWeaponDataEntity}
     * @return {@link Resource}<{@link LABLORDWeaponDataEntity}>
     */
    private Resource<LABLORDWeaponDataEntity> getWeaponDataResource(
            final LABLORDWeaponDataEntity entity) {
        Resource<LABLORDWeaponDataEntity> resource =
                new Resource<LABLORDWeaponDataEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves a single {@link LABLORDWeaponDataEntity}.
     * @param entity the {@link LABLORDWeaponDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDWeaponDataEntity>> save(
            @RequestBody
            final LABLORDWeaponDataEntity entity) {

        if (entity.getDamages() != null
                && !entity.getDamages().isEmpty()) {
            // get Id of each
            Iterator<String> iter = entity.getDamages().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                LABLORDDieRollEntity value = entity.getDamages().get(key);
                if (value.getId() == null) {
                    setDamagesIdFromRepository(value, entity, key);
                }
            }
        }

        LABLORDWeaponDataEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDWeaponDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDWeaponDataEntity}s.
     * @param entities the list of {@link LABLORDWeaponDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDWeaponDataEntity>> save(
            @RequestBody
            final List<LABLORDWeaponDataEntity> entities) {
        List<Resource<LABLORDWeaponDataEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponDataEntity>>();
        Iterator<LABLORDWeaponDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    private void setDamagesIdFromRepository(final LABLORDDieRollEntity value,
            final LABLORDWeaponDataEntity entity, final String key) {
        try {
            List<Resource<LABLORDDieRollEntity>> list = null;
            LABLORDDieRollEntity saved = null;
            Method method = null;
            Field field = null;
            try {
                method = LABLORDDieRollController.class
                        .getDeclaredMethod(
                                "getByName",
                                new Class[] { String.class });
                field = LABLORDDieRollEntity.class
                        .getDeclaredField("name");
            } catch (NoSuchMethodException
                    | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(value) != null) {
                    list = (List<Resource<LABLORDDieRollEntity>>) method
                            .invoke(
                                    LABLORDDieRollController
                                            .getInstance(),
                                    (String) field.get(value));
                }
            }
            if (list == null) {
                try {
                    method = LABLORDDieRollController.class
                            .getDeclaredMethod(
                                    "getByCode",
                                    new Class[] { String.class });
                    field = LABLORDDieRollEntity.class
                            .getDeclaredField("code");
                } catch (NoSuchMethodException
                        | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(value) != null) {
                        list = (List<Resource<LABLORDDieRollEntity>>) method
                                .invoke(
                                        LABLORDDieRollController
                                                .getInstance(),
                                        (String) field.get(value));
                    }
                }
            }
            if (list != null
                    && !list.isEmpty()) {
                saved = list.get(0).getContent();
            }
            if (saved == null) {
                saved = (LABLORDDieRollEntity) ((Resource) LABLORDDieRollController
                        .getInstance()
                        .save(value).get(0)).getContent();
            }
            entity.getDamages().put(key, saved);
            list = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDWeaponDataEntity} instance
     */
    private void setIdFromRepository(final LABLORDWeaponDataEntity entity) {
        List<LABLORDWeaponDataEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDWeaponDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDWeaponDataEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDWeaponDataEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDWeaponDataEntity>) method.invoke(
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
     * Updates a single {@link LABLORDWeaponDataEntity}.
     * @param entity the {@link LABLORDWeaponDataEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponDataEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDWeaponDataEntity>> update(
            @RequestBody
            final LABLORDWeaponDataEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }

        if (entity.getDamages() != null
                && !entity.getDamages().isEmpty()) {
            // get Id of each
            Iterator<String> iter = entity.getDamages().keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                LABLORDDieRollEntity value = entity.getDamages().get(key);
                if (value.getId() == null) {
                    setDamagesIdFromRepository(value, entity, key);
                }
            }
        }

        LABLORDWeaponDataEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDWeaponDataEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Updates multiple {@link LABLORDWeaponDataEntity}s.
     * @param entities the list of {@link LABLORDWeaponDataEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDWeaponDataEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDWeaponDataEntity>> update(
            @RequestBody
            final List<LABLORDWeaponDataEntity> entities) {
        List<Resource<LABLORDWeaponDataEntity>> resources =
                new ArrayList<Resource<LABLORDWeaponDataEntity>>();
        Iterator<LABLORDWeaponDataEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
