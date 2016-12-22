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

import com.osrapi.models.wfrp.WFRPWeaponReloadEntity;
import com.osrapi.repositories.wfrp.WFRPWeaponReloadRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/weapon_reloads")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPWeaponReloadController {
    /** the static instance of {@link WFRPWeaponReloadController}. */
    private static WFRPWeaponReloadController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPWeaponReloadController}
     */
    public static WFRPWeaponReloadController getInstance() {
        if (instance == null) {
            new WFRPWeaponReloadController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPWeaponReloadRepository repository;
    /** Creates a new instance of {@link WFRPWeaponReloadController}. */
    public WFRPWeaponReloadController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPWeaponReloadEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponReloadEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPWeaponReloadEntity>> getAll() {
        Iterator<WFRPWeaponReloadEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPWeaponReloadEntity>> resources =
                new ArrayList<Resource<WFRPWeaponReloadEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponReloadResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPWeaponReloadEntity}s that share a code.
     * @param code the weapon_reload' code
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponReloadEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<WFRPWeaponReloadEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<WFRPWeaponReloadEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<WFRPWeaponReloadEntity>> resources =
                new ArrayList<Resource<WFRPWeaponReloadEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponReloadResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPWeaponReloadEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponReloadEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPWeaponReloadEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPWeaponReloadEntity entity = repository.findOne(id);
        List<Resource<WFRPWeaponReloadEntity>> resources =
                new ArrayList<Resource<WFRPWeaponReloadEntity>>();
        resources.add(getWeaponReloadResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPWeaponReloadEntity}s that share a name.
     * @param name the weapon_reload' name
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponReloadEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPWeaponReloadEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPWeaponReloadEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPWeaponReloadEntity>> resources =
                new ArrayList<Resource<WFRPWeaponReloadEntity>>();
        while (iter.hasNext()) {
            resources.add(getWeaponReloadResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPWeaponReloadEntity}.
     * @param entity the {@link WFRPWeaponReloadEntity}
     * @return {@link Resource}<{@link WFRPWeaponReloadEntity}>
     */
    private Resource<WFRPWeaponReloadEntity> getWeaponReloadResource(
            final WFRPWeaponReloadEntity entity) {
        Resource<WFRPWeaponReloadEntity> resource =
                new Resource<WFRPWeaponReloadEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }

    /**
     * Saves multiple {@link WFRPWeaponReloadEntity}s.
     * @param entities the list of {@link WFRPWeaponReloadEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponReloadEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPWeaponReloadEntity>> save(
            @RequestBody
            final List<WFRPWeaponReloadEntity> entities) {
        List<Resource<WFRPWeaponReloadEntity>> resources =
                new ArrayList<Resource<WFRPWeaponReloadEntity>>();
        Iterator<WFRPWeaponReloadEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPWeaponReloadEntity}.
     * @param entity the {@link WFRPWeaponReloadEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPWeaponReloadEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPWeaponReloadEntity>> save(
            @RequestBody
            final WFRPWeaponReloadEntity entity) {

        WFRPWeaponReloadEntity savedEntity = repository.save(entity);
        List<Resource<WFRPWeaponReloadEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
