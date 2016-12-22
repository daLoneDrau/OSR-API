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

import com.osrapi.models.wfrp.WFRPSourcebookEntity;
import com.osrapi.repositories.wfrp.WFRPSourcebookRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/sourcebooks")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPSourcebookController {
    /** the static instance of {@link WFRPSourcebookController}. */
    private static WFRPSourcebookController instance;
    /**
     * Gets the static instance.
     * @return {@link WFRPSourcebookController}
     */
    public static WFRPSourcebookController getInstance() {
        if (instance == null) {
            new WFRPSourcebookController();
        }
        return instance;
    }
    /** the data repository. */
    @Autowired
    private WFRPSourcebookRepository repository;
    /** Creates a new instance of {@link WFRPSourcebookController}. */
    public WFRPSourcebookController() {
        instance = this;
    }
    /**
     * Gets a list of {@link WFRPSourcebookEntity}s.
     * @return {@link List}<{@link Resource}<{@link WFRPSourcebookEntity}>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Resource<WFRPSourcebookEntity>> getAll() {
        Iterator<WFRPSourcebookEntity> iter = repository.findAll()
                .iterator();
        List<Resource<WFRPSourcebookEntity>> resources =
                new ArrayList<Resource<WFRPSourcebookEntity>>();
        while (iter.hasNext()) {
            resources.add(getSourcebookResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPSourcebookEntity}s that share a code.
     * @param code the sourcebook' code
     * @return {@link List}<{@link Resource}<{@link WFRPSourcebookEntity}>>
     */
    @RequestMapping(path = "code/{code}", method = RequestMethod.GET)
    public List<Resource<WFRPSourcebookEntity>> getByCode(
            @PathVariable
            final String code) {
        Iterator<WFRPSourcebookEntity> iter = repository.findByCode(code)
                .iterator();
        List<Resource<WFRPSourcebookEntity>> resources =
                new ArrayList<Resource<WFRPSourcebookEntity>>();
        while (iter.hasNext()) {
            resources.add(getSourcebookResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a single {@link WFRPSourcebookEntity}.
     * @param id the event type's id
     * @return {@link List}<{@link Resource}<{@link WFRPSourcebookEntity}>>
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Resource<WFRPSourcebookEntity>> getById(
            @PathVariable
            final Long id) {
        WFRPSourcebookEntity entity = repository.findOne(id);
        List<Resource<WFRPSourcebookEntity>> resources =
                new ArrayList<Resource<WFRPSourcebookEntity>>();
        resources.add(getSourcebookResource(entity));
        entity = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPSourcebookEntity}s that share a name.
     * @param name the sourcebook' name
     * @return {@link List}<{@link Resource}<{@link WFRPSourcebookEntity}>>
     */
    @RequestMapping(path = "name/{name}", method = RequestMethod.GET)
    public List<Resource<WFRPSourcebookEntity>> getByName(
            @PathVariable
            final String name) {
        Iterator<WFRPSourcebookEntity> iter = repository.findByName(name)
                .iterator();
        List<Resource<WFRPSourcebookEntity>> resources =
                new ArrayList<Resource<WFRPSourcebookEntity>>();
        while (iter.hasNext()) {
            resources.add(getSourcebookResource(iter.next()));
        }
        iter = null;
        return resources;
    }
    /**
     * Gets a list of {@link WFRPSourcebookEntity}s that share a owned.
     * @param owned the sourcebook' owned
     * @return {@link List}<{@link Resource}<{@link WFRPSourcebookEntity}>>
     */
    @RequestMapping(path = "owned/{owned}", method = RequestMethod.GET)
    public List<Resource<WFRPSourcebookEntity>> getByOwned(
            @PathVariable
            final Boolean owned) {
        Iterator<WFRPSourcebookEntity> iter = repository.findByOwned(owned)
                .iterator();
        List<Resource<WFRPSourcebookEntity>> resources =
                new ArrayList<Resource<WFRPSourcebookEntity>>();
        while (iter.hasNext()) {
            resources.add(getSourcebookResource(iter.next()));
        }
        iter = null;
        return resources;
    }

    /**
     * Gets a {@link Resource} instance with links for the
     * {@link WFRPSourcebookEntity}.
     * @param entity the {@link WFRPSourcebookEntity}
     * @return {@link Resource}<{@link WFRPSourcebookEntity}>
     */
    private Resource<WFRPSourcebookEntity> getSourcebookResource(
            final WFRPSourcebookEntity entity) {
        Resource<WFRPSourcebookEntity> resource =
                new Resource<WFRPSourcebookEntity>(
                        entity);
        // link to entity
        resource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(getClass()).getById(
                        entity.getId()))
                .withSelfRel());
        return resource;
    }
    /**
     * Saves multiple {@link WFRPSourcebookEntity}s.
     * @param entities the list of {@link WFRPSourcebookEntity} instances
     * @return {@link List}<{@link Resource}<{@link WFRPSourcebookEntity}>>
     */
    @RequestMapping(path = "/bulk", method = RequestMethod.POST)
    public List<Resource<WFRPSourcebookEntity>> save(
            @RequestBody
            final List<WFRPSourcebookEntity> entities) {
        List<Resource<WFRPSourcebookEntity>> resources =
                new ArrayList<Resource<WFRPSourcebookEntity>>();
        Iterator<WFRPSourcebookEntity> iter = entities.iterator();
        while (iter.hasNext()) {
            resources.add(save(iter.next()).get(0));
        }
        iter = null;
        return resources;
    }
    /**
     * Saves a single {@link WFRPSourcebookEntity}.
     * @param entity the {@link WFRPSourcebookEntity} instance
     * @return {@link List}<{@link Resource}<{@link WFRPSourcebookEntity}>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<Resource<WFRPSourcebookEntity>> save(
            @RequestBody
            final WFRPSourcebookEntity entity) {

        WFRPSourcebookEntity savedEntity = repository.save(entity);
        List<Resource<WFRPSourcebookEntity>> list = getById(
                savedEntity.getId());
        savedEntity = null;
        return list;
    }
}
