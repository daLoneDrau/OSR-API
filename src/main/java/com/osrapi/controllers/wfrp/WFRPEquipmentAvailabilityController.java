package com.osrapi.controllers.wfrp;

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

import com.osrapi.models.wfrp.WFRPEquipmentAvailabilityEntity;
import com.osrapi.repositories.wfrp.WFRPEquipmentAvailabilityRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/equipment_availabilitys")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPEquipmentAvailabilityController {
    /** the static instance of {@link WFRPEquipmentAvailabilityController}. */
    private static WFRPEquipmentAvailabilityController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPEquipmentAvailabilityController}
     */
    public static WFRPEquipmentAvailabilityController getInstance() {
        if (instance == null) {
            new WFRPEquipmentAvailabilityController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPEquipmentAvailabilityRepository repository;
    /**
     * Creates a new instance of {@link WFRPEquipmentAvailabilityController}.
     */
    public WFRPEquipmentAvailabilityController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPEquipmentAvailabilityEntity}s.
     * @return {@link List}<{@link Resource}<
     *         {@link WFRPEquipmentAvailabilityEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentAvailabilityEntity>> getAll() {
        Iterator<WFRPEquipmentAvailabilityEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPEquipmentAvailabilityEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentAvailabilityEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentAvailabilityResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPEquipmentAvailabilityEntity}s that share a
     * code.
     * @param code the equipment_availability' code
     * @return {@link List}<{@link Resource}<
     *         {@link WFRPEquipmentAvailabilityEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentAvailabilityEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<WFRPEquipmentAvailabilityEntity> iter = repository
                .findByCode(code)
                .iterator();
        List<Resource<WFRPEquipmentAvailabilityEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentAvailabilityEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentAvailabilityResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPEquipmentAvailabilityEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<
     *         {@link WFRPEquipmentAvailabilityEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentAvailabilityEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPEquipmentAvailabilityEntity entity = repository.findOne(id);
        List<Resource<WFRPEquipmentAvailabilityEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentAvailabilityEntity>>();
        resources.add(getEquipmentAvailabilityResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPEquipmentAvailabilityEntity}s that share a
     * name.
     * @param name the equipment_availability' name
     * @return {@link List}<{@link Resource}<
     *         {@link WFRPEquipmentAvailabilityEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPEquipmentAvailabilityEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPEquipmentAvailabilityEntity> iter = repository
                .findByName(name)
                .iterator();
        List<Resource<WFRPEquipmentAvailabilityEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentAvailabilityEntity>>();
        while (iter.hasNext()) {
            resources.add(getEquipmentAvailabilityResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPEquipmentAvailabilityEntity}.
     * @param entity the {@link WFRPEquipmentAvailabilityEntity}
     * @return {@link Resource}<{@link WFRPEquipmentAvailabilityEntity}>
     */
    private Resource<WFRPEquipmentAvailabilityEntity>
            getEquipmentAvailabilityResource(
                    final WFRPEquipmentAvailabilityEntity entity) {
        Resource<WFRPEquipmentAvailabilityEntity> resource =
                new Resource<WFRPEquipmentAvailabilityEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }

    /**
     * Saves multiple {@link WFRPEquipmentAvailabilityEntity}s.
     * @param entities the list of {@link WFRPEquipmentAvailabilityEntity}
     *            instances
     * @return {@link List}<{@link Resource}<
     *         {@link WFRPEquipmentAvailabilityEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPEquipmentAvailabilityEntity>> save(
            @RequestBody
            final List<WFRPEquipmentAvailabilityEntity> entities) {
        List<Resource<WFRPEquipmentAvailabilityEntity>> resources =
                new ArrayList<Resource<WFRPEquipmentAvailabilityEntity>>();
        Iterator<WFRPEquipmentAvailabilityEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPEquipmentAvailabilityEntity}.
     * @param entity the {@link WFRPEquipmentAvailabilityEntity} instance
     * @return {@link List}<{@link Resource}<
     *         {@link WFRPEquipmentAvailabilityEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPEquipmentAvailabilityEntity>> save(
            @RequestBody
            final WFRPEquipmentAvailabilityEntity entity) {

        WFRPEquipmentAvailabilityEntity savedEntity = repository.save(entity);
        List<Resource<WFRPEquipmentAvailabilityEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
