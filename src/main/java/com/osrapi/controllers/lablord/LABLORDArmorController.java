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

import com.osrapi.models.lablord.LABLORDArmorEntity;
import com.osrapi.models.lablord.LABLORDArmorTypeEntity;
import com.osrapi.repositories.lablord.LABLORDArmorRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/armors")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDArmorController {
    /** the static instance of {@link LABLORDArmorController}. */
    private static LABLORDArmorController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDArmorController}
     */
    public static LABLORDArmorController getInstance() {
        if (instance == null) {
            new LABLORDArmorController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDArmorRepository repository;
    /** Creates a new instance of {@link LABLORDArmorController}. */
    public LABLORDArmorController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDArmorEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDArmorEntity>> getAll() {
        Iterator<LABLORDArmorEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDArmorEntity>> resources =
                new ArrayList<Resource<LABLORDArmorEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmorResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDArmorEntity}.
     * @param entity the {@link LABLORDArmorEntity}
     * @return {@link Resource}<{@link LABLORDArmorEntity}>
     */
    private Resource<LABLORDArmorEntity> getArmorResource(
            final LABLORDArmorEntity entity) {
        Resource<LABLORDArmorEntity> resource =
                new Resource<LABLORDArmorEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Gets a list of {@link LABLORDArmorEntity}s that share a ac.
     * @param ac the armor' ac
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorEntity}>>
     */
    @RequestMapping(path = "ac/{ac}", method = RequestMethod.GET)
    public List<Resource<LABLORDArmorEntity>> getByAc(
            @PathVariable
            final Long ac) {
        Iterator<LABLORDArmorEntity> iter = repository.findByAc(ac)
                .iterator();
        List<Resource<LABLORDArmorEntity>> resources =
                new ArrayList<Resource<LABLORDArmorEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmorResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDArmorEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDArmorEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDArmorEntity entity = repository.findOne(id);
        List<Resource<LABLORDArmorEntity>> resources =
                new ArrayList<Resource<LABLORDArmorEntity>>();
        resources.add(getArmorResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Saves a single {@link LABLORDArmorEntity}.
     * @param entity the {@link LABLORDArmorEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDArmorEntity>> save(
            @RequestBody
            final LABLORDArmorEntity entity) {
        if (entity.getType() != null
                && entity.getType().getId() == null) {
            setTypeIdFromRepository(entity);
        }

        LABLORDArmorEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDArmorEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDArmorEntity}s.
     * @param entities the list of {@link LABLORDArmorEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDArmorEntity>> save(
            @RequestBody
            final List<LABLORDArmorEntity> entities) {
        List<Resource<LABLORDArmorEntity>> resources =
                new ArrayList<Resource<LABLORDArmorEntity>>();
        Iterator<LABLORDArmorEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDArmorEntity} instance
     */
    private void setIdFromRepository(final LABLORDArmorEntity entity) {
        List<LABLORDArmorEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDArmorEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDArmorEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDArmorEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDArmorEntity>) method.invoke(
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
    private void setTypeIdFromRepository(
            final LABLORDArmorEntity entity) {
        LABLORDArmorTypeEntity memberEntity = null;
        List<Resource<LABLORDArmorTypeEntity>> list = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = LABLORDArmorTypeController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                field = LABLORDArmorTypeEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {}
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity.getType()) != null) {
                    list = (List<Resource<LABLORDArmorTypeEntity>>) method
                            .invoke(
                                    LABLORDArmorTypeController.getInstance(),
                                    (String) field
                                            .get(entity.getType()));
                }
            }
            if (list == null) {
                try {
                    method = LABLORDArmorTypeController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = LABLORDArmorTypeEntity.class
                            .getDeclaredField("code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {}
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getType()) != null) {
                        list = (List<Resource<LABLORDArmorTypeEntity>>) method
                                .invoke(LABLORDArmorTypeController
                                        .getInstance(), (String) field.get(
                                                entity.getType()));
                    }
                }
            }
            method = null;
            field = null;
        } catch (SecurityException | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {}
        if (list != null
                && !list.isEmpty()) {
            memberEntity = list.get(0).getContent();
        }
        if (memberEntity == null) {
            memberEntity =
                    (LABLORDArmorTypeEntity) ((Resource) LABLORDArmorTypeController
                            .getInstance().save(
                                    entity.getType())
                            .get(0)).getContent();
        }
        entity.setType(memberEntity);
        list = null;
    }

    /**
     * Updates a single {@link LABLORDArmorEntity}.
     * @param entity the {@link LABLORDArmorEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDArmorEntity>> update(
            @RequestBody
            final LABLORDArmorEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getType() != null
                && entity.getType().getId() == null) {
            setTypeIdFromRepository(entity);
        }

        LABLORDArmorEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDArmorEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }

    /**
     * Updates multiple {@link LABLORDArmorEntity}s.
     * @param entities the list of {@link LABLORDArmorEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDArmorEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDArmorEntity>> update(
            @RequestBody
            final List<LABLORDArmorEntity> entities) {
        List<Resource<LABLORDArmorEntity>> resources =
                new ArrayList<Resource<LABLORDArmorEntity>>();
        Iterator<LABLORDArmorEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
