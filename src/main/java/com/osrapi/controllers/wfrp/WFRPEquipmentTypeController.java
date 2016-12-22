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

import com.osrapi.models.wfrp.WFRPEquipmentTypeEntity;
import com.osrapi.repositories.wfrp.WFRPEquipmentTypeRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/equipment_types")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPEquipmentTypeController {
	/** the static instance of {@link WFRPEquipmentTypeController}. */
	private static WFRPEquipmentTypeController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPEquipmentTypeController}
	 */
	public static WFRPEquipmentTypeController getInstance() {
		if (instance == null) {
			new WFRPEquipmentTypeController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPEquipmentTypeRepository repository;
	/** Creates a new instance of {@link WFRPEquipmentTypeController}. */
	public WFRPEquipmentTypeController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPEquipmentTypeEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPEquipmentTypeEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPEquipmentTypeEntity>> getAll() {
		Iterator<WFRPEquipmentTypeEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPEquipmentTypeEntity>> resources = new ArrayList<Resource<WFRPEquipmentTypeEntity>>();
		while (iter.hasNext()) {
			resources.add(getEquipmentTypeResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPEquipmentTypeEntity}s that share a code.
	 * @param code the equipment_type' code
	 * @return {@link List}<{@link Resource}<{@link WFRPEquipmentTypeEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<WFRPEquipmentTypeEntity>> getByCode(
			@PathVariable final Long code) {
		Iterator<WFRPEquipmentTypeEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<WFRPEquipmentTypeEntity>> resources = new ArrayList<Resource<WFRPEquipmentTypeEntity>>();
		while (iter.hasNext()) {
			resources.add(getEquipmentTypeResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPEquipmentTypeEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPEquipmentTypeEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPEquipmentTypeEntity>> getById(
			@PathVariable final Long id) {
		WFRPEquipmentTypeEntity entity = repository.findOne(id);
		List<Resource<WFRPEquipmentTypeEntity>> resources = new ArrayList<Resource<WFRPEquipmentTypeEntity>>();
		resources.add(getEquipmentTypeResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPEquipmentTypeEntity}s that share a name.
	 * @param name the equipment_type' name
	 * @return {@link List}<{@link Resource}<{@link WFRPEquipmentTypeEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPEquipmentTypeEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPEquipmentTypeEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPEquipmentTypeEntity>> resources = new ArrayList<Resource<WFRPEquipmentTypeEntity>>();
		while (iter.hasNext()) {
			resources.add(getEquipmentTypeResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPEquipmentTypeEntity}.
	 * @param entity the {@link WFRPEquipmentTypeEntity}
	 * @return {@link Resource}<{@link WFRPEquipmentTypeEntity}>
	 */
	private Resource<WFRPEquipmentTypeEntity> getEquipmentTypeResource(
			final WFRPEquipmentTypeEntity entity) {
		Resource<WFRPEquipmentTypeEntity> resource = new Resource<WFRPEquipmentTypeEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}

	/**
	 * Saves multiple {@link WFRPEquipmentTypeEntity}s.
	 * @param entities the list of {@link WFRPEquipmentTypeEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPEquipmentTypeEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPEquipmentTypeEntity>> save(
			@RequestBody final List<WFRPEquipmentTypeEntity> entities) {
		List<Resource<WFRPEquipmentTypeEntity>> resources = new ArrayList<Resource<WFRPEquipmentTypeEntity>>();
		Iterator<WFRPEquipmentTypeEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPEquipmentTypeEntity}.
	 * @param entity the {@link WFRPEquipmentTypeEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPEquipmentTypeEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPEquipmentTypeEntity>> save(
			@RequestBody final WFRPEquipmentTypeEntity entity) {

		WFRPEquipmentTypeEntity savedEntity = repository.save(entity);
		List<Resource<WFRPEquipmentTypeEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
