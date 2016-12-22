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

import com.osrapi.models.wfrp.WFRPWeaponGroupEntity;
import com.osrapi.repositories.wfrp.WFRPWeaponGroupRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/weapon_groups")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPWeaponGroupController {
    /** the static instance of {@link WFRPWeaponGroupController}. */
    private static WFRPWeaponGroupController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPWeaponGroupController}
     */
    public static WFRPWeaponGroupController getInstance() {
        if (instance == null) {
            new WFRPWeaponGroupController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPWeaponGroupRepository repository;
    /** Creates a new instance of {@link WFRPWeaponGroupController}. */
    public WFRPWeaponGroupController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPWeaponGroupEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPWeaponGroupEntity>> getAll() {
        Iterator<WFRPWeaponGroupEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPWeaponGroupEntity>> resources =
                new ArrayList<Resource<WFRPWeaponGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPWeaponGroupEntity}s that share a code.
     * @param code the weapon_group' code
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponGroupEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<WFRPWeaponGroupEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<WFRPWeaponGroupEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<WFRPWeaponGroupEntity>> resources =
                new ArrayList<Resource<WFRPWeaponGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPWeaponGroupEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponGroupEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPWeaponGroupEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPWeaponGroupEntity entity = repository.findOne(id);
        List<Resource<WFRPWeaponGroupEntity>> resources =
                new ArrayList<Resource<WFRPWeaponGroupEntity>>();
        resources.add(getWeaponGroupResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPWeaponGroupEntity}s that share a name.
     * @param name the weapon_group' name
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponGroupEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPWeaponGroupEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPWeaponGroupEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPWeaponGroupEntity>> resources =
                new ArrayList<Resource<WFRPWeaponGroupEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponGroupResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPWeaponGroupEntity}.
     * @param entity the {@link WFRPWeaponGroupEntity}
     * @return {@link Resource}<{@link WFRPWeaponGroupEntity}>
     */
    private Resource<WFRPWeaponGroupEntity> getWeaponGroupResource(
            final WFRPWeaponGroupEntity entity) {
        Resource<WFRPWeaponGroupEntity> resource =
                new Resource<WFRPWeaponGroupEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }

    /**
     * Saves multiple {@link WFRPWeaponGroupEntity}s.
     * @param entities the list of {@link WFRPWeaponGroupEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponGroupEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPWeaponGroupEntity>> save(
            @RequestBody
            final List<WFRPWeaponGroupEntity> entities) {
        List<Resource<WFRPWeaponGroupEntity>> resources =
                new ArrayList<Resource<WFRPWeaponGroupEntity>>();
        Iterator<WFRPWeaponGroupEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPWeaponGroupEntity}.
     * @param entity the {@link WFRPWeaponGroupEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponGroupEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPWeaponGroupEntity>> save(
            @RequestBody
            final WFRPWeaponGroupEntity entity) {

        WFRPWeaponGroupEntity savedEntity = repository.save(entity);
        List<Resource<WFRPWeaponGroupEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
