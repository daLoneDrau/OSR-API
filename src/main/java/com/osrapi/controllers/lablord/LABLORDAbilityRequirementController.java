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

import com.osrapi.models.lablord.LABLORDAbilityEntity;
import com.osrapi.models.lablord.LABLORDAbilityRequirementEntity;
import com.osrapi.repositories.lablord.LABLORDAbilityRequirementRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/ability_requirements")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDAbilityRequirementController {
    /** the static instance of {@link LABLORDAbilityRequirementController}. */
    private static LABLORDAbilityRequirementController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDAbilityRequirementController}
     */
    public static LABLORDAbilityRequirementController getInstance() {
        if (instance == null) {
            new LABLORDAbilityRequirementController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDAbilityRequirementRepository repository;
    /**
     * Creates a new instance of {@link LABLORDAbilityRequirementController}.
     */
    public LABLORDAbilityRequirementController() {
        instance = this;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDAbilityRequirementEntity}.
     * @param entity the {@link LABLORDAbilityRequirementEntity}
     * @return {@link Resource}<{@link LABLORDAbilityRequirementEntity}>
     */
    private Resource<LABLORDAbilityRequirementEntity>
            getAbilityRequirementResource(
                    final LABLORDAbilityRequirementEntity entity) {
        Resource<LABLORDAbilityRequirementEntity> resource =
                new Resource<LABLORDAbilityRequirementEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Gets a list of {@link LABLORDAbilityRequirementEntity}s.
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityRequirementEntity>> getAll() {
        Iterator<LABLORDAbilityRequirementEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDAbilityRequirementEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityRequirementEntity>>();
        while (iter.hasNext()) {
            resources.add(getAbilityRequirementResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDAbilityRequirementEntity}s that share a
     * code.
     * @param code the ability_requirement' code
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityRequirementEntity>> getByCode(
            @PathVariable
            final String code) {
        Iterator<LABLORDAbilityRequirementEntity> iter = repository
                .findByCode(code)
                .iterator();
        List<Resource<LABLORDAbilityRequirementEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityRequirementEntity>>();
        while (iter.hasNext()) {
            resources.add(getAbilityRequirementResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDAbilityRequirementEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityRequirementEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDAbilityRequirementEntity entity = repository.findOne(id);
        List<Resource<LABLORDAbilityRequirementEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityRequirementEntity>>();
        resources.add(getAbilityRequirementResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDAbilityRequirementEntity}s that share a
     * requirement.
     * @param requirement the ability_requirement' requirement
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(path = "requirement/{requirement}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDAbilityRequirementEntity>> getByRequirement(
            @PathVariable
            final Long requirement) {
        Iterator<LABLORDAbilityRequirementEntity> iter = repository
                .findByRequirement(requirement)
                .iterator();
        List<Resource<LABLORDAbilityRequirementEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityRequirementEntity>>();
        while (iter.hasNext()) {
            resources.add(getAbilityRequirementResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link LABLORDAbilityRequirementEntity}.
     * @param entity the {@link LABLORDAbilityRequirementEntity} instance
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDAbilityRequirementEntity>> save(
            @RequestBody
            final LABLORDAbilityRequirementEntity entity) {
        if (entity.getAbility() != null
                && entity.getAbility().getId() == null) {
            setAbilityIdFromRepository(entity);
        }

        LABLORDAbilityRequirementEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDAbilityRequirementEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDAbilityRequirementEntity}s.
     * @param entities the list of {@link LABLORDAbilityRequirementEntity}
     *            instances
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDAbilityRequirementEntity>> save(
            @RequestBody
            final List<LABLORDAbilityRequirementEntity> entities) {
        List<Resource<LABLORDAbilityRequirementEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityRequirementEntity>>();
        Iterator<LABLORDAbilityRequirementEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    private void setAbilityIdFromRepository(
            final LABLORDAbilityRequirementEntity entity) {
        LABLORDAbilityEntity memberEntity = null;
        List<Resource<LABLORDAbilityEntity>> list = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = LABLORDAbilityController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                field = LABLORDAbilityEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {}
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity.getAbility()) != null) {
                    list = (List<Resource<LABLORDAbilityEntity>>) method
                            .invoke(
                                    LABLORDAbilityController.getInstance(),
                                    (String) field
                                            .get(entity.getAbility()));
                }
            }
            if (list == null) {
                try {
                    method = LABLORDAbilityController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = LABLORDAbilityEntity.class
                            .getDeclaredField("code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {}
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getAbility()) != null) {
                        list = (List<Resource<LABLORDAbilityEntity>>) method
                                .invoke(LABLORDAbilityController
                                        .getInstance(), (String) field.get(
                                                entity.getAbility()));
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
                    (LABLORDAbilityEntity) ((Resource) LABLORDAbilityController
                            .getInstance().save(
                                    entity.getAbility())
                            .get(0)).getContent();
        }
        entity.setAbility(memberEntity);
        list = null;
    }

    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDAbilityRequirementEntity} instance
     */
    private void setIdFromRepository(
            final LABLORDAbilityRequirementEntity entity) {
        List<LABLORDAbilityRequirementEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDAbilityRequirementEntity.class
                        .getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDAbilityRequirementEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDAbilityRequirementEntity.class
                            .getDeclaredField(
                                    "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDAbilityRequirementEntity>) method
                                .invoke(
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
     * Updates a single {@link LABLORDAbilityRequirementEntity}.
     * @param entity the {@link LABLORDAbilityRequirementEntity} instance
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDAbilityRequirementEntity>> update(
            @RequestBody
            final LABLORDAbilityRequirementEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getAbility() != null
                && entity.getAbility().getId() == null) {
            setAbilityIdFromRepository(entity);
        }

        LABLORDAbilityRequirementEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDAbilityRequirementEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Updates multiple {@link LABLORDAbilityRequirementEntity}s.
     * @param entities the list of {@link LABLORDAbilityRequirementEntity}
     *            instances
     * @return {@link List}<{@link Resource}<
     *         {@link LABLORDAbilityRequirementEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDAbilityRequirementEntity>> update(
            @RequestBody
            final List<LABLORDAbilityRequirementEntity> entities) {
        List<Resource<LABLORDAbilityRequirementEntity>> resources =
                new ArrayList<Resource<LABLORDAbilityRequirementEntity>>();
        Iterator<LABLORDAbilityRequirementEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
