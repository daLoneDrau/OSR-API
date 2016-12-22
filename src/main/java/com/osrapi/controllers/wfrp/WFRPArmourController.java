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

import com.osrapi.models.wfrp.WFRPArmourCoverageEntity;
import com.osrapi.models.wfrp.WFRPArmourEntity;
import com.osrapi.models.wfrp.WFRPArmourTypeEntity;
import com.osrapi.repositories.wfrp.WFRPArmourRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/armours")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPArmourController {
    /** the static instance of {@link WFRPArmourController}. */
    private static WFRPArmourController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPArmourController}
     */
    public static WFRPArmourController getInstance() {
        if (instance == null) {
            new WFRPArmourController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPArmourRepository repository;
    /** Creates a new instance of {@link WFRPArmourController}. */
    public WFRPArmourController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPArmourEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPArmourEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPArmourEntity>> getAll() {
        Iterator<WFRPArmourEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPArmourEntity>> resources =
                new ArrayList<Resource<WFRPArmourEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmourResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPArmourEntity}.
     * @param entity the {@link WFRPArmourEntity}
     * @return {@link Resource}<{@link WFRPArmourEntity}>
     */
    private Resource<WFRPArmourEntity> getArmourResource(
            final WFRPArmourEntity entity) {
        Resource<WFRPArmourEntity> resource = new Resource<WFRPArmourEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Gets a list of {@link WFRPArmourEntity}s that share a ap.
     * @param ap the armour' ap
     * @return {@link List}<{@link Resource}<{@link WFRPArmourEntity}>>
     */
    @RequestMapping(path = "ap/{ap}", method = RequestMethod.GET)
    public List<Resource<WFRPArmourEntity>> getByAp(
            @PathVariable
            final Long ap) {
        Iterator<WFRPArmourEntity> iter = repository.findByAp(ap)
                .iterator();
        List<Resource<WFRPArmourEntity>> resources =
                new ArrayList<Resource<WFRPArmourEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmourResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPArmourEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPArmourEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPArmourEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPArmourEntity entity = repository.findOne(id);
        List<Resource<WFRPArmourEntity>> resources =
                new ArrayList<Resource<WFRPArmourEntity>>();
        resources.add(getArmourResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPArmourEntity}s that share a name.
     * @param name the armour' name
     * @return {@link List}<{@link Resource}<{@link WFRPArmourEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPArmourEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPArmourEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPArmourEntity>> resources =
                new ArrayList<Resource<WFRPArmourEntity>>();
        while (iter.hasNext()) {
            resources.add(getArmourResource(iter.next()));
        }
        iter = null;
        return resources;
    }

    /**
     * Saves multiple {@link WFRPArmourEntity}s.
     * @param entities the list of {@link WFRPArmourEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPArmourEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPArmourEntity>> save(
            @RequestBody
            final List<WFRPArmourEntity> entities) {
        List<Resource<WFRPArmourEntity>> resources =
                new ArrayList<Resource<WFRPArmourEntity>>();
        Iterator<WFRPArmourEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPArmourEntity}.
     * @param entity the {@link WFRPArmourEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPArmourEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPArmourEntity>> save(
            @RequestBody
            final WFRPArmourEntity entity) {
        if (entity.getCoverage() != null
                && !entity.getCoverage().isEmpty()) {
            for (int i = entity.getCoverage().size() - 1; i >= 0; i--) {
                WFRPArmourCoverageEntity coverage = null;
                List<Resource<WFRPArmourCoverageEntity>> list = null;
                try {
                    Method method = WFRPArmourCoverageController.class
                            .getDeclaredMethod(
                                    "getByName", new Class[] { String.class });
                    Field field = WFRPArmourCoverageEntity.class
                            .getDeclaredField("name");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getCoverage().get(i)) != null) {
                            list = (List<
                                    Resource<WFRPArmourCoverageEntity>>) method
                                            .invoke(
                                                    WFRPArmourCoverageController
                                                            .getInstance(),
                                                    (String) field.get(entity
                                                            .getCoverage()
                                                            .get(i)));
                        }
                    }
                    if (list == null) {
                        method = WFRPArmourCoverageController.class
                                .getDeclaredMethod(
                                        "getByCode",
                                        new Class[] { String.class });
                        field = WFRPArmourCoverageEntity.class.getDeclaredField(
                                "code");
                        if (method != null
                                && field != null) {
                            field.setAccessible(true);
                            if (field
                                    .get(entity.getCoverage().get(i)) != null) {
                                list = (List<Resource<
                                        WFRPArmourCoverageEntity>>) method
                                                .invoke(
                                                        WFRPArmourCoverageController
                                                                .getInstance(),
                                                        (String) field
                                                                .get(entity
                                                                        .getCoverage()
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
                    coverage = list.get(0).getContent();
                }
                if (coverage == null) {
                    coverage =
                            (WFRPArmourCoverageEntity) ((Resource) WFRPArmourCoverageController
                                    .getInstance()
                                    .save(entity.getCoverage().get(i)).get(0))
                                            .getContent();
                }
                entity.getCoverage().set(i, coverage);
                list = null;
            }
        }

        if (entity.getType() != null
                && entity.getType().getId() == null) {
            WFRPArmourTypeEntity type = null;
            List<Resource<WFRPArmourTypeEntity>> list = null;
            try {
                Method method = WFRPArmourTypeController.class
                        .getDeclaredMethod(
                                "getByName", new Class[] { String.class });
                Field field = WFRPArmourTypeEntity.class
                        .getDeclaredField("name");
                if (method != null
                        && field != null) {
                    field.setAccessible(true);
                    if (field.get(entity.getType()) != null) {
                        list = (List<Resource<WFRPArmourTypeEntity>>) method
                                .invoke(
                                        WFRPArmourTypeController.getInstance(),
                                        (String) field.get(entity.getType()));
                    }
                }
                if (list == null) {
                    method = WFRPArmourTypeController.class.getDeclaredMethod(
                            "getByCode", new Class[] { String.class });
                    field = WFRPArmourTypeEntity.class.getDeclaredField(
                            "code");
                    if (method != null
                            && field != null) {
                        field.setAccessible(true);
                        if (field.get(entity.getType()) != null) {
                            list = (List<Resource<WFRPArmourTypeEntity>>) method
                                    .invoke(
                                            WFRPArmourTypeController
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
                type = (WFRPArmourTypeEntity) ((Resource) WFRPArmourTypeController
                        .getInstance()
                        .save(entity.getType()).get(0)).getContent();
            }
            entity.setType(type);
            list = null;
        }

        WFRPArmourEntity savedEntity = repository.save(entity);
        List<Resource<WFRPArmourEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
