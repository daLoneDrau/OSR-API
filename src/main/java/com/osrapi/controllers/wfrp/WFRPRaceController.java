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

import com.osrapi.models.wfrp.WFRPRaceEntity;
import com.osrapi.models.wfrp.WFRPSkillChoiceEntity;
import com.osrapi.models.wfrp.WFRPSkillEntity;
import com.osrapi.models.wfrp.WFRPTalentChoiceEntity;
import com.osrapi.models.wfrp.WFRPTalentEntity;
import com.osrapi.repositories.wfrp.WFRPRaceRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/races")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPRaceController {
    /** the static instance of {@link WFRPRaceController}. */
    private static WFRPRaceController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPRaceController}
     */
    public static WFRPRaceController getInstance() {
        if (instance == null) {
            new WFRPRaceController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPRaceRepository repository;
    /** Creates a new instance of {@link WFRPRaceController}. */
    public WFRPRaceController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPRaceEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPRaceEntity>> getAll() {
        Iterator<WFRPRaceEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPRaceEntity>> resources =
                new ArrayList<Resource<WFRPRaceEntity>>();
        while (iter.hasNext()) {
            resources.add(getRaceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPRaceEntity}s that share a background.
     * @param background the race' background
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(path = "background/{background}",
            method = RequestMethod.GET)
    public List<Resource<WFRPRaceEntity>> getByBackground(
            @PathVariable
            final String background) {
        Iterator<WFRPRaceEntity> iter = repository.findByBackground(background)
                .iterator();
        List<Resource<WFRPRaceEntity>> resources =
                new ArrayList<Resource<WFRPRaceEntity>>();
        while (iter.hasNext()) {
            resources.add(getRaceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPRaceEntity}s that share a code.
     * @param code the race' code
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<WFRPRaceEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<WFRPRaceEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<WFRPRaceEntity>> resources =
                new ArrayList<Resource<WFRPRaceEntity>>();
        while (iter.hasNext()) {
            resources.add(getRaceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPRaceEntity}s that share a description.
     * @param description the race' description
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<WFRPRaceEntity>> getByDescription(
            @PathVariable
            final String description) {
        Iterator<WFRPRaceEntity> iter = repository
                .findByDescription(description)
                .iterator();
        List<Resource<WFRPRaceEntity>> resources =
                new ArrayList<Resource<WFRPRaceEntity>>();
        while (iter.hasNext()) {
            resources.add(getRaceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPRaceEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPRaceEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPRaceEntity entity = repository.findOne(id);
        List<Resource<WFRPRaceEntity>> resources =
                new ArrayList<Resource<WFRPRaceEntity>>();
        resources.add(getRaceResource(entity));
        entity = null;
        return resources;
    }

    /**
     * Gets a list of {@link WFRPRaceEntity}s that share a name.
     * @param name the race' name
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPRaceEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPRaceEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPRaceEntity>> resources =
                new ArrayList<Resource<WFRPRaceEntity>>();
        while (iter.hasNext()) {
            resources.add(getRaceResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPRaceEntity}.
     * @param entity the {@link WFRPRaceEntity}
     * @return {@link Resource}<{@link WFRPRaceEntity}>
     */
    private Resource<WFRPRaceEntity> getRaceResource(
            final WFRPRaceEntity entity) {
        Resource<WFRPRaceEntity> resource = new Resource<WFRPRaceEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link WFRPRaceEntity}s.
     * @param entities the list of {@link WFRPRaceEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPRaceEntity>> save(
            @RequestBody
            final List<WFRPRaceEntity> entities) {
        List<Resource<WFRPRaceEntity>> resources =
                new ArrayList<Resource<WFRPRaceEntity>>();
        Iterator<WFRPRaceEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPRaceEntity}.
     * @param entity the {@link WFRPRaceEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPRaceEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPRaceEntity>> save(
            @RequestBody
            final WFRPRaceEntity entity) {
        if (entity.getSkillChoices() != null
                && !entity.getSkillChoices().isEmpty()) {
            for (int i = entity.getSkillChoices().size() - 1; i >= 0; i--) {
                WFRPSkillChoiceEntity skillChoices = null;
                List<Resource<WFRPSkillChoiceEntity>> list = null;
                try {
                    Method method = WFRPSkillChoiceController.class
                            .getDeclaredMethod(
                                    "getByName", new Class[] { String.class });
                    Field field = WFRPSkillChoiceEntity.class
                            .getDeclaredField("name");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field
                                .get(entity.getSkillChoices().get(i)) != null) {
                            list = (List<
                                    Resource<WFRPSkillChoiceEntity>>) method
                                            .invoke(
                                                    WFRPSkillChoiceController
                                                            .getInstance(),
                                                    (String) field.get(entity
                                                            .getSkillChoices()
                                                            .get(i)));
                        }
                    }
                    if (list == null) {
                        method = WFRPSkillChoiceController.class
                                .getDeclaredMethod(
                                        "getByCode",
                                        new Class[] { String.class });
                        field = WFRPSkillChoiceEntity.class.getDeclaredField(
                                "code");
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(
                                    entity.getSkillChoices().get(i)) != null) {
                                list = (List<
                                        Resource<WFRPSkillChoiceEntity>>) method
                                                .invoke(
                                                        WFRPSkillChoiceController
                                                                .getInstance(),
                                                        (String) field
                                                                .get(entity
                                                                        .getSkillChoices()
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
                    skillChoices = list.get(0).getContent();
                }
                if (skillChoices == null) {
                    skillChoices =
                            (WFRPSkillChoiceEntity) ((Resource) WFRPSkillChoiceController
                                    .getInstance()
                                    .save(entity.getSkillChoices().get(i))
                                    .get(0))
                                            .getContent();
                }
                entity.getSkillChoices().set(i, skillChoices);
                list = null;
            }
        }

        if (entity.getSkills() != null
                && !entity.getSkills().isEmpty()) {
            for (int i = entity.getSkills().size() - 1; i >= 0; i--) {
                WFRPSkillEntity skills = null;
                List<Resource<WFRPSkillEntity>> list = null;
                try {
                    Method method = WFRPSkillController.class.getDeclaredMethod(
                            "getByName", new Class[] { String.class });
                    Field field = WFRPSkillEntity.class
                            .getDeclaredField("name");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getSkills().get(i)) != null) {
                            list = (List<Resource<WFRPSkillEntity>>) method
                                    .invoke(
                                            WFRPSkillController.getInstance(),
                                            (String) field.get(
                                                    entity.getSkills().get(i)));
                        }
                    }
                    if (list == null) {
                        method = WFRPSkillController.class.getDeclaredMethod(
                                "getByCode", new Class[] { String.class });
                        field = WFRPSkillEntity.class.getDeclaredField(
                                "code");
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getSkills().get(i)) != null) {
                                list = (List<Resource<WFRPSkillEntity>>) method
                                        .invoke(
                                                WFRPSkillController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity.getSkills()
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
                    skills = list.get(0).getContent();
                }
                if (skills == null) {
                    skills = (WFRPSkillEntity) ((Resource) WFRPSkillController
                            .getInstance()
                            .save(entity.getSkills().get(i)).get(0))
                                    .getContent();
                }
                entity.getSkills().set(i, skills);
                list = null;
            }
        }

        if (entity.getTalentChoices() != null
                && !entity.getTalentChoices().isEmpty()) {
            for (int i = entity.getTalentChoices().size() - 1; i >= 0; i--) {
                WFRPTalentChoiceEntity talentChoices = null;
                List<Resource<WFRPTalentChoiceEntity>> list = null;
                try {
                    Method method = WFRPTalentChoiceController.class
                            .getDeclaredMethod(
                                    "getByName", new Class[] { String.class });
                    Field field = WFRPTalentChoiceEntity.class
                            .getDeclaredField("name");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(
                                entity.getTalentChoices().get(i)) != null) {
                            list = (List<
                                    Resource<WFRPTalentChoiceEntity>>) method
                                            .invoke(
                                                    WFRPTalentChoiceController
                                                            .getInstance(),
                                                    (String) field.get(
                                                            entity.getTalentChoices()
                                                                    .get(i)));
                        }
                    }
                    if (list == null) {
                        method = WFRPTalentChoiceController.class
                                .getDeclaredMethod(
                                        "getByCode",
                                        new Class[] { String.class });
                        field = WFRPTalentChoiceEntity.class.getDeclaredField(
                                "code");
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(
                                    entity.getTalentChoices().get(i)) != null) {
                                list = (List<Resource<
                                        WFRPTalentChoiceEntity>>) method
                                                .invoke(
                                                        WFRPTalentChoiceController
                                                                .getInstance(),
                                                        (String) field
                                                                .get(entity
                                                                        .getTalentChoices()
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
                    talentChoices = list.get(0).getContent();
                }
                if (talentChoices == null) {
                    talentChoices =
                            (WFRPTalentChoiceEntity) ((Resource) WFRPTalentChoiceController
                                    .getInstance()
                                    .save(entity.getTalentChoices().get(i))
                                    .get(0))
                                            .getContent();
                }
                entity.getTalentChoices().set(i, talentChoices);
                list = null;
            }
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

        WFRPRaceEntity savedEntity = repository.save(entity);
        List<Resource<WFRPRaceEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
