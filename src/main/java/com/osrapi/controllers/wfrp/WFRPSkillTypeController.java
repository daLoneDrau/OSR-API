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

import com.osrapi.models.wfrp.WFRPSkillTypeEntity;
import com.osrapi.repositories.wfrp.WFRPSkillTypeRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/skill_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPSkillTypeController {
    /** the static instance of {@link WFRPSkillTypeController}. */
    private static WFRPSkillTypeController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPSkillTypeController}
     */
    public static WFRPSkillTypeController getInstance() {
        if (instance == null) {
            new WFRPSkillTypeController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPSkillTypeRepository repository;
    /** Creates a new instance of {@link WFRPSkillTypeController}. */
    public WFRPSkillTypeController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPSkillTypeEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPSkillTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPSkillTypeEntity>> getAll() {
        Iterator<WFRPSkillTypeEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPSkillTypeEntity>> resources =
                new ArrayList<Resource<WFRPSkillTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPSkillTypeEntity}s that share a code.
     * @param code the skill_type' code
     * @return {@link List}<{@link Resource}<{@link WFRPSkillTypeEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<WFRPSkillTypeEntity>> getByCode(
            @PathVariable
            final Long code) {
        Iterator<WFRPSkillTypeEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<WFRPSkillTypeEntity>> resources =
                new ArrayList<Resource<WFRPSkillTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPSkillTypeEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPSkillTypeEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPSkillTypeEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPSkillTypeEntity entity = repository.findOne(id);
        List<Resource<WFRPSkillTypeEntity>> resources =
                new ArrayList<Resource<WFRPSkillTypeEntity>>();
        resources.add(getSkillTypeResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPSkillTypeEntity}s that share a name.
     * @param name the skill_type' name
     * @return {@link List}<{@link Resource}<{@link WFRPSkillTypeEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPSkillTypeEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPSkillTypeEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPSkillTypeEntity>> resources =
                new ArrayList<Resource<WFRPSkillTypeEntity>>();
        while (iter.hasNext()) {
            resources.add(getSkillTypeResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPSkillTypeEntity}.
     * @param entity the {@link WFRPSkillTypeEntity}
     * @return {@link Resource}<{@link WFRPSkillTypeEntity}>
     */
    private Resource<WFRPSkillTypeEntity> getSkillTypeResource(
            final WFRPSkillTypeEntity entity) {
        Resource<WFRPSkillTypeEntity> resource =
                new Resource<WFRPSkillTypeEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }

    /**
     * Saves multiple {@link WFRPSkillTypeEntity}s.
     * @param entities the list of {@link WFRPSkillTypeEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPSkillTypeEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPSkillTypeEntity>> save(
            @RequestBody
            final List<WFRPSkillTypeEntity> entities) {
        List<Resource<WFRPSkillTypeEntity>> resources =
                new ArrayList<Resource<WFRPSkillTypeEntity>>();
        Iterator<WFRPSkillTypeEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPSkillTypeEntity}.
     * @param entity the {@link WFRPSkillTypeEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPSkillTypeEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPSkillTypeEntity>> save(
            @RequestBody
            final WFRPSkillTypeEntity entity) {

        WFRPSkillTypeEntity savedEntity = repository.save(entity);
        List<Resource<WFRPSkillTypeEntity>> list = getById(savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
