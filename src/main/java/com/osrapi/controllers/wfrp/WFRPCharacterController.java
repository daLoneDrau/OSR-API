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

import com.osrapi.models.wfrp.WFRPCharacterEntity;
import com.osrapi.models.wfrp.WFRPGenderEntity;
import com.osrapi.models.wfrp.WFRPRaceEntity;
import com.osrapi.models.wfrp.WFRPSkillEntity;
import com.osrapi.models.wfrp.WFRPTalentEntity;
import com.osrapi.repositories.wfrp.WFRPCharacterRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/characters")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPCharacterController {
    /** the static instance of {@link WFRPCharacterController}. */
    private static WFRPCharacterController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPCharacterController}
     */
    public static WFRPCharacterController getInstance() {
        if (instance == null) {
            new WFRPCharacterController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPCharacterRepository repository;
    /** Creates a new instance of {@link WFRPCharacterController}. */
    public WFRPCharacterController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getAll() {
        Iterator<WFRPCharacterEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a agility.
     * @param agility the character' agility
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "agility/{agility}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByAgility(
            @PathVariable
            final Long agility) {
        Iterator<WFRPCharacterEntity> iter = repository.findByAgility(agility)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a attacks.
     * @param attacks the character' attacks
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "attacks/{attacks}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByAttacks(
            @PathVariable
            final Long attacks) {
        Iterator<WFRPCharacterEntity> iter = repository.findByAttacks(attacks)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a ballisticSkill.
     * @param ballisticSkill the character' ballisticSkill
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "ballistic_skill/{ballisticSkill}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByBallisticSkill(
            @PathVariable
            final Long ballisticSkill) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByBallisticSkill(ballisticSkill)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a fatePoints.
     * @param fatePoints the character' fatePoints
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "fate_points/{fatePoints}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByFatePoints(
            @PathVariable
            final Long fatePoints) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByFatePoints(fatePoints)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }

    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a fellowship.
     * @param fellowship the character' fellowship
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "fellowship/{fellowship}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByFellowship(
            @PathVariable
            final Long fellowship) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByFellowship(fellowship)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPCharacterEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPCharacterEntity entity = repository.findOne(id);
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        resources.add(getCharacterResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a insanityPoints.
     * @param insanityPoints the character' insanityPoints
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "insanity_points/{insanityPoints}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByInsanityPoints(
            @PathVariable
            final Long insanityPoints) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByInsanityPoints(insanityPoints)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a intelligence.
     * @param intelligence the character' intelligence
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "intelligence/{intelligence}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByIntelligence(
            @PathVariable
            final Long intelligence) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByIntelligence(intelligence)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a magic.
     * @param magic the character' magic
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "magic/{magic}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByMagic(
            @PathVariable
            final Long magic) {
        Iterator<WFRPCharacterEntity> iter = repository.findByMagic(magic)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a movement.
     * @param movement the character' movement
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "movement/{movement}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByMovement(
            @PathVariable
            final Long movement) {
        Iterator<WFRPCharacterEntity> iter = repository.findByMovement(movement)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a name.
     * @param name the character' name
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPCharacterEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a strength.
     * @param strength the character' strength
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "strength/{strength}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByStrength(
            @PathVariable
            final Long strength) {
        Iterator<WFRPCharacterEntity> iter = repository.findByStrength(strength)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a strengthBonus.
     * @param strengthBonus the character' strengthBonus
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "strength_bonus/{strengthBonus}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByStrengthBonus(
            @PathVariable
            final Long strengthBonus) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByStrengthBonus(strengthBonus)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a toughness.
     * @param toughness the character' toughness
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "toughness/{toughness}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByToughness(
            @PathVariable
            final Long toughness) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByToughness(toughness)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a toughnessBonus.
     * @param toughnessBonus the character' toughnessBonus
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "toughness_bonus/{toughnessBonus}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByToughnessBonus(
            @PathVariable
            final Long toughnessBonus) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByToughnessBonus(toughnessBonus)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a weaponSkill.
     * @param weaponSkill the character' weaponSkill
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "weapon_skill/{weaponSkill}",
            method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByWeaponSkill(
            @PathVariable
            final Long weaponSkill) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByWeaponSkill(weaponSkill)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a willPower.
     * @param willPower the character' willPower
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "will_power/{willPower}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByWillPower(
            @PathVariable
            final Long willPower) {
        Iterator<WFRPCharacterEntity> iter = repository
                .findByWillPower(willPower)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCharacterEntity}s that share a wounds.
     * @param wounds the character' wounds
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "wounds/{wounds}", method = RequestMethod.GET)
    public List<Resource<WFRPCharacterEntity>> getByWounds(
            @PathVariable
            final Long wounds) {
        Iterator<WFRPCharacterEntity> iter = repository.findByWounds(wounds)
                .iterator();
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        while (iter.hasNext()) {
            resources.add(getCharacterResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPCharacterEntity}.
     * @param entity the {@link WFRPCharacterEntity}
     * @return {@link Resource}<{@link WFRPCharacterEntity}>
     */
    private Resource<WFRPCharacterEntity> getCharacterResource(
            final WFRPCharacterEntity entity) {
        Resource<WFRPCharacterEntity> resource =
                new Resource<WFRPCharacterEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link WFRPCharacterEntity}s.
     * @param entities the list of {@link WFRPCharacterEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPCharacterEntity>> save(
            @RequestBody
            final List<WFRPCharacterEntity> entities) {
        List<Resource<WFRPCharacterEntity>> resources =
                new ArrayList<Resource<WFRPCharacterEntity>>();
        Iterator<WFRPCharacterEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPCharacterEntity}.
     * @param entity the {@link WFRPCharacterEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPCharacterEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPCharacterEntity>> save(
            @RequestBody
            final WFRPCharacterEntity entity) {
        if (entity.getGender() != null
                && entity.getGender().getId() == null) {
            WFRPGenderEntity gender = null;
            List<Resource<WFRPGenderEntity>> list = null;
            try {
                Method method = WFRPGenderController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                Field field = WFRPGenderEntity.class.getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getGender()) != null) {
                        list = (List<Resource<WFRPGenderEntity>>) method.invoke(
                                WFRPGenderController.getInstance(),
                                (String) field.get(entity.getGender()));
                    }
                }
                if (list == null) {
                    method = WFRPGenderController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = WFRPGenderEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getGender()) != null) {
                            list = (List<Resource<WFRPGenderEntity>>) method
                                    .invoke(
                                            WFRPGenderController.getInstance(),
                                            (String) field
                                                    .get(entity.getGender()));
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
                gender = list.get(0).getContent();
            }
            if (gender == null) {
                gender = (WFRPGenderEntity) ((Resource) WFRPGenderController
                        .getInstance()
                        .save(entity.getGender()).get(0)).getContent();
            }
            entity.setGender(gender);
            list = null;
        }

        if (entity.getRace() != null
                && entity.getRace().getId() == null) {
            WFRPRaceEntity race = null;
            List<Resource<WFRPRaceEntity>> list = null;
            try {
                Method method = WFRPRaceController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                Field field = WFRPRaceEntity.class.getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getRace()) != null) {
                        list = (List<Resource<WFRPRaceEntity>>) method.invoke(
                                WFRPRaceController.getInstance(),
                                (String) field.get(entity.getRace()));
                    }
                }
                if (list == null) {
                    method = WFRPRaceController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = WFRPRaceEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getRace()) != null) {
                            list = (List<Resource<WFRPRaceEntity>>) method
                                    .invoke(
                                            WFRPRaceController.getInstance(),
                                            (String) field
                                                    .get(entity.getRace()));
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
                race = list.get(0).getContent();
            }
            if (race == null) {
                race = (WFRPRaceEntity) ((Resource) WFRPRaceController
                        .getInstance()
                        .save(entity.getRace()).get(0)).getContent();
            }
            entity.setRace(race);
            list = null;
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

        WFRPCharacterEntity savedEntity = repository.save(entity);
        List<Resource<WFRPCharacterEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
