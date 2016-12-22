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

import com.osrapi.models.wfrp.WFRPArmourTypeEntity;
import com.osrapi.repositories.wfrp.WFRPArmourTypeRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/armour_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPArmourTypeController {
	/** the static instance of {@link WFRPArmourTypeController}. */
	private static WFRPArmourTypeController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPArmourTypeController}
	 */
	public static WFRPArmourTypeController getInstance() {
		if (instance == null) {
			new WFRPArmourTypeController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPArmourTypeRepository repository;
	/** Creates a new instance of {@link WFRPArmourTypeController}. */
	public WFRPArmourTypeController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPArmourTypeEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourTypeEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPArmourTypeEntity>> getAll() {
		Iterator<WFRPArmourTypeEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPArmourTypeEntity>> resources = new ArrayList<Resource<WFRPArmourTypeEntity>>();
		while (iter.hasNext()) {
			resources.add(getArmourTypeResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPArmourTypeEntity}.
	 * @param entity the {@link WFRPArmourTypeEntity}
	 * @return {@link Resource}<{@link WFRPArmourTypeEntity}>
	 */
	private Resource<WFRPArmourTypeEntity> getArmourTypeResource(
			final WFRPArmourTypeEntity entity) {
		Resource<WFRPArmourTypeEntity> resource = new Resource<WFRPArmourTypeEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Gets a list of {@link WFRPArmourTypeEntity}s that share a code.
	 * @param code the armour_type' code
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourTypeEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<WFRPArmourTypeEntity>> getByCode(
			@PathVariable final Long code) {
		Iterator<WFRPArmourTypeEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<WFRPArmourTypeEntity>> resources = new ArrayList<Resource<WFRPArmourTypeEntity>>();
		while (iter.hasNext()) {
			resources.add(getArmourTypeResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPArmourTypeEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourTypeEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPArmourTypeEntity>> getById(
			@PathVariable final Long id) {
		WFRPArmourTypeEntity entity = repository.findOne(id);
		List<Resource<WFRPArmourTypeEntity>> resources = new ArrayList<Resource<WFRPArmourTypeEntity>>();
		resources.add(getArmourTypeResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPArmourTypeEntity}s that share a name.
	 * @param name the armour_type' name
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourTypeEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPArmourTypeEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPArmourTypeEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPArmourTypeEntity>> resources = new ArrayList<Resource<WFRPArmourTypeEntity>>();
		while (iter.hasNext()) {
			resources.add(getArmourTypeResource(iter.next()));
		}
		iter = null;
		return resources;
	}

	/**
	 * Saves multiple {@link WFRPArmourTypeEntity}s.
	 * @param entities the list of {@link WFRPArmourTypeEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourTypeEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPArmourTypeEntity>> save(
			@RequestBody final List<WFRPArmourTypeEntity> entities) {
		List<Resource<WFRPArmourTypeEntity>> resources = new ArrayList<Resource<WFRPArmourTypeEntity>>();
		Iterator<WFRPArmourTypeEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPArmourTypeEntity}.
	 * @param entity the {@link WFRPArmourTypeEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourTypeEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPArmourTypeEntity>> save(
			@RequestBody final WFRPArmourTypeEntity entity) {

		WFRPArmourTypeEntity savedEntity = repository.save(entity);
		List<Resource<WFRPArmourTypeEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
