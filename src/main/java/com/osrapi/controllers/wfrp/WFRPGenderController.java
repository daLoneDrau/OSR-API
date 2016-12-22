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

import com.osrapi.models.wfrp.WFRPGenderEntity;
import com.osrapi.repositories.wfrp.WFRPGenderRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/genders")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPGenderController {
    /** the static instance of {@link WFRPGenderController}. */
    private static WFRPGenderController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPGenderController}
     */
    public static WFRPGenderController getInstance() {
        if (instance == null) {
            new WFRPGenderController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPGenderRepository repository;
    /** Creates a new instance of {@link WFRPGenderController}. */
    public WFRPGenderController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPGenderEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPGenderEntity>> getAll() {
        Iterator<WFRPGenderEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPGenderEntity>> resources =
                new ArrayList<Resource<WFRPGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPGenderEntity}s that share a description.
     * @param description the gender' description
     * @return {@link List}<{@link Resource}<{@link WFRPGenderEntity}>>
     */
    @RequestMapping(path = "description/{description}",
            method = RequestMethod.GET)
    public List<Resource<WFRPGenderEntity>> getByDescription(
            @PathVariable
            final String description) {
        Iterator<WFRPGenderEntity> iter = repository
                .findByDescription(description)
                .iterator();
        List<Resource<WFRPGenderEntity>> resources =
                new ArrayList<Resource<WFRPGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPGenderEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPGenderEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPGenderEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPGenderEntity entity = repository.findOne(id);
        List<Resource<WFRPGenderEntity>> resources =
                new ArrayList<Resource<WFRPGenderEntity>>();
        resources.add(getGenderResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPGenderEntity}s that share a name.
     * @param name the gender' name
     * @return {@link List}<{@link Resource}<{@link WFRPGenderEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPGenderEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPGenderEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPGenderEntity>> resources =
                new ArrayList<Resource<WFRPGenderEntity>>();
        while (iter.hasNext()) {
            resources.add(getGenderResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPGenderEntity}.
     * @param entity the {@link WFRPGenderEntity}
     * @return {@link Resource}<{@link WFRPGenderEntity}>
     */
    private Resource<WFRPGenderEntity> getGenderResource(
            final WFRPGenderEntity entity) {
        Resource<WFRPGenderEntity> resource = new Resource<WFRPGenderEntity>(
                entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }

    /**
     * Saves multiple {@link WFRPGenderEntity}s.
     * @param entities the list of {@link WFRPGenderEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPGenderEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPGenderEntity>> save(
            @RequestBody
            final List<WFRPGenderEntity> entities) {
        List<Resource<WFRPGenderEntity>> resources =
                new ArrayList<Resource<WFRPGenderEntity>>();
        Iterator<WFRPGenderEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPGenderEntity}.
     * @param entity the {@link WFRPGenderEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPGenderEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPGenderEntity>> save(
            @RequestBody
            final WFRPGenderEntity entity) {

        WFRPGenderEntity savedEntity = repository.save(entity);
        List<Resource<WFRPGenderEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
