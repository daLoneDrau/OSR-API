package com.osrapi.controllers.wfrp;

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

import com.osrapi.models.wfrp.WFRPCharacteristicEntity;
import com.osrapi.models.wfrp.WFRPSkillEntity;
import com.osrapi.models.wfrp.WFRPSkillTypeEntity;
import com.osrapi.models.wfrp.WFRPTalentEntity;
import com.osrapi.repositories.wfrp.WFRPSkillRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/skills")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPSkillController {
    /** the static instance of {@link WFRPSkillController}. */
    private static WFRPSkillController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPSkillController}
     */
    public static WFRPSkillController getInstance() {
        if (instance == null) {
            new WFRPSkillController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPSkillRepository repository;
    /** Creates a new instance of {@link WFRPSkillController}. */
    public WFRPSkillController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPSkillEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPSkillEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPSkillEntity>> getAll() {
        Iterator<WFRPSkillEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPSkillEntity>> resources =
                new ArrayList<Resource<WFRPSkillEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPSkillEntity}s that share a description.
     * @param description the skill' description
     * @return {@link List}<{@link Resource}<{@link WFRPSkillEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<WFRPSkillEntity>> getByDescription(
            @PathVariable
            final String description) {
        Iterator<WFRPSkillEntity> iter = repository
                .findByDescription(description)
                .iterator();
        List<Resource<WFRPSkillEntity>> resources =
                new ArrayList<Resource<WFRPSkillEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPSkillEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPSkillEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPSkillEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPSkillEntity entity = repository.findOne(id);
        List<Resource<WFRPSkillEntity>> resources =
                new ArrayList<Resource<WFRPSkillEntity>>();
        resources.add(getSkillResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPSkillEntity}s that share a name.
     * @param name the skill' name
     * @return {@link List}<{@link Resource}<{@link WFRPSkillEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPSkillEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPSkillEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPSkillEntity>> resources =
                new ArrayList<Resource<WFRPSkillEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPSkillEntity}.
     * @param entity the {@link WFRPSkillEntity}
     * @return {@link Resource}<{@link WFRPSkillEntity}>
     */
    private Resource<WFRPSkillEntity> getSkillResource(
            final WFRPSkillEntity entity) {
        Resource<WFRPSkillEntity> resource = new Resource<WFRPSkillEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }

    /**
     * Saves multiple {@link WFRPSkillEntity}s.
     * @param entities the list of {@link WFRPSkillEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPSkillEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPSkillEntity>> save(
            @RequestBody
            final List<WFRPSkillEntity> entities) {
        List<Resource<WFRPSkillEntity>> resources =
                new ArrayList<Resource<WFRPSkillEntity>>();
        Iterator<WFRPSkillEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPSkillEntity}.
     * @param entity the {@link WFRPSkillEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPSkillEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPSkillEntity>> save(
            @RequestBody
            final WFRPSkillEntity entity) {
        if (entity.getCharacteristic() != null
                && entity.getCharacteristic().getId() == null) {
            WFRPCharacteristicEntity characteristic = null;
            List<Resource<WFRPCharacteristicEntity>> list = null;
            try {
                Method method = WFRPCharacteristicController.class
                        .getDeclaredMethod(
                                "getByName", new Class[] { String.class });
                Field field = WFRPCharacteristicEntity.class
                        .getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getCharacteristic()) != null) {
                        list = (List<Resource<WFRPCharacteristicEntity>>) method
                                .invoke(
                                        WFRPCharacteristicController
                                                .getInstance(),
                                        (String) field.get(
                                                entity.getCharacteristic()));
                    }
                }
                if (list == null) {
                    method = WFRPCharacteristicController.class
                            .getDeclaredMethod(
                                    "getByCode", new Class[] { String.class });
                    field = WFRPCharacteristicEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getCharacteristic()) != null) {
                            list = (List<
                                    Resource<WFRPCharacteristicEntity>>) method
                                            .invoke(
                                                    WFRPCharacteristicController
                                                            .getInstance(),
                                                    (String) field.get(entity
                                                            .getCharacteristic()));
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
                characteristic = list.get(0).getContent();
            }
            if (characteristic == null) {
                characteristic =
                        (WFRPCharacteristicEntity) ((Resource) WFRPCharacteristicController
                                .getInstance()
                                .save(entity.getCharacteristic()).get(0))
                                        .getContent();
            }
            entity.setCharacteristic(characteristic);
            list = null;
        }

        if (entity.getTalents() != null
                && !entity.getTalents().isEmpty()) {
            for (int i = entity.getTalents().size() - 1; i >= 0; i--) {
                WFRPTalentEntity talents = null;
                List<Resource<WFRPTalentEntity>> list = null;
                try {
                    Method method = WFRPTalentController.class
                            .getDeclaredMethod(
                                    "getByName", new Class[] { String.class });
                    Field field = WFRPTalentEntity.class
                            .getDeclaredField("name");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getTalents().get(i)) != null) {
                            list = (List<Resource<WFRPTalentEntity>>) method
                                    .invoke(
                                            WFRPTalentController.getInstance(),
                                            (String) field.get(entity
                                                    .getTalents().get(i)));
                        }
                    }
                    if (list == null) {
                        method = WFRPTalentController.class.getDeclaredMethod(
                                "getByCode", new Class[] { String.class });
                        field = WFRPTalentEntity.class.getDeclaredField(
                                "code");
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getTalents().get(i)) != null) {
                                list = (List<Resource<WFRPTalentEntity>>) method
                                        .invoke(
                                                WFRPTalentController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getTalents()
                                                                .get(i)));
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
                    talents = list.get(0).getContent();
                }
                if (talents == null) {
                    talents =
                            (WFRPTalentEntity) ((Resource) WFRPTalentController
                                    .getInstance()
                                    .save(entity.getTalents().get(i)).get(0))
                                            .getContent();
                }
                entity.getTalents().set(i, talents);
                list = null;
            }
        }

        if (entity.getType() != null
                && entity.getType().getId() == null) {
            WFRPSkillTypeEntity type = null;
            List<Resource<WFRPSkillTypeEntity>> list = null;
            try {
                Method method = WFRPSkillTypeController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                Field field = WFRPSkillTypeEntity.class
                        .getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getType()) != null) {
                        list = (List<Resource<WFRPSkillTypeEntity>>) method
                                .invoke(
                                        WFRPSkillTypeController.getInstance(),
                                        (String) field.get(entity.getType()));
                    }
                }
                if (list == null) {
                    method = WFRPSkillTypeController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = WFRPSkillTypeEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getType()) != null) {
                            list = (List<Resource<WFRPSkillTypeEntity>>) method
                                    .invoke(
                                            WFRPSkillTypeController
                                                    .getInstance(),
                                            (String) field
                                                    .get(entity.getType()));
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
                type = list.get(0).getContent();
            }
            if (type == null) {
                type = (WFRPSkillTypeEntity) ((Resource) WFRPSkillTypeController
                        .getInstance()
                        .save(entity.getType()).get(0)).getContent();
            }
            entity.setType(type);
            list = null;
        }

        WFRPSkillEntity savedEntity = repository.save(entity);
        List<Resource<WFRPSkillEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
