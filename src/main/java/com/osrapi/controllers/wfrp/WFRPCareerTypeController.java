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

import com.osrapi.models.wfrp.WFRPCareerTypeEntity;
import com.osrapi.repositories.wfrp.WFRPCareerTypeRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/career_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPCareerTypeController {
    /** the static instance of {@link WFRPCareerTypeController}. */
    private static WFRPCareerTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPCareerTypeController}
     */
    public static WFRPCareerTypeController getInstance() {
        if (instance == null) {
            new WFRPCareerTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPCareerTypeRepository repository;
    /** Creates a new instance of {@link WFRPCareerTypeController}. */
    public WFRPCareerTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPCareerTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPCareerTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPCareerTypeEntity>> getAll() {
        Iterator<WFRPCareerTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPCareerTypeEntity>> resources =
                new ArrayList<Resource<WFRPCareerTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getCareerTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCareerTypeEntity}s that share a code.
     * @param code the career_type' code
     * @return {@link List}<{@link Resource}<{@link WFRPCareerTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<WFRPCareerTypeEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<WFRPCareerTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<WFRPCareerTypeEntity>> resources =
                new ArrayList<Resource<WFRPCareerTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getCareerTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPCareerTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPCareerTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPCareerTypeEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPCareerTypeEntity entity = repository.findOne(id);
        List<Resource<WFRPCareerTypeEntity>> resources =
                new ArrayList<Resource<WFRPCareerTypeEntity>>();
        resources.add(getCareerTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPCareerTypeEntity}s that share a name.
     * @param name the career_type' name
     * @return {@link List}<{@link Resource}<{@link WFRPCareerTypeEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPCareerTypeEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPCareerTypeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPCareerTypeEntity>> resources =
                new ArrayList<Resource<WFRPCareerTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getCareerTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPCareerTypeEntity}.
     * @param entity the {@link WFRPCareerTypeEntity}
     * @return {@link Resource}<{@link WFRPCareerTypeEntity}>
     */
    private Resource<WFRPCareerTypeEntity> getCareerTypeResource(
            final WFRPCareerTypeEntity entity) {
        Resource<WFRPCareerTypeEntity> resource =
                new Resource<WFRPCareerTypeEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }

    /**
     * Saves multiple {@link WFRPCareerTypeEntity}s.
     * @param entities the list of {@link WFRPCareerTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPCareerTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPCareerTypeEntity>> save(
            @RequestBody
            final List<WFRPCareerTypeEntity> entities) {
        List<Resource<WFRPCareerTypeEntity>> resources =
                new ArrayList<Resource<WFRPCareerTypeEntity>>();
        Iterator<WFRPCareerTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPCareerTypeEntity}.
     * @param entity the {@link WFRPCareerTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPCareerTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPCareerTypeEntity>> save(
            @RequestBody
            final WFRPCareerTypeEntity entity) {

        WFRPCareerTypeEntity savedEntity = repository.save(entity);
        List<Resource<WFRPCareerTypeEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
