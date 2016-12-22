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

import com.osrapi.models.wfrp.WFRPArmourEntity;
import com.osrapi.models.wfrp.WFRPEquipmentAvailabilityEntity;
import com.osrapi.models.wfrp.WFRPEquipmentEntity;
import com.osrapi.models.wfrp.WFRPEquipmentTypeEntity;
import com.osrapi.models.wfrp.WFRPWeaponEntity;
import com.osrapi.models.wfrp.WFRPWeaponQualityEntity;
import com.osrapi.repositories.wfrp.WFRPEquipmentRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/equipments")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPEquipmentController {
    /** the static instance of {@link WFRPEquipmentController}. */
    private static WFRPEquipmentController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPEquipmentController}
     */
    public static WFRPEquipmentController getInstance() {
        if (instance == null) {
            new WFRPEquipmentController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPEquipmentRepository repository;
    /** Creates a new instance of {@link WFRPEquipmentController}. */
    public WFRPEquipmentController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPEquipmentEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getAll() {
        Iterator<WFRPEquipmentEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPEquipmentEntity}s that share a costGc.
     * @param costGc the equipment' costGc
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "cost_gc/{costGc}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getByCostGc(
            @PathVariable
            final Long costGc) {
        Iterator<WFRPEquipmentEntity> iter = repository.findByCostGc(costGc)
                .iterator();
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPEquipmentEntity}s that share a costP.
     * @param costP the equipment' costP
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "cost_p/{costP}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getByCostP(
            @PathVariable
            final Long costP) {
        Iterator<WFRPEquipmentEntity> iter = repository.findByCostP(costP)
                .iterator();
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPEquipmentEntity}s that share a costS.
     * @param costS the equipment' costS
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "cost_s/{costS}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getByCostS(
            @PathVariable
            final Long costS) {
        Iterator<WFRPEquipmentEntity> iter = repository.findByCostS(costS)
                .iterator();
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPEquipmentEntity}s that share a description.
     * @param description the equipment' description
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getByDescription(
            @PathVariable
            final String description) {
        Iterator<WFRPEquipmentEntity> iter = repository
                .findByDescription(description)
                .iterator();
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }

    /**
     * Gets a list of {@link WFRPEquipmentEntity}s that share a encumbrance.
     * @param encumbrance the equipment' encumbrance
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "encumbrance/{encumbrance}",
            method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getByEncumbrance(
            @PathVariable
            final Long encumbrance) {
        Iterator<WFRPEquipmentEntity> iter = repository
                .findByEncumbrance(encumbrance)
                .iterator();
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPEquipmentEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPEquipmentEntity entity = repository.findOne(id);
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        resources.add(getEquipmentResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPEquipmentEntity}s that share a name.
     * @param name the equipment' name
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPEquipmentEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPEquipmentEntity}.
     * @param entity the {@link WFRPEquipmentEntity}
     * @return {@link Resource}<{@link WFRPEquipmentEntity}>
     */
    private Resource<WFRPEquipmentEntity> getEquipmentResource(
            final WFRPEquipmentEntity entity) {
        Resource<WFRPEquipmentEntity> resource =
                new Resource<WFRPEquipmentEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link WFRPEquipmentEntity}s.
     * @param entities the list of {@link WFRPEquipmentEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPEquipmentEntity>> save(
            @RequestBody
            final List<WFRPEquipmentEntity> entities) {
        List<Resource<WFRPEquipmentEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentEntity>>();
        Iterator<WFRPEquipmentEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPEquipmentEntity}.
     * @param entity the {@link WFRPEquipmentEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPEquipmentEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPEquipmentEntity>> save(
            @RequestBody
            final WFRPEquipmentEntity entity) {
        if (entity.getArmourData() != null
                && entity.getArmourData().getId() == null) {
            WFRPArmourEntity armourData = null;
            List<Resource<WFRPArmourEntity>> list = null;
            try {
                Method method = WFRPArmourController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                Field field = WFRPArmourEntity.class.getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getArmourData()) != null) {
                        list = (List<Resource<WFRPArmourEntity>>) method.invoke(
                                WFRPArmourController.getInstance(),
                                (String) field.get(entity.getArmourData()));
                    }
                }
                if (list == null) {
                    method = WFRPArmourController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = WFRPArmourEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getArmourData()) != null) {
                            list = (List<Resource<WFRPArmourEntity>>) method
                                    .invoke(
                                            WFRPArmourController.getInstance(),
                                            (String) field.get(
                                                    entity.getArmourData()));
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
                armourData = list.get(0).getContent();
            }
            if (armourData == null) {
                armourData = (WFRPArmourEntity) ((Resource) WFRPArmourController
                        .getInstance()
                        .save(entity.getArmourData()).get(0)).getContent();
            }
            entity.setArmourData(armourData);
            list = null;
        }

        if (entity.getAvailability() != null
                && entity.getAvailability().getId() == null) {
            WFRPEquipmentAvailabilityEntity availability = null;
            List<Resource<WFRPEquipmentAvailabilityEntity>> list = null;
            try {
                Method method = WFRPEquipmentAvailabilityController.class
                        .getDeclaredMethod(
                                "getByName", new Class[] { String.class });
                Field field = WFRPEquipmentAvailabilityEntity.class
                        .getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getAvailability()) != null) {
                        list = (List<Resource<
                                WFRPEquipmentAvailabilityEntity>>) method
                                        .invoke(
                                                WFRPEquipmentAvailabilityController
                                                        .getInstance(),
                                                (String) field
                                                        .get(entity
                                                                .getAvailability()));
                    }
                }
                if (list == null) {
                    method = WFRPEquipmentAvailabilityController.class
                            .getDeclaredMethod(
                                    "getByCode", new Class[] { String.class });
                    field = WFRPEquipmentAvailabilityEntity.class
                            .getDeclaredField(
                                    "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getAvailability()) != null) {
                            list = (List<Resource<
                                    WFRPEquipmentAvailabilityEntity>>) method
                                            .invoke(
                                                    WFRPEquipmentAvailabilityController
                                                            .getInstance(),
                                                    (String) field.get(
                                                            entity.getAvailability()));
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
                availability = list.get(0).getContent();
            }
            if (availability == null) {
                availability =
                        (WFRPEquipmentAvailabilityEntity) ((Resource) WFRPEquipmentAvailabilityController
                                .getInstance()
                                .save(entity.getAvailability()).get(0))
                                        .getContent();
            }
            entity.setAvailability(availability);
            list = null;
        }

        if (entity.getType() != null
                && entity.getType().getId() == null) {
            WFRPEquipmentTypeEntity type = null;
            List<Resource<WFRPEquipmentTypeEntity>> list = null;
            try {
                Method method = WFRPEquipmentTypeController.class
                        .getDeclaredMethod(
                                "getByName", new Class[] { String.class });
                Field field = WFRPEquipmentTypeEntity.class
                        .getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getType()) != null) {
                        list = (List<Resource<WFRPEquipmentTypeEntity>>) method
                                .invoke(
                                        WFRPEquipmentTypeController
                                                .getInstance(),
                                        (String) field.get(entity.getType()));
                    }
                }
                if (list == null) {
                    method = WFRPEquipmentTypeController.class
                            .getDeclaredMethod(
                                    "getByCode", new Class[] { String.class });
                    field = WFRPEquipmentTypeEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getType()) != null) {
                            list = (List<
                                    Resource<WFRPEquipmentTypeEntity>>) method
                                            .invoke(
                                                    WFRPEquipmentTypeController
                                                            .getInstance(),
                                                    (String) field
                                                            .get(entity
                                                                    .getType()));
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
                type = (WFRPEquipmentTypeEntity) ((Resource) WFRPEquipmentTypeController
                        .getInstance()
                        .save(entity.getType()).get(0)).getContent();
            }
            entity.setType(type);
            list = null;
        }

        if (entity.getWeaponData() != null
                && entity.getWeaponData().getId() == null) {
            WFRPWeaponEntity weaponData = null;
            List<Resource<WFRPWeaponEntity>> list = null;
            try {
                Method method = WFRPWeaponController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                Field field = WFRPWeaponEntity.class.getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getWeaponData()) != null) {
                        list = (List<Resource<WFRPWeaponEntity>>) method.invoke(
                                WFRPWeaponController.getInstance(),
                                (String) field.get(entity.getWeaponData()));
                    }
                }
                if (list == null) {
                    method = WFRPWeaponController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = WFRPWeaponEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getWeaponData()) != null) {
                            list = (List<Resource<WFRPWeaponEntity>>) method
                                    .invoke(
                                            WFRPWeaponController.getInstance(),
                                            (String) field.get(
                                                    entity.getWeaponData()));
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
                weaponData = list.get(0).getContent();
            }
            if (weaponData == null) {
                weaponData = (WFRPWeaponEntity) ((Resource) WFRPWeaponController
                        .getInstance()
                        .save(entity.getWeaponData()).get(0)).getContent();
            }
            entity.setWeaponData(weaponData);
            list = null;
        }

        if (entity.getWeaponQuality() != null
                && entity.getWeaponQuality().getId() == null) {
            WFRPWeaponQualityEntity weaponQuality = null;
            List<Resource<WFRPWeaponQualityEntity>> list = null;
            try {
                Method method = WFRPWeaponQualityController.class
                        .getDeclaredMethod(
                                "getByName", new Class[] { String.class });
                Field field = WFRPWeaponQualityEntity.class
                        .getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getWeaponQuality()) != null) {
                        list = (List<Resource<WFRPWeaponQualityEntity>>) method
                                .invoke(
                                        WFRPWeaponQualityController
                                                .getInstance(),
                                        (String) field.get(
                                                entity.getWeaponQuality()));
                    }
                }
                if (list == null) {
                    method = WFRPWeaponQualityController.class
                            .getDeclaredMethod(
                                    "getByCode", new Class[] { String.class });
                    field = WFRPWeaponQualityEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getWeaponQuality()) != null) {
                            list = (List<
                                    Resource<WFRPWeaponQualityEntity>>) method
                                            .invoke(
                                                    WFRPWeaponQualityController
                                                            .getInstance(),
                                                    (String) field.get(
                                                            entity.getWeaponQuality()));
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
                weaponQuality = list.get(0).getContent();
            }
            if (weaponQuality == null) {
                weaponQuality =
                        (WFRPWeaponQualityEntity) ((Resource) WFRPWeaponQualityController
                                .getInstance()
                                .save(entity.getWeaponQuality()).get(0))
                                        .getContent();
            }
            entity.setWeaponQuality(weaponQuality);
            list = null;
        }

        WFRPEquipmentEntity savedEntity = repository.save(entity);
        List<Resource<WFRPEquipmentEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
