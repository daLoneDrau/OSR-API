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

import com.osrapi.models.lablord.LABLORDDamageEntity;
import com.osrapi.models.lablord.LABLORDDieEntity;
import com.osrapi.repositories.lablord.LABLORDDamageRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/damages")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDDamageController {
    /** the static instance of {@link LABLORDDamageController}. */
    private static LABLORDDamageController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDDamageController}
     */
    public static LABLORDDamageController getInstance() {
        if (instance == null) {
            new LABLORDDamageController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDDamageRepository repository;
    /** Creates a new instance of {@link LABLORDDamageController}. */
    public LABLORDDamageController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDDamageEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDDamageEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDDamageEntity>> getAll() {
        Iterator<LABLORDDamageEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDDamageEntity>> resources =
                new ArrayList<Resource<LABLORDDamageEntity>>();
        while (iter.hasNext()) {
            resources.add(getDamageResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDDamageEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDDamageEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDDamageEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDDamageEntity entity = repository.findOne(id);
        List<Resource<LABLORDDamageEntity>> resources =
                new ArrayList<Resource<LABLORDDamageEntity>>();
        resources.add(getDamageResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDDamageEntity}s that share a number.
     * @param number the damage' number
     * @return {@link List}<{@link Resource}<{@link LABLORDDamageEntity}>>
     */
    @RequestMapping(path = "number/{number}", method = RequestMethod.GET)
    public List<Resource<LABLORDDamageEntity>> getByNumber(
            @PathVariable
            final Long number) {
        Iterator<LABLORDDamageEntity> iter = repository.findByNumber(number)
                .iterator();
        List<Resource<LABLORDDamageEntity>> resources =
                new ArrayList<Resource<LABLORDDamageEntity>>();
        while (iter.hasNext()) {
            resources.add(getDamageResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDDamageEntity}.
     * @param entity the {@link LABLORDDamageEntity}
     * @return {@link Resource}<{@link LABLORDDamageEntity}>
     */
    private Resource<LABLORDDamageEntity> getDamageResource(
            final LABLORDDamageEntity entity) {
        Resource<LABLORDDamageEntity> resource =
                new Resource<LABLORDDamageEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves a single {@link LABLORDDamageEntity}.
     * @param entity the {@link LABLORDDamageEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDDamageEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDDamageEntity>> save(
            @RequestBody
            final LABLORDDamageEntity entity) {
        if (entity.getDie() != null
                && entity.getDie().getId() == null) {
            LABLORDDieEntity die = null;
            List<Resource<LABLORDDieEntity>> list = null;
            try {
                Method method = LABLORDDieController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                Field field = LABLORDDieEntity.class.getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getDie()) != null) {
                        list = (List<Resource<LABLORDDieEntity>>) method.invoke(
                                LABLORDDieController.getInstance(),
                                (String) field.get(entity.getDie()));
                    }
                }
                if (list == null) {
                    method = LABLORDDieController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = LABLORDDieEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getDie()) != null) {
                            list = (List<Resource<LABLORDDieEntity>>) method
                                    .invoke(
                                            LABLORDDieController.getInstance(),
                                            (String) field
                                                    .get(entity.getDie()));
                        }
                    }
                }
                method = null;
                field = null;
            } catch (NoSuchMethodException | SecurityException
                    | NoSuchFieldException | IllegalArgumentException
                    | IllegalAccessException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
            if (list != null
                    && !list.isEmpty()) {
                die = list.get(0).getContent();
            }
            if (die == null) {
                die = (LABLORDDieEntity) ((Resource) LABLORDDieController
                        .getInstance()
                        .save(entity.getDie()).get(0)).getContent();
            }
            entity.setDie(die);
            list = null;
        }

        LABLORDDamageEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDDamageEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDDamageEntity}s.
     * @param entities the list of {@link LABLORDDamageEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDDamageEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDDamageEntity>> save(
            @RequestBody
            final List<LABLORDDamageEntity> entities) {
        List<Resource<LABLORDDamageEntity>> resources =
                new ArrayList<Resource<LABLORDDamageEntity>>();
        Iterator<LABLORDDamageEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Updates a single {@link LABLORDDamageEntity}.
     * @param entity the {@link LABLORDDamageEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDDamageEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDDamageEntity>> update(
            @RequestBody
            final LABLORDDamageEntity entity) {
        if (entity.getId() == null) {
            List<LABLORDDamageEntity> old = null;
            try {
                Method method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                Field field = LABLORDDamageEntity.class
                        .getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDDamageEntity>) method
                                .invoke(repository, (String) field.get(entity));
                    }
                }
                if (old == null
                        || old != null
                                && old.size() > 1) {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDDamageEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity) != null) {
                            old = (List<LABLORDDamageEntity>) method.invoke(
                                    repository, (String) field.get(entity));
                        }
                    }
                }
                method = null;
                field = null;
            } catch (NoSuchMethodException | SecurityException
                    | NoSuchFieldException | IllegalArgumentException
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
        LABLORDDamageEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDDamageEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Updates multiple {@link LABLORDDamageEntity}s.
     * @param entities the list of {@link LABLORDDamageEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDDamageEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDDamageEntity>> update(
            @RequestBody
            final List<LABLORDDamageEntity> entities) {
        List<Resource<LABLORDDamageEntity>> resources =
                new ArrayList<Resource<LABLORDDamageEntity>>();
        Iterator<LABLORDDamageEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
