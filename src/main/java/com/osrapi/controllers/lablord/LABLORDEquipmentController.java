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
import com.osrapi.models.lablord.LABLORDEquipmentEntity;
import com.osrapi.models.lablord.LABLORDEquipmentTypeEntity;
import com.osrapi.models.lablord.LABLORDWeaponDataEntity;
import com.osrapi.repositories.lablord.LABLORDEquipmentRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/equipments")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDEquipmentController {
    /** the static instance of {@link LABLORDEquipmentController}. */
    private static LABLORDEquipmentController instance;
    /**
     * Gets the static instance.
     * @return {@link LABLORDEquipmentController}
     */
    public static LABLORDEquipmentController getInstance() {
        if (instance == null) {
            new LABLORDEquipmentController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private LABLORDEquipmentRepository repository;
    /** Creates a new instance of {@link LABLORDEquipmentController}. */
    public LABLORDEquipmentController() {
        instance = this;
    }
    /**
     * Gets a list of {@link LABLORDEquipmentEntity}s.
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentEntity>> getAll() {
        Iterator<LABLORDEquipmentEntity> iter = repository.findAll()
                .iterator();
        List<Resource<LABLORDEquipmentEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDEquipmentEntity}s that share a description.
     * @param description the equipment' description
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentEntity>> getByDescription(
            @PathVariable
            final String description) {
        Iterator<LABLORDEquipmentEntity> iter = repository
                .findByDescription(description)
                .iterator();
        List<Resource<LABLORDEquipmentEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link LABLORDEquipmentEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentEntity>> getById(
            @PathVariable
            final Long id) {
        LABLORDEquipmentEntity entity = repository.findOne(id);
        List<Resource<LABLORDEquipmentEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentEntity>>();
        resources.add(getEquipmentResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDEquipmentEntity}s that share a name.
     * @param name the equipment' name
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<LABLORDEquipmentEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<LABLORDEquipmentEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link LABLORDEquipmentEntity}s that share a weight.
     * @param weight the equipment' weight
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(path = "weight/{weight}", method = RequestMethod.GET)
    public List<Resource<LABLORDEquipmentEntity>> getByWeight(
            @PathVariable
            final float weight) {
        Iterator<LABLORDEquipmentEntity> iter = repository.findByWeight(weight)
                .iterator();
        List<Resource<LABLORDEquipmentEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link LABLORDEquipmentEntity}.
     * @param entity the {@link LABLORDEquipmentEntity}
     * @return {@link Resource}<{@link LABLORDEquipmentEntity}>
     */
    private Resource<LABLORDEquipmentEntity> getEquipmentResource(
            final LABLORDEquipmentEntity entity) {
        Resource<LABLORDEquipmentEntity> resource =
                new Resource<LABLORDEquipmentEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves a single {@link LABLORDEquipmentEntity}.
     * @param entity the {@link LABLORDEquipmentEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<LABLORDEquipmentEntity>> save(
            @RequestBody
            final LABLORDEquipmentEntity entity) {
        System.out.println("save " + entity.getName());
        if (entity.getArmorData() != null
                && entity.getArmorData().getId() == null) {
            setArmorDataIdFromRepository(entity);
        }

        if (entity.getType() != null
                && !entity.getType().isEmpty()) {
            for (int i = entity.getType().size() - 1; i >= 0; i--) {
                LABLORDEquipmentTypeEntity type = null;
                List<Resource<LABLORDEquipmentTypeEntity>> list = null;
                try {
                    Method method = null;
                    try {
                        method = LABLORDEquipmentTypeController.class
                                .getDeclaredMethod(
                                        "getByName",
                                        new Class[] { String.class });
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
                    try {
                        field = LABLORDEquipmentTypeEntity.class
                                .getDeclaredField("name");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getType().get(i)) != null) {
                            list = (List<Resource<
                                    LABLORDEquipmentTypeEntity>>) method
                                            .invoke(
                                                    LABLORDEquipmentTypeController
                                                            .getInstance(),
                                                    (String) field.get(
                                                            entity.getType()
                                                                    .get(i)));
                        }
                    }
                    if (list == null) {
                        try {
                            method = LABLORDEquipmentTypeController.class
                                    .getDeclaredMethod(
                                            "getByCode",
                                            new Class[] { String.class });
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        try {
                            field = LABLORDEquipmentTypeEntity.class
                                    .getDeclaredField(
                                            "code");
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getType().get(i)) != null) {
                                list = (List<Resource<
                                        LABLORDEquipmentTypeEntity>>) method
                                                .invoke(
                                                        LABLORDEquipmentTypeController
                                                                .getInstance(),
                                                        (String) field
                                                                .get(entity
                                                                        .getType()
                                                                        .get(i)));
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
                    type = list.get(0).getContent();
                }
                if (type == null) {
                    type = (LABLORDEquipmentTypeEntity) ((Resource) LABLORDEquipmentTypeController
                            .getInstance()
                            .save(entity.getType().get(i)).get(0)).getContent();
                }
                entity.getType().set(i, type);
                list = null;
            }
        }

        if (entity.getWeaponData() != null
                && entity.getWeaponData().getId() == null) {
            setWeaponDataIdFromRepository(entity);
        }

        LABLORDEquipmentEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDEquipmentEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Saves multiple {@link LABLORDEquipmentEntity}s.
     * @param entities the list of {@link LABLORDEquipmentEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<LABLORDEquipmentEntity>> save(
            @RequestBody
            final List<LABLORDEquipmentEntity> entities) {
        List<Resource<LABLORDEquipmentEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentEntity>>();
        Iterator<LABLORDEquipmentEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }

    private void setArmorDataIdFromRepository(
            final LABLORDEquipmentEntity entity) {
        LABLORDArmorEntity memberEntity = null;
        List<Resource<LABLORDArmorEntity>> list = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = LABLORDArmorController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                field = LABLORDArmorEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {}
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity.getArmorData()) != null) {
                    list = (List<Resource<LABLORDArmorEntity>>) method
                            .invoke(
                                    LABLORDArmorController.getInstance(),
                                    (String) field
                                            .get(entity.getArmorData()));
                }
            }
            if (list == null) {
                try {
                    method = LABLORDArmorController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = LABLORDArmorEntity.class
                            .getDeclaredField("code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {}
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getArmorData()) != null) {
                        list = (List<Resource<LABLORDArmorEntity>>) method
                                .invoke(LABLORDArmorController
                                        .getInstance(), (String) field.get(
                                                entity.getArmorData()));
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
                    (LABLORDArmorEntity) ((Resource) LABLORDArmorController
                            .getInstance().save(
                                    entity.getArmorData())
                            .get(0)).getContent();
        }
        entity.setArmorData(memberEntity);
        list = null;
    }

    /**
     * Tries to set the Id for an entity to be saved by locating it in the
     * repository.
     * @param entity the {@link LABLORDEquipmentEntity} instance
     */
    private void setIdFromRepository(final LABLORDEquipmentEntity entity) {
        List<LABLORDEquipmentEntity> old = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = repository.getClass().getDeclaredMethod(
                        "findByName", new Class[] { String.class });
                field = LABLORDEquipmentEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    old = (List<LABLORDEquipmentEntity>) method.invoke(
                            repository, (String) field.get(entity));
                }
            }
            if (old == null
                    || old != null
                            && old.size() > 1) {
                try {
                    method = repository.getClass().getDeclaredMethod(
                            "findByCode", new Class[] { String.class });
                    field = LABLORDEquipmentEntity.class.getDeclaredField(
                            "code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity) != null) {
                        old = (List<LABLORDEquipmentEntity>) method.invoke(
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

    private void setWeaponDataIdFromRepository(
            final LABLORDEquipmentEntity entity) {
        LABLORDWeaponDataEntity memberEntity = null;
        List<Resource<LABLORDWeaponDataEntity>> list = null;
        try {
            Method method = null;
            Field field = null;
            try {
                method = LABLORDWeaponDataController.class.getDeclaredMethod(
                        "getByName", new Class[] { String.class });
                field = LABLORDWeaponDataEntity.class.getDeclaredField("name");
            } catch (NoSuchMethodException | NoSuchFieldException e) {}
            if (method != null
                    && field != null) {
                field.setAccessible(true);
                if (field.get(entity.getWeaponData()) != null) {
                    list = (List<Resource<LABLORDWeaponDataEntity>>) method
                            .invoke(
                                    LABLORDWeaponDataController.getInstance(),
                                    (String) field
                                            .get(entity.getWeaponData()));
                }
            }
            if (list == null) {
                try {
                    method = LABLORDWeaponDataController.class
                            .getDeclaredMethod(
                                    "getByCode", new Class[] { String.class });
                    field = LABLORDWeaponDataEntity.class
                            .getDeclaredField("code");
                } catch (NoSuchMethodException | NoSuchFieldException e) {}
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getWeaponData()) != null) {
                        list = (List<Resource<LABLORDWeaponDataEntity>>) method
                                .invoke(LABLORDWeaponDataController
                                        .getInstance(), (String) field.get(
                                                entity.getWeaponData()));
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
                    (LABLORDWeaponDataEntity) ((Resource) LABLORDWeaponDataController
                            .getInstance().save(
                                    entity.getWeaponData())
                            .get(0)).getContent();
        }
        entity.setWeaponData(memberEntity);
        list = null;
    }
    /**
     * Updates a single {@link LABLORDEquipmentEntity}.
     * @param entity the {@link LABLORDEquipmentEntity} instance
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public List<Resource<LABLORDEquipmentEntity>> update(
            @RequestBody
            final LABLORDEquipmentEntity entity) {
        if (entity.getId() == null) {
            setIdFromRepository(entity);
        }
        if (entity.getArmorData() != null
                && entity.getArmorData().getId() == null) {
            setArmorDataIdFromRepository(entity);
        }

        if (entity.getType() != null
                && !entity.getType().isEmpty()) {
            for (int i = entity.getType().size() - 1; i >= 0; i--) {
                LABLORDEquipmentTypeEntity type = null;
                List<Resource<LABLORDEquipmentTypeEntity>> list = null;
                try {
                    Method method = null;
                    try {
                        method = LABLORDEquipmentTypeController.class
                                .getDeclaredMethod(
                                        "getByName",
                                        new Class[] { String.class });
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Field field = null;
                    try {
                        field = LABLORDEquipmentTypeEntity.class
                                .getDeclaredField("name");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getType().get(i)) != null) {
                            list = (List<Resource<
                                    LABLORDEquipmentTypeEntity>>) method
                                            .invoke(
                                                    LABLORDEquipmentTypeController
                                                            .getInstance(),
                                                    (String) field.get(
                                                            entity.getType()
                                                                    .get(i)));
                        }
                    }
                    if (list == null) {
                        try {
                            method = LABLORDEquipmentTypeController.class
                                    .getDeclaredMethod(
                                            "getByCode",
                                            new Class[] { String.class });
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        try {
                            field = LABLORDEquipmentTypeEntity.class
                                    .getDeclaredField(
                                            "code");
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field.get(entity.getType().get(i)) != null) {
                                list = (List<Resource<
                                        LABLORDEquipmentTypeEntity>>) method
                                                .invoke(
                                                        LABLORDEquipmentTypeController
                                                                .getInstance(),
                                                        (String) field
                                                                .get(entity
                                                                        .getType()
                                                                        .get(i)));
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
                    type = list.get(0).getContent();
                }
                if (type == null) {
                    type = (LABLORDEquipmentTypeEntity) ((Resource) LABLORDEquipmentTypeController
                            .getInstance()
                            .save(entity.getType().get(i)).get(0)).getContent();
                }
                entity.getType().set(i, type);
                list = null;
            }
        }

        if (entity.getWeaponData() != null
                && entity.getWeaponData().getId() == null) {
            setWeaponDataIdFromRepository(entity);
        }

        LABLORDEquipmentEntity savedEntity = repository.save(entity);
        List<Resource<LABLORDEquipmentEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
    /**
     * Updates multiple {@link LABLORDEquipmentEntity}s.
     * @param entities the list of {@link LABLORDEquipmentEntity} instances
     * @return {@link List}<{@link Resource}<{@link LABLORDEquipmentEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.PUT)
    public List<Resource<LABLORDEquipmentEntity>> update(
            @RequestBody
            final List<LABLORDEquipmentEntity> entities) {
        List<Resource<LABLORDEquipmentEntity>> resources =
                new ArrayList<Resource<LABLORDEquipmentEntity>>();
        Iterator<LABLORDEquipmentEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(update(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
}
